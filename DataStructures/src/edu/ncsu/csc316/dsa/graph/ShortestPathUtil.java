package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
        // Use a map to track the shorted path cost to each vertex
        Map<Vertex<V>, Integer> m = new LinearProbingHashMap<Vertex<V>, Integer>();

        Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
        Map<Vertex<V>, Boolean> found = new LinearProbingHashMap<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
        
        // Use an adaptable priority queue to order vertices by weight
        HeapAdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<Integer, Vertex<V>>();
        
        // Initialize information for each vertex
        for (Vertex<V> v : graph.vertices()) {
            if (v == start) {
                weights.put(v, 0);
            } else {
                weights.put(v, Integer.MAX_VALUE);
            }
            found.put(v, false);
            pqEntries.put(v, q.insert(weights.get(v), v));
        }
        
        // Main loop
        while (!q.isEmpty()) {
            Vertex<V> u = q.deleteMin().getValue();
            // At this point, we know the shortest path to vertex u
            // so we can add this cost to our shortest path cost map
            m.put(u, weights.get(u));
            found.put(u, true);
            for (Edge<E> e : graph.outgoingEdges(u)) {
                Vertex<V> z = graph.opposite(u, e);
                int r = e.getElement().getWeight() + weights.get(u);
                if (!found.get(z) && r < weights.get(z)) {
                    weights.put(z, r);
                    q.replaceKey(pqEntries.get(z), r);
                }
            }
        }
        return m;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
        // Create a map to store edges in the shortest path tree
        Map<Vertex<V>, Edge<E>> m = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
        
        for (Vertex<V> v : costs) {
            if (v != start) {
                for (Edge<E> e : graph.incomingEdges(v)) {
                    Vertex<V> u = graph.opposite(v, e);
                    if (costs.get(v) == costs.get(u) + e.getElement().getWeight()) {
                        m.put(v, e);
                    }
                }
            }
        }
        return m;
    }
}
