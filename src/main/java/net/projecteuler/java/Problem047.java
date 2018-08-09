package net.projecteuler.java;

import java.util.List;

import net.projecteuler.java.util.Number;

/**
 * The first two consecutive numbers to have two distinct prime factors are:<p>
 * 14 = 2 x 7</br>
 * 15 = 3 x 5</br></p>
 * The first three consecutive numbers to have three distinct prime factors are:<p>
 * 644 = 2<sup>2</sup> x 7 x 23</br>
 * 645 = 3 x 5 x 43</br>
 * 646 = 2 x 17 x 19.</br></p>
 * Find the first four consecutive integers to have four distinct primes factors.
 * What is the first of these numbers?
 *
 * @author vuser
 *
 */
public class Problem047 {

	public static void main(String[] args) {
		assert calculate() == 134043 : "failed";
	}

	private static long calculate() {
		long n = 1;
		int counter = 0;
		for (; counter < 4; n++) {
			List<Long> primeFactors = Number.factor(n);
			if (primeFactors.size() >= 4) {
				if (Number.primeExponent(primeFactors).size() == 4) {
					counter++;
				} else {
					counter = 0;
				}
			} else {
				counter = 0;
			}
		}
		return n - 4;
	}
}