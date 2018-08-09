package net.projecteuler.java;

import java.math.BigInteger;
import java.util.List;

import net.projecteuler.java.util.ContinuedFraction;
import net.projecteuler.java.util.Pattern;
import net.projecteuler.java.util.Rational;

/**
 * If a box contains twenty-one coloured discs, composed of fifteen blue discs
 * and six red discs, and two discs were taken at random, it can be seen that
 * the probability of taking two blue discs, P(BB) = (15/21)x(14/20) = 1/2.</br>
 * The next such arrangement, for which there is exactly 50% chance of taking
 * two blue discs at random, is a box containing eighty-five blue discs and
 * thirty-five red discs.</br>
 * By finding the first arrangement to contain over 10<sup>12</sup> = 1,000,000,000,000
 * discs in total, determine the number of blue discs that the box would contain.
 *
 * @author vuser
 *
 */
public class Problem100 {

	public static void main(String[] args) {
		assert calculate() == 756872327473L : "failed";
	}

	/**
	 * Calculation is based on the following:</br>
	 * let b is the blue disk, r is a red disk; b + r >= 10<sup>12</sup></br>
	 * 2 * b * (b - 1) = (b + r) * (b + r - 1)</br>
	 * b<sup>2</sup> - 2br - b - r - r<sup>2</sup> = 0</br>
	 * b = (1 + 2r + &radic;(1 + 8r<sup>2</sup>)) / 2</br>
	 * It can be seen, that 1 + 8r<sup>2</sup> must be a square, that means
	 * it forms a Pell equatation: x<sup>2</sup> - Dy<sup>2</sup> = 1.
	 */
	private static long calculate() {

		int d = 8;

		List<Integer> fractions = ContinuedFraction.toContinuedFraction(d);
		List<Rational> rationals = ContinuedFraction.fromContinuedFraction(fractions);

		int period = Pattern.getPattern(fractions.subList(1, fractions.size() - 1)).size();

		double total = Math.pow(10, 12);

		long blue = 0;
		search: for (int i = 2; i < rationals.size(); i = i + period) {
			// period is even, the solutions of the Pell equatation are
			// the x = pkn-1, y = qkn-1 k = 1, 2, ...
			BigInteger y = rationals.get(i - 1).denominator();

			double r = y.doubleValue();
			double b = ((1 + 2 * r) + Math.sqrt(1 + 8 * r * r)) / 2;

			if (b + r >= total) {
				blue = (long) b;
				break search;
			}
		}

		return blue;
	}
}