package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SkipListMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class SkipListMapTest {

    private Map<Integer, String> map;
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
        studentMap = new SkipListMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SkipListMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        assertEquals(5, map.size());
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
        
        assertEquals("string1", map.put(1, "string1NEW"));
        assertEquals("string1NEW", map.get(1));
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
        
        assertNull(map.get(10));
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals(5, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals(4, map.size());
        
        assertNull(map.remove(10));
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s5, 5));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s1, 1));
        
        assertEquals(studentMap.get(s1), (Integer) 1);
        assertEquals(studentMap.get(s2), (Integer) 2);
        assertEquals(studentMap.get(s3), (Integer) 3);
        assertEquals(studentMap.get(s4), (Integer) 4);
        assertEquals(studentMap.get(s5), (Integer) 5);
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
        assertEquals(i.next(), (Integer) 2);
        assertTrue(i.hasNext());
        assertEquals(i.next(), (Integer) 3);
        assertTrue(i.hasNext());
        assertEquals(i.next(), (Integer) 4);
        assertTrue(i.hasNext());
        assertEquals(i.next(), (Integer) 5);
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        assertEquals(it.next().getKey(), (Integer) 2);
        assertTrue(it.hasNext());
        assertEquals(it.next().getKey(), (Integer) 3);
        assertTrue(it.hasNext());
        assertEquals(it.next().getKey(), (Integer) 4);
        assertTrue(it.hasNext());
        assertEquals(it.next().getKey(), (Integer) 5);
        assertFalse(it.hasNext());
        
        try {
            it.remove();
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());

        assertEquals(it.next(), "string1");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string2");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string3");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string4");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string5");
        assertFalse(it.hasNext());
        
        try {
            it.remove();
            fail("UnsupportedOperatitonExceptiton should have been thrown.");      
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}

