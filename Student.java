package cs350CodeCompare;

import java.util.Iterator;
import java.util.List;

import cs350CodeCompare.Student;
import cs350CodeCompare.File;

import java.lang.Object;
import java.util.ArrayList;
import java.util.Arrays;


public class Student implements Cloneable, Iterable<File> 
{
	String _studentName;
	String _studentID;
	ArrayList<File> _fileList;
	
	  /**
	   * Create a "blank" book with empty strings for title, publisher, ISBN
	   * and an empty (zero-length) list of authors.
	   */
		//Mutator
	  public Student() 
	  {
		  _studentName = "";
		  _studentID = ""; 
		  _fileList = null;
	  }
	
	/**
	   * Create an student.
	   * @param 
	   * @param 
	   */
	  public Student (String studentname, String studentID, File[] _files) 
	  {
	    this._studentName = studentname;
	    this._studentID = studentID;
	    ArrayList <Student> _students;
	    
	    _fileList = new ArrayList<File>();
	    
	    if (_files == null)
			  return;
		  
		  for (int i = 0; i < _files.length; ++i)
		  {
			  this._fileList.add(_files[i]);
		  }
	  }
	  
//	  /**
//	   * Get the name of the Student in a form suitable for sorting.
//	   *     _surname, _givenName
//	   * @return sortable name of the Student
//	   */
//	  public String getSortingName() {
//	    return _surname + ", " + _givenName;
//	  }
	  
	  
	  /**
	   * Return the Student's name in conventional form.
	   *    _givenName _surname
	   */
	  public String toString() {
	    return _studentName + " : " + _studentID;
	  }
	  
//	  /**
//	   * Return the _surname of this Student
//	   * @return the _surname
//	   */
//	  public String get_surname() {
//	    return _surname;
//	  }
//	  
//	  /**
//	   * Return the given name of this Student 
//	   * @return the given name
//	   */
//	  public String get_givenName() {
//	    return _givenName;
//	  }

	  public String getStudentName()
	  {
		  return _studentName;
	  }
	  
	  public String getStudentID()
	  {
		  return _studentID;
	  }
	  
	  public void setStudentName(String studentName)
	  {
		  _studentName = studentName;
	  }
	  
	  public void setStudentID(String studentID)
	  {
		  _studentID = studentID;
	  }
	  
	  /**
	   * How many Files does this Student have?
	   * @return number of Files
	   */
	  //Accessor
	  public int numFiles() {
	    // TODO
		  if (_fileList == null)
			  return 0;
		  
	    return _fileList.size();
	  }
	  
	  
	  /**
	   * Add an File to the end of the Files's list (if that File is
	   * not already in there). 
	   *  
	   * @param au File to be added
	   */
	  //Mutator
	  public void addFile(File fi) 
	  {
		  if (_fileList == null)
			  _fileList = new ArrayList<File>();
		  
		  this._fileList.add(fi);
	  }
	  

	  // Comparison and hashing

	  /**
	   * Compares two Files for equality. They are considered equal if
	   * they have the same ISBN.
	   *
	   * @param obj object to be compared for equality with this duration
	   * @return <tt>true</tt> if the specified object is equal to this one
	   */
	  //Accessor
	  public boolean equals(Object right) {
		  
	    if (right instanceof Student) 
	     {
	        Student s = (Student) right;
		     return _studentName.equals(s._studentName) && _studentID.equals(s._studentID)
		    		&& s.equalStudent(_fileList);	      
		  } else {
	        return false;
	      }	  
	  
	  }
	  
	  public void print()
	  {
		  
		  System.out.println(this._studentName);
		  System.out.println(this._studentID);
		  //File[] fArr = new File[100];
		  
		  //The safest way to loop through an array 
		  //converted from an arrayList
		  //Specifying array bounds is dangerous
		  //because there are potential null pointers
		  Object[] fArr = _fileList.toArray();
		  
		  //Loop continuation condition:
		  //
		  for(int i =0; i< fArr.length && fArr[i] !=null; i++)
		  {
			  File f = (File)fArr[i];
			  f.print();
		  }
		  
		  //System.out.println(this._studentName);
	  }
	  
	  private boolean equalStudent(ArrayList <File> f)
	  {
		  boolean found = false;
		  
		  if (f.size() != _fileList.size())
			  return false;
		  
		  for (int i = 0; i < f.size(); i++)
		  {
			  found = false;
			  for(int j = 0; j < _fileList.size() && !found; j++)
			  {
				  found = f.get(i).equals(_fileList.get(j));
			  }
			  
			  if (!found) 
				  return false;
		  }
		  
		  return true;
	  }
	  
	  /**
	   * Returns the hash code value for this object.
	   *
	   * @return the hash code value for this File
	   */
	  //Accessor
	  public int hashCode() {
	    // TODO
		 return (_studentName + _studentID).hashCode();
		  //return 0;
	  }
	  
	  /**
	   * Provide access to the list of Files. e.g.,
	   *     File File = new File(...);
	   *     for (Student s: File) {
	   *        doSomethingWithFile (s);
	   *     }
	   * 
	   * @return iterator over the Files.
	   */
	  //Accessor
	  public Iterator<File> iterator() 
	  {
		  if (_fileList == null)
			  return null;
		  
	    // TODO
	    return _fileList.iterator();
	  }
	  
	  /**
	   * Compare this Student to another, returning a negative
	   * value if this Student should follow the other in a sorted list,
	   * zero if they are equal, and a positive value if this Student should
	   * precede the other in a sorted list.
	   * 
	   * @param au the other Student to be compared against.
	   * @return number whose sign indicates the comparison result
	   */
	  public int compareTo(Student s) 
	  {
	    return getStudentName().compareTo(s.getStudentName());
	  }
	  
	  /**
	   * Return a (deep) copy of this object.
	   */
	  //Mutator
	  @Override
	  public Object clone()  
	  {
	    return new Student(_studentName, _studentID, _fileList.toArray(new File[_fileList.size()]));
	  }
}
