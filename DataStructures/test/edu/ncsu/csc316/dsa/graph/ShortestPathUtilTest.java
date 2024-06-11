package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the shortest path util methods
 * @author Ethan Treece
 *
 */
public class ShortestPathUtilTest {

    /** Directed graph */
    private Graph<Integer, W> graph;

    /** Vertex 0 */
    private Vertex<Integer> v0;
    
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
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        graph = new EdgeListGraph<Integer, W>();
    }
    
    /**
     * Builds an undirected graph sample
     */
    private void buildDirectedSample() {
        v0 = graph.insertVertex(0);
        v1 = graph.insertVertex(1);
        v2 = graph.insertVertex(2);
        v3 = graph.insertVertex(3);
        v4 = graph.insertVertex(4);
        v5 = graph.insertVertex(5);
        
        graph.insertEdge(v0, v5, new W(3));
        graph.insertEdge(v0, v4, new W(6));
        graph.insertEdge(v1, v2, new W(6));
        graph.insertEdge(v3, v2, new W(4));
        graph.insertEdge(v3, v1, new W(1));
        graph.insertEdge(v3, v4, new W(1));
        graph.insertEdge(v4, v3, new W(3));
        graph.insertEdge(v4, v1, new W(1));
        graph.insertEdge(v5, v4, new W(1));
        graph.insertEdge(v5, v3, new W(3));
    }
    
    /**
     * Tests the dijkstra() method
     */
    @Test
    public void testDijkstra() {
        buildDirectedSample();
        Map<Vertex<Integer>, Integer> m = ShortestPathUtil.dijkstra(graph, v0);

        assertEquals(6, m.size());
        
        assertEquals(0, (int) m.get(v0));
        assertEquals(5, (int) m.get(v1));
        assertEquals(9, (int) m.get(v2));
        assertEquals(5, (int) m.get(v3));
        assertEquals(4, (int) m.get(v4));
        assertEquals(3, (int) m.get(v5));
    }
    
    /**
     * Tests the shortestPathTree() method
     */
    @Test
    public void testShortestPathTree() {
        buildDirectedSample();
        Map<Vertex<Integer>, Edge<W>> m = ShortestPathUtil.shortestPathTree(graph, v0, ShortestPathUtil.dijkstra(graph, v0));
        
        assertEquals(5, m.size());
    }
    
    /**
     * W represents weighted objects
     * @author Ethan Treece
     *
     */
    public class W implements Weighted {
        
        /** Weight */
        private int w;
        
        /**
         * Constructor for W object
         * @param w weight
         */
        public W(int w) {
            setW(w);
        }

        /**
         * Gets the weight
         * @return weight
         */
        public int getW() {
            return w;
        }

        /**
         * Sets the weight
         * @param w weight
         */
        public void setW(int w) {
            this.w = w;
        }

        @Override
        public int getWeight() {
            return getW();
        }
    }    

}
