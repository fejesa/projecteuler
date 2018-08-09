package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * Consider the divisors of 30: 1,2,3,5,6,10,15,30.</br> It can be seen that for
 * every divisor d of 30, d+30/d is prime.
 * <p>
 * Find the sum of all positive integers n not exceeding 100 000 000 such that
 * for every divisor d of n, d+n/d is prime.
 * 
 * @author vuser
 * 
 */
public class Problem357 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		assert calculate() == 1739023853137L : "failed";
	}

	private static long calculate() {
		int limit = 100000000;
		Number.generatePrimes(limit);

		long sum = 0;
		for (int n = 0; n <= limit; n++) {
			if (isPrimeGenerating(n))
				sum += n;
		}
		return sum;
	}

	private static boolean isPrimeGenerating(int n) {
		for (int i = 1, end = Number.sqrt(n); i <= end; i++) {
			if (n % i == 0) {
				if (!Number.isPrime(i + n / i))
					return false;
			}
		}
		return true;
	}
}