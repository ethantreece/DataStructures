package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class UpTreeDisjointSetForestTest {

    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());

        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        
        assertEquals("three", three.getElement());
        assertEquals("four", four.getElement());
        assertEquals("five", five.getElement());
        
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        Position<String> eleven = set.makeSet("eleven");
        Position<String> twelve = set.makeSet("twelve");
        
        assertEquals(one, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(three, set.find("three"));
        assertEquals(four, set.find("four"));
        assertEquals(five, set.find("five"));
        assertEquals(six, set.find("six"));
        assertEquals(seven, set.find("seven"));
        assertEquals(eight, set.find("eight"));
        assertEquals(nine, set.find("nine"));
        assertEquals(ten, set.find("ten"));
        assertEquals(eleven, set.find("eleven"));
        assertEquals(twelve, set.find("twelve"));
        // you should draw out examples by hand (or use the examples
        // in the lecture slides or textbook) to help guide your test cases.
        // Be sure to perform find operations followed by union
        // operations to make sure the appropriate root notes are
        // returned and used when union-ing
        
        set.union(one, three);
        assertEquals(three, set.find("three"));
        assertEquals(three, set.find("one"));
        
        set.union(two, four);
        assertEquals(four, set.find("four"));
        assertEquals(four, set.find("two"));
        
        set.union(six, nine);
        assertEquals(nine, set.find("nine"));
        assertEquals(nine, set.find("six"));
        
        set.union(twelve, eleven);
        assertEquals(eleven, set.find("eleven"));
        assertEquals(eleven, set.find("twelve"));
        
        set.union(ten, eleven);
        assertEquals(eleven, set.find("ten"));
        
        set.union(eight, eleven);
        assertEquals(eleven, set.find("eight"));
        
        set.union(four, eleven);
        assertEquals(eleven, set.find("four"));
        assertEquals(eleven, set.find("two"));
        
    }
}