package net.projecteuler.java;

import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.Number;
import net.projecteuler.java.util.PrimeGenerator;

/**
 * Solving the Diophantine equation 1/<var>x</var> + 1/<var>y</var> = 1/<var>n</var></br>
 * (Number of ways to write 1/n as a sum of exactly 2 unit fractions.)</br>
 * What is the least value of n for which the number of distinct solutions exceeds one-thousand?
 *
 * @author vbox
 *
 */
public class Problem108 {

	public static void main(String[] args) {
		assert calculate() == 180180 : "failed";
	}

	/**
	 * The equation is equivalent to
	 * <p>
	 * (x - n) * (y - n) = n<sup>2</sup>
	 * </p>
	 * and n<sup>2</sup> = p<sub>1</sub><sup>2a1</sup> * p<sub>2</sub><sup>2a2</sup> *... * p<sub>k</sub><sup>2ak</sup>
	 * has (2a1 + 1) * (2a2 + 1) * ... * (2ak + 1) positive divisors, where p<sub>k</sub> is prime factor.</br>
	 * The formula is: a(n) = ((2a1 + 1) * (2a2 + 1) * ... * (2ak + 1) + 1) /2.
	 *
	 */
	private static long calculate() {
		// The solution is greater than 2 * 3 * 5 * 7 * 11 * 13... 
		List<Integer> primes = new PrimeGenerator(17).getPrimeList();
		long number = 1;
		for (int i = 0; i < primes.size() - 1; i++) {
			number *= primes.get(i);
		}
		search: for (int i = 0; ; i++) {
			number *= primes.get(i);
			Map<Long, Integer> primeExponent = Number.primeExponent(number * number);
			int counter = 1;
			// Applies the formula: ((2a1 + 1) * (2a2 + 1) * ... * (2ak + 1) + 1) /2
			for (int p : primeExponent.values()) {
				counter *= (p + 1);
			}
			if ((counter + 1)/2 >= 1000) {
				break search;
			}
			if (i + 1 == primes.size()) {
				i = 0;
			}
		}
		return number;
	}
}
