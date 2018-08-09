package net.projecteuler.java;

/**
 * Consider the fraction, n/d, where n and d are positive integers.
 * If n < d and HCF(n, d) = 1, it is called a reduced proper fraction.</br>
 * If we list the set of reduced proper fractions for d <= 8 in ascending order of size, we get:
 * <p>
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * </p>
 * It can be seen that 2/5 is the fraction immediately to the left of 3/7.</br>
 * By listing the set of reduced proper fractions for d <= 1,000,000 in ascending order
 * of size, find the numerator of the fraction immediately to the left of 3/7.
 *
 * @author vuser
 *
 */
public class Problem071 {

	public static void main(String[] args) {
		assert calculate() == 428570 : "failed";
	}

	/**
	 * We has to find the mediant between the two boundaries.
	 * @see <a href="http://en.wikipedia.org/wiki/Mediant_(mathematics)">Mediant</a>
	 * @return
	 */
	private static int calculate() {
		int a = 2;
		int b = 3;
		int c = 5;
		int d = 7;
		int number = a;
		while (c + d <= 1000000) { // Loop while the denominator exceeds the limit
			number += b;
			c += d;
		}
		return number;
	}
}