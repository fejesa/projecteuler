package net.projecteuler.java;

import java.math.BigInteger;

/**
 * There are exactly ten ways of selecting three from five, 12345:</p>
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345</p>
 * In combinatorics, we use the notation, <sup>5</sup>C<sub>3</sub> = 10.</br>
 * In general,<p>
 * <sup>n</sup>C<sub>r</sub> = n! / (r! * (n - r)!)</p>
 * ,where r <= n, n! = n * (n - 1)...3 * 2* 1, and 0! = 1.</br>
 * It is not until n = 23, that a value exceeds one-million: <sup>23</sup>C<sub>10</sub> = 1144066.</br>
 * How many, not necessarily distinct, values of  <sup>n</sup>C<sub>r</sub>, for 1 <= n <= 100, are greater than one-million?
 * 
 * @author vbox
 *
 */
public class Problem053 {

	public static void main(String[] args) {
		assert calculate() == 4075 : "failed";
	}

	private static int calculate() {
		int counter = 0;
		BigInteger threshold = BigInteger.valueOf(1000000);
		for (int i = 2; i <= 100; i++) {
			BigInteger n = factorial(i);
			for (int j = 1; j < i; j++) {
				BigInteger r = factorial(j);
				BigInteger nr = factorial(i - j);
				BigInteger c = n.divide(r).divide(nr);
				if (c.compareTo(threshold) == 1) {
					counter++;
				}
			}
			
		}

		return counter;
	}

	private static BigInteger factorial(int number) {
		BigInteger b = BigInteger.ONE;
		for (int i = 1; i <= number; i++) {
			b = b.multiply(BigInteger.valueOf(i));
		}
		return b;
	}
}