package net.projecteuler.java;

import java.math.BigInteger;

import net.projecteuler.java.util.Number;

/**
 * If we take 47, reverse and add, 47 + 74 = 121, which is palindromic. Not all numbers produce palindromes so quickly. For example,<p>
 * 349 + 943 = 1292,</br>
 * 1292 + 2921 = 4213</br>
 * 4213 + 3124 = 7337</br></p>
 * That is, 349 took three iterations to arrive at a palindrome.</br>
 * Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome.
 * A number that never forms a palindrome through the reverse and add process is called a Lychrel number.
 * Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that
 * a number is Lychrel until proven otherwise. In addition you are given that for every number below ten-thousand,
 * it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing
 * power that exists, has managed so far to map it to a palindrome.
 * In fact, 10677 is the first number to be shown to require over fifty iterations before producing
 * a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).</br>
 * Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.</br>
 * How many Lychrel numbers are there below ten-thousand?</br>
 * <b>NOTE:</b> Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.
 *
 * @author vuser
 *
 */
public class Problem055 {

	public static void main(String[] args) {
		assert calculate() == 249 : "failed";
	}

	private static int calculate() {
		int limit = 10000;
		int counter = 0;

		for (int i = 1; i < limit; i++) {
			BigInteger sum = BigInteger.valueOf(i);
			search: for (int j = 0; j < 50; j++) {
				sum = sum.add(Number.reverse(sum));
				if (Number.isPalindrome(sum)) {
					++counter;
					break search;
				}
			}
		}

		return limit - counter - 1;
	}
}