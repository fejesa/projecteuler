package net.projecteuler.java;

import java.util.Arrays;

/**
 * Find the number of integers 1 < n < 10<sup>7</sup>, for which n and n + 1
 * have the same number of positive divisors.</br>
 * For example, 14 has the positive divisors 1, 2, 7, 14 while 15 has 1, 3, 5, 15.
 *
 * @author vuser
 *
 */
public class Problem179 {

	public static void main(String... args) {
		assert solve() == 986262 : "failed";
	}

	private static int solve() {
		final int limit = 10000000;
		int[] sieve = new int[limit + 1];
		Arrays.fill(sieve, 2); // don't worry about elements 0 & 1

		for (int i = 2; i <= (int) Math.sqrt(limit); i++) {

			int j = i * i;
			sieve[j]--;

			while (j <= limit) {
				sieve[j] += 2;
				j += i;
			}
		}

		int answer = 0;

		for (int i = 2; i < limit; i++) {
			if (sieve[i] == sieve[i + 1])
				answer++;
		}
		return answer;
	}
}