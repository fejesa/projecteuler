package net.projecteuler.java;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * It is possible to write five as a sum in exactly six different ways:<p>
 * 4 + 1</br>
 * 3 + 2</br>
 * 3 + 1 + 1</br>
 * 2 + 2 + 1</br>
 * 2 + 1 + 1 + 1</br>
 * 1 + 1 + 1 + 1 + 1</br>
 * </p>
 * How many different ways can one hundred be written as a sum of at least two positive integers?
 *
 * @author vuser
 *
 */
public class Problem076 {

	public static void main(String[] args) {
		assert calculate(100) == 190569291L : "failed";
	}

	/**
	 * Solution is based on
	 * <a href="http://en.wikipedia.org/wiki/Pentagonal_number_theorem">Pentagonal number theorem</a>
	 * @see <a href="http://www.numericana.com/answer/numbers.htm#partitions">Partition Function</a>
	 * @return
	 */
	private static long calculate(int limit) {

		BigInteger MINUS_ONE = BigInteger.valueOf(-1);

		List<BigInteger> p = new ArrayList<BigInteger>();
		p.add(BigInteger.ONE);

		for (int number = 1; number <= limit; number++) {
			BigInteger s = BigInteger.ZERO;
			for (int j = 1, k = 1; j > 0; ++k) {
				j = number - (3 * k * k + k) / 2;
				if (j >= 0) {
					s = s.subtract(MINUS_ONE.pow(k).multiply(p.get(j)));
				}
				j = number - (3 * k * k - k) / 2;
				if (j >= 0) {
					s = s.subtract(MINUS_ONE.pow(k).multiply(p.get(j)));
				}
			}
			p.add(s);
		}

		// Gets the last element and
		// subtracts 1, because the problem definition says 'at least two integers..'
		return p.get(limit).intValue() - 1;
	}
}