package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;


/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class UnorderedLinkedMapTest {

    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertEquals(5, map.size());
        
        assertEquals(map.get(1), "string1");
        assertEquals(map.get(2), "string2");
        assertEquals(map.get(3), "string3");
        assertEquals(map.get(4), "string4");
        assertEquals(map.get(5), "string5");
        
        assertEquals(map.put(1, "string1NEW"), "string1");
        assertEquals(map.get(1), "string1NEW");
        
        assertEquals("string1NEW", map.put(1, null));
        assertNull(map.put(null, null));
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals(map.get(1), "string1");
        assertEquals(map.get(2), "string2");
        assertEquals(map.get(3), "string3");
        assertEquals(map.get(4), "string4");
        assertEquals(map.get(5), "string5");
        assertNull(map.get(6));
        
        assertEquals("string1", map.put(1, null));
        assertNull(map.get(null));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals(map.remove(1), "string1");
        assertEquals(4, map.size());
        
        assertNull(map.remove(1));
        assertNull(map.remove(null));
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Integer> i = map.iterator();
        assertTrue(i.hasNext());
        assertEquals(i.next(), (Integer) 1);
        assertTrue(i.hasNext());
        assertEquals(i.next(), (Integer) 4);
        assertTrue(i.hasNext());
        assertEquals(i.next(), (Integer) 2);
        assertTrue(i.hasNext());
        assertEquals(i.next(), (Integer) 5);
        assertTrue(i.hasNext());
        assertEquals(i.next(), (Integer) 3);
        assertFalse(i.hasNext());
        
        try {
            i.remove();
            fail("UnsupportedOperationException should have been thrown.");      
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        } 
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Entry<Integer, String>> i = map.entrySet().iterator();
        assertTrue(i.hasNext());
        assertEquals(i.next().getKey(), (Integer) 1);
        assertTrue(i.hasNext());
        assertEquals(i.next().getKey(), (Integer) 4);
        assertTrue(i.hasNext());
        assertEquals(i.next().getKey(), (Integer) 2);
        assertTrue(i.hasNext());
        assertEquals(i.next().getKey(), (Integer) 5);
        assertTrue(i.hasNext());
        assertEquals(i.next().getKey(), (Integer) 3);
        assertFalse(i.hasNext());
        
        try {
            i.remove();
            fail("UnsupportedOperationException should have been thrown.");      
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<String> i = map.values().iterator();
        assertTrue(i.hasNext());
        assertEquals(i.next(), "string1");
        assertTrue(i.hasNext());
        assertEquals(i.next(), "string4");
        assertTrue(i.hasNext());
        assertEquals(i.next(), "string2");
        assertTrue(i.hasNext());
        assertEquals(i.next(), "string5");
        assertTrue(i.hasNext());
        assertEquals(i.next(), "string3");
        assertFalse(i.hasNext());
        
        try {
            i.remove();
            fail("UnsupportedOperationException should have been thrown.");      
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}
