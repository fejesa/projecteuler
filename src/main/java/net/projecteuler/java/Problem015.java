package net.projecteuler.java;

import java.math.BigInteger;

/**
 * Starting in the top left corner in a 20 by 20 grid, how many routes are there to the bottom right corner?
 *
 * @author vuser
 *
 */
public class Problem015 {

	public static void main(String[] args) {
		assert calculate() == 137846528820L : "failed";
	}

	/**
	 * Solution is based on permutation with repetition:</br>
	 * P<sub>n</sub><sup>(n1, n2)</sup> = n!/(n1!n2!)
	 * @return
	 */
	private static long calculate() {
		BigInteger result = BigInteger.ONE;
		for (int i = 40; i > 20; i--) {
			result = result.multiply(BigInteger.valueOf(i));
			result = result.divide(BigInteger.valueOf(40 - i + 1));
		}

		return result.longValue();
	}
}
