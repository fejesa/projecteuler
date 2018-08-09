package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * Euler's Totient function, φ(n) [sometimes called the phi function],
 * is used to determine the number of numbers less than n which are relatively
 * prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than
 * nine and relatively prime to nine, φ(9)=6.</br>
 * n    Relatively Prime φ(n) n/φ(n)</br>
 * 2    1                1    2</br>
 * 3    1,2              2    1.5</br>
 * 4    1,3              2    2</br>
 * 5    1,2,3,4          4    1.25</br>
 * 6    1,5              2    3</br>
 * 7    1,2,3,4,5,6      6    1.1666...</br>
 * 8    1,3,5,7          4    2</br>
 * 9    1,2,4,5,7,8      6    1.5</br>
 * 10   1,3,7,9          4    2.5</br>
 * It can be seen that n=6 produces a maximum n/φ(n) for n <= 10.</br>
 * Find the value of n <=1,000,000 for which n/φ(n) is a maximum.
 *
 * @author vuser
 *
 */
public class Problem069 {

	public static void main(String[] args) {
		assert calculate() == 510510L : "failed";
	}

	private static long calculate() {
		long number = 1;
		double max = 0;

		// Iteration step is 30, see more information:
		// http://en.wikipedia.org/wiki/Totient#Computing_example
		for (long i = 0; i <= 1000000; i += 30) {
			long totient = Number.getTotient(i);
			double quotient = (double) i / totient;

			if (max < quotient) {
				max = quotient;
				number = i;
			}
		}

		return number;
	}
}
