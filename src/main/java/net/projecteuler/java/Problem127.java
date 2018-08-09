package net.projecteuler.java;

import java.util.HashSet;
import java.util.Set;

import net.projecteuler.java.util.Number;

/**
 * The radical of n, rad(n), is the product of distinct prime factors of n.
 * For example, 504 = 23 * 32 * 7, so rad(504) = 2 * 3 * 7 = 42.</br>
 * We shall define the triplet of positive integers (a, b, c) to be an abc-hit if:
 * <ol>
 * <li>GCD(a, b) = GCD(a, c) = GCD(b, c) = 1</li>
 * <li>a < b</li>
 * <li>a + b = c</li>
 * <li>rad(abc) < c</li>
 * </ol>
 * For example, (5, 27, 32) is an abc-hit, because:
 * <ol>
 * <li>GCD(5, 27) = GCD(5, 32) = GCD(27, 32) = 1</li>
 * <li>5 < 27</li>
 * <li>5 + 27 = 32</li>
 * <li>rad(4320) = 30 < 32</li>
 * </ol>
 * It turns out that abc-hits are quite rare and there are only thirty-one
 * abc-hits for c < 1000, with sum(c) = 12523. Find sum(c) for c < 120000.
 *
 * @author vuser
 *
 */
public class Problem127 {

	public static void main(String... args) {
		assert solve() == 18407904 : "failed";
	}

	private static int limit = 120000;
	// Caching the distinct prime factor of numbers up to the limit
	private static Long[][] cache = new Long[limit][];

	private static long solve() {

		int limit = 120000;
		Number.generatePrimes(limit); // generate primes because of factorization
		// Build up the cache
		for (int i = 0; i < limit; i++) {
			Set<Long> list = new HashSet<Long>(Number.factor(i));
			cache[i] = list.toArray(new Long[list.size()]);
		}

		long sum = 0;
		for (int c = 3; c < limit; c++) {
			boolean even = c % 2 == 0; // Just one even number can be in the triplet
			loop: for (int a = 1; 2 * a < c; a = even ? a + 2 : a + 1) {
				// If there is a common prime common gdc <> 1
				if (hasIntersect(cache[c], cache[a]) || hasIntersect(cache[c], cache[c - a])) {
					continue loop;
				}

				if (c > rad(a, c - a, c)) {
					sum += c;
				}
			}
		}
		return sum;
	}

	private static boolean hasIntersect(Long[] set1, Long[] set2) {
		for (int i = 0; i < set1.length; i++) {
			for (int j = 0; j < set2.length; j++) {
				if (set1[i].compareTo(set2[j]) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	private static long rad(int a, int b, int c) {
		Set<Long> primes = new HashSet<Long>();
		update(a, primes);
		update(b, primes);
		update(c, primes);

		long rad = 1;
		for (long p : primes) {
			rad *= p;
		}
		return rad;
	}

	private static void update(int n, Set<Long> primes) {
		Long[] array = cache[n];
		for (int i = 0; i < array.length; i++) {
			primes.add(array[i]);
		}
	}
}