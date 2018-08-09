package net.projecteuler.java;

import java.util.ArrayList;
import java.util.List;

import net.projecteuler.java.util.Number;

/**
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and
 * concatenating them in any order the result will always be prime.
 * For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes,
 * 792, represents the lowest sum for a set of four primes with this property.</b>
 * Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 *
 * @author vuser
 *
 */
public class Problem060 {

	public static void main(String[] args) {
		assert calculate() == 26033 : "failed";
	}

	private static long calculate() {

		List<List<Integer>> combinedList = new ArrayList<List<Integer>>();

		// Let's set the max to 10000
		for (int n = 3; n < 10000; n = n + 2) {

			if (Number.isPrime(n)) {

				// Contains the (original) passed combined list - add to the combined list at end of the iteration 
				List<List<Integer>> passedList = new ArrayList<List<Integer>>();

				for (List<Integer> combined : combinedList) {
					boolean passed = true;
					check: for (Integer l : combined) {
						if (!isConstructPrime(n, l)) {
							passed = false;
							break check;
						}
					}

					if (passed) {
						// We clone the passed list because of another combination with different (next) prime
						List<Integer> clone = new ArrayList<Integer>();
						clone.addAll(combined);
						passedList.add(clone);

						// Add the given prime to the list
						combined.add(n);
					}
				}

				if (!passedList.isEmpty()) {
					combinedList.addAll(passedList);
				}

				// Add a new list contains the prime
				List<Integer> l = new ArrayList<Integer>();
				l.add(n);
				combinedList.add(l);

			}
		}

		long sum = Integer.MAX_VALUE;

		for (List<Integer> combined : combinedList) {
			if (combined.size() == 5) {
				System.out.println(combined);
				long s = 0;
				for (Integer i : combined) {
					s += i;
				}
				sum = Math.min(s, sum);
			}
		}

		return sum;
	}

	private static boolean isConstructPrime(int a, int b) {
		int ab = Integer.valueOf(Integer.toString(a) + Integer.toString(b));
		int ba = Integer.valueOf(Integer.toString(b) + Integer.toString(a));

		return Number.isPrime(ab) && Number.isPrime(ba);
	}
}