package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the CountingSorter class
 * @author Ethan Treece
 *
 */
public class CountingSorterTest {
	
    /** Student one */
    private Student sOne;
    
    /** Student two */
    private Student sTwo;
    
    /** Student three */
    private Student sThree;
    
    /** Student four */
    private Student sFour;
    
    /** Student five */
    private Student sFive;
	
    /** Sorter */
	private CountingSorter<Student> sorter;

	/**
     * Sets up tests
     */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}
	
	/**
	 * Tests sorting when the students are in random order
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * Tests sorting when the students are already sorted
	 */
	@Test
	public void testSortStudentAlreadySorted() {
	    Student[] original = { sOne, sTwo, sThree, sFour, sFive };
        sorter.sort(original);
        assertEquals(sOne, original[0]);
        assertEquals(sTwo, original[1]);
        assertEquals(sThree, original[2]);
        assertEquals(sFour, original[3]);
        assertEquals(sFive, original[4]);
	}
	
	/**
	 * Tests sorting when the students are in reverse order
	 */
	@Test
	public void testSortStudentReverse() {
	    Student[] original = { sFive, sFour, sThree, sTwo, sOne };
        sorter.sort(original);
        assertEquals(sOne, original[0]);
        assertEquals(sTwo, original[1]);
        assertEquals(sThree, original[2]);
        assertEquals(sFour, original[3]);
        assertEquals(sFive, original[4]);
	}

}
