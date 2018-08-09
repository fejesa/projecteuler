package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * A number chain is created by continuously adding the square of the
 * digits in a number to form a new number until it has been seen before.
 * For example,<p>
 * 44 -> 32 -> 13 -> 10 -> 1 -> 1</br>
 * 85 -> 89 -> 145 -> 42 -> 20 -> 4 -> 16 -> 37 -> 58 -> 89</p>
 * Therefore any chain that arrives at 1 or 89 will become stuck in an
 * endless loop.
 * What is most amazing is that EVERY starting number will eventually arrive at 1 or 89.</br>
 * How many starting numbers below ten million will arrive at 89?
 * @author vuser
 *
 */
public class Problem092 {

	public static void main(String[] args) {
		assert calculate() == 8581146 : "failed";
	}

	private static int calculate() {
		int counter = 0;
		for (int i = 1; i < 10000000; i++) {
			if (getChainEnd(i) == 89) {
				++counter;
			}
		}
		return counter;
	}

	private static int getChainEnd(int number) {
		for (; number != 1 && number != 89; ) {
			int[] digits = Number.getDigits(number);
			int sum = 0;
			for (int i = 0; i < digits.length; i++) {
				sum += digits[i]* digits[i];
			}
			number = sum;
		}
		return number;
	}
}
