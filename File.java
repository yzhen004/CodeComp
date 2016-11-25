package cs350CodeCompare;

import java.util.ArrayList;
import java.util.Iterator;

import cs350CodeCompare.Student;
import cs350CodeCompare.File;

public class File implements Cloneable, Comparable<File> 
{
	 String _fileName;
	 String _fileExt;
	 String _filePath;
	 
	  /**
	   * Create a "blank" File with empty strings for fileName, fileID, filePath
	   * and an empty (zero-length) list of Students.
	   */
		//Mutator
	  public File() 
	  {
		  _fileName = "";
		  _fileExt = "";
		  _filePath = "";
	  }

	  /**
	   * Create a new File.
	   * @param fileName fileName of the File
	   * @param fileID fileID of the File.
	   * @param filePath filePath identifier for the File. 
	   * @param Students list of Students for this File.
	   */
	  //Mutator
	  public File(String fileName, String fileExt, String filePath) {
		  this._fileName = fileName;
		  this._fileExt = fileExt;
		  this._filePath = filePath;
	  }

	  public void print()
	  {
		  System.out.println(this._fileName);
		  System.out.println(_fileExt);
		  System.out.println(_filePath);
	  }
	  
	  /**
	   * Get the fileName of this File.
	   * @return the fileName
	   */
	  //Accessor
	  public String getfileName() {
	    // TODO
	    return _fileName;
	  }

	  /**
	   * Set the fileName of this File.
	   * @param fileName the fileName to set
	   */
	  //Mutator
	  public void setfileName(String fileName) {
	    // TODO
		  _fileName = fileName;
	  }

	  /**
	   * Get the filePath of this File.
	   * @return the filePath
	   */
	  //Accessor
	  public String getfilePath() {
	    // TODO
	    return _filePath;
	  }

	  /**
	   * Set the filePath of this File.
	   * @param filePath the filePath to set
	   */
	  //Mutator
	  public void setfilePath(String filePath) 
	  {
	    // TODO
		  _filePath = filePath;
	  }

	  /**
	   * Get the fileExt of this File.
	   * @return the fileExt
	   */
	  //Accessor
	  public String getfileExt() {
	    // TODO
	    return _fileExt;
	  }

	  /**
	   * Set the fileExt of this File.
	   * @param fileExt the fileExt to set
	   */
	  //Mutator
	  public void setfileExt(String fileExt) {
	    // TODO
		  _fileExt = fileExt;
	  }

	  /**
	   * Compare this File to another, returning a negative
	   * value if this File should follow the other in a sorted list,
	   * zero if they are equal, and a positive value if this File should
	   * precede the other in a sorted list.
	   * 
	   * @param au the other File to be compared against.
	   * @return number whose sign indicates the comparison result
	   */
	  public int compareTo(File au) {
	    return getfileName().compareTo(au.getfileName());
	  }

	  /**
	   * Return a (deep) copy of this object.
	   */
	  //Mutator
	  @Override
	  public Object clone()  
	  {
	    return new File(_fileName, _fileExt, _filePath);
	  }
}
