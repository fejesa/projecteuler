package net.projecteuler.java;

import java.math.BigInteger;

/**
 * 2<sup>15</sup> = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.</br>
 * What is the sum of the digits of the number 2<sup>1000</sup>?
 *
 * @author vuser
 *
 */
public class Problem016 {

	public static void main(String[] args) {
		assert calculate() == 1366 : "failed";
	}

	private static long calculate() {
		String s = BigInteger.valueOf(2).pow(1000).toString();
		int sum = 0;
		for (int j = 0; j < s.length(); j++) {
			sum += s.charAt(j) - 48;
		}
		return sum;
	}
}
