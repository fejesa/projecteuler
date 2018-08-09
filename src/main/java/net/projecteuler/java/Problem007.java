package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10001<sup>st</sup> prime number?
 * @author vuser
 *
 */
public class Problem007 {

	public static void main(String[] args) {
		assert calculate() == 104743L : "failed";
	}

	static long calculate() {
		int index = 1;
		long number = 3;
		while (index < 101) {
			if (Number.isPrime(number)) {
				++index;
			}
			number += 2;
		}
		return number - 2;
	}
}