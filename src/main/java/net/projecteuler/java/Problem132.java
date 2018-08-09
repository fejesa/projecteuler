package net.projecteuler.java;

import java.math.BigInteger;

import net.projecteuler.java.util.PrimeGenerator;

/**
 * A number consisting entirely of ones is called a repunit.
 * We shall define R(k) to be a repunit of length k.</br>
 * For example, R(10) = 1111111111 = 11* 41* 271 * 9091,
 * and the sum of these prime factors is 9414.</br>
 * Find the sum of the first forty prime factors of R(10<sup>9</sup>).
 *
 * @author vuser
 *
 */
public class Problem132 {

	public static void main(String... args) {
		assert solve() == 843296 : "failed";
	}

	/**
	 * Repunit can be represedted like this:
	 * R(k) = (10<sup>k</sup> - 1) / 9</br>
	 * repunit can be divisible p prime if (10<sup>k</sup> - 1) / 9 = 0 (mod p)</br>
	 * therefore</br>
	 * 10<sup>k</sup> - 1 = 0 (mod 9p)</br>
	 * and 10<sup>k</sup> = 1 (mod 9p)</br>
	 * So, if 10<sup>k</sup> mod 9p = 1 then we have found a divisor for R(k).
	 * To efficiently calculate this we can use Modular Exponentiation.
	 *
	 * @return
	 */
	private static long solve() {

		int limit = 1000000000;
		long sum = 0;

		PrimeGenerator pg = new PrimeGenerator(limit);
		BigInteger base = BigInteger.TEN;
		BigInteger exponent = BigInteger.valueOf(limit);

		for (int i = 0, counter = 0; counter < 40 && i < pg.getLimit(); i++) {
			if (pg.isPrime(i)
			        && base.modPow(exponent, BigInteger.valueOf(i * 9)).equals(BigInteger.ONE)) {
				sum += i;
				counter++;
			}
		}

		return sum;
	}
}