package net.projecteuler.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.projecteuler.java.util.Number;

/**
 * Let d(n) be defined as the sum of proper divisors of n
 * (numbers less than n which divide evenly into n).</br>
 * If d(a) = b and d(b) = a, where a <> b, then a and b are an amicable pair
 * and each of a and b are called amicable numbers.</br>
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110;
 * therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.</br>
 * Evaluate the sum of all the amicable numbers under 10000.
 *
 * @author vuser
 *
 */
public class Problem021 {

	public static void main(String[] args) {
		assert calculate(10000) == 31626L : "failed";
	}

	private static long calculate(long limit) {
		long sum = 0;
		Map<Long, Long> map = new HashMap<Long, Long>();
		for (long i = 1; i < limit; i++) {
			Set<Long> divisors = Number.getDivisors(i);
			long s = Number.divisorsSum(divisors);
			if (s > 0 && s != i) {
				map.put(i, s);
			}
		}

		Set<Long> amicables = new HashSet<Long>();
		for (Map.Entry<Long, Long> entry : map.entrySet()) {
			Long pair = map.get(entry.getValue());
			if (pair != null && entry.getKey().compareTo(pair) == 0) {
				amicables.add(entry.getKey());
			}
		}

		for (Long amicable : amicables) {
			sum += amicable;
		}
		return sum;
	}

	
}