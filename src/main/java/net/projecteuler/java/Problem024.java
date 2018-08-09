package net.projecteuler.java;

import net.projecteuler.java.util.PermutationGenerator;

/**
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation
 * of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically,
 * we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:</br>
 * 012   021   102   120   201   210</br>
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 *
 * @author vbox
 *
 */
public class Problem024 {

	public static void main(String[] args) {
		assert calculate().equals("2783915460") : "failed";
	}

	private static String calculate() {
		PermutationGenerator permutations = new PermutationGenerator(10);
		for (int index = 0; index < 10000000; index++) {
			int[] p = permutations.getNext();
			if (index == 999999) {
				char[] c = new char[p.length];
				for (int i = 0; i < p.length; i++) {
					c[i] = (char) (p[i] + 48);
				}
				return String.valueOf(c); 
			}
		}

		return null;
	}
}