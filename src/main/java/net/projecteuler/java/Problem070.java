package net.projecteuler.java;

import java.util.List;

import net.projecteuler.java.util.Number;
import net.projecteuler.java.util.PrimeGenerator;

/**
 * Euler's Totient function, φ(n) [sometimes called the phi function], is used to
 * determine the number of positive numbers less than or equal to n which are relatively
 * prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and
 * relatively prime to nine, φ(9)=6.</br>
 * The number 1 is considered to be relatively prime to every positive number, so φ(1)=1.</br>
 * Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.</br>
 * Find the value of n, 1 < n < 10<sup>7</sup>, for which φ(n) is a permutation of n
 * and the ratio n/φ(n) produces a minimum.
 *
 * @author vuser
 *
 */
public class Problem070 {

	public static void main(String[] args) {
		assert calculate(10000000) == 8319823L : "failed";
	}

	/** 
	 * Accord­ing to <a>href="http://mathworld.wolfram.com/TotientFunction.html">MathWorld</a>
	 * the totient function of n equals n – 1, if n is a prime, under normal circumstances
	 * n divided by totient n is minimal if n is a prime, but then they wouldn't be permutations
	 * of each other as n cannot be the permutation of n – 1.</br>
	 * A semi-prime on the other hand, n, where p and q are primes and n = p * q,
	 * its totient func­tion equals (p – 1) * (q – 1).</br>
	 * The two prime numbers should also be near sqrt(10<sup>7</sup>) so that the totient
	 * function will be maximized.
	 * @param limit
	 * @return
	 */
	private static long calculate(int limit) {
		long number = 0;

		double min = limit;

		PrimeGenerator primeGenerator = new PrimeGenerator(5000); // The two prime numbers should also be near sqrt(1e7)
		List<Integer> primes = primeGenerator.getPrimeList();

		// Creates semiprimes based on the prime list
		for (int i = 0; i < primes.size(); i++) {
			int p = primes.get(i);

			for (int j = i; j < primes.size(); j++) {
				int q = primes.get(j);

				long n = p * q;
				if (n <= limit) { // Semiprime is limited
					long totient = (p - 1) * (q - 1);
					if (Number.isPermutation(n, totient)) {
						double quotient = (double) n / totient;

						if (min > quotient) {
							min = quotient;
							number = n;
						}
					}
				}
			}
		}

		return number;
	}
}