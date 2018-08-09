package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * It was proposed by Christian Goldbach that every odd composite number can be
 * written as the sum of a prime and twice a square.</br>
 * 9 = 7 + 2 * 1<sup>2</sup></br>
 * 15 = 7 + 2 * 2<sup>2</sup></br>
 * 21 = 3 + 2 * 3<sup>2</sup></br>
 * 25 = 7 + 2 * 3<sup>2</sup></br>
 * 27 = 19 + 2 * 2<sup>2</sup></br>
 * 33 = 31 + 2 * 1<sup>2</sup></br>
 * It turns out that the conjecture was false.</br>
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 *
 * @author vuser
 *
 */
public class Problem046 {

	public static void main(String[] args) {
		assert calculate() == 5777L : "failed";
	}

	private static long calculate() {
		long exception = 0;
		search: for (long i = 3; i < 10000 ; i = i + 2) { // Only the odd numbers are checked
			if (!Number.isPrime(i)) { // Means composite
				boolean passed = false;
				check: for (long j = i - 1; j > 2; j--) {
					if (Number.isPrime(j)) {
						double d = Math.sqrt((i - j) / 2);
						if (d == Math.floor(d)) {
							passed = true;
							break check;
						}
					}
				}
				if (!passed) {
					exception = i;
					break search;
				}
			}
		}
		return exception;
	}
}
