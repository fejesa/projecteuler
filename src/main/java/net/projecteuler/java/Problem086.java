package net.projecteuler.java;

/**
 * Exploring the shortest path from one corner of a cuboid to another.
 *
 * @author vuser
 *
 */
public class Problem086 {

	public static void main(String... args) {
		assert solve() == 1818 : "failed";
	}

	private static int solve() {
		int counter = 0;
		int m = 2;
		while (counter < 1000000) {
			++m;
			for (int i = 2; i < 2 * m; i++) {
				double d = Math.sqrt(m * m + i * i);
				if (d == Math.floor(d)) {
					counter += Math.min(i, m + 1) - (i + 1) / 2;
				}
			}
		}

		return m;
	}
}