package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * The decimal number, 585 = 1001001001<sub>2</sub> (binary), is palindromic in both bases.
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.</br>
 * <b>(Please note that the palindromic number, in either base, may not include leading zeros.)</b>
 *
 * @author vbox
 *
 */
public class Problem036 {

	public static void main(String[] args) {
		assert calculate() == 872187 : "failed";
	}

	private static int calculate() {
		int sum = 0;
		for (int i = 0; i < 1000000; i++) {
			if (Number.isPalindrome(i) && isPalindromeBinary(i)) {
				sum += i;
			}
		}
		return sum;
	}

	private static boolean isPalindromeBinary(int number) {
		String binary = Integer.toBinaryString(number);
		if (binary.length() == 1) {
			return true;
		}

		for (int i = 0; i < binary.length() / 2; i++) {
			if (binary.charAt(i) - binary.charAt(binary.length() - i - 1) != 0) {
				return false;
			}
		}
		return true;
	}
}
