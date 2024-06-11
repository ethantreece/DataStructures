package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class LinearProbingHashMapTest {

    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        
        assertNull(map.put(0, "string0"));
        assertEquals(1, map.size());
        assertNull(map.put(1, "string1"));
        assertEquals(2, map.size());
        assertNull(map.put(2, "string2"));
        assertEquals(3, map.size());
        assertNull(map.put(6, "string6"));
        
        assertEquals(4, map.size());
        assertNull(map.put(7, "string7"));
        assertEquals(5, map.size());
        assertNull(map.put(14, "string14"));
        assertEquals(6, map.size());
        
        assertEquals(6, map.size());
        
        assertEquals("string0", map.get(0));
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string6", map.get(6));
        assertEquals("string7", map.get(7));
        assertEquals("string14", map.get(14));
        
        assertEquals("string1", map.put(1, "string1NEW"));
        
        assertEquals("string0", map.get(0));
        assertEquals("string1NEW", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string6", map.get(6));
        assertEquals("string7", map.get(7));
        assertEquals("string14", map.get(14));
        
        assertNull(map.get(10));
        
        map = new LinearProbingHashMap<Integer, String>();
        map = new LinearProbingHashMap<Integer, String>(true);
        map = new LinearProbingHashMap<Integer, String>(7);
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        
        assertNull(map.put(0, "string0"));
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(14, "string14"));
        
        assertEquals(6, map.size());
        
        assertEquals("string0", map.get(0));
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string6", map.get(6));
        assertEquals("string7", map.get(7));
        assertEquals("string14", map.get(14));
        
        assertEquals("string1", map.put(1, "string1NEW"));
        
        assertEquals("string0", map.get(0));
        assertEquals("string1NEW", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string6", map.get(6));
        assertEquals("string7", map.get(7));
        assertEquals("string14", map.get(14));
        
        assertNull(map.get(10));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        
        assertNull(map.put(0, "string0"));
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(14, "string14"));
        assertEquals(6, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals(5, map.size());
        
        assertEquals("string7", map.remove(7));
        assertEquals(4, map.size());
        
        assertNull(map.remove(10));
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(0, "string0"));
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(14, "string14"));
        
        Iterator<Integer> it = map.iterator();
        
        assertEquals(6, (int) it.next());
        assertEquals(0, (int) it.next());
        assertEquals(1, (int) it.next());
        assertEquals(2, (int) it.next());
        assertEquals(7, (int) it.next());
        assertEquals(14, (int) it.next());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(0, "string0"));
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(14, "string14"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator(); 
        assertEquals(6, (int) it.next().getKey());
        assertEquals(0, (int) it.next().getKey());
        assertEquals(1, (int) it.next().getKey());
        assertEquals(2, (int) it.next().getKey());
        assertEquals(7, (int) it.next().getKey());
        assertEquals(14, (int) it.next().getKey());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
        assertNull(map.put(0, "string0"));
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(14, "string14"));
        
        Iterator<String> it = map.values().iterator();
        assertEquals("string6", (String) it.next());
        assertEquals("string0", (String) it.next());
        assertEquals("string1", (String) it.next());
        assertEquals("string2", (String) it.next());
        assertEquals("string7", (String) it.next());
        assertEquals("string14", (String) it.next());
        assertFalse(it.hasNext());
    }
}