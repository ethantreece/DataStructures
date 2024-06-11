package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the MergeSorter class
 * @author Ethan Treece
 *
 */
public class QuickSorterTest {

    /** Data in ascending order */
    private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
    
    /** Data in ascending order */
    private Integer[] dataAscendingCopy = { 1, 2, 3, 4, 5 };
    
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
    
    /** Students in ascending order */
    private Student[] studentsAscendingCopy = { sOne, sTwo, sThree, sFour, sFive };
    
    /** Students in descending order */
    private Student[] studentsDescending = { sFive, sFour, sThree, sTwo, sOne };
    
    /** Students in random order */
    private Student[] studentsRandom = { sFour, sOne, sFive, sThree, sTwo };

    /** integer sort */
    private QuickSorter<Integer> integerSorter;
    
    /** integer sort with first pivot */
    private QuickSorter<Integer> integerSorterFirstPivot;
    
    /** integer sort with middle pivot */
    private QuickSorter<Integer> integerSorterMiddlePivot;
    
    /** integer sort with last pivot */
    private QuickSorter<Integer> integerSorterLastPivot;
    
    /** student sort */
    private QuickSorter<Student> studentSorter;

    /**
     * Sets up tests
     */
    @Before
    public void setUp() {
        integerSorter = new QuickSorter<Integer>();
        integerSorterFirstPivot = new QuickSorter<Integer>(QuickSorter.FIRST_ELEMENT_SELECTOR);
        integerSorterMiddlePivot = new QuickSorter<Integer>(QuickSorter.LAST_ELEMENT_SELECTOR);
        integerSorterLastPivot = new QuickSorter<Integer>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
        Comparator<Student> c = null;
        studentSorter = new QuickSorter<Student>(c);
    }

    /**
     * Tests the sorting of integers with arrays of ascending,
     * descending, and random integers.
     */
    @Test
    public void testSortIntegers() {
        integerSorter.sort(dataAscendingCopy);
        assertEquals(dataAscending[0], dataAscendingCopy[0]);
        assertEquals(dataAscending[1], dataAscendingCopy[1]);
        assertEquals(dataAscending[2], dataAscendingCopy[2]);
        assertEquals(dataAscending[3], dataAscendingCopy[3]);
        assertEquals(dataAscending[4], dataAscendingCopy[4]);

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
        
        integerSorterFirstPivot.sort(dataRandom);
        assertEquals(dataAscending[0], dataRandom[0]);
        assertEquals(dataAscending[1], dataRandom[1]);
        assertEquals(dataAscending[2], dataRandom[2]);
        assertEquals(dataAscending[3], dataRandom[3]);
        assertEquals(dataAscending[4], dataRandom[4]);
        
        integerSorterMiddlePivot.sort(dataRandom);
        assertEquals(dataAscending[0], dataRandom[0]);
        assertEquals(dataAscending[1], dataRandom[1]);
        assertEquals(dataAscending[2], dataRandom[2]);
        assertEquals(dataAscending[3], dataRandom[3]);
        assertEquals(dataAscending[4], dataRandom[4]);
        
        integerSorterLastPivot.sort(dataRandom);
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
        assertEquals(studentsAscending[0], studentsAscendingCopy[0]);
        assertEquals(studentsAscending[1], studentsAscendingCopy[1]);
        assertEquals(studentsAscending[2], studentsAscendingCopy[2]);
        assertEquals(studentsAscending[3], studentsAscendingCopy[3]);
        assertEquals(studentsAscending[4], studentsAscendingCopy[4]);

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
