package net.projecteuler.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.projecteuler.java.util.Number;

/**
 * A perfect number is a number for which the sum of its proper divisors is exactly
 * equal to the number. For example, the sum of the proper divisors of 28 would
 * be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.</br>
 * A number n is called deficient if the sum of its proper divisors is less
 * than n and it is called abundant if this sum exceeds n.</br>
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest
 * number that can be written as the sum of two abundant numbers is 24.</br>
 * By mathematical analysis, it can be shown that all integers greater than 28123
 * can be written as the sum of two abundant numbers. However, this upper limit
 * cannot be reduced any further by analysis even though it is known that the
 * greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.</br>
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 *
 * @author vuser
 *
 */
public class Problem023 {

	public static void main(String[] args) {
		assert calculate() == 4179871L : "failed";
	}

	private static long calculate() {
		long sum = 0;

		long limit = 20161; 
		List<Long> abundants = new ArrayList<Long>();
		for (long i = 0; i <= limit; i++) {
			Set<Long> divisors = Number.getDivisors(i);
			long s = Number.divisorsSum(divisors);
			if (s > i) {
				abundants.add(i);
			} else {
				abundants.add(0L);
			}
		}

		for (int i = 1; i <= limit; i++) {
			boolean passed = false;
			for(int j = 0; j < i; j++) {
				Long abundant = abundants.get(j);
				if (abundant > 0 && abundant + abundants.get(i - j) == i) {
					passed = true;
					break;
				}
			}
			if (!passed) {
				sum += i;
			}
		}

		return sum;
	}
}