package net.projecteuler.java;

import java.math.BigDecimal;
import java.math.MathContext;

import net.projecteuler.java.util.Number;

/**
 * The fraction <sup>49</sup>/<sub>98</sub> is a curious fraction, as an inexperienced
 * mathematician in attempting to simplify it may incorrectly believe that
 * <sup>49</sup>/<sub>98</sub> = <sup>4</sup>/<sub>8</sub>, which is correct, is obtained by cancelling the 9s.
 * We shall consider fractions like, <sup>30</sup>/<sub>50</sub> = <sup>3</sup>/<sub>5</sub>,
 * to be trivial examples.</br>
 * There are exactly four non-trivial examples of this type of fraction,
 * less than one in value, and containing two digits in the numerator and denominator.
 * If the product of these four fractions is given in its lowest common terms,
 * find the value of the denominator.
 *
 * @author vuser
 *
 */
public class Problem033 {

	public static void main(String[] args) {
		assert calculate() == 100 : "failed";
	}

	private static int calculate() {

		int numerator = 1;
		int denominator = 1;

		for (int i = 12; i < 100; i++) {
			if (i % 10 == 0 || i % 11 == 0) { // filter: 20, 22, 30, 33, ..., 90, 99
				continue;
			}

			int[] n = Number.getDigits(i);
			for (int j = i + 1; j < 100; j++) {
				int[] d = Number.getDigits(j);
				int a = 0; int b = 0;
				if (n[0] == d[0]) {
					a = n[1];
					b = d[1];
				} else if (n[0] == d[1]) {
					a = n[1];
					b = d[0];
				} else if (n[1] == d[0]) {
					a = n[0];
					b = d[1];
				} else if (n[1] == d[1]) {
					a = n[0];
					b = d[0];
				}

				if (a * b  > 0) {
					MathContext mc = MathContext.DECIMAL128;
					BigDecimal quotientOrigin = new BigDecimal(i).divide(new BigDecimal(j), mc);
					BigDecimal quotientCurious = new BigDecimal(a).divide(new BigDecimal(b), mc);
					if (quotientOrigin.compareTo(quotientCurious) == 0) {
						numerator *= i;
						denominator *= j;
					}
				}
			}
		}

		return denominator / gcd(numerator, denominator);
	}

	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}
