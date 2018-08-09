package net.projecteuler.java;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.Partition;

/**
 * Investigating the number of ways of replacing
 * square tiles with one of three colored tiles.
 *
 * @author vuser
 *
 */
public class Problem116 {

	public static void main(String ... args) {
		assert solve() == 20492570929L : "failed";
	}

	/**
	 * Solution is based on the partition of a positive integer n.</br>
	 * I.e. 5 can be produced as 2 + 1 + 1 + 1. Because of a number can be used
	 * more than one in a given partition we have to use permutation with repetition of that number.
	 */
	private static long solve() {
		BigInteger counter = BigInteger.ONE;

		// Produces all partition of the given number
		List<List<Integer>> list = Partition.partition(7);
		for (List<Integer> partition : list) {
			Map<Integer, Integer> map = checkPartition(partition);
			if (map.isEmpty()) {
				continue;
			}

			long numerator = 0;
			BigInteger denominator = BigInteger.ONE;

			// Calculates the permutation with repetition of the numbers
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				numerator += entry.getValue();
				denominator = denominator.multiply(factor(entry.getValue()));
			}
			counter = counter.add(factor(numerator).divide(denominator));
		}

		return counter.longValue();
	}

	private static Map<Integer, Integer> checkPartition(List<Integer> partition) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : partition) {
			if (i >= 1 && i < 5) {
				Integer value = map.get(i);
				if (value == null) {
					map.put(i, 1);
				} else {
					map.put(i, value + 1);
				}
				++count;
			}
		}
		// List must contain 1 and 2 or 3 or 4
		if (!map.containsKey(1) || count != partition.size() || map.size() != 2) {
			return Collections.emptyMap();
		}

		return map;
	}

	private static BigInteger factor(long n) {
		BigInteger factor = BigInteger.ONE;
		for (long i = 2; i <= n; i++) {
			factor = factor.multiply(BigInteger.valueOf(i));
		}
		return factor;
	}
}