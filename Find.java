package cs350CodeCompare;


/*
 * Copyright (c) 2009, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;
import java.util.*;
import java.util.regex.Pattern;
 
/**
 * Sample code that finds files that
 * match the specified glob pattern.
 * For more information on what
 * constitutes a glob pattern, see
 * http://docs.oracle.com/javase/javatutorials/tutorial/essential/io/fileOps.html#glob
 *
 * The file or directories that match
 * the pattern are printed to
 * standard out.  The number of
 * matches is also printed.
 *
 * When executing this application,
 * you must put the glob pattern
 * in quotes, so the shell will not
 * expand any wild cards:
 *     java Find . -name "*.java"
 */
 
public class Find {
	//Search in hashset by name
	//If not found, then create student object
	
	//Add a file
	//Add student to hashset
	//
	static HashMap<String, Student> studentFiles = new HashMap<String, Student>();;
	
    /**
     * A {@code FileVisitor} that finds
     * all files that match the
     * specified pattern.
     */
    public static class Finder
        extends SimpleFileVisitor<Path> {
 
        private final PathMatcher matcher;
        private int numMatches = 0;
        public String path;
        
        
        //Constructor
        Finder(String pattern) {
            matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + pattern);
        }
 
        // Compares the glob pattern against
        // the file or directory name.
        void find(Path file) 
        {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) 
            {
                numMatches++;
                System.out.println(file);
                
                //call PopulateStudentFile
                populateStudentFiles(file.toString());
            }
        }
        
        public void populateStudentFiles (String spath)
        {
        	 String versionNum;
        	 String studentVersion;
        	 String studentName;
        	 String fileName;
        	 String packageFolder;
        	 String directoryName;
        	 String studentID;
        	 String fileExtension;
        	 
        	 //ArrayList splitFile = new ArrayList();
        	 
        	 final int NameIndex = 3; 
        	 final int extIndex = 8;
        	
        	//Split -> string array
        	//Take elements in array to create list
        	
        	 
        	String[] pathPiece;
        	//pathPiece = spath.replace('\\','-').split("-");
        	
        	//Split the path into the string array pathPiece
        	//use Pattern.quote() to escape the entire string because "\" is a special character
        	pathPiece = spath.split(Pattern.quote("\\"));
        	
        	//Put the studentVersion in the third index of the pathPiece array
        	studentVersion = pathPiece[NameIndex];
        	
        	//Split the studentVersion into student name and version number
        	//into the studentSplit array
        	String[] studentSplit;
        	
        	studentSplit = studentVersion.split(Pattern.quote("."));
        	
        	//Student name
        	studentName = studentSplit[0];
        	
        	//Version number
        	versionNum = studentSplit[1];
        	
        	fileName = pathPiece[extIndex];
        	
        	String[] extSplit;
        	
        	extSplit = fileName.split(Pattern.quote("."));
        	
        	fileExtension = extSplit[1];
        	
        	
        	boolean found = studentFiles.containsKey(studentName);
        	
    		//Add a file
    		//File(String fileName, String fileExt, String filePath)
    		
    		File f1 = new File(fileName, fileExtension, spath);
        	
        	//If not found
        	if (!found)
        	{
        		//Create student object
        		Student s1 = new Student();
        		s1.setStudentName(studentName);
        		//s1.setStudentID(studentID);	
        		
        		s1.addFile(f1);
        		
        		//Add student to hashset
        		studentFiles.put(studentName, s1);
        		
        		
        	}
        	else
        	{
        		//Found, update
        		studentFiles.get(studentName).addFile(f1);;
        		
        		//Get from Hashset
        		//Add file
        		
        		//Update hashmap
        		//Push back student
        	}
        }
 
        // Prints the total number of
        // matches to standard out.
        void done() {
            System.out.println("Matched: "
                + numMatches);
        }
 
        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file,
                BasicFileAttributes attrs) {
            find(file);
            return CONTINUE;
        }
 
        // Invoke the pattern matching
        // method on each directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                BasicFileAttributes attrs) {
            find(dir);
            return CONTINUE;
        }
 
        //Return error message if there is an error in visiting the file.
        @Override
        public FileVisitResult visitFileFailed(Path file,
                IOException exc) 
        {
            System.err.println(exc);
            return CONTINUE;
        }
    }
 
    //Error reporting
    static void usage() {
        System.err.println("java Find <path>" +
            " -name \"<glob_pattern>\"");
        System.exit(-1);
    }
 
    
    //Example arguments
    //G:\Assignment1\. -name "*.java"
    public static void main(String[] args)
        throws IOException {
 
        if (args.length < 3 || !args[1].equals("-name"))
            usage();
 
        //Get the starting directory's path and place
        //it in the first element of the arguments array
        Path startingDir = Paths.get(args[0]);
        //Glob pattern
        String pattern = args[2];
 
        Finder finder = new Finder(pattern);
        Files.walkFileTree(startingDir, finder);
        finder.done();
        
        //Print out the students in Hashmap
        for (Student s : studentFiles.values()) 
        {
			s.print();
		}
        
    }
}