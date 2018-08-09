package net.projecteuler.java;

import java.util.ArrayList;
import java.util.List;

import net.projecteuler.java.util.PermutationGenerator;

/**
 * We shall say that an n-digit number is pandigital if it makes use of all the digits
 * 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.
 * The product 7254 is unusual, as the identity, 39 x 186 = 7254, containing multiplicand,
 * multiplier, and product is 1 through 9 pandigital.</br>
 * Find the sum of all products whose multiplicand/multiplier/product identity
 * can be written as a 1 through 9 pandigital.</br>
 * <b>HINT</b>: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 *
 * @author vuser
 *
 */
public class Problem032 {

	public static void main(String[] args) {
		assert calculate() == 45228 : "failed";
	}

	private static int calculate() {
		List<Integer> products = new ArrayList<Integer>();
		// Generates all possible permutation
		PermutationGenerator permutations = new PermutationGenerator(9);
		int sum = 0;
		while (permutations.hasMore()) {
			int[] p = permutations.getNext();
			int[] m = new int[p.length];
			for (int i = 0; i < p.length; i++) {
				if (p[i] == 0) {
					m[i] = 9;
				} else {
					m[i] = p[i];
				}
			}

			int product = m[0] + m[1] * 10 + m[2] * 100 + m[3] * 1000;

			if (products.contains(product)) {
				continue;
			}
			if ((m[4] + m[5] * 10 + m[6] * 100) * (m[7] + m[8] * 10) == product
					|| (m[4] + m[5] * 10) * (m[6] + m[7] * 10 + m[8] * 100) == product
					|| m[4] * (m[5] + m[6] * 10 + m[7] * 100 + m[8] * 1000) == product
					|| (m[4] + m[5] * 10 + m[6] * 100 + m[7] * 1000) * m[8] == product) {
				products.add(product);
				sum += product;
			}
		}

		return sum;
	}
}