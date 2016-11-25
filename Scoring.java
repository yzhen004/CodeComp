package main.java.cs350CodeCompare;

import java.util.*;
import java.util.regex.Pattern;
import java.util.Collections; 

import edu.odu.cs.cs350.codeCompCommon.iterators.*;
import edu.odu.cs.cs350.codeCompCommon.*;

/**
 * Storage for phrases found in submissions from different students.
 * 
 * A sentence (a complete file) is passed as a character string. The
 * character codes are presumed to represent integer-valued token kinds.
 * 
 * Typical usage:
 * 
 *   SharedPhrases phrases = new SharedPhrases(); 
 *   phrases.addSentence ("abc", "jones"); // Jones has written abc
 *   phrases.addSentence ("ab", "smith"); // Smith has written ab
 *   phrases.addSentence ("ac", "doe"); // Doe has written ac
 *   for (String p: phrases.allPhrases()) {
 *      System.out.print (p + ":");
 *      for (String source: phrases.sourcesOf(p)) {
 *          System.out.print (" " + source);
 *      } 
 *      System.out.println();
 *   }
 * 
 * The output would be something like:
 *    a: jones smith doe
 *    ab: jones smith
 *    ac: jones doe
 *    abc: jones
 *    b: jones smith
 *    bc: jones
 *    c: jones doe
 * 
 * though the order of the lines and the order of source names within each line
 * may vary.
 * 
 * @see TokenStream#toString()
 * 
 * @author zeil
 *
 */
public class Scoring 
{
	 SharedPhrases phrases = new SharedPhrases();
	 
	 //L used to store length of tokens submitted by the student
	 //Key: Students Names
	 //Value: Token length total
	 HashMap<String, Integer> L = new HashMap<String, Integer>();
	 
	 //T used to store the running total of student scores
	 //Key: StudentKey generated by two Students and a delimiter (StudentName1 % StudentName2) 
	 //Value: The score of the two students
	 HashMap<String, Double> T = new HashMap<String, Double>();
	 
	 final char delimiter = '%';
	 
	 
	 public void score()
	 {
		//Split the studentVersion into student name and version number
     	//into the studentSplit array
     	String[] studentSplit;
    	
     	ArrayList<String> studentList = new ArrayList<String>();
     	
		 for (CharSequence p: phrases.allPhrases() ) 
		 {
	       System.out.print (p + ":");
           
           studentList.clear();	       
	       for (String source: phrases.sourcesOf(p.toString()))
	       {
	           System.out.println (" " + source);
	           
	           //Get list of students from source
	           studentSplit = source.split(" ");
	           
	           //Sort studentlist
		       Collections.addAll(studentList, studentSplit);
		       studentscore(p.toString(), studentList);
 
		   } 
		       //System.out.println();
		 }
		 
		 updateScoreByLength();
	 }
	 
	 public void studentscore(String p, ArrayList<String> studentList)
	 {
		 String studentKey;
		 
		 for (int i = 0; i < studentList.size() - 1; i++)
		 {
			 for(int j = i+1; j < studentList.size(); j++)
			 {
				 studentKey = studentList.get(i) + delimiter + studentList.get(j);
				 
				 accumulateT(studentKey, p, studentList.size());
			 }
		 }
	 }
	 
	 public void accumulateT(String StudentKey, String phrase, int numStudents)
	 {
		 double runningTotal = 0.0;
		 
		 runningTotal = phrase.length() / (numStudents - 1) / (numStudents - 1);
		 
		 double score = 0.0;
		 
		 if (T.containsKey(StudentKey))
		 {
			 score = T.get(StudentKey);
		 }
		 
		 T.put(StudentKey, score + runningTotal);
	 }
	 
	 public void updateScoreByLength()
	 {
		 /* Display content using Iterator*/
	      Set set = T.entrySet();
	      Iterator iterator = set.iterator();
	      String StudentKey;
	      double score = 0.0;
	      
	      String student1;
	      String student2;
	      
	      while(iterator.hasNext()) {
	         Map.Entry mEntry = (Map.Entry)iterator.next();

	         StudentKey = mEntry.getKey().toString();
	         
	         //Student 1's student key for token length
	         //Example: a % b
	         //Start: First index
	         //End: The index of delimiter
	         student1 = StudentKey.substring(0, StudentKey.indexOf(delimiter));
	        //Student 2's student key for token length
	         //Example: a % b
	         //Start: The index of delimiter
	         //End: Length of the whole key minus the delimiter length minus one
	         student2 = StudentKey.substring(StudentKey.indexOf(delimiter)+1, StudentKey.length());
	         
	         score = T.get(StudentKey);
	         
	         //Compute student score
	         score = 4 * score / L.get(student1) / L.get(student2);
	         
	         //Update student score in T
	         T.put(StudentKey, score);
	      }
	 }
	 
	 public void print()
	 {
		 /* Display content using Iterator*/
	      Set set = T.entrySet();
	      Iterator iterator = set.iterator();
	      String StudentKey;
	      double score = 0.0;
	      
	      String student1;
	      String student2;
	      
	      while(iterator.hasNext()) {
	         Map.Entry mEntry = (Map.Entry)iterator.next();

	         StudentKey = mEntry.getKey().toString();
	         
	         //Student 1's student key for token length
	         //Example: a % b
	         //Start: First index
	         //End: The index of delimiter
	         student1 = StudentKey.substring(0, StudentKey.indexOf(delimiter));
	        //Student 2's student key for token length
	         //Example: a % b
	         //Start: The index of delimiter
	         //End: Length of the whole key minus the delimiter length minus one
	         student2 = StudentKey.substring(StudentKey.indexOf(delimiter)+1, StudentKey.length());
	         
	         score = T.get(StudentKey);
	         
	         
	         System.out.print(student1 + "\t\t");
	         System.out.print(student2 + "\t\t");
	         System.out.println(score);
	         
	      }
	 }
	 
	 public void addToken(String phrase, String studentName)
	 {
		 phrases.addSentence(phrase, studentName);
		 
		 int length = 0;
		 
		 if (L.containsKey(studentName))
		 {
			 length = L.get(studentName);
		 }
		 
		 L.put(studentName, phrase.length() + length);
	 }
}
