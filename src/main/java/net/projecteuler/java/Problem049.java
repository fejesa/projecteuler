package net.projecteuler.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.projecteuler.java.util.Number;

/**
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330,
 * is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes,
 * exhibiting this property, but there is one other 4-digit increasing sequence.</br>
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 *
 * @author vbox
 *
 */
public class Problem049 {

	public static void main(String[] args) {
		assert calculate().equals("296962999629") : "failed";
	}

	private static String calculate() {
		// Primes are stored as digits/prime key/value pairs
		Map<Set<Integer>, List<Integer>> primes = new HashMap<Set<Integer>, List<Integer>>();
		for (int i = 1000; i < 10000; i++) {
			if (Number.isPrime(i)) {
				Set<Integer> digitSet = Number.getDigitSet(i);
				List<Integer> perms = primes.get(digitSet);
				if (perms == null) {
					perms = new ArrayList<Integer>();
					primes.put(digitSet, perms);
				}
				perms.add(i);
			}
		}

		Set<Integer> list = null;
		search: for (Map.Entry<Set<Integer>, List<Integer>> entry : primes.entrySet()) {
			if (!entry.getValue().contains(1487)) { // Excludes the defined one
				Collections.sort(entry.getValue());
				list = new TreeSet<Integer>();
				for (int i = 0; i < entry.getValue().size(); i++) {
					for (int j = i + 1; j < entry.getValue().size(); j++) {
						if (entry.getValue().get(j) - entry.getValue().get(i) == 3330) { // Diff is defined in the problem definition
							list.add(entry.getValue().get(i));
							list.add(entry.getValue().get(j));
						}
						if (list.size() == 3) {
							break search;
						}
					}
				}
			}
		}

		StringBuilder builder = new StringBuilder();
		for (Integer i : list) {
			builder.append(i);
		}
		return builder.toString();
	}
}