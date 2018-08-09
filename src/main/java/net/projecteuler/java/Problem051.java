package net.projecteuler.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.CombinationGenerator;
import net.projecteuler.java.util.Number;

/**
 * By replacing the 1<sup>st</sup> digit of *3, it turns out that six of the nine possible values:<p>
 * 13, 23, 43, 53, 73, and 83, are all prime.</p>
 * By replacing the 3<sup>rd</sup> and 4<sup>th</sup> digits of 56**3 with the same digit,
 * this 5-digit number is the first example having seven primes among the ten generated numbers,
 * yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993.</br>
 * Consequently 56003, being the first member of this family, is the smallest prime with this property.</br>
 * Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits)
 * with the same digit, is part of an eight prime value family.
 *
 * @author vuser
 *
 */
public class Problem051 {

	public static void main(String[] args) {
		assert calculate() == 121313L : "failed";
	}

	private static long calculate() {
		long r = 0;

		// Contains the masks as key and the list of the numbers can be matched to the mask.
		Map<String, List<Long>> map = new HashMap<String, List<Long>>();
		search: for (long n = 56000; ; n++) {
			if (Number.isPrime(n)) { // Only the prime numbers are instrumented 
				List<String> masks = getMasks(n);
				for (String mask : masks) {

					List<Long> numbers = map.get(mask);
					if (numbers == null) {
						numbers = new ArrayList<Long>();
						map.put(mask, numbers);
					}

					numbers.add(n);

					if (numbers.size() > 7) {
						//System.out.println(mask + ":" + numbers);
						r = numbers.get(0); // The smallest prime in the list
						break search;
					}
				}
			}
		}

		return r;
	}

	/**
	 * Produces the list of the masks can be applicable to the given number.
	 * @param n The number.
	 * @return List of masks.
	 */
	private static List<String> getMasks(long n) {

		String number = Long.toString(n);

		List<String> masks = new ArrayList<String>();

		Map<Integer, List<Integer>> map = map(n);
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			List<List<Integer>> combinations = getCombinations(entry.getValue().size());
			for (List<Integer> c : combinations) {
				masks.add(mask(number, entry.getValue(), c));
			}
		}

		return masks;
	}

	/**
	 * Creates the mask of the number.
	 * @param number The original number.
	 * @param positions List of the position that can be masked.
	 * @param combination List of the index of the positions that must be masked.
	 * @return The masked number.
	 */
	private static String mask(String number, List<Integer> positions, List<Integer> combination) {
		List<Integer> filtered = new ArrayList<Integer>();
		for (Integer index : combination) {
			filtered.add(positions.get(index));
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < number.length(); i++) {

			if (filtered.contains(i)) {
				builder.append('*');
			} else {
				builder.append(number.charAt(i));
			}
		}

		return builder.toString();
	}

	/**
	 * Produces all of the combination of n from 2 to n.
	 * @param n The number (number of the given digits).
	 * @return List of all combinations.
	 */
	private static List<List<Integer>> getCombinations(int n) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();

		for (int r = 2; r <= n; r++) {
			CombinationGenerator combinations = new CombinationGenerator(n, r);
			while (combinations.hasMore()) {
				int[] next = combinations.getNext();
				List<Integer> l = new ArrayList<Integer>();
				for (int i = 0; i < next.length; i++) {
					l.add(next[i]);
				}
				list.add(l);
			}
		}

		return list;
	}

	/**
	 * Produces the map of the digits of the given number that occur more than 1.
	 * @param n The number.
	 * @return Map of the digits and their occurrence.
	 */
	private static Map<Integer, List<Integer>> map(long n) {
		String number = Long.toString(n);

		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i <= 9; i++) {
			char c = (char)(i + 48);
			List<Integer> list = new ArrayList<Integer>();
			for (int j = 0; j < number.length(); j++) {
				if (c == number.charAt(j)) {
					list.add(j);
				}
			}
			if (list.size() > 1) {
				map.put(i, list);
			}
		}
		return map;
	}
}