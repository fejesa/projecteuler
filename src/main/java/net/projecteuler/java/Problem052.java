package net.projecteuler.java;

import java.util.Set;

import net.projecteuler.java.util.Number;

/**
 * It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.</br>
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 *
 * @author vbox
 *
 */
public class Problem052 {

	public static void main(String[] args) {
		assert calculate() == 142857L : "failed";
	}

	private static long calculate() {
		long number = 0;
		long min = 1; long max = 1;
		search: for (; ;) {
			// Because of if 2,3...6 is a multiplier the factor min-max value patterns are:
			// 10..16
			// 100..166
			// 1000..1666
			min *= 10; max = min * 5 / 3;
			for (long j = min; j <= max; j++) {
				Set<Integer> set2 = Number.getDigitSet(2 * j);
				Set<Integer> set3 = Number.getDigitSet(3 * j);
				Set<Integer> set4 = Number.getDigitSet(4 * j);
				Set<Integer> set5 = Number.getDigitSet(5 * j);
				Set<Integer> set6 = Number.getDigitSet(6 * j);
				if (set2.equals(set3) && set3.equals(set4) && set4.equals(set5) && set5.equals(set6)) {
					number = j;
					break search;
				}
			}
		}

		return number;
	}
}