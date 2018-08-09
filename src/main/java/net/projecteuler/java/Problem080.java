package net.projecteuler.java;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import net.projecteuler.java.util.ContinuedFraction;
import net.projecteuler.java.util.Number;
import net.projecteuler.java.util.Rational;

/**
 * It is well known that if the square root of a natural number is not an integer,
 * then it is irrational. The decimal expansion of such square roots is infinite
 * without any repeating pattern at all.</br>
 * The square root of two is 1.41421356237309504880..., and the digital sum of the
 * first one hundred decimal digits is 475.</br>
 * For the first one hundred natural numbers, find the total of the digital sums of
 * the first one hundred decimal digits for all the irrational square roots.
 *
 * @author vuser
 *
 */
public class Problem080 {

	public static void main(String[] args) {
		assert calculate() == 40886 : "failed";
	}

	/**
	 * For the calculation we can use the continued fractions for square-roots.
	 * @return
	 */
	private static int calculate() {
		int sum = 0;

		MathContext mc = new MathContext(100, RoundingMode.DOWN);

		for (int i = 1; i <= 100; i++) {
			List<Integer> list = ContinuedFraction.toContinuedFraction(i);
			if (list.size() == 1) { // Not irrational
				continue;
			}

			List<Rational> rlist = ContinuedFraction.fromContinuedFraction(list);
			Rational r = rlist.get(rlist.size() - 1);

			BigDecimal decimal = new BigDecimal(r.numerator()).divide(new BigDecimal(r.denominator()), mc);
			int[] digits = Number.getDigits(decimal);
			for (int index = 0; index < 100; index++) {
				sum += digits[index];
			}
		}

		return sum;
	}
}
