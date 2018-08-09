package net.projecteuler.java.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Integer factorization using the Pollard-Rho method.
 * @author vuser
 * @see<a href="http://en.wikipedia.org/wiki/Pollard's_rho_algorithm">Pollard's rho algorithm</a>
 * @see<a href="http://introcs.cs.princeton.edu/java/78crypto/PollardRho.java.html">Java implementation</a>
 */
public class PollardRho {

	private final static BigInteger ZERO = new BigInteger("0");
	private final static BigInteger ONE = new BigInteger("1");
	private final static BigInteger TWO = new BigInteger("2");

	private final static SecureRandom random = new SecureRandom();

	private static BigInteger rho(BigInteger number) {
		BigInteger divisor;
		BigInteger c = new BigInteger(number.bitLength(), random);
		BigInteger x = new BigInteger(number.bitLength(), random);
		BigInteger xx = x;

		// check divisibility by 2
		if (number.mod(TWO).compareTo(ZERO) == 0)
			return TWO;

		do {
			x = x.multiply(x).mod(number).add(c).mod(number);
			xx = xx.multiply(xx).mod(number).add(c).mod(number);
			xx = xx.multiply(xx).mod(number).add(c).mod(number);
			divisor = x.subtract(xx).gcd(number);

		} while ((divisor.compareTo(ONE)) == 0);

		return divisor;
	}

	public static void factor(BigInteger number, List<BigInteger> factors) {
		if (number.compareTo(ONE) == 0) {
			return;
		}
		if (number.isProbablePrime(20)) {
			factors.add(number);
			return;
		}
		BigInteger divisor = rho(number);
		factor(divisor, factors);
		factor(number.divide(divisor), factors);
	}

	public static boolean isPrime(BigInteger number) {
		List<BigInteger> factors = new ArrayList<BigInteger>();
		factor(number, factors);
		return factors.size() == 1;
	}

	public static void main(String[] args) {
		BigInteger number = new BigInteger("44343535354351600000003434353");
		List<BigInteger> factors = new ArrayList<BigInteger>();
		factor(number, factors);
		System.out.println(factors);
	}
}