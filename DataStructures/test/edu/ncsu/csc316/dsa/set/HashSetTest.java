package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for HashSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a hash table data structure 
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class HashSetTest {

    /** set */
    private Set<Integer> set;
    
    /** test set */
    private Set<Integer> testSet;
    
    /**
     * Create new instances of a hash-based set before each test case executes
     */ 
    @Before
    public void setUp() {
        // Will use a "production" hash map with random alpha, beta values
        set = new HashSet<Integer>();
        
        // Will use our hash map for testing, which uses alpha=1, beta=1, prime=7
        testSet = new HashSet<Integer>(true);
    }

    /**
     * Test the output of the add behavior
     */      
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(1);
        assertEquals(1, set.size());

        set.add(2);
        assertEquals(2, set.size());

        set.add(3);
        assertEquals(3, set.size());

        set.add(4);
        assertEquals(4, set.size());

        set.add(5);
        assertEquals(5, set.size());
        
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));
        
        assertFalse(set.contains(-1));
        assertFalse(set.contains(0));
        assertFalse(set.contains(10));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        
        assertEquals(5, set.size());
        assertFalse(set.isEmpty());
        
        assertEquals(5, (int) set.remove(5));
        assertEquals(4, (int) set.remove(4));
        assertEquals(3, (int) set.remove(3));
        assertEquals(2, (int) set.remove(2));
        assertEquals(1, (int) set.remove(1));
        
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
    }

    /**
     * Test the output of the retainAll behavior
     */     
    @Test
    public void testRetainAll() {       

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        
        testSet.add(2);
        testSet.add(4);
        testSet.add(6);
        testSet.add(8);
        
        set.retainAll(testSet);
        
        assertEquals(2, set.size());
        assertTrue(set.contains(2));
        assertTrue(set.contains(4));
    }


    /**
     * Test the output of the removeAll behavior
     */    
    @Test
    public void testRemoveAll() {

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        
        testSet.add(2);
        testSet.add(4);
        testSet.add(6);
        testSet.add(8);
        
        set.removeAll(testSet);
        
        assertEquals(3, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertTrue(set.contains(5));
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        
        testSet.add(2);
        testSet.add(4);
        testSet.add(6);
        testSet.add(8);
        
        set.addAll(testSet);
        
        assertEquals(7, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));
        assertTrue(set.contains(6));
        assertTrue(set.contains(8));
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        // Since our hash map uses random numbers, it can
        // be difficult to test that our hash set iterator returns
        // values in a certain order.
        // We could approach this problem with a few different options:
        // (1) instead of checking the specific order of entries
        //      visited by the iterator, we could save each
        //      iterator.next() into an array, then check that the 
        //      array *contains* the entry, regardless of its exact location
        //
        // (2) implement an isTesting flag for HashSet, similar to testSet in the setUp method. 
        //     This is the more appropriate option since we can control the
        //     entire execution and know exactly which values should appear
        //     in the set and in the correct expected order from using the iterator 
        //
        // Revisit your test cases for HashMap iterator -- those tests can be adapted
        //     to work here, too, since you have already worked out the details
        //     of where entries with certain keys will be stored and in what order
        //     they will be stored
        assertTrue(testSet.isEmpty());
        testSet.add(3);
        testSet.add(5);
        testSet.add(2);
        testSet.add(4);
        testSet.add(1);
        testSet.add(6);
        assertEquals(6, testSet.size());
        assertFalse(testSet.isEmpty());
        
        Iterator<Integer> it = testSet.iterator();
        assertTrue(it.hasNext());
        assertEquals(6, (int) it.next()); // should be index 0
        assertEquals(1, (int) it.next()); // should be index 2
        assertEquals(2, (int) it.next()); // should be index 3
        assertEquals(3, (int) it.next()); // should be index 4
        assertEquals(4, (int) it.next()); // should be index 5
        assertEquals(5, (int) it.next()); // should be index 6   
        assertFalse(it.hasNext());
        
    }
}