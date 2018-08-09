package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * Euler published the remarkable quadratic formula:</br>
 * n<sup>2</sup> + n + 41</br>
 * It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39.
 * However, when n = 40, 40<sup>2</sup> + 40 + 41 = 40(40 + 1) + 41 is divisible by 41,
 * and certainly when n = 41, 41<sup>2</sup> + 41 + 41 is clearly divisible by 41.</br>
 * Using computers, the incredible formula  n<sup>2</sup> - 79n + 1601 was discovered, which produces
 * 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, 79 and 1601, is 126479.</br>
 * Considering quadratics of the form:</br>
 * n<sup>2</sup> + a*n + b, where |a| 1000 and |b| < 1000</br>
 * where |n| is the modulus/absolute value of n, e.g. |11| = 11 and |4| = 4</br>
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the
 * maximum number of primes for consecutive values of n, starting with n = 0.
 *
 * @author vbox
 *
 */
public class Problem027 {

	public static void main(String[] args) {
		assert calculate() == -59231 : "failed";
	}

	private static int calculate() {
		int product = 0;

		int min = -999;
		int max = 999;

		int consecutive = 0;
		for (int a = min; a < max; a++) {
			for (int b = min; b < max; b++) {
				boolean passed = true;
				for (int n = 0; passed; n++) {
					passed = Number.isPrime(n * n + a * n + b);
					if (passed) {
						if (n > consecutive) {
							consecutive = n;
							product = a * b;
						}
					}
				}
			}
		}
		return product;
	}
}