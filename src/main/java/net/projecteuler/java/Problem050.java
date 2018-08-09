package net.projecteuler.java;

import java.util.ArrayList;
import java.util.List;

import net.projecteuler.java.util.Number;

/**
 * The prime 41, can be written as the sum of six consecutive primes:</br>
 * 41 = 2 + 3 + 5 + 7 + 11 + 13</br>
 * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
 * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms,
 * and is equal to 953.</br>
 * Which prime, below one-million, can be written as the sum of the most consecutive primes?
 *
 * @author vuser
 *
 */
public class Problem050 {

	public static void main(String[] args) {
		assert calculate() == 997651 : "failed";
	}

	private static int calculate() {
		int number = 0;
		int max = 0;
		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i < 1000000; i++) {
			if (Number.isPrime(i)) {
				primes.add(i);
				int cn = getConsecutiveNumber(i, primes);
				if (cn > max) {
					max = cn;
					number = i;
				}
			}
		}
		return number;
	}

	private static int getConsecutiveNumber(int number, List<Integer> primes) {
		int max = 0;
		for (int i = 0; i < primes.size() - max; i++) {
			int sum = 0; int counter = 0;
			search: for (int j = i; j < primes.size() - max && sum < number; j++) {
				sum += primes.get(j);
				counter++;
				if (sum == number) {
					max = Math.max(max, counter);
					break search;
				}
			}
		}
		return max;
	}
}