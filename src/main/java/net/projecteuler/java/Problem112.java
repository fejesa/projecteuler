package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * Working from left-to-right if no digit is exceeded by the digit to its left it is called
 * an increasing number; for example, 134468.
 * <p>
 * Similarly if no digit is exceeded by the digit to its right it is called a decreasing number; for example, 66420.
 * </p>
 * We shall call a positive integer that is neither increasing nor decreasing a "bouncy" number; for example, 155349.
 * Clearly there cannot be any bouncy numbers below one-hundred, but just over half of the numbers below
 * one-thousand (525) are bouncy. In fact, the least number for which the proportion of bouncy numbers first
 * reaches 50% is 538.
 * <p>
 * Surprisingly, bouncy numbers become more and more common and by the time we reach
 * 21780 the proportion of bouncy numbers is equal to 90%.
 * </p>
 * Find the least number for which the proportion of bouncy numbers is exactly 99%.
 *
 * @author vbox
 *
 */
public class Problem112 {

	public static void main(String[] args) {
		assert calculate() == 1587000 : "failed";
	}

	private static int calculate() {
		// Starts from 100
		int i = 100;
		search: for (double counter = 0, total = 99; ; i++) {
			if (Number.isBouncy(i)) {
				++counter;
			}
			++total;
			if (counter / total == 0.99) {
				break search;
			}
		}

		return i;
	}
}
