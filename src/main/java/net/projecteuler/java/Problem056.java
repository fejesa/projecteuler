package net.projecteuler.java;

import java.math.BigInteger;

import net.projecteuler.java.util.Number;

/**
 * A googol (10<sup>100</sup>) is a massive number: one followed by one-hundred zeros; 100<sup>100</sup>
 * is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the
 * digits in each number is only 1.</br>
 * Considering natural numbers of the form, a<sup>b</sup>, where a, b < 100, what is the maximum digital sum?
 *
 * @author vbox
 *
 */
public class Problem056 {

	public static void main(String[] args) {
		assert calculate() == 972 : "failed";
	}

	private static int calculate() {
		int max = 0;
		for (int a = 2; a < 100; a++) {
			BigInteger ba = BigInteger.valueOf(a);
			for (int b = 1; b < 100; b++) {
				ba = ba.multiply(BigInteger.valueOf(a));
				int[] digits = Number.getDigits(ba);
				int sum = 0;
				for (int i = 0; i < digits.length; i++) {
					sum += digits[i];
				}
				max = Math.max(max, sum);
			}
		}
		return max;
	}
}