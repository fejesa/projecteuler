package net.projecteuler.java;

import java.util.List;

import net.projecteuler.java.util.PrimeGenerator;

/**
 * A composite is a number containing at least two prime factors.
 * For example, 15 = 3 * 5; 9 = 3 * 3; 12 = 2 * 2 * 3.</br>
 * There are ten composites below thirty containing precisely two,
 * not necessarily distinct, prime factors: 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.</br>
 * How many composite integers, n < 10<sup>8</sup>, have precisely two,
 * not necessarily distinct, prime factors?
 *
 * @author vuser
 *
 */
public class Problem187 {

	public static void main(String... args) {
		assert solve() == 17427258 : "failed";
	}

	private static long solve() {
		long limit = 100000000;
		// We just generate the primes up to limit / 2
		PrimeGenerator sieve = new PrimeGenerator((int) (limit / 2));

		int[] array = toPrimitive(sieve.getPrimeList());

		int counter = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				// Cast must be required because of overflow
				if ((long) array[i] * array[j] > limit) {
					break;
				}
				++counter;
			}
		}

		return counter;
	}

	private static int[] toPrimitive(List<Integer> list) {
		int[] array = new int[list.size()];
		int index = 0;
		for (Integer i : list) {
			array[index++] = i.intValue();
		}
		return array;
	}
}