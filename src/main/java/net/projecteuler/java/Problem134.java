package net.projecteuler.java;

import java.util.List;

import net.projecteuler.java.util.ExtendedEuclid;
import net.projecteuler.java.util.PrimeGenerator;

/**
 * Consider the consecutive primes p<sub>1</sub> = 19 and p<sub>2</sub> = 23. It
 * can be verified that 1219 is the smallest number such that the last digits
 * are formed by p1 whilst also being divisible by p<sub>2</sub>.
 * <p>
 * In fact, with the exception of p<sub>1</sub> = 3 and p<sub>2</sub> = 5, for
 * every pair of consecutive primes, p<sub>2</sub> > p<sub>1</sub>, there exist
 * values of n for which the last digits are formed by p<sub>1</sub> and n is
 * divisible by p<sub>2</sub>. Let S be the smallest of these values of n.
 * </p>
 * Find S for every pair of consecutive primes with 5 <= p1 <= 1000000.
 * 
 * @author vuser
 * 
 */
public class Problem134 {

	public static void main(String... args) {
		assert solve() == 18613426663617118L : "failed";
	}

	private static long solve() {
		long sum = 0;
		int limit = 1000000;
		PrimeGenerator sieve = new PrimeGenerator(limit + 10);
		List<Integer> primes = sieve.getPrimeList();
		for (int i = 0; i < primes.size(); i++) {
			int p1 = primes.get(i);
			if (p1 < 5) {
				continue;
			}
			if (p1 > limit) {
				break;
			}
			int p2 = primes.get(i + 1);
			int d = (int) (Math.floor(Math.log10(p1)) + 1);
			long a = ExtendedEuclid.algorithm(p2, (int) Math.pow(10, d));
			long s = (long) (((a * p1) % Math.pow(10, d)) * p2);

			sum += s;
		}

		return sum;
	}
}
