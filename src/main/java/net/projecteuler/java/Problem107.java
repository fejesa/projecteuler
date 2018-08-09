package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import net.projecteuler.java.util.UnionFind;
import net.projecteuler.java.util.graph.EdgeWeightedGraph;
import net.projecteuler.java.util.graph.WeightedEdge;

/**
 * Determining the most efficient way to connect the network.
 *
 * @author vuser
 *
 */
public class Problem107 {

	public static void main(String... args) throws Exception {
		assert calculate("src/main/resources/problem107.txt", 40) == 259679 : "failed";
	}

	private static double calculate(String file, int size) throws IOException {

		Comparator<WeightedEdge> comparator = new Comparator<WeightedEdge>() {
			@Override
			public int compare(WeightedEdge e1, WeightedEdge e2) {
				return e1.compareTo(e2);
			}
		};

		MinQueue pq = new MinQueue(100, comparator);

		double total = 0;
		EdgeWeightedGraph graph = load(file, size);
		for (WeightedEdge e : graph.edges()) {
			pq.offer(e);
			total += e.weight();
		}

		LinkedList<WeightedEdge> mst = new LinkedList<WeightedEdge>();
		UnionFind uf = new UnionFind(graph.v());

		while (!pq.isEmpty() && mst.size() < graph.v() - 1) {
			List<WeightedEdge> minEdges = pq.delMin();
			for (WeightedEdge e : minEdges) {
				int v = e.either(), w = e.other(v); // and its vertices.
				if (uf.connected(v, w)) {
					continue; // Ignore ineligible edges.
				}

				uf.union(v, w); // Merge components.
				mst.offer(e); // Add edge to mst.
			}
		}

		double minimal = 0;
		for (WeightedEdge e : mst) {
			minimal += e.weight();
		}

		return total / 2 - minimal;
	}

	private static EdgeWeightedGraph load(String file, int size) throws IOException {

		EdgeWeightedGraph g = new EdgeWeightedGraph(size);

		BufferedReader in = new BufferedReader(new FileReader(file));
		try {
			String line = null;
			int lineIndex = 0;
			while ((line = in.readLine()) != null) {
				String[] array = line.split(",");
				for (int i = 0; i < size; i++) {
					String a = array[i].trim();
					if (!a.equals("-") && !a.equals("")) {
						g.addEdge(new WeightedEdge(lineIndex, i, Integer.parseInt(a)));
					}
				}
				++lineIndex;
			}
		} finally {
			in.close();
		}

		return g;

	}
}

@SuppressWarnings("serial")
class MinQueue extends PriorityQueue<WeightedEdge> {
	
	public MinQueue(int initialCapacity, Comparator<? super WeightedEdge> comparator) {
		super(initialCapacity, comparator);
	}

	public List<WeightedEdge> delMin() {
		WeightedEdge edge = poll();
		if (edge == null) {
			return null;
		}
		List<WeightedEdge> list = new ArrayList<WeightedEdge>();
		loop: while(true) {
			WeightedEdge we = peek();
			if (we.weight() == edge.weight()) {
				list.add(poll());
			} else {
				break loop;
			}
		}
		return list;
	}
}