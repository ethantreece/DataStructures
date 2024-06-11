package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Tests the minimum spanning tree util methods
 * @author Ethan Treece
 *
 */
public class MinimumSpanningTreeUtilTest {

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
     * Tests the kruskal() method
     */
    @Test
    public void testKruskal() {
        buildDirectedSample();
        PositionalList<Edge<W>> p = MinimumSpanningTreeUtil.kruskal(graph);
        
        assertEquals(5, p.size());
        
        Iterator<Edge<W>> it = p.iterator();
        
        assertEquals(1, it.next().getElement().getWeight());
        assertEquals(1, it.next().getElement().getWeight());
        assertEquals(1, it.next().getElement().getWeight());
        assertEquals(3, it.next().getElement().getWeight());
        assertEquals(4, it.next().getElement().getWeight());
        
        assertFalse(it.hasNext());
    }

    /**
     * Tests the primJarnik() method
     */
    @Test
    public void testPrimJarnik() {
        buildDirectedSample();
        PositionalList<Edge<W>> p = MinimumSpanningTreeUtil.primJarnik(graph);

        assertEquals(5, p.size());

        Iterator<Edge<W>> it = p.iterator();
        
        assertEquals(3, it.next().getElement().getWeight());
        assertEquals(1, it.next().getElement().getWeight());
        assertEquals(1, it.next().getElement().getWeight());
        assertEquals(1, it.next().getElement().getWeight());
        assertEquals(4, it.next().getElement().getWeight());
        
        assertFalse(it.hasNext());
    }
    
    /**
     * W represents weighted objects
     * @author Ethan Treece
     *
     */
    public class W implements Weighted {
        
        private int w;
        
        public W(int w) {
            setW(w);
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        @Override
        public int getWeight() {
            return getW();
        }
    }  

}
