package net.projecteuler.java;

import java.math.BigInteger;

import net.projecteuler.java.util.PrimeGenerator;

/**
 * Let p<sub>n</sub> be the n<i>th</i> prime: 2, 3, 5, 7, 11, ...,
 * and let r be the remainder when (p<sub>n</sub> - 1)<sup>n</sup> + (p<sub>n</sub> + 1)<sup>n</sup>
 * is divided by p<sub>n</sub><sup>2</sup>.</br>
 * For example, when n = 3, p<sub>3</sub> = 5, and 43 + 63 = 280 => 5 mod 25.</br>
 * The least value of n for which the remainder first exceeds 10<sup>9</sup> is 7037.</br>
 * Find the least value of n for which the remainder first exceeds 10<sup>10</sup>.
 *
 * @author vuser
 *
 */
public class Problem123 {

	public static void main(String ... args) {
		assert solve() == 21035 : "failed";
	}

	private static int solve() {
		// Generates primes
		PrimeGenerator sieve = new PrimeGenerator(10000000);
		// We start the process from the 21000th prime - approximation
		int index = 21000;
		BigInteger limit = BigInteger.valueOf(10000000000L);
		for (int i = sieve.getPrimeByIndex(index); ; i++) {
			if (sieve.isPrime(i)) {
				// If the index is even the odd equals 2 - we can skip this calculation
				if (index % 2 != 0) {
					BigInteger pn1 = BigInteger.valueOf(i - 1).pow(index);
					BigInteger pn2 = BigInteger.valueOf(i + 1).pow(index);
					BigInteger sum = pn1.add(pn2);
					BigInteger p2 = BigInteger.valueOf(i).pow(2);
					BigInteger[] b = sum.divideAndRemainder(p2);
					if (b[1].compareTo(limit) > 0) {
						break;
					}
				}
				++index;
			}
		}
		System.out.println(sieve.getPrimeByIndex(index));
		return index;
	}
}
