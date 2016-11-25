package cs350CodeCompare;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TestStudent {


	  String defaultName = "Yingbo Zheng";
	  String defaultID = "1234567890123";
	  File[] emptyFiles = {};
	  File jones = new File("AboutDialog", ".java", "G:/Assignment1/.");
	  File smith = new File("tip_calc_main.", "java", "G:/Assignment1/.");
	  File doe = new File("tip_calc_main.", "java", "G:/Assignment1/.");
	  File[] twoFiles = {jones, smith};
	  Student blank = new Student();
	  
	  /**
	   * @throws java.lang.Exception
	   */
	  @Before
	  public void setUp() throws Exception {
	  }

	  /**
	   * Test method for {@link edu.odu.cs.cs350.Student#Student()}.
	   */
	  @Test
	  public final void testStudent() {
	    Student Student = new Student();
	    assertEquals("", Student.getStudentName());
	    assertEquals("", Student.getStudentID());
	    //assertEquals("", Student.getPublisher());
	    assertEquals(0, Student.numFiles());
	    Iterator<File> it = Student.iterator();
	    //assertFalse(it.hasNext());
	    assertEquals(Student, blank);
	    assertEquals(Student.hashCode(), blank.hashCode());
	    assertEquals(Student.toString(), blank.toString());
	  }

	  /**
	   * Test method for 
	   * {@link edu.odu.cs.cs350.Student#Student(java.lang.String, java.lang.String, java.lang.String, edu.odu.cs.cs350.File[])}.
	   */
	  @Test
	  public final void testStudentStringStringStringFileArray() {
	    Student Student = new Student(defaultName, defaultID, twoFiles);
	    assertEquals(defaultName, Student.getStudentName());
	    //assertEquals(defaultID, Student.getStudentID());
	    assertEquals(defaultID, Student.getStudentID());
	    assertEquals(2, Student.numFiles());
	    int count = 0;
	    for (File au: Student) {
	      assertEquals (twoFiles[count], au);
	      ++count;
	    }
	    assertEquals (2, count);
	    assertNotEquals(Student, blank);
	    assertNotEquals(Student.hashCode(), blank.hashCode());
	    String StudentRepr = Student.toString();
	    assertTrue (StudentRepr.contains(defaultName));
	    assertTrue (StudentRepr.contains(defaultID));
	    //assertTrue (StudentRepr.contains(defaultID));
	    assertTrue (StudentRepr.contains("Jones"));
	    assertTrue (StudentRepr.contains("Smith"));
	  }

	  /**
	   * Test method for {@link edu.odu.cs.cs350.Student#setName(java.lang.String)}.
	   */
	  @Test
	  public final void testSetName() {
	    Student Student = new Student(defaultName, /*/*defaultPublisher*/ 
	        defaultID, twoFiles);
	    Student.setStudentName("something else");
	    assertEquals("something else", Student.getStudentName());
	    assertEquals(defaultID, Student.getStudentID());
	    //assertEquals(/*defaultPublisher*/, Student.getPublisher());
	    assertEquals(2, Student.numFiles());
	    int count = 0;
	    for (File au: Student) {
	      assertEquals (twoFiles[count], au);
	      ++count;
	    }
	    assertEquals (2, count);
	    assertNotEquals(Student, blank);
	    assertNotEquals(Student.hashCode(), blank.hashCode());
	    String StudentRepr = Student.toString();
	    //assertTrue (StudentRepr.contains(/*defaultPublisher*/));
	    assertTrue (StudentRepr.contains(defaultID));
	    assertTrue (StudentRepr.contains("Jones"));
	    assertTrue (StudentRepr.contains("Smith"));
	  }

	  /**
	   * Test method for {@link edu.odu.cs.cs350.Student#setID(java.lang.String)}.
	   */
	  @Test
	  public final void testSetID() {
	    Student Student = new Student(defaultName, /*defaultPublisher,*/ 
	        defaultID, twoFiles);
	    Student.setStudentID("something else");
	    assertEquals("something else", Student.getStudentID());
	    assertEquals(defaultName, Student.getStudentName());
	    //assertEquals(defaultPublisher, Student.getPublisher());
	    assertEquals(2, Student.numFiles());
	    int count = 0;
	    for (File au: Student) {
	      assertEquals (twoFiles[count], au);
	      ++count;
	    }
	    assertEquals (2, count);
	    assertNotEquals(Student, blank);
	    assertNotEquals(Student.hashCode(), blank.hashCode());
	    String StudentRepr = Student.toString();
	    assertTrue (StudentRepr.contains(defaultName));
	    //assertTrue (StudentRepr.contains(/*defaultPublisher*/));
	    assertTrue (StudentRepr.contains("Jones"));
	    assertTrue (StudentRepr.contains("Smith"));
	  }

	  /**
	   * Test method for {@link edu.odu.cs.cs350.Student#setPublisher(java.lang.String)}.
	   */
	  @Test
	  public final void testSetPublisher() {
	    Student Student = new Student(defaultName, 
	        defaultID, twoFiles);
	    //Student.setPublisher("something else");
	    //assertEquals("something else", Student.getPublisher());
	    assertEquals(defaultName, Student.getStudentName());
	    assertEquals(defaultID, Student.getStudentID());
	    assertEquals(2, Student.numFiles());
	    int count = 0;
	    for (File au: Student) {
	      assertEquals (twoFiles[count], au);
	      ++count;
	    }
	    assertEquals (2, count);
	    assertNotEquals(Student, blank);
	    assertNotEquals(Student.hashCode(), blank.hashCode());
	    String StudentRepr = Student.toString();
	    assertTrue (StudentRepr.contains(defaultName));
	    assertTrue (StudentRepr.contains(defaultID));
	    assertTrue (StudentRepr.contains("Jones"));
	    assertTrue (StudentRepr.contains("Smith"));
	  }

	  /**
	   * Test method for {@link edu.odu.cs.cs350.Student#addFile(edu.odu.cs.cs350.File)}.
	   */
	  @Test
	  public final void testAddFile() {
	    Student Student0 = new Student(defaultName, defaultID, twoFiles);
	    Student Student = new Student(defaultName, defaultID, twoFiles);
	    
	    Student.addFile(smith); // already in the File list
	    assertEquals(2, Student.numFiles());
	    int count = 0;
	    for (File au: Student) {
	      assertEquals (twoFiles[count], au);
	      ++count;
	    }
	    assertEquals (2, count);
	    assertEquals (Student0, Student);
	    
	    Student.addFile(doe); // already in the File list
	    File[] threeFiles = {jones, smith, doe};
	    assertEquals(3, Student.numFiles());
	    count = 0;
	    for (File au: Student) {
	      assertEquals (threeFiles[count], au);
	      ++count;
	    }
	    assertEquals (3, count);
	    assertEquals (Student0, Student);
	    
	    assertEquals(defaultName, Student.getStudentName());
	    assertEquals(defaultID, Student.getStudentID());
	    //assertEquals(/*defaultPublisher*/, Student.getPublisher());

	    assertEquals(Student0.hashCode(), Student.hashCode());
	    String StudentRepr = Student.toString();
	    assertTrue (StudentRepr.contains(defaultName));
	    //assertTrue (StudentRepr.contains(/*defaultPublisher*/));
	    assertTrue (StudentRepr.contains(defaultID));
	    assertTrue (StudentRepr.contains("Jones"));
	    assertTrue (StudentRepr.contains("Smith"));
	    assertTrue (StudentRepr.contains("Doe"));
	  }

	  /**
	   * Test method for {@link edu.odu.cs.cs350.Student#clone()}.
	   */
	  @Test
	  public final void testClone() {
	    Student Student0 = new Student(defaultName, /*defaultPublisher,*/ defaultID, twoFiles);
	    Student Student = (Student)Student0.clone();
	    assertEquals(defaultName, Student.getStudentName());
	    assertEquals(defaultID, Student.getStudentID());
	    //assertEquals(/*defaultPublisher*/, Student.getPublisher());
	    assertEquals(2, Student.numFiles());
	    int count = 0;
	    for (File f: Student) {
	      assertEquals (twoFiles[count], f);
	      ++count;
	    }
	    assertEquals (2, count);
	    assertEquals(Student0, Student);
	    assertEquals(Student.hashCode(), Student0.hashCode());
	    String StudentRepr = Student.toString();
	    assertEquals (Student0.toString(), StudentRepr);
	  }


}
