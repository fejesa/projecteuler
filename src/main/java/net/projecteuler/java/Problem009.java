package net.projecteuler.java;

/**
 * A Pythagorean triplet is a set of three natural numbers, a b c, for which,
 * a<sup>2</sup> + b<sup>2</sup> = c<sup>2</sup></br>
 * For example, 3<sup>2</sup> + 4<sup>2</sup> = 9 + 16 = 25 = 5<sup>2</sup>.</br>
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product <var>abc</var>.
 *
 * @author vuser
 *
 */
public class Problem009 {

	public static void main(String[] args) {
		assert calculate() == 31875000 : "failed";
	}

	/**
	 * Simpler form is: 500000 - 1000a - 1000b + 2ab = 0.
	 * @return
	 */
	private static long calculate() {
		int max = 999;
		int a = 0;
		int b = 1;
		boolean solved = false;
		for (; !solved && a <= max;) {
			++a;
			for (b = 1; !solved && b <= max;) {
				if (500000 - 1000 * a - 1000 * b + a * b == 0) {
					solved = true;
				} else {
					++b;
				}
			}
		}

		return a * b * (1000 - a - b);
	}
}
