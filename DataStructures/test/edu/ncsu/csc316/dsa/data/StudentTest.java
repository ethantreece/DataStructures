package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Student class
 * @author Ethan Treece
 *
 */
public class StudentTest {

	
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
    
    /** Student one with a different first name */
    private Student sOneDifferentFirst;
    
    /** Student one with a different ID */
    private Student sOneDifferentID;
    
    /** Student three copy */
    private Student sThreeEquals;

    /**
     * Sets up tests
     */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneDifferentFirst = new Student("OneFirst2", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneDifferentID = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID2");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sThreeEquals = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Tests Student.setFirst()
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests Student.setLast()
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests Student.setId()
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests Student.setGpa()
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests Student.setUnityID()
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests Student.compareTo()
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		assertTrue(sOne.compareTo(sOneDifferentFirst) < 0);
		assertTrue(sOne.compareTo(sOneDifferentID) < 0);
	}
	
	/**
	 * Tests Student.setCreditHours
	 */
	@Test
	public void testSetCreditHours() {
		assertEquals(1, sOne.getCreditHours());
		sOne.setCreditHours(2);
		assertEquals(2, sOne.getCreditHours());
	}
	
	/**
	 * Tests Student.equals()
	 */
	@Test
	public void testEquals() {
		assertTrue(sThree.equals(sThreeEquals));
		assertFalse(sThree.equals(sFour));
	}
	
	/**
	 * Tests Student.toString()
	 */
	@Test
	public void testToString() {
		assertEquals("FiveFirst FiveLast,5,5,5.0,fiveUnityID", sFive.toString());
	}
}
