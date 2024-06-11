package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the graph traversal util methods
 * @author Ethan Treece
 *
 */
public class GraphTraversalUtilTest {

    /** Undirected graph */
    private Graph<Integer, Integer> graph;

    /** Vertex 1 */
    private Vertex<Integer> v1;

    /** Vertex 2 */
    private Vertex<Integer> v2;

    /** Vertex 3 */
    private Vertex<Integer> v3;

    /** Vertex 4 */
    private Vertex<Integer> v4;

    /** Vertex 5 */
    private Vertex<Integer> v5;

    /** Vertex 6 */
    private Vertex<Integer> v6;

    /** Vertex 7 */
    private Vertex<Integer> v7;

    /** Vertex 8 */
    private Vertex<Integer> v8;

    /** Vertex 9 */
    private Vertex<Integer> v9;

    /** Vertex 10 */
    private Vertex<Integer> v10;

    /** Vertex 11 */
    private Vertex<Integer> v11;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        graph = new EdgeListGraph<Integer, Integer>();
    }
    
    /**
     * Builds an undirected graph sample
     */
    private void buildUndirectedSample() {
        v1 = graph.insertVertex(1);
        v2 = graph.insertVertex(2);
        v3 = graph.insertVertex(3);
        v4 = graph.insertVertex(4);
        v5 = graph.insertVertex(5);
        v6 = graph.insertVertex(6);
        v7 = graph.insertVertex(7);
        v8 = graph.insertVertex(8);
        v9 = graph.insertVertex(9);
        v10 = graph.insertVertex(10);
        v11 = graph.insertVertex(11);
        
        graph.insertEdge(v1, v2, 5);
        graph.insertEdge(v1, v3, 5);
        graph.insertEdge(v2, v3, 5);
        graph.insertEdge(v2, v11, 5);
        graph.insertEdge(v4, v5, 5);
        graph.insertEdge(v4, v6, 5);
        graph.insertEdge(v4, v9, 5);
        graph.insertEdge(v4, v10, 5);
        graph.insertEdge(v6, v7, 5);
        graph.insertEdge(v6, v8, 5);
        graph.insertEdge(v6, v9, 5);
        graph.insertEdge(v6, v10, 5);
        graph.insertEdge(v7, v8, 5);
        graph.insertEdge(v9, v10, 5);
        graph.insertEdge(v9, v11, 5);
        graph.insertEdge(v10, v11, 5);
    }
    
    /**
     * Tests the depthFirstSearch() method
     */
    @Test
    public void testDepthFirstSearch() {
        buildUndirectedSample();
        Map<Vertex<Integer>, Edge<Integer>> m = GraphTraversalUtil.depthFirstSearch(graph, v1);
        assertEquals(10, m.size());
        
        assertEquals(graph.getEdge(v1, v2), m.get(v2));
        assertEquals(graph.getEdge(v2, v3), m.get(v3));
        assertEquals(graph.getEdge(v2, v11), m.get(v11));
        assertEquals(graph.getEdge(v4, v5), m.get(v5));
        assertEquals(graph.getEdge(v4, v6), m.get(v6));
        assertEquals(graph.getEdge(v4, v9), m.get(v4));
        assertEquals(graph.getEdge(v6, v7), m.get(v7));
        assertEquals(graph.getEdge(v7, v8), m.get(v8));
        assertEquals(graph.getEdge(v6, v10), m.get(v10));
        assertEquals(graph.getEdge(v9, v11), m.get(v9));
    }
    
    /**
     * Tests the breadthFirstSearch() method
     */
    @Test
    public void testBreadthFirstSearch() {
        buildUndirectedSample();
        Map<Vertex<Integer>, Edge<Integer>> m = GraphTraversalUtil.breadthFirstSearch(graph, v1);
        assertEquals(10, m.size());

        assertEquals(graph.getEdge(v1, v2), m.get(v2));
        assertEquals(graph.getEdge(v1, v3), m.get(v3));
        assertEquals(graph.getEdge(v2, v11), m.get(v11));
        assertEquals(graph.getEdge(v11, v9), m.get(v9));
        assertEquals(graph.getEdge(v11, v10), m.get(v10));
        assertEquals(graph.getEdge(v9, v4), m.get(v4));
        assertEquals(graph.getEdge(v9, v6), m.get(v6));
        assertEquals(graph.getEdge(v4, v5), m.get(v5));
        assertEquals(graph.getEdge(v6, v7), m.get(v7));
        assertEquals(graph.getEdge(v6, v8), m.get(v8));
    }

}
