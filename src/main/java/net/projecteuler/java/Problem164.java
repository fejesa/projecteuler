package net.projecteuler.java;

import net.projecteuler.java.util.Counter;

/**
 * How many 20 digit numbers n (without any leading zero)
 * exist such that no three consecutive digits of n have a sum greater than 9?
 *
 * @author andrian
 *
 */
public class Problem164 {

	public static void main(String... args) {
		assert solve() == 378158756814587L : "failed";
	}

	private static long[][][] data = new long[10][10][19];

	private static long solve() {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					calculate(k, j, i);
				}
			}
		}

		long s = 0;
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 10 - i; j++) {
				s += data[i][j][18];
			}
		}

		return s;
	}

	private static void calculate(int x1, int x2, int l) {
		if (l == 0)
			data[x1][x2][l] = 1;
		else if (l == 1)
			data[x1][x2][l] = 10 - x1 - x2;
		else {
			for (int i = 0; i < 10 - x1 - x2; i++) {
				data[x1][x2][l] += data[x2][i][l - 1];
			}
		}
	}

	/**
	 * Builds a tree recursively. This approach does not provide a good
	 * performance.
	 * 
	 * @return
	 */
	private static long recursiveSolution() {
		Counter counter = new Counter();
		for (int i = 1; i <= 9; i++) {
			recursive(counter, Integer.toString(i), 7);
		}
		return counter.getValue();
	}

	private static void recursive(Counter counter, String number, int limit) {
		int sum = 0;
		int length = Math.min(3, number.length());
		int s = 0;
		for (int i = 0; i < length; i++) {
			sum += number.charAt(number.length() - i - 1) - 48;
			if (i < 2) {
				s = sum;
			}
		}
		if (sum > 9) {
			return;
		}

		if (number.length() == limit) {
			counter.increment();
			return;
		}

		for (int i = 0; i <= 9 - s; i++) {
			recursive(counter, number + i, limit);
		}
	}
}