package net.projecteuler.java;

import java.util.ArrayList;
import java.util.List;

import net.projecteuler.java.util.Number;

/**
 * The proper divisors of a number are all the divisors excluding the number itself.
 * For example, the proper divisors of 28 are 1, 2, 4, 7, and 14. As the sum of these
 * divisors is equal to 28, we call it a perfect number.</br>
 * Interestingly the sum of the proper divisors of 220 is 284 and the sum of
 * the proper divisors of 284 is 220, forming a chain of two numbers.
 * For this reason, 220 and 284 are called an amicable pair.</br>
 * Perhaps less well known are longer chains. For example, starting with 12496,
 * we form a chain of five numbers:<p>
 * 12496 -> 14288 -> 15472 -> 14536 -> 14264 (-> 12496 -> ...)
 * </p>
 * Since this chain returns to its starting point, it is called an amicable chain.</br>
 * Find the smallest member of the longest amicable chain with no element exceeding one million.
 *
 * @author vbox
 *
 */
public class Problem095 {

	public static void main(String[] args) {
		assert calculate(1000000) == 14316 : "failed";
	}

	private static int calculate(int limit) {

		// Generates primes up to limit
		Number.generatePrimes(limit);

		List<Integer> longest = new ArrayList<Integer>();

		// Stores the divisor sum of each number
		int[][] map = new int[limit][2]; 

		for (int i = 2 ; i < limit; i++) {

			// Primes can be skipped
			if (Number.isPrime(i)) {
				continue;
			}

			boolean passed = false;
			List<Integer> chain = new ArrayList<Integer>();

			search: for (int sum = i; ;) {
				// If the next value in the sequence equals the first one we found a chain
				Integer first = (chain.size() > 0) ? chain.get(0) : null;
				if (first != null && first.compareTo(sum) == 0) {
					passed = true;
					break search;
				}

				// The next value is already in the sequence, but not the first one - it is not a chain
				if (chain.contains(sum)) {
					break search;
				}

				chain.add(sum);

				// If the next value is calculated before we used that
				int next = map[sum - 1][1];
				if (next == 0) {
					next = (int) Number.divisorsSum(Number.getDivisors(sum));
					if (Number.isPrime(next) || next > limit) {
						break search;
					}
					map[sum - 1][1] = next;
				}

				sum = next;
			}

			if (passed && longest.size() < chain.size()) {
				longest = chain;
			}

		}

		int min = Integer.MAX_VALUE;
		for (int e : longest) {
			min = Math.min(min, e);
		}

		return min;
	}
}