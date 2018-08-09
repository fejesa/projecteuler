package net.projecteuler.java.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ContinuedFraction {

	private final static int MAX_FRACTION_LENGTH = 250;

	public static List<Integer> toContinuedFraction(Rational rational) {

		List<Integer> result = new ArrayList<Integer>();

		Rational r = rational;

		frac: for (;;) {

			int i = r.numerator().divide(r.denominator()).intValue();
			result.add(i);

			Rational diff = r.subtract(new Rational(i));

			if (diff.numerator().equals(BigInteger.ZERO)) {
				break frac;
			}

			r = diff.reciprocal();
		}

		return result;
	}

	public static List<Integer> getContinuedFractionOfE() {
		List<Integer> fractions = new ArrayList<Integer>();
		// add a0 first
		fractions.add(Integer.valueOf(2));
		// add the fraction periodical of e (that is [1 2k 1])
		for (int k = 1; k <= 50; ++k)
		{
			fractions.add(1);
			fractions.add(k * 2);
			fractions.add(1);
		}

		return fractions;
	}
	
	public static List<Integer> toContinuedFraction(int number) {
		// the algorithm to compute square roots using continued fraction is adapted from wikipedia
		int a0 = (int) Math.sqrt(number);
		int d0 = 1;
		int m0 = 0;
		int mn = m0;
		int dn = d0;
		int an = a0;

		// add a0 immediately
		List<Integer> result = new ArrayList<Integer>();
		result.add(an);

		// if S is an even root, there is no fraction to calculate
		if (number == a0 * a0) {
			return result;
		}

		// now generate all digits of the fraction and add them to the result array
		for (int i = 0; i < MAX_FRACTION_LENGTH; ++i)
		{
			mn = dn * an - mn;
			dn = (number - mn * mn) / dn;
			an = (a0 + mn) / dn;
			result.add(an);
		}
		return result;
	}

	/**
	 * Produces the convergent rationals sequence from the franction list.
	 * @param fractions The fraction list.
	 * @return
	 * @see <a href="http://en.wikipedia.org/wiki/Continued_fraction#Some_useful_theorems">Continued fraction</a>
	 */
	public static List<Rational> fromContinuedFraction(List<Integer> fractions) {
		List<BigInteger> pList = new ArrayList<BigInteger>();
		pList.add(BigInteger.ONE); pList.add(BigInteger.valueOf(fractions.get(0)));
		convert(fractions, pList);

		List<BigInteger> qList = new ArrayList<BigInteger>();
		qList.add(BigInteger.ZERO); qList.add(BigInteger.ONE);
		convert(fractions, qList);

		List<Rational> rationals = new ArrayList<Rational>();
		for (int i = 1; i < fractions.size(); i++) {
			rationals.add(new Rational(pList.get(i), qList.get(i)));
		}

		return rationals;
	}

	private static void convert(List<Integer> fractions, List<BigInteger> list) {
		for (int i = 2; i < fractions.size(); i++) {
			BigInteger x = BigInteger.valueOf(fractions.get(i - 1))
				.multiply(list.get(i - 1))
				.add(list.get(i - 2));
			list.add(x);
		}
	}
}