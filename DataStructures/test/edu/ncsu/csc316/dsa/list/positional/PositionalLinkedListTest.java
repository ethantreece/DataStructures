package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class PositionalLinkedListTest {

    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("second");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        @SuppressWarnings("unused")
        Position<String> second = list.addFirst("second");
        assertEquals(2, list.size());
        assertEquals(first, list.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("second");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        
        Position<String> third = list.addFirst("third");
        assertEquals(3, list.size());
        assertEquals(third, list.first());
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        assertEquals(first, list.last());
        
        Position<String> second = list.addLast("second");
        assertEquals(2, list.size());
        assertEquals(second, list.last());
        
        Position<String> third = list.addLast("third");
        assertEquals(3, list.size());
        assertEquals(third, list.last());
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
        Position<String> one = list.addLast("one");
        Position<String> two = list.addLast("two");
        Position<String> three = list.addLast("three");
        Position<String> four = list.addLast("four");
        
        assertNull(list.before(one));
        assertEquals(one, list.before(two));
        assertEquals(two, list.before(three));
        assertEquals(three, list.before(four));
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
        Position<String> one = list.addLast("one");
        Position<String> two = list.addLast("two");
        Position<String> three = list.addLast("three");
        Position<String> four = list.addLast("four");
        
        assertEquals(two, list.after(one));
        assertEquals(three, list.after(two));
        assertEquals(four, list.after(three));
        assertNull(list.after(four));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
        Position<String> two = list.addLast("two");
        Position<String> three = list.addLast("three");
        Position<String> five = list.addLast("five");
        assertEquals(3, list.size());
        
        list.addBefore(two, "one");
        assertEquals(4, list.size());
        assertEquals("one", list.first().getElement());
        assertEquals("one", list.before(two).getElement());
        
        list.addBefore(five, "four");
        assertEquals(5, list.size());
        assertEquals("four", list.after(three).getElement());
        assertEquals("four", list.before(five).getElement());
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
        Position<String> one = list.addLast("one");
        Position<String> three = list.addLast("three");
        Position<String> four = list.addLast("four");
        assertEquals(3, list.size());
        
        list.addAfter(one, "two");
        assertEquals(4, list.size());
        assertEquals("two", list.after(one).getElement());
        assertEquals("two", list.before(three).getElement());
        
        list.addAfter(four, "five");
        assertEquals(5, list.size());
        assertEquals("five", list.after(four).getElement());
        assertEquals("five", list.last().getElement());
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
        Position<String> one = list.addLast("one");
        Position<String> two = list.addLast("two");
        Position<String> three = list.addLast("three");
        
        assertEquals("one", list.set(one, "zero"));
        assertEquals(3, list.size());
        assertEquals("zero", list.first().getElement());
        
        assertEquals("two", list.set(two, "TWO"));
        assertEquals(3, list.size());
        assertEquals("TWO", list.before(three).getElement());
        
        assertEquals("three", list.set(three, "five"));
        assertEquals(3, list.size());
        assertEquals("five", list.last().getElement());
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
        Position<String> one = list.addLast("one");
        Position<String> two = list.addLast("two");
        Position<String> three = list.addLast("three");
        Position<String> four = list.addLast("four");
        
        assertEquals(4, list.size());
        assertEquals("one", list.remove(one));
        assertEquals(3, list.size());
        assertEquals("two", list.first().getElement());
        
        assertEquals("four", list.remove(four));
        assertEquals(2, list.size());
        assertEquals("two", list.first().getElement());
        assertEquals("three", list.last().getElement());
        
        assertEquals("two", list.remove(two));
        assertEquals(1, list.size());
        assertEquals("three", list.first().getElement());
        assertEquals("three", list.last().getElement());
        
        assertEquals("three", list.remove(three));
        assertEquals(0, list.size());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
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
        assertEquals("two", list.first().getElement());
        assertEquals("four", list.after(list.first()).getElement());
        assertEquals("six", list.after(list.after(list.first())).getElement());
        assertEquals("eight", list.before(list.before(list.last())).getElement());
        assertEquals("ten", list.before(list.last()).getElement());
        assertEquals("twelve", list.last().getElement());
        
        assertEquals("two", it.next());
        
        // Remove from front
        it.remove();
        
        assertEquals(5, list.size());
        assertEquals("four", list.first().getElement());
        assertEquals("six", list.after(list.first()).getElement());
        assertEquals("eight", list.after(list.after(list.first())).getElement());
        assertEquals("ten", list.before(list.last()).getElement());
        assertEquals("twelve", list.last().getElement());
        
        assertEquals("four", it.next());
        assertEquals("six", it.next());
        assertEquals("eight", it.next());
        
        // Remove from middle
        it.remove();
        
        assertEquals(4, list.size());
        assertEquals("four", list.first().getElement());
        assertEquals("six", list.after(list.first()).getElement());
        assertEquals("ten", list.before(list.last()).getElement());
        assertEquals("twelve", list.last().getElement());
        
        assertEquals("ten", it.next());
        assertEquals("twelve", it.next());
        
        // Remove from back
        it.remove();
        
        assertEquals(3, list.size());
        assertEquals("four", list.first().getElement());
        assertEquals("ten", list.last().getElement());
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
//        // Use accessor methods to check that the list is correct
//        assertEquals(1, list.size());
//        assertFalse(list.isEmpty());
//        assertEquals("one", list.first().getElement());
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
//        assertEquals("two", list.first().getElement());
//        
//        // Try the remove() method before using the next() method again
//        try {
//            it.remove();
//        } catch(Exception e) {
//            assertTrue(e instanceof IllegalStateException);
//        }
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
        assertFalse(it.hasNext());
        it.remove();
        assertEquals(2, list.size());
    }

}