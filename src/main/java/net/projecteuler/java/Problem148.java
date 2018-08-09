package net.projecteuler.java;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Problem148 {

	public static void main(String ... args) {
		System.out.println(solve());
	}

	private static long solve() {
	    long limit = 5000;
		Map<Long, Integer> powers = getPowers(limit);
		System.out.println(powers);
		int count = 0;
		for (int n = 1; n < limit; n++) {
			long npower = getPower(n, powers);
			long rc = 0;
			long middle = 0;
			for (int k = 1; k <= n / 2; k++) {
				long kpower = getPower(k, powers);
				long nkpower = getPower(n - k, powers);
				if (npower - (kpower + nkpower) > 0) {
					++rc;
					if (n % 2 == 0 && k == n / 2) {
	                    middle = 1;
	                }
				}
			}
			System.out.println(n + ":" + (rc * 2 - middle));
			if (rc * 2 - middle == 0) {
			    System.out.println("-----------" + count);
			}
			count += rc * 2 - middle;
		}
		return count;
	}

	private static long getPower(long n, Map<Long, Integer> powers) {
		long p = (long) Math.floor(n / 7);
		long plus = 0;
		for (Map.Entry<Long, Integer> entry : powers.entrySet()) {
		    plus += (long) Math.floor(n / entry.getKey());
		}
		return p + plus;
	}

	private static Map<Long, Integer> getPowers(long limit) {
		NavigableMap<Long, Integer> map = new TreeMap<Long, Integer>();
		for (int i = 2; ; i++) {
			long pow = (long) Math.pow(7, i);
			if (pow > limit) {
				break;
			}
			//map.put(pow, i * (i + 1) / 2 - 1);
			map.put(pow, i);
		}

		//return map.descendingMap();
		return map;
	}
}
