package net.projecteuler.java;

import java.math.BigInteger;

/**
 * The 5-digit number, 16807=7<sup>5</sup>, is also a fifth power.
 * Similarly, the 9-digit number, 134217728=8<sup>9</sup>, is a ninth power.</br>
 *
 * How many n-digit positive integers exist which are also an nth power?
 * @author vuser
 *
 */
public class Problem063 {

	public static void main(String[] args) {
		assert calculate() == 49 : "failed";
	}

	private static int calculate() {
		int result = 0;

		int limit = 30;
		for (int a = 1; a < limit; a++) {
			for (int b = 1; b < limit; b++) {
				BigInteger i = BigInteger.valueOf(a).pow(b);
				double length = i.toString().length();
				if (b - 1 < length && length <= b) {
					++result;
				}
			}
		}

		return result;
	}
}