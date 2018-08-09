package net.projecteuler.java;

import java.util.HashSet;
import java.util.Set;

import net.projecteuler.java.util.Number;

/**
 * The palindromic number 595 is interesting because it can be written as the sum of
 * consecutive squares: 6<sup>2</sup> + 7<sup>2</sup> + 8<sup>2</sup> + 9<sup>2</sup> + 10<sup>2</sup> + 11<sup>2</sup> + 12<sup>2</sup>.
 * <p>
 * There are exactly eleven palindromes below one-thousand that can be written as
 * consecutive square sums, and the sum of these palindromes is 4164.
 * Note that 1 = 0<sup>2</sup> + 1<sup>2</sup> has not been included as this problem is concerned
 * with the squares of positive integers.
 * </p>
 * Find the sum of all the numbers less than 108 that are both palindromic and can be written as the sum of consecutive squares.
 *
 * @author vbox
 *
 */
public class Problem125 {

	public static void main(String[] args) {
		assert calculate() == 2906969179L : "failed";
	}

	private static long calculate() {
		// The iteration limit to sqrt(limit)
		int limit = 100000000;
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 1, sqrtLimit = (int) Math.sqrt(limit); i < sqrtLimit; i++) {
			int sos = i * i;
			for (int j = i + 1; j < sqrtLimit; j++) {
				sos += j * j;
				if (sos > limit) {
					break;
				}
				if (Number.isPalindrome(sos)) {
					set.add(sos);
				}
			}
		}

		long sum = 0;
		for (Integer i : set) {
			sum += i;
		}

		return sum;
	}
}