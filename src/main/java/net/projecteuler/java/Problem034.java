package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.</br>
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 * <b>Note</b>: as 1! = 1 and 2! = 2 are not sums they are not included.
 *
 * @author vbox
 *
 */
public class Problem034 {

	public static void main(String[] args) {
		assert calculate() == 40730 : "failed";
	}

	/**
	 * A <i>factorion</i> is a natural number that equals the sum of the factorials of its decimal digits.
	 * For example, 145 is a factorion because 1! + 4! + 5! = 1 + 24 + 120 = 145.
	 * Upper boound: if n is a natural number of d digits that is a factorion,
	 * then 10<sup>d-1</sup> <= n <= 9!d. This fails to hold for d >= 8 thus n has at most 7 digits, and the first
	 * upper bound is 9,999,999. But the maximum sum of factorials of digits for a 7 digit number is 9!7 = 2,540,160
	 * establishing the second upper bound.
	 * 
	 * @return
	 */
	private static int calculate() {
		int[] factorials = new int[] {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

		int sum = 0;

		for (int i = 3; i < 50000; i++) {
			int[] digists = Number.getDigits(i);
			int s = 0;
			for (int j = 0; j < digists.length; j++) {
				s += factorials[digists[j]];
			}
			if (s == i) {
				sum += i;
			}
		}

		return sum;
	}
}