package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class RedBlackTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());     
        
        tree.put(4, null);
        tree.put(7, null);
        tree.put(12, null);
        assertEquals(7, (int) tree.root().getElement().getKey());
        assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int) tree.right(tree.root()).getElement().getKey());
        
        tree.put(15, null);
        assertEquals(7, (int) tree.root().getElement().getKey());
        assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(3, null);
        tree.put(5, null);
        assertEquals(7, (int) tree.root().getElement().getKey());
        assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(14, null);
        assertEquals(7, (int) tree.root().getElement().getKey());
        assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(12, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(18, null);
        assertEquals(7, (int) tree.root().getElement().getKey());
        assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(12, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(18, (int) tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        
        tree.put(16, null);
        assertEquals(7, (int) tree.root().getElement().getKey());
        assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(12, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(16, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
        assertEquals(18, (int) tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        
        tree.put(17, null);
        assertEquals(14, (int) tree.root().getElement().getKey());
        assertEquals(7, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(12, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(18, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(17, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(4, "4");
        tree.put(7, "7");
        tree.put(12, "12");
        tree.put(15, "15");
        tree.put(3, "3");
        tree.put(5, "5");
        tree.put(14, "14");
        tree.put(18, "18");
        tree.put(16, "16");
        tree.put(17, "17");
        
        assertEquals("4", tree.get(4));
        assertEquals("7", tree.get(7));
        assertEquals("12", tree.get(12));
        assertEquals("15", tree.get(15));
        assertEquals("3", tree.get(3));
        assertEquals("5", tree.get(5));
        assertEquals("14", tree.get(14));
        assertEquals("18", tree.get(18));
        assertEquals("16", tree.get(16));
        assertEquals("17", tree.get(17));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());

        tree.put(4, "4");
        tree.put(7, "7");
        tree.put(12, "12");
        tree.put(15, "15");
        tree.put(3, "3");
        tree.put(5, "5");
        tree.put(14, "14");
        tree.put(18, "18");
        tree.put(16, "16");
        tree.put(17, "17");
        
        tree.remove(3);
        assertEquals(14, (int) tree.root().getElement().getKey());
        assertEquals(7, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(12, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(18, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(5, (int) tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(17, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
        
        tree.remove(12);
        assertEquals(14, (int) tree.root().getElement().getKey());
        assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(18, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(17, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
        
        tree.remove(17);
        assertEquals(14, (int) tree.root().getElement().getKey());
        assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(18, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.remove(18);
        assertEquals(14, (int) tree.root().getElement().getKey());
        assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        
        tree.remove(15);
        assertEquals(14, (int) tree.root().getElement().getKey());
        assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        
        tree.remove(16);
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(7, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        
    }
}