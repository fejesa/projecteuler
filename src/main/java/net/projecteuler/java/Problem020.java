package net.projecteuler.java;

import java.math.BigInteger;

/**
 * n! means n * (n  1) * ... * 3 * 2 * 1</br>
 * For example, 10! = 10 * 9 * ... * 3 * 2 * 1 = 3628800, and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.</br>
 * Find the sum of the digits in the number 100!
 *
 * @author vbox
 *
 */
public class Problem020 {

	public static void main(String[] args) {
		assert calculate(100) == 648 : "failed";
	}

	private static int calculate(int n) {
		BigInteger fact = BigInteger.ONE;
		for (int i = n; i > 1; i--) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}
		String digits = fact.toString();
		int sum = 0;
		for (int i = 0; i < digits.length(); i++) {
			sum += digits.charAt(i) - 48;
		}
		return sum;
	}
}
