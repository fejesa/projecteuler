package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * The number 3797 has an interesting property. Being prime itself, it is possible to
 * continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7.
 * Similarly we can work from right to left: 3797, 379, 37, and 3.</br>
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.</br>
 * <b>NOTE</b>: 2, 3, 5, and 7 are not considered to be truncatable primes.
 *
 * @author vbox
 *
 */
public class Problem037 {

	public static void main(String[] args) {
		assert calculate() == 748317L : "failed";
	}

	private static long calculate() {
		long sum = 0;
		int counter = 0;
		for (long i = 10; counter < 11; i++) {
			if (Number.isPrime(i) && isTruncatablePrime(i)) {
				sum += i;
				counter++;
			}
		}
		return sum;
	}

	private static boolean isTruncatablePrime(long number) {
		int digits = (int) Math.log10(number);
		long n = number;
		// truncates right
		for (int i = 0; i < digits; i++) {
			 n /= 10;
			 if (!Number.isPrime(n)) {
				 return false;
			 }
		}

		// truncates left
		String s = Long.toString(number);
		for (int i = 1; i < digits + 1; i++) {
			if(!Number.isPrime(Long.valueOf(s.substring(i)))) {
				return false;
			}
		}
		return true;
	}
}