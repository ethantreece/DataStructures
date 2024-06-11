package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the BubbleSorter class
 * @author Ethan Treece
 *
 */
public class BubbleSorterTest {

    /** Data in ascending order */
    private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
    
    /** Data in descending order */
    private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
    
    /** Data in random order */
    private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
    
    /** Student one */
    private Student sOne = new Student("A", "A", 1, 1, 1.0, "A");
    
    /** Student two */
    private Student sTwo = new Student("B", "B", 2, 2, 2.0, "B");
    
    /** Student three */
    private Student sThree = new Student("C", "C", 3, 3, 3.0, "C");
    
    /** Student four */
    private Student sFour = new Student("D", "D", 4, 4, 4.0, "D");
    
    /** Student five */
    private Student sFive = new Student("E", "E", 5, 5, 5.0, "E");
    
    /** Students in ascending order */
    private Student[] studentsAscending = { sOne, sTwo, sThree, sFour, sFive };
    
    /** Students in descending order */
    private Student[] studentsDescending = { sFive, sFour, sThree, sTwo, sOne };
    
    /** Students in random order */
    private Student[] studentsRandom = { sFour, sOne, sFive, sThree, sTwo };

    /** integer sort */
    private BubbleSorter<Integer> integerSorter;
    
    /** student order */
    private BubbleSorter<Student> studentSorter;

    /**
     * Sets up tests
     */
    @Before
    public void setUp() {
        integerSorter = new BubbleSorter<Integer>();
        studentSorter = new BubbleSorter<Student>();
    }

    /**
     * Tests the sorting of integers with arrays of ascending,
     * descending, and random integers.
     */
    @Test
    public void testSortIntegers() {
        integerSorter.sort(dataAscending);
        assertEquals(dataAscending[0], dataAscending[0]);
        assertEquals(dataAscending[1], dataAscending[1]);
        assertEquals(dataAscending[2], dataAscending[2]);
        assertEquals(dataAscending[3], dataAscending[3]);
        assertEquals(dataAscending[4], dataAscending[4]);

        integerSorter.sort(dataDescending);
        assertEquals(dataAscending[0], dataDescending[0]);
        assertEquals(dataAscending[1], dataDescending[1]);
        assertEquals(dataAscending[2], dataDescending[2]);
        assertEquals(dataAscending[3], dataDescending[3]);
        assertEquals(dataAscending[4], dataDescending[4]);

        integerSorter.sort(dataRandom);
        assertEquals(dataAscending[0], dataRandom[0]);
        assertEquals(dataAscending[1], dataRandom[1]);
        assertEquals(dataAscending[2], dataRandom[2]);
        assertEquals(dataAscending[3], dataRandom[3]);
        assertEquals(dataAscending[4], dataRandom[4]);
    }

    /**
     * Tests the sorting of Students with arrays of ascending,
     * descending, and random Students.
     */
    @Test
    public void testSortStudent() {
        studentSorter.sort(studentsAscending);
        assertEquals(studentsAscending[0], studentsAscending[0]);
        assertEquals(studentsAscending[1], studentsAscending[1]);
        assertEquals(studentsAscending[2], studentsAscending[2]);
        assertEquals(studentsAscending[3], studentsAscending[3]);
        assertEquals(studentsAscending[4], studentsAscending[4]);

        studentSorter.sort(studentsDescending);
        assertEquals(studentsAscending[0], studentsDescending[0]);
        assertEquals(studentsAscending[1], studentsDescending[1]);
        assertEquals(studentsAscending[2], studentsDescending[2]);
        assertEquals(studentsAscending[3], studentsDescending[3]);
        assertEquals(studentsAscending[4], studentsDescending[4]);

        studentSorter.sort(studentsRandom);
        assertEquals(studentsAscending[0], studentsRandom[0]);
        assertEquals(studentsAscending[1], studentsRandom[1]);
        assertEquals(studentsAscending[2], studentsRandom[2]);
        assertEquals(studentsAscending[3], studentsRandom[3]);
        assertEquals(studentsAscending[4], studentsRandom[4]);
        
    }

}
