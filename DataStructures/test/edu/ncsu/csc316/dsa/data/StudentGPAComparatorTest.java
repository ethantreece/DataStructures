package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the StudentGPAComparator class
 * @author Ethan Treece
 *
 */
public class StudentGPAComparatorTest {

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

    /** Comparator */
	private StudentGPAComparator comparator;

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

		comparator = new StudentGPAComparator();
	}

	/**
	 * Tests the compare() method
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sThree, sThree) == 0);
		assertTrue(comparator.compare(sFive, sFour) < 0);
	}

}
