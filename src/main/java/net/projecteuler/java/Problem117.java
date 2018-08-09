package net.projecteuler.java;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.Partition;

/**
 * Investigating the number of ways of tiling a row using different-sized tiles.</br>
 * See more {@link Problem116}
 *
 * @author vuser
 *
 */
public class Problem117 {

	public static void main(String ... args) {
		assert solve() == 100808458960497L : "failed";
	}

	private static long solve() {
		BigInteger counter = BigInteger.ZERO;

		List<List<Integer>> list = Partition.partition(50);
		for (List<Integer> partition : list) {
			Map<Integer, Integer> map = checkPartition(partition);
			if (map.isEmpty()) {
				continue;
			}

			long numerator = 0;
			BigInteger denominator = BigInteger.ONE;

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
		if (count != partition.size()) {
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