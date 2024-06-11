package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class ArrayBasedListTest {

    /** List */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("fourth");
        
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
        assertEquals("fourth", list.get(3));
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("fourth");
        
        assertEquals("fourth", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
        list.addFirst("first");
        list.addFirst("second");
        list.addFirst("third");
        list.addFirst("fourth");
        
        assertEquals("fourth", list.get(0));
        assertEquals("third", list.get(1));
        assertEquals("second", list.get(2));
        assertEquals("first", list.get(3));
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("fourth");
        
        assertEquals("first", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        list.addLast("two");
        list.addLast("four");
        list.addLast("six");
        list.addLast("eight");
        list.addLast("ten");
        list.addLast("twelve");
        
        assertEquals(6, list.size());
        assertEquals("two", list.get(0));
        assertEquals("four", list.get(1));
        assertEquals("six", list.get(2));
        assertEquals("eight", list.get(3));
        assertEquals("ten", list.get(4));
        assertEquals("twelve", list.get(5));
        
        assertEquals("two", it.next());
        
        // Remove from front
        it.remove();
        
        assertEquals(5, list.size());
        assertEquals("four", list.get(0));
        assertEquals("six", list.get(1));
        assertEquals("eight", list.get(2));
        assertEquals("ten", list.get(3));
        assertEquals("twelve", list.get(4));
        
        assertEquals("four", it.next());
        assertEquals("six", it.next());
        assertEquals("eight", it.next());
        
        // Remove from middle
        it.remove();
        
        assertEquals(4, list.size());
        assertEquals("four", list.get(0));
        assertEquals("six", list.get(1));
        assertEquals("ten", list.get(2));
        assertEquals("twelve", list.get(3));
        
        assertEquals("ten", it.next());
        assertEquals("twelve", it.next());
        
        // Remove from back
        it.remove();
        
        assertEquals(3, list.size());
        assertEquals("four", list.get(0));
        assertEquals("six", list.get(1));
        assertEquals("ten", list.get(2));
        
//        // Try different operations to make sure they work
//        // as expected for an empty list (at this point)
//        try{
//            it.remove();
//        } catch(Exception e) {
//            assertTrue(e instanceof IllegalStateException);
//        }
//        assertFalse(it.hasNext());
//
//        // Now add an element
//        list.addLast("one");
//        
//        // Use accessor methods to check that the list is correct
//        assertEquals(1, list.size());
//        assertFalse(list.isEmpty());
//        assertEquals("one", list.get(0));
//        
//        // Create an iterator for the list that has 1 element
//        it = list.iterator();
//        
//        // Try different iterator operations to make sure they work
//        // as expected for a list that contains 1 element (at this point)
//        assertTrue(it.hasNext());
//        assertEquals("one", it.next());
//        assertFalse(it.hasNext());
//        try{
//            it.next();
//        } catch(Exception e) {
//            assertTrue(e instanceof NoSuchElementException);
//        }
//        
//        
//        // Try the remove() method that it removes the first element
//        // as expected, and the list now has 1 element, "two"
//        list.addLast("two");
//        it.remove();
//        assertEquals(1, list.size());
//        assertEquals("two", list.get(0));
//        
//        // Try the remove() method before using the next() method again
//        try {
//            it.remove();
//        } catch(Exception e) {
//            assertTrue(e instanceof IllegalStateException);
//        }
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("fourth");
        
        assertEquals("second", list.remove(1));
        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("third", list.get(1));
        assertEquals("fourth", list.get(2));
        assertEquals("third", list.remove(1));
        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("fourth", list.get(1));
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("fourth");
        
        assertEquals("first", list.removeFirst());
        assertEquals(3, list.size());
        assertEquals("second", list.get(0));
        assertEquals("third", list.get(1));
        assertEquals("fourth", list.get(2));
        assertEquals("second", list.removeFirst());
        assertEquals(2, list.size());
        assertEquals("third", list.get(0));
        assertEquals("fourth", list.get(1));
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("fourth");
        
        assertEquals("fourth", list.removeLast());
        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
        assertEquals("third", list.removeLast());
        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("fourth");
        
        assertEquals("first", list.set(0, "firstNEW"));
        assertEquals("firstNEW", list.get(0));
        assertEquals("second", list.set(1, "secondNEW"));
        assertEquals("secondNEW", list.get(1));
        assertEquals("fourth", list.set(3, "fourthNEW"));
        assertEquals("fourthNEW", list.get(3));
    }
}