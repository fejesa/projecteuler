package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * We shall say that an n-digit number is pandigital if it makes use of
 * all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.
 * What is the largest n-digit pandigital prime that exists?
 *
 * @author vuser
 *
 */
public class Problem041 {

	public static void main(String[] args) {
		assert calculate() == 7652413 : "failed";
	}

	private static long calculate() {
		int digits = 0;
		long maxPandigitalPrime = 0;
		for (long number = 7654321; number >= 1234567; number = number - 2) {
			int d = Number.getNDigitPandigital(number);
			if (d > 0 && Number.isPrime(number)) {
				if (d >= digits && number > maxPandigitalPrime) {
					maxPandigitalPrime = number;
					digits = d;
				}
			}
		}
		return maxPandigitalPrime;
	}
}