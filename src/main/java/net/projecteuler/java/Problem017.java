package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five,
 * then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.</br>
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
 * how many letters would be used?</br>
 * <b>NOTE</b>: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
 * contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
 * The use of "and" when writing out numbers is in compliance with British usage.
 *
 * @author vuser
 *
 */
public class Problem017 {

	private static int[] oneToNine = new int[] {3, 3, 5, 4, 4, 3, 5, 5, 4};
	private static int[] oneToNineteen = new int[] {3, 3, 5, 4, 4, 3, 5, 5, 4, 3, 6, 6, 8, 8, 7, 7, 9, 8, 8};
	private static int[] tenners = new int[] {3, 6, 6, 5, 5, 5, 7, 6, 6};
	private static int hundred = 7;
	private static int thousand = 8;

	public static void main(String args[]) {
		int sum = 0;
		for (int i = 1; i <= 1000; i++) {
			int s = getNumberOfLetters(i);
			sum += s;
		}
		assert sum == 21124 : "failed";
	}

	private static int getNumberOfLetters(int number) {
		int sum = 0;
		if (number < 100) {
			sum += untilHundred(number);
		} else {
			int[] digits = Number.getDigits(number);
			if (number < 1000) {
				sum += oneToNine[digits[2] - 1] + hundred;
				if (digits[0] > 0 || digits[1] > 0) {
					sum += untilHundred(digits[0] + 10 * digits[1]) + 3;
				}
			} else {
				sum += oneToNine[digits[3] - 1] + thousand;
			}
		}

		return sum;
	}

	private static int untilHundred(int number) {
		int sum = 0;
		if (number == 0) {
			return sum;
		}

		if (number < 20) {
			sum += oneToNineteen[number - 1];
		} else {
			int[] digits = Number.getDigits(number);
			if (digits[0] > 0) {
				sum += oneToNine[digits[0] - 1];
			}
			sum += tenners[digits[1] - 1];
		}
		return sum;
	}
}