package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * Consider the fraction, n/d, where n and d are positive integers. If nd and HCF(n,d)=1,
 * it is called a reduced proper fraction.</br>
 * If we list the set of reduced proper fractions for d <= 8 in ascending order of size, we get:
 * <p>
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * </p>
 * It can be seen that there are 21 elements in this set.</br>
 * How many elements would be contained in the set of reduced proper fractions for d 1,000,000?
 *
 * @author vuser
 *
 */
public class Problem072 {

	public static void main(String[] args) {
		assert calculate(1000000) == 303963552391L : "failed";
	}

	/**
	 * Length of the sequence is related to the Euler totient.
	 * @see <a href="http://en.wikipedia.org/wiki/Farey_sequence#Sequence_length">Farey sequence length</a>
	 */
	private static long calculate(int limit) {
		long length = 1;
		Number.generatePrimes(limit);
		for (int i = 1; i <= limit; i++) {
			length += Number.getTotient(i);
		}
		return length - 2;
	}
}