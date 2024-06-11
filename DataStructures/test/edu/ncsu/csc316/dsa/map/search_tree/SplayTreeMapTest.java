package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.search_tree.*;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class SplayTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty()); 
        
        // ZIG-ZIG both right children
        tree.put(20, "10");
        tree.put(10, "20");
        tree.put(30, "30");
        assertEquals(30, (int) tree.root().getElement().getKey());
        assertEquals(20, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        
        setUp();
        
        // ZIG-ZIG both left children
        tree.put(20, "10");
        tree.put(30, "20");
        tree.put(10, "30");
        assertEquals(10, (int) tree.root().getElement().getKey());
        assertEquals(20, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        
        setUp();
        
        // ZIG-ZAG x is left child y is right child
        tree.put(30, "10");
        tree.put(10, "30");
        tree.put(20, "20");
        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());
        
        setUp();
        
        // ZIG-ZAG x is right child y is left child
        tree.put(10, "10");
        tree.put(30, "30");
        tree.put(20, "20");
        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());
        
        setUp();
        
        // ZIG x is right child of y
        tree.put(10, "10");
        tree.put(20, "20");
        assertEquals(20, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
        
        setUp();
        
        // ZIG x is left child of y
        tree.put(20, "10");
        tree.put(10, "20");
        assertEquals(10, (int) tree.root().getElement().getKey());
        assertEquals(20, (int) tree.right(tree.root()).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
        tree.put(20, "10");
        tree.put(10, "20");
        tree.put(30, "30");
        tree.get(10);
        assertEquals(10, (int) tree.root().getElement().getKey());
        tree.get(20);
        assertEquals(20, (int) tree.root().getElement().getKey());
        tree.get(30);
        assertEquals(30, (int) tree.root().getElement().getKey());
        assertEquals(20, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.left(tree.left(tree.root())).getElement().getKey());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());  
        
        tree.put(30, "30");
        tree.put(10, "10");
        tree.put(20, "20");
        
        tree.remove(20);
        assertEquals(30, (int) tree.root().getElement().getKey());
        
//        tree.put(8, "8");
//        tree.put(3, "3");
//        tree.put(10, "10");
//        tree.put(4, "4");
//        tree.put(11, "11");
//        tree.put(6, "6");
//        tree.put(5, "5");
//        tree.put(7, "7");
//        tree.get(3);
//        tree.get(6);
//        tree.get(8);
//        tree.get(3);
//        tree.get(8);
//        tree.get(4);
//        tree.get(8);
//        tree.get(6);
//        tree.get(8);
//        tree.get(3);
//        tree.get(8);
//        // Tree should look like the following
//        //            8
//        //         /    \
//        //        3     10
//        //        \       \
//        //         4       11
//        //          \
//        //           6
//        //          / \
//        //         5   7
//        assertEquals(8, (int) tree.root().getElement().getKey());
//        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
//        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
//        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
//        assertEquals(11, (int) tree.right(tree.right(tree.root())).getElement().getKey());
//        assertEquals(6, (int) tree.right(tree.right(tree.left(tree.root()))).getElement().getKey());
//        assertEquals(5, (int) tree.left(tree.right(tree.right(tree.left(tree.root())))).getElement().getKey());
//        assertEquals(7, (int) tree.right(tree.right(tree.right(tree.left(tree.root())))).getElement().getKey());
//        
//        tree.remove(8);
//        // Tree should look like the following after removal and restructuring
//        //            6
//        //         /    \
//        //        4      7
//        //       / \      \
//        //      3   5      10
//        //                  \
//        //                   11
//        assertEquals(6, (int) tree.root().getElement().getKey());
//        assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
//        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
//        assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
//        assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
//        assertEquals(10, (int) tree.right(tree.right(tree.root())).getElement().getKey());
//        assertEquals(11, (int) tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        
    }
}