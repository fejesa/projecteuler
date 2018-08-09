package net.projecteuler.java.util.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {

	private final int x;
	private final int y;
	private final int number;

	private int minDistance = Integer.MAX_VALUE;
	private Vertex previous = null;
	private List<Edge> adjacents = new ArrayList<Edge>();

	public Vertex(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
	}

	public int getMinDistance() {
    	return minDistance;
    }

	public void setMinDistance(int minDistance) {
    	this.minDistance = minDistance;
    }

	public Vertex getPrevious() {
    	return previous;
    }

	public void setPrevious(Vertex previous) {
    	this.previous = previous;
    }

	public List<Edge> getAdjacents() {
    	return adjacents;
    }

	public void addAdjacent(Vertex vertex, int weight) {
		Edge edge = new Edge(vertex, weight);
		adjacents.add(edge);
	}

	public int getX() {
    	return x;
    }

	public int getY() {
    	return y;
    }

	public int getNumber() {
    	return number;
    }

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
    public int compareTo(Vertex o) {
		return Double.compare(minDistance, o.minDistance);
    }
}