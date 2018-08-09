package net.projecteuler.java;

import java.util.List;

import net.projecteuler.java.util.ContinuedFraction;
import net.projecteuler.java.util.Number;
import net.projecteuler.java.util.Rational;

/**
 * Find the sum of digits in the numerator of the 100<sup>th</sup>
 * convergent of the continued fraction for <i>e</i>.
 *
 * @author vuser
 */
public class Problem065 {

	public static void main(String[] args) {
		assert calculate() == 272 : "failed";
	}

	private static int calculate() {
		List<Integer> fractions = ContinuedFraction.getContinuedFractionOfE();
		// Converts the fractions to real number (rational number)
		List<Rational> rationals = ContinuedFraction.fromContinuedFraction(fractions);

		// Gets the digits of the given numerator (100th convergent) of the rational
		int[] digits = Number.getDigits(rationals.get(99).numerator());
		int sum = 0;
		for (int i : digits) {
			sum += i;
		}
		return sum;
	}
}