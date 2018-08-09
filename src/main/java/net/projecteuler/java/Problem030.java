package net.projecteuler.java;

/**
 * Surprisingly there are only three numbers that can be written as the
 * sum of fourth powers of their digits:</br>
 * 1634 = 1<sup>4</sup> + 6<sup>4</sup> + 3<sup>4</sup> + 4<sup>4</sup></br>
 * 8208 = 8<sup>4</sup> + 2<sup>4</sup> + 0<sup>4</sup> + 8<sup>4</sup></br>
 * 9474 = 9<sup>4</sup> + 4<sup>4</sup> + 7<sup>4</sup> + 4<sup>4</sup></br>
 * As 1 = 14 is not a sum it is not included.
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.</br>
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 *
 * @author vuser
 *
 */
public class Problem030 {

	public static void main(String[] args) {
		assert calculate() == 443839 : "failed";
	}

	private static int calculate() {
		int sum = 0;

		int min = 2;
		// max ~ 9(exp 5) * 5
		double max = Math.pow(9, 5) * 5;

		for (int i = min; i <= max; i++) {
			int[] digits = getDigits(i);
			int isum = 0;
			for (int digit : digits) {
				isum += digit * digit * digit * digit * digit;				
			}
			if (isum == i) {
				sum += i;
			}
		}
		return sum;
	}

	private static int[] getDigits(int number) {
		int numDigits = (int) Math.log10(number) + 1;
		int[] digits = new int[numDigits];
		for (int i = 0; i < numDigits; i++) {
			digits[i] = number % 10;
			number /= 10; 
		}

		return digits;
	}
}