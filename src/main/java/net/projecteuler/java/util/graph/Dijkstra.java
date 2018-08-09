package net.projecteuler.java.util.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	
	public static void execute(Vertex source) {

		source.setMinDistance(0);

		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.getAdjacents()) {
				Vertex v = e.getTarget();
				int weight = e.getWeight();
				int distanceThroughU = u.getMinDistance() + weight;
				if (distanceThroughU < v.getMinDistance()) {
					vertexQueue.remove(v);
					v.setMinDistance(distanceThroughU);
					v.setPrevious(u);
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Vertex> getShortestPath(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.getPrevious()) {
			path.add(vertex);
		}
		Collections.reverse(path);
		return path;
	}

	public static long getDistance(Vertex target) {
		List<Vertex> path = getShortestPath(target);
		long sum = 0;
		for (Vertex v : path) {
			sum += v.getNumber();
		}
		return sum;
	}
	
	public static int[][] readFile(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		List<String> lines = new ArrayList<String>();
		String str;
		while ((str = in.readLine()) != null) {
			lines.add(str);
		}
		in.close();

		int[][] matrix = new int[lines.size()][lines.get(lines.size() - 1).split(",").length];

		int rowIndex = 0;
		for (String line : lines) {
			String[] row = line.split(",");
			int columnIndex = 0;
			for (String n : row) {
				matrix[rowIndex][columnIndex] = Integer.valueOf(n);
				++columnIndex;
			}
			++rowIndex;
		}

		return matrix;
	}

	public static Vertex[][] transform(int[][] matrix) {
		int length = matrix[0].length;
		Vertex[][] vertices = new Vertex[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				Vertex v = new Vertex(i, j, matrix[i][j]);
				vertices[i][j] = v;
			}
		}
		return vertices;
	}

	public static void main(String[] args) throws Exception {
		//int[][] matrix = readFile("src/main/resources/test_dijkstra.txt");
		int[][] matrix = readFile("src/main/resources/problem08x.txt");
		Vertex[][] vertices = new Vertex[matrix[0].length][matrix[1].length];
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				Vertex v = new Vertex(i, j, matrix[i][j]);
				vertices[i][j] = v;
			}
		}

		for (int i = 0; i < vertices[0].length; i++) {
			for (int j = 0; j < vertices[i].length; j++) {
				Vertex v = vertices[i][j];
				if (i + 1 < vertices[0].length) {
					 v.addAdjacent(vertices[i + 1][j], matrix[i][j]);
				}
				if (j + 1 < vertices[1].length) {
					v.addAdjacent(vertices[i][j + 1], matrix[i][j]);
				}
			}
		}

		execute(vertices[0][0]);

		Vertex target = vertices[vertices[0].length - 1][vertices[1].length - 1];
		System.out.println(getShortestPath(target));
		
		System.out.println(getDistance(target));
	}
}