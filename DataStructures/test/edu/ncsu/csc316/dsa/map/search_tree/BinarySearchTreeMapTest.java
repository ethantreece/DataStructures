package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class BinarySearchTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        assertEquals("one", tree.root().getElement().getValue());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals("two", tree.get(2));
        assertEquals("two", tree.right(tree.root()).getElement().getValue());
        
        tree.put(3, "three");
        assertEquals(3, tree.size());
        assertEquals("three", tree.get(3));
        
        tree.put(2, "twoNEW");
        assertEquals(3, tree.size());
        assertEquals("twoNEW", tree.get(2));
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.get(1));
        
        tree.put(2, "two");
        assertEquals("two", tree.get(2));
        
        tree.put(10, "ten");
        assertEquals("ten", tree.get(10));
        
        tree.put(6, "six");
        assertEquals("six", tree.get(6));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(2, "two");
        tree.put(6, "six");
        tree.put(4, "four");
        tree.put(9, "nine");
        tree.put(3, "three");
        tree.put(5, "five");
        tree.put(10, "ten");
        assertEquals(7, tree.size());
        assertEquals("two", tree.root().getElement().getValue());
        assertNull(tree.left(tree.root()).getElement());
        // Tree should look like the following
        //      2
        //       \
        //        6
        //       / \
        //      4   9
        //     / \   \
        //    3   5   10
        
        // Remove the root
        tree.remove(2);
        assertEquals(6, tree.size());
        assertEquals("six", tree.root().getElement().getValue());
        //        6
        //       / \
        //      4   9
        //     / \   \
        //    3   5   10
        
        // Remove node that has only right child
        tree.remove(9);
        assertEquals(5, tree.size());
        assertEquals("six", tree.root().getElement().getValue());
        assertEquals("ten", tree.right(tree.root()).getElement().getValue());
        //        6
        //       / \
        //      4  10
        //     / \
        //    3   5
        
        // Remove node that has both children
        tree.remove(4);
        assertEquals(4, tree.size());
        assertEquals("six", tree.root().getElement().getValue());
        assertEquals("five", tree.left(tree.root()).getElement().getValue());
        //        6
        //       / \
        //      5  10
        //     / 
        //    3 
        
        // Remove node that has only left child
        tree.remove(5);
        assertEquals(3, tree.size());
        assertEquals("six", tree.root().getElement().getValue());
        assertEquals("three", tree.left(tree.root()).getElement().getValue());
        //        6
        //       / \
        //      3  10
        
        String s = tree.toString();
        assertEquals(s, tree.toString());
        
        Iterable<Entry<Integer, String>> iterable = tree.entrySet();
        Iterator<Entry<Integer, String>> i = iterable.iterator();
        assertTrue(i.hasNext());
        assertEquals("three", i.next().getValue());
        assertTrue(i.hasNext());
        assertEquals("six", i.next().getValue());
        assertTrue(i.hasNext());
        assertEquals("ten", i.next().getValue());
        assertFalse(i.hasNext());
        
    }
}