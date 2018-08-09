package net.projecteuler.java;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.projecteuler.java.util.Partition;
import net.projecteuler.java.util.PermutationGenerator;
import net.projecteuler.java.util.PrimeGenerator;

/**
 * Using all of the digits 1 through 9 and concatenating them freely
 * to form decimal integers, different sets can be formed. Interestingly
 * with the set {2,5,47,89,631}, all of the elements belonging to it are prime.
 * <p>
 * How many distinct sets containing each of the digits one through nine
 * exactly once contain only prime elements?
 * </p>
 * @author vuser
 *
 */
public class Problem118 {

	public static void main(String... args) {
		assert solve() == 44680 : "failed";
	}

	private static int solve() {

		// Contains the primes up to 9999999 those have distinct digits
		BitSet primesSet = getPrimes();

		// The partitions of the number 9
		List<List<Integer>> partitions = Partition.partition(9);

		// Permutations of number 9 
		PermutationGenerator permutations = new PermutationGenerator(9);

		// Because we are looking for distinct set of primes the product of
		// those sets have different values; i.e. p1 * p2 * p3
		// and p2 * p3 * p4 are different
		Set<Integer> set = new HashSet<Integer>();

		while (permutations.hasMore()) {
			int[] p = permutations.getNext();
			for (List<Integer> part : partitions) {
				int product = 1;
				int index = 0, end = 0;
				boolean passed = true;
				part: for (int i : part) {
					end += i - 1;
					// Produces a number from the digits
					int num = number(p, 0, index, end);
					// If the number is prime 'stores' it in the product
					if (primesSet.get(num)) {
						product *= num;
					} else {
						passed = false;
						break part;
					}
					index = ++end;
				}
				if (passed) {
					set.add(product);
				}
			}
		}

		return set.size();
	}

	/**
	 * Produces a number from the permutation
	 */
	private static int number(int[] permutation, int number, int index, int end) {
		if (index - 1 == end) {
			return number;
		}
		return number(permutation, number * 10 + permutation[index] + 1, ++index, end);
	}

	/**
	 * Generates the primes that have distinct digits.
	 * The result is stored in a {@link BitSet} because of the performance reason.
	 * @return
	 */
	private static BitSet getPrimes() {
		List<Integer> primes = new ArrayList<Integer>();
		PrimeGenerator sieve = new PrimeGenerator(99999999);
		for (int i = 2; i < sieve.getLimit(); i++) {
			if (sieve.isPrime(i)) {
				if (isDistinct(i)) {
					primes.add(i);
				}
			}
		}
		BitSet set = new BitSet(sieve.getLimit());
		for (int i : primes) {
			set.set(i, true);
		}
		return set;
	}

	private static boolean isDistinct(int i) {
		Set<Integer> set = new HashSet<Integer>();
		while (i != 0) {
			int m = i % 10;
			if (m == 0 || !set.add(m)) {
				return false;
			}
			i = i / 10;
		}
		return true;
	}
}