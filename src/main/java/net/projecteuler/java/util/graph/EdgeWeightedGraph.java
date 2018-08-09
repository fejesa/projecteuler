package net.projecteuler.java.util.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * The <tt>EdgeWeightedGraph</tt> class represents an undirected graph of
 * vertices named 0 through V-1, where each edge has a real-valued weight. It
 * supports the following operations: add an edge to the graph, in the graph,
 * iterate over all of the neighbors incident to a vertex. Parallel edges and
 * self-loops are permitted.
 * <p>
 * For additional documentation,
 * @see <a href="http://algs4.cs.princeton.edu/43mst">Section 4.3 of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.</a>
 */
public class EdgeWeightedGraph {

	private final int v;
	private int e;
	private LinkedList<WeightedEdge>[] adj;

	/**
	 * Create an empty edge-weighted graph with V vertices.
	 */
	@SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int v) {
		if (v < 0) {
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		}
		this.v = v;
		this.e = 0;
		adj = (LinkedList<WeightedEdge>[]) new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<WeightedEdge>();
		}
	}

	/**
	 * Return the number of vertices in this graph.
	 */
	public int v() {
		return v;
	}

	/**
	 * Return the number of edges in this graph.
	 */
	public int e() {
		return e;
	}

	/**
	 * Add the edge e to this graph.
	 */
	public void addEdge(WeightedEdge we) {
		int v = we.either();
		int w = we.other(v);
		adj[v].add(we);
		adj[w].add(we);
		e++;
	}

	public Collection<WeightedEdge> edges() {
		Collection<WeightedEdge> edges = new ArrayList<WeightedEdge>();
        for (int i = 0; i < v; i++) {
            int selfLoops = 0;
            for (WeightedEdge e : adj[i]) {
                if (e.other(i) > i) {
                    edges.add(e);
                }
                // only add one copy of each self loop
                else if (e.other(i) == i) {
                    if (selfLoops % 2 == 0) {
                    	edges.add(e);
                    }
                    selfLoops++;
                }
            }
        }
		return edges;
	}
}