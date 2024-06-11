package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure 
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class SeparateChainingHashMapTest {

    private Map<Integer, String> map;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
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
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        map = new SeparateChainingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        // You should create some collisions to check that entries
        // are placed in the correct buckets
        assertNull(map.put(1, "string1"));
        assertEquals("string1", map.put(1, "string1NEW"));
        
        assertNull(map.put(0, "string0"));
        
        it = map.entrySet().iterator();
        assertEquals(0, (int)it.next().getKey()); // should be in a map in index 1
        assertEquals(1, (int)it.next().getKey()); // should be in a map in index 2
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        assertNull(map.put(7, "string7"));
        assertNull(map.put(14, "string14"));
        
        it = map.entrySet().iterator();
        assertEquals(0, (int)it.next().getKey()); // should be in a map in index 1
        assertEquals(7, (int)it.next().getKey()); // should be in a map in index 1
        assertEquals(14, (int)it.next().getKey()); // should be in a map in index 1
        assertEquals(1, (int)it.next().getKey()); // should be in a map in index 2
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        assertEquals(6, map.size());
        
        map = new SeparateChainingHashMap<Integer, String>();
        map = new SeparateChainingHashMap<Integer, String>(true);
        map = new SeparateChainingHashMap<Integer, String>(7);
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
        assertEquals(7, (int) it.next());
        assertEquals(14, (int) it.next());
        assertEquals(1, (int) it.next());
        assertEquals(2, (int) it.next());
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
        assertEquals(7, (int) it.next().getKey());
        assertEquals(14, (int) it.next().getKey());
        assertEquals(1, (int) it.next().getKey());
        assertEquals(2, (int) it.next().getKey());
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
        assertEquals("string7", (String) it.next());
        assertEquals("string14", (String) it.next());
        assertEquals("string1", (String) it.next());
        assertEquals("string2", (String) it.next());
        assertFalse(it.hasNext());
    }
}