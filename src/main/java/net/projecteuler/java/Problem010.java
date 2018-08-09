package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 *
 * @author vuser
 *
 */
public class Problem010 {

	public static void main(String[] args) {
		assert calculate(2000000) == 142913828922L : "failed";
	}

	private static long calculate(long limit) {
		long sum = 0;
		for (long i = 2; i <= limit; i++) {
			if (Number.isPrime(i)) {
				sum += i;
			}
		}
		return sum;
	}
}