package net.projecteuler.java;

import java.io.IOException;

import net.projecteuler.java.util.graph.Dijkstra;
import net.projecteuler.java.util.graph.Vertex;

/**
 * The minimal path sum in the 5 by 5 matrix below, by starting in any cell in the left column
 * and finishing in any cell in the right column, and <b>only moving up, down, and right</b>,
 * is indicated in red and bold; the sum is equal to 994.<p>
 * 131 673 <span style="color:#ff0000;">234</span> <span style="color:#ff0000;">103</span> <span style="color:#ff0000;">&nbsp;&nbsp;18</span></br>
 * <span style="color:#ff0000;">201</span><span style="color:#ff0000;">&nbsp;&nbsp;&nbsp;96&nbsp;</span><span style="color:#ff0000;">342</span> 965 150</br>
 * 630 803 746 422 111</br>
 * 537 699 497 121 956</br>
 * 805 732 524 &nbsp;37&nbsp; 331</br>
 * </p>
 * Find the minimal path sum, in the given text file containing a 80 by 80 matrix, from the left column to the right column.
 *
 * @author vbox
 *
 */
public class Problem082 {

	public static void main(String[] args) throws Exception {
		assert calculate() == 260324 : "failed";
	}

	private static long calculate() throws IOException {
		int[][] matrix = Dijkstra.readFile("src/main/resources/problem08x.txt");

		long min = Long.MAX_VALUE;
		int length = matrix[0].length;

		for (int i = 0; i < length; i++) {
			Vertex[][] vertices = build(matrix);
			Vertex source = vertices[i][0];
			Dijkstra.execute(source);
			for (int j = 0; j < length; j++) {
				Vertex target = vertices[j][length - 1];
				min = Math.min(min, Dijkstra.getDistance(target));
			}
		}

		return min;
	}

	private static Vertex[][] build(int[][] matrix) {
		Vertex[][] vertices = Dijkstra.transform(matrix);

		int length = matrix[0].length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				Vertex v = vertices[i][j];
				if (i + 1 < length) {
					 v.addAdjacent(vertices[i + 1][j], matrix[i][j]); // down direction
				}
				if (j + 1 < length) {
					v.addAdjacent(vertices[i][j + 1], matrix[i][j]); // right direction
				}
				if (i > 0) {
					v.addAdjacent(vertices[i - 1][j], matrix[i][j]); // up direction
				}
			}
		}
		return vertices;
	}
}
