package net.projecteuler.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.Number;

/**
 * Determining the k<sup>th</sup> element of the sorted radical function.
 *
 * @author vuser
 *
 */
public class Problem124 {

	public static void main(String ... args) {
		assert calculate() == 21417 : "failed";
	}

	private static int calculate() {
		List<Map<Long, Integer>> list = new ArrayList<Map<Long,Integer>>();

		// Insert one
		Map<Long, Integer> one = new HashMap<Long, Integer>();
		one.put(1L, 1);
		list.add(one);

		// Prime factorizations
		for (long i = 2; i <= 100000; i++) {
			Map<Long, Integer> primeExponent = Number.primeExponent(i);
			list.add(primeExponent);
		}

		// Comparator used by sort
		Comparator<Map<Long, Integer>> comparator = new Comparator<Map<Long,Integer>>() {

			/**
			 * Calculates the radicals of the two numbers based on the primes exponents.
			 * If the two radicals are equal the two numbers are compared.
			 */
			@Override
			public int compare(Map<Long, Integer> pe1, Map<Long, Integer> pe2) {

				int n1Rad = 1;
				int n2Rad = 1;

				int n1 = 1;
				int n2 = 1;

				for (Map.Entry<Long, Integer> e : pe1.entrySet()) {
					n1Rad *= e.getKey();
					n1 *= Math.pow(e.getKey(), e.getValue());
				}
				for (Map.Entry<Long, Integer> e : pe2.entrySet()) {
					n2Rad *= e.getKey();
					n2 *= Math.pow(e.getKey(), e.getValue());
				}
				if (n1Rad == n2Rad) {
					return n1 - n2;
				}

				return n1Rad - n2Rad;
			}
		};

		Collections.sort(list, comparator);

		int n = 1;
		for (Map.Entry<Long, Integer> e : list.get(9999).entrySet()) {
			n *= Math.pow(e.getKey(), e.getValue());
		}

		return n;
	}
}