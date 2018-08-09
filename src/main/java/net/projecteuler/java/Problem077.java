package net.projecteuler.java;

import net.projecteuler.java.util.Counter;
import net.projecteuler.java.util.Number;

/**
 * It is possible to write ten as the sum of primes in exactly five different ways:
 * <p>
 * 7 + 3</br>
 * 5 + 5</br>
 * 5 + 3 + 2</br>
 * 3 + 3 + 2 + 2</br>
 * 2 + 2 + 2 + 2 + 2</p>
 * What is the first value which can be written as the sum of primes in over five thousand different ways?
 *
 * @author vuser
 *
 */
public class Problem077 {

	public static void main(String[] args) {
		assert calculate() == 71 : "failed";
	}

	private static int calculate() {
		int number = 1;
		search: for (; ; number++) {
			Counter counter = new Counter();
			partition(number, number, counter);

			if (counter.getValue() >= 5000) {
				break search;
			}
		}
		return number;
	}

	public static void partition(int n, int max, Counter counter) {
		if (n == 0) {
			counter.increment();
			return;
		}

		for (int i = Math.min(max, n); i >= 1; i--) {
			if (Number.isPrime(i)) { // Only the prime numbers are checked
				partition(n - i, i, counter);
			}
		}
	}
}