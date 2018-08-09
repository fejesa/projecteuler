package net.projecteuler.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.projecteuler.java.util.Number;

/**
 * The number, 197, is called a circular prime because all rotations of the digits:
 * 197, 971, and 719, are themselves prime.</br>
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.</br>
 * How many circular primes are there below one million?
 *
 * @author vbox
 *
 */
public class Problem035 {

	public static void main(String[] args) {
		assert calculate() == 55 : "failed";
	}

	private static int calculate() {

		List<Integer> circularPrimes = new ArrayList<Integer>();
		circularPrimes.add(2); // 2 is circular
		for (int i = 3; i < 1000000; i = i + 2) { // Iteration step is 2 because of performance

			if (!circularPrimes.contains(i) && Number.isPrime(i)) {
				Set<Integer> circulars = getCircularNumbers(i);
				boolean passed = true;
				for (Integer c : circulars) {
					if (!Number.isPrime(c)) {
						passed = false; break;
					}
				}
				if (passed) {
					circularPrimes.addAll(circulars);
				}
			}

		}
		return circularPrimes.size();
	}

	private static Set<Integer> getCircularNumbers(int number) {
		Set<Integer> set = new HashSet<Integer>();
		for (int j = 0; j < (int) Math.log10(number) + 1; j++) {
			number = circular(number);
			set.add(number);
		}
		return set;
	}

	private static int circular(int number) {
		int[] d = Number.getDigits(number);
		int r = 0;
		for (int i = d.length - 1; i >= 0; i--) {
			if (i == 0) {
				r += d[i] * Math.pow(10, d.length - 1);
			} else {
				r += d[i] * Math.pow(10, i - 1);
			}
		}
		return r;
	}
}