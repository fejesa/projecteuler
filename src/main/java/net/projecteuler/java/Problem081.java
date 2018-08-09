package net.projecteuler.java;

import java.io.IOException;

import net.projecteuler.java.util.graph.Dijkstra;
import net.projecteuler.java.util.graph.Vertex;

/**
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right,
 * by <b>only moving to the right and down</b>, is indicated in bold red and is equal to 2427.
 * <p>
 * <span style="color:#ff0000;">131</span> 673 234 103 18</br>
 * <span style="color:#ff0000;">201</span><span style="color:#ff0000;">&nbsp;&nbsp;&nbsp;96&nbsp;</span><span style="color:#ff0000;">342</span> 965 150</br>
 * 630 803 <span style="color:#ff0000;">746</span><span style="color:#ff0000;">&nbsp;422&nbsp;</span>111</br>
 * 537 699 497 <span style="color:#ff0000;">121</span> 956</br>
 * 805 732 524 <span style="color:#ff0000;">&nbsp;&nbsp;37&nbsp;</span><span style="color:#ff0000;">331</span></br>
 * </p>
 * Find the minimal path sum, in the given text file containing a 80 by 80 matrix,
 * from the top left to the bottom right by only moving right and down.
 *
 * @author vbox
 *
 */
public class Problem081 {

	public static void main(String[] args) throws Exception {
		assert calculate() == 427337 : "failed";
	}

	private static long calculate() throws IOException {
		int[][] matrix = Dijkstra.readFile("src/main/resources/problem08x.txt");
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
			}
		}

		Dijkstra.execute(vertices[0][0]);

		return Dijkstra.getDistance(vertices[length - 1][length - 1]);
	}
}
