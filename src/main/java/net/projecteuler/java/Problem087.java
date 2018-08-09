package net.projecteuler.java;

import java.util.BitSet;
import java.util.List;

import net.projecteuler.java.util.PrimeGenerator;

/**
 * The smallest number expressible as the sum of a prime square, prime cube,
 * and prime fourth power is 28. In fact, there are exactly four numbers below
 * fifty that can be expressed in such a way:
 * <p>
 * 28 = 2<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup></br>
 * 33 = 3<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup></br>
 * 49 = 5<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup></br>
 * 47 = 2<sup>2</sup> + 3<sup>3</sup> + 2<sup>4</sup>
 * </p>
 * How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and prime fourth power?
 *
 * @author vbox
 *
 */
public class Problem087 {

	public static void main(String[] args) {
		assert calculate(50000000) == 1097343 : "failed";
	}

	private static int calculate(int limit) {

		// Generates primes: Pmax^2 ~ limit
		PrimeGenerator primeGenerator = new PrimeGenerator((int) Math.floor(Math.sqrt(limit)) + 10);

		// BitSet is used to set a given number/index if it can be produced as p1^2 + p2^3 + p4^4
		// because there are numbers that can be produced more than once
		BitSet numbers = new BitSet(limit);
		numbers.set(0, limit, false);

		List<Integer> list = primeGenerator.getPrimeList();

		for (int a : list) {
			int powera = a * a;
			searchB: for (int b : list) {
				int powerb = b * b * b;
				if (powera + powerb > limit) {
					break searchB;
				}

				searchC: for (int c : list) {
					int powerc = c * c * c * c;
					int sum = powera + powerb + powerc;
					if (sum > limit) {
						break searchC;
					}
					// Set the index - the number is passed
					numbers.set(sum);
				}
			}
		}

		// Set the number of set bit - the solution
		return numbers.cardinality();
	}
}
