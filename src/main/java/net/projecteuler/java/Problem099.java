package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Comparing two numbers written in index form like 2<sup>11</sup> and 3<sup>7</sup>
 * is not difficult, as any calculator would confirm that
 * 2<sup>11</sup> = 2048 < 3<sup>7</sup> = 2187.
 * <p>
 * However, confirming that 632382<sup>518061</sup> > 519432<sup>525806</sup> would be
 * much more difficult, as both numbers contain over three million digits.
 * </p>
 * Using the given file containing one thousand lines with a base/exponent
 * pair on each line, determine which line number has the greatest numerical value.
 *
 * @author vuser
 *
 */
public class Problem099 {

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem099.txt") == 709 : "failed";
	}

	private static int calculate(String file) throws IOException {
		int[][] numbers = read(file);
		double max = 0;
		int line = 0;
		for (int i = 0; i < numbers.length; i++) {
			int a = numbers[i][0];
			int b = numbers[i][1];

			// Comparison based on log(x^y) = y log(x)
			double d = b * Math.log10(a);

			if (d > max) {
				max = d;
				line = i;
			}
		}

		// Must add +1 because the line number is required
		return line + 1;
	}

	private static int[][] read(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		List<String> lines = new ArrayList<String>();
		String str;
		while ((str = in.readLine()) != null) {
			lines.add(str);
		}
		in.close();

		int[][] matrix = new int[lines.size()][2];

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
}