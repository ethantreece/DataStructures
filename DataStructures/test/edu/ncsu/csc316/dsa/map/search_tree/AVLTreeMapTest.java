package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class AVLTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(44, "44");
        tree.put(17, "17");
        tree.put(78, "78");
        tree.put(32, "32");
        tree.put(50, "50");
        tree.put(88, "88");
        tree.put(48, "48");
        tree.put(62, "62");
        // Tree should look like the following
        //           44
        //         /    \
        //       17     78
        //        \    /  \
        //        32  50   88
        //            / \
        //           48  62
        assertEquals(44, (int) tree.root().getElement().getKey());
        assertEquals(17, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(78, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(32, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(50, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(88, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(48, (int) tree.left(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(62, (int) tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
        
        tree.put(54, "54");
        // Tree should look like the following after restructuring
        //           44
        //         /    \
        //       17     62
        //        \    /  \
        //        32  50   78
        //            / \    \
        //           48  54   88
        assertEquals(44, (int) tree.root().getElement().getKey());
        assertEquals(17, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(62, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(32, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(50, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(78, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(48, (int) tree.left(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(54, (int) tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(88, (int) tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        
        tree.put(44, "44");
        tree.put(17, "17");
        tree.put(78, "78");
        tree.put(32, "32");
        tree.put(50, "50");
        tree.put(88, "88");
        tree.put(48, "48");
        tree.put(62, "62");
        // Tree should look like the following
        //           44
        //         /    \
        //       17     78
        //        \    /  \
        //        32  50   88
        //            / \
        //           48  62
        assertEquals("44", tree.get(44));
        assertEquals("17", tree.get(17));
        assertEquals("78", tree.get(78));
        assertEquals("32", tree.get(32));
        assertEquals("50", tree.get(50));
        assertEquals("88", tree.get(88));
        assertEquals("48", tree.get(48));
        assertEquals("62", tree.get(62));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty()); 
        
        tree.put(44, "44");
        tree.put(17, "17");
        tree.put(78, "78");
        tree.put(32, "32");
        tree.put(50, "50");
        tree.put(88, "88");
        tree.put(48, "48");
        tree.put(62, "62");
        tree.put(54, "54");
        // Tree should look like the following
        //           44
        //         /    \
        //       17     62
        //        \    /  \
        //        32  50   78
        //            / \    \
        //           48  54   88
        
        tree.remove(32);
        // Tree should look like the following after deletion and restructuring
        //           62
        //         /    \
        //       44     78
        //      / \       \
        //    17   50      88
        //         / \  
        //        48  54
        assertEquals(62, (int) tree.root().getElement().getKey());
        assertEquals(44, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(78, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(17, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(50, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(88, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(48, (int) tree.left(tree.right(tree.left(tree.root()))).getElement().getKey());
        assertEquals(54, (int) tree.right(tree.right(tree.left(tree.root()))).getElement().getKey());
    }
}