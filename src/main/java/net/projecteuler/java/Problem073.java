package net.projecteuler.java;

import java.math.BigInteger;

import net.projecteuler.java.util.Rational;

/**
 * Consider the fraction, n/d, where n and d are positive integers.
 * If nd and HCF(n,d)=1, it is called a reduced proper fraction.</br>
 * If we list the set of reduced proper fractions for d  8 in ascending order of size, we get:
 * <p>
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * </p>
 * It can be seen that there are 3 fractions between 1/3 and 1/2.</br>
 * How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d  12,000?
 *
 * @author vuser
 *
 */
public class Problem073 {

	public static void main(String[] args) {
		assert calculate(12000) == 7295372 : "failed";
	}

	private static int calculate(int d) {
		int counter = 0;

		Rational half = new Rational(1, 2);
		Rational tierce = new Rational(1, 3);
		Rational r0 = new Rational(0, 1);
		Rational r1 = new Rational(1, d);

		// repeat until r0 equals 1/2
		while (r0.compareTo(half) < 0) {
			BigInteger num = r0.denominator().add(BigInteger.valueOf(d)).divide(r1.denominator()).multiply(r1.numerator()).subtract(r0.numerator());
			BigInteger den = r0.denominator().add(BigInteger.valueOf(d)).divide(r1.denominator()).multiply(r1.denominator()).subtract(r0.denominator());

			Rational rnew = new Rational(num, den);
			r0 = r1;
			r1 = rnew;

			if (r0.compareTo(tierce) > 0) {
				++counter;
			}
		}

		return --counter;
	}
}
