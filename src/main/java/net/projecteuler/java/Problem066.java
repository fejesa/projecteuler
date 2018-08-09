package net.projecteuler.java;

import java.math.BigInteger;
import java.util.List;

import net.projecteuler.java.util.ContinuedFraction;
import net.projecteuler.java.util.Pattern;
import net.projecteuler.java.util.Rational;

/**
 * Find the value of D <= 1000 in minimal solutions of x for which the largest value of x is obtained.
 *
 * @author vuser
 *
 */
public class Problem066 {

	public static void main(String[] args) {
		assert calculate() == 661 : "failed";
	}

	/**
	 * Quadratic equations in the form of x<sup>2</sup> - Dy<sup>2</sup> = 1 are called
	 * Pell Equations. The minimum solution for such equations are calculated by
	 * the convergents of the root of D.
	 * @see <a href="http://mathworld.wolfram.com/PellEquation.html">Pell Equations</a>
	 */
	private static int calculate() {
		BigInteger max = BigInteger.ZERO;
		int value = 0;
		for (int d = 1; d <= 1000; d++) {
			// d should not be square
			int root = (int) Math.sqrt(d);
			if (root * root == d) {
				continue;
			}

			List<Integer> fractions = ContinuedFraction.toContinuedFraction(d);
			List<Rational> rationals = ContinuedFraction.fromContinuedFraction(fractions);

			int period = Pattern.getPattern(fractions.subList(1, fractions.size() - 1)).size();
			BigInteger x = null;
			if (period % 2 == 0) {
				// even
				x = rationals.get(period - 1).numerator();
			} else {
				// odd
				x = rationals.get(2 * period - 1).numerator();
			}

			if (x.subtract(max).compareTo(BigInteger.ZERO) > 0) {
				max = x;
				value = d;
			}
		}

		return value;
	}
}