package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * Take the number 192 and multiply it by each of 1, 2, and 3:</br>
 * 192 x 1 = 192</br>
 * 192 x 2 = 384</br>
 * 192  3 = 576</br>
 * By concatenating each product we get the 1 to 9 pandigital, 192384576.
 * We will call 192384576 the concatenated product of 192 and (1,2,3)</br>
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5,
 * giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).</br>
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the
 * concatenated product of an integer with (1,2, ... , n) where n > 1?
 *
 * @author vuser
 *
 */
public class Problem038 {

	public static void main(String[] args) {
		assert calculate() == 932718654L : "failed";
	}

	private static long calculate() {
		long max = 0;

		for (int i = 1; i < 10000; i++) {
			StringBuilder builder = new StringBuilder();
			append: for (int j = 1; j < 10; j++) {
				builder.append(i * j);
				if (builder.length() >= 9) {
					break append;
				}
			}

			String s = builder.toString();
			if (s.length() == 9) {
				long number = Long.parseLong(s);
				if (Number.getNDigitPandigital(number) == 9 && number > max) {
					max = number;
				}
			}
		}

		return max;
	}
}
