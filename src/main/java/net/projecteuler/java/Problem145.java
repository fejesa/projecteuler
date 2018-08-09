package net.projecteuler.java;

import java.util.BitSet;

/**
 * Some positive integers n have the property that the sum [ n + reverse(n) ]
 * consists entirely of odd (decimal) digits. For instance, 36 + 63 = 99
 * and 409 + 904 = 1313. We will call such numbers <i>reversible</i>; so 36, 63, 409,
 * and 904 are reversible. Leading zeroes are not allowed in either n or reverse(n).</br>
 * There are 120 reversible numbers below one-thousand.</br>
 * How many reversible numbers are there below one-billion (10<sup>9</sup>)?
 *
 * @author vbox
 *
 */
public class Problem145 {

	public static void main(String... args) {
		assert solve() == 608720 : "failed";
	}

	private static int solve() {
		int limit = 100000000; // There are 9 digit solution, because of leading zero - we calculate till 10^8
		// BitSet is used to mark the number and its reversed one in order to avoid
		// calculation twice, because of performance
		BitSet numbers = new BitSet(limit);

		for (int i = 0; i < numbers.size(); i = i + 10) {
			numbers.set(i, true); // We do not calculate with multiple of 10
		}
		int counter = 0;
		for (int i = 1; i < limit; i++) {
			if (numbers.get(i)) {
				continue;
			}
			int reversedNum = 0, number = i;
			while (number != 0) {
				reversedNum = reversedNum * 10 + number % 10;
				number = number / 10;
			}

			// Sets the reversed value in the bit set
			numbers.set(reversedNum, true);

			int sum = i + reversedNum;
			boolean passed = true;
			while (sum != 0) {
				if (sum % 2 == 0) {
					passed = false;
					break;
				}
				sum = sum / 10;
			}
			if (passed) {
				++counter;
			}
		}

		return counter * 2;
	}
}