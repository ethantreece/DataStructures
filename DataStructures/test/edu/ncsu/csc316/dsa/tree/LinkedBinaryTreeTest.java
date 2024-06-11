package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 *
 */
public class LinkedBinaryTreeTest {

    private LinkedBinaryTree<String> tree;
    private Position<String> one;
    private Position<String> two;
    private Position<String> three;
    private Position<String> four;
    private Position<String> five;
    private Position<String> six;
    private Position<String> seven;
    private Position<String> eight;
    private Position<String> nine;
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     *
     * @param <E> the type of elements stored in the tree
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        
        assertEquals("one", tree.set(one, "one NEW"));
        assertEquals("one NEW", one.getElement());
        
        assertEquals("ten", tree.set(ten, "ten NEW"));
        assertEquals("ten NEW", ten.getElement());
        
        assertEquals("two", tree.set(two, "two NEW"));
        assertEquals("two NEW", two.getElement());
        
        assertEquals("eight", tree.set(eight, "eight NEW"));
        assertEquals("eight NEW", eight.getElement());
        
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        assertEquals(2, tree.numChildren(ten));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        
        assertEquals("one", tree.parent(two).getElement());
        assertEquals("one", tree.parent(three).getElement());
        
        assertEquals("one", tree.parent(tree.parent(tree.parent(nine))).getElement());
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        
        assertEquals("two", tree.sibling(three).getElement());
        assertEquals("three", tree.sibling(two).getElement());
        
        assertNull(tree.sibling(four));
        assertNull(tree.sibling(one));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(ten));
        assertTrue(tree.isInternal(four));
        
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(ten));
        assertFalse(tree.isLeaf(four));
        
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        
        assertTrue(tree.isRoot(one));
        
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterable<Position<String>> preorder = tree.preOrder();
        Iterator<Position<String>> i = preorder.iterator();
        assertEquals("one", i.next().getElement());
        assertEquals("two", i.next().getElement());
        assertEquals("six", i.next().getElement());
        assertEquals("ten", i.next().getElement());
        assertEquals("seven", i.next().getElement());
        assertEquals("five", i.next().getElement());
        assertEquals("three", i.next().getElement());
        assertEquals("four", i.next().getElement());
        assertEquals("eight", i.next().getElement());
        assertEquals("nine", i.next().getElement());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterable<Position<String>> postorder = tree.postOrder();
        Iterator<Position<String>> i = postorder.iterator();
        assertEquals("six", i.next().getElement());
        assertEquals("seven", i.next().getElement());
        assertEquals("five", i.next().getElement());
        assertEquals("ten", i.next().getElement());
        assertEquals("two", i.next().getElement());
        assertEquals("eight", i.next().getElement());
        assertEquals("nine", i.next().getElement());
        assertEquals("four", i.next().getElement());
        assertEquals("three", i.next().getElement());
        assertEquals("one", i.next().getElement());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterable<Position<String>> inorder = tree.inOrder();
        Iterator<Position<String>> i = inorder.iterator();
        
        try {
            i.remove();
            fail("Should throw exception");
        } catch (Exception e) {
            assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
        
        assertEquals("six", i.next().getElement());
        assertEquals("two", i.next().getElement());
        assertEquals("seven", i.next().getElement());
        assertEquals("ten", i.next().getElement());
        assertEquals("five", i.next().getElement());
        assertEquals("one", i.next().getElement());
        assertEquals("eight", i.next().getElement());
        assertEquals("four", i.next().getElement());
        assertEquals("nine", i.next().getElement());
        assertEquals("three", i.next().getElement());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        assertTrue(tree.isEmpty());
        
        one = tree.addRoot("one");
        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        
    }
    
    /**
     * Tests the levelOrder method
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterable<Position<String>> levelorder = tree.levelOrder();
        Iterator<Position<String>> i = levelorder.iterator();
        assertEquals("one", i.next().getElement());
        assertEquals("two", i.next().getElement());
        assertEquals("three", i.next().getElement());
        assertEquals("six", i.next().getElement());
        assertEquals("ten", i.next().getElement());
        assertEquals("four", i.next().getElement());
        assertEquals("seven", i.next().getElement());
        assertEquals("five", i.next().getElement());
        assertEquals("eight", i.next().getElement());
        assertEquals("nine", i.next().getElement());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
        createTree();
        
        assertEquals("eleven", tree.addLeft(nine, "eleven").getElement());
        assertEquals(11, tree.size());
        assertEquals("eleven", tree.left(nine).getElement());
        
        try {
            tree.addLeft(one, "two new");
            fail("Exception should be thrown");
        } catch (Exception e) {
            assertEquals("Node already has a left child.", e.getMessage());
        }
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
        createTree();
        
        assertEquals("eleven", tree.addRight(nine, "eleven").getElement());
        assertEquals(11, tree.size());
        assertEquals("eleven", tree.right(nine).getElement());
        
        try {
            tree.addRight(one, "two new");
            fail("Exception should be thrown");
        } catch (Exception e) {
            assertEquals("Node already has a right child.", e.getMessage());
        }
    }

    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        
        assertEquals("nine", tree.remove(nine));
        assertEquals(9, tree.size());
        assertNull(tree.right(four));
        
        assertEquals("three", tree.remove(three));
        assertEquals(8, tree.size());
        assertEquals("four", tree.right(one).getElement());
        
        assertEquals("six", tree.remove(six));
        assertEquals(7, tree.size());
        assertNull(tree.left(two));
        
        try {
            tree.remove(ten);
            fail("Exception should be thrown");
        } catch (Exception e) {
            assertEquals("The node has two children", e.getMessage());
        }
    }
    
    /**
     * Tests the toString method
     */
    @Test
    public void testToString() {
        createTree();
        String s = tree.toString();
        String expected = "LinkedBinaryTree[\n"
                + "one\n"
                + " two\n"
                + "  six\n"
                + "  ten\n"
                + "   seven\n"
                + "   five\n"
                + " three\n"
                + "  four\n"
                + "   eight\n"
                + "   nine\n"
                + "]";
        assertEquals(expected, s);
    }
}