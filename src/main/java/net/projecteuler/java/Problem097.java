package net.projecteuler.java;

import java.math.BigInteger;

/**
 * The first known prime found to exceed one million digits was discovered in 1999,
 * and is a Mersenne prime of the form 2<sup>6972593</sup> - 1; it contains exactly 2,098,960 digits.
 * Subsequently other Mersenne primes, of the form 2<sup>p - 1</sup>,
 * have been found which contain more digits.</br>
 * However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits:
 * 28433 * 2<sup>7830457</sup> + 1.</br>
 * Find the last ten digits of this prime number.
 *
 * @author vuser
 *
 */
public class Problem097 {

	public static void main(String[] args) {
		assert calculate() == 8739992577L : "failed";
	}

	private static long calculate() {
		BigInteger a = new BigInteger("2"); 
		BigInteger mod = new BigInteger("10000000000"); 
		a = a.modPow(new BigInteger("7830457"), mod).multiply(new BigInteger("28433")).add(BigInteger.ONE).mod(mod);
		return a.longValue();
	}
}