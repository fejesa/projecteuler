package net.projecteuler.java;

import java.math.BigInteger;

/**
 * The smallest positive integer n for which the numbers n<sup>2</sup>+1,
 * n<sup>2</sup>+3, n<sup>2</sup>+7, n<sup>2</sup>+9, n<sup>2</sup>+13,
 * and n<sup>2</sup>+27 are consecutive primes is 10.
 * The sum of all such integers n below one-million is 1242490.
 * What is the sum of all such integers n below 150 million?
 *
 * @author vuser
 */
public class Problem146 {

	public static void main(String... args) {
		assert solve() == 676333270L : "failed";
	}

	private static long solve() {
		long sum = 0;
		// Analysis:
		// n = 0 (mod 2, 5) so number is multiple of 10
		// if n^2 = 0 (mod 3, 7, 13) then n^2 + (3, 7, 13) is composite so number is not multiple of 3, 7, 13
		// The number n must be a multiple of 10, but not of 3, 7, 13
		for (long i = 10; i < 150000000; i = i + 10) {
			if (i % 3 == 0 || i % 7 == 0 || i % 13 == 0) {
				continue;
			}
			long square = i * i;

			int certainty = 1;
			if (BigInteger.valueOf(square + 1).isProbablePrime(certainty)
			        && BigInteger.valueOf(square + 3).isProbablePrime(certainty)
			        && BigInteger.valueOf(square + 7).isProbablePrime(certainty)
			        && BigInteger.valueOf(square + 9).isProbablePrime(certainty)
			        && BigInteger.valueOf(square + 13).isProbablePrime(certainty)
			        && BigInteger.valueOf(square + 27).isProbablePrime(certainty)) {

				// Double checking
				if (check(square)) {
					sum += i;
				}
			}
		}

		return sum;
	}

	private static boolean check(long square) {
		int count = 0;
		// The primes must be consecutive that means between n^2 + 1 and n^2 + 27
		// only six primes should be there. So iterate the range and checks them.
		for (int i = 1; i < 28; i = i + 2) {
			if (BigInteger.valueOf(square + i).isProbablePrime(1)) {
				++count;
			}
		}
		return count == 6;
	}
}