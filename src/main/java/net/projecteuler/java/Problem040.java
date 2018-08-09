package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * An irrational decimal fraction is created by concatenating the positive integers:</br>
 * 0.12345678910<b>1</b>112131415161718192021...</br>
 * It can be seen that the 12<sup>th</sup> digit of the fractional part is 1.
 * If d<sub>n</sub> represents the n<sup>th</sup> digit of the fractional part,
 * find the value of the following expression.</br>
 * d<sub>1</sub> x d<sub>10</sub> x d<sub>100</sub> x d<sub>1000</sub> x d<sub>10000</sub> x d<sub>100000</sub> x d<sub>1000000</sub>
 *
 * @author vuser
 *
 */
public class Problem040 {

	public static void main(String[] args) {
		assert calculate() == 210 : "failed";
	}

	private static int calculate() {
		int product = 1;
		int nextIndex = 1;
		int length = 0;
		for (int i = 1; nextIndex <= 1000000; i++) {
			int d = (int) Math.log10(i) + 1;
			if (length + d >= nextIndex) {
				int[] digits = Number.getDigits(i);
				product *= digits[length + d - nextIndex];
				nextIndex *= 10;
			}
			length += d;
		}
		return product;
	}
}