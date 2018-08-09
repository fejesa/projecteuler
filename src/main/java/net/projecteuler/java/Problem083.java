package net.projecteuler.java;

import java.io.IOException;

import net.projecteuler.java.util.graph.Dijkstra;
import net.projecteuler.java.util.graph.Vertex;

/**
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right,
 * by moving <b>left, right, up, and down</b>, is indicated in bold red and is equal to 2297.
 * <p>
 * <span style="color:#ff0000;">131</span> 673 <span style="color:#ff0000;">234</span> <span style="color:#ff0000;">103</span> <span style="color:#ff0000;">18</span></br>
 * <span style="color:#ff0000;">201</span><span style="color:#ff0000;">&nbsp;&nbsp;&nbsp;96&nbsp;</span><span style="color:#ff0000;">342</span> 965 <span style="color:#ff0000;">150</span></br>
 * 630 803 746</span><span style="color:#ff0000;">&nbsp;422&nbsp;</span> <span style="color:#ff0000;">111</span></br>
 * 537 699 497 <span style="color:#ff0000;">121</span> 956</br>
 * 805 732 524 <span style="color:#ff0000;">&nbsp;&nbsp;37&nbsp;</span><span style="color:#ff0000;">331</span></br>
 * </p>
 * Find the minimal path sum, in the given text file containing a 80 by 80 matrix,
 * from the top left to the bottom right by only moving right and down.
 *
 * @author vbox
 *
 */
public class Problem083 {

	public static void main(String[] args) throws Exception {
		assert calculate() == 425185 : "failed";
	}

	private static long calculate() throws IOException {
		int[][] matrix = Dijkstra.readFile("src/main/resources/problem08x.txt");
		Vertex[][] vertices = build(matrix);

		Dijkstra.execute(vertices[0][0]);
		return Dijkstra.getDistance(vertices[vertices.length - 1][vertices.length - 1]);
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
				if (j > 0) {
					v.addAdjacent(vertices[i][j - 1], matrix[i][j]); // left direction
				}
				if (i > 0) {
					v.addAdjacent(vertices[i - 1][j], matrix[i][j]); // up direction
				}
			}
		}

		return vertices;
	}
}
