package net.projecteuler.java;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * It is possible to show that the square root of two can be expressed as an infinite continued fraction.<p>
 * sqrt(2) = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...</p>
 * By expanding this for the first four iterations, we get:<p>
 * 1 + 1/2 = 3/2 = 1.5</br>
 * 1 + 1/(2 + 1/2) = 7/5 = 1.4</br>
 * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...</br>
 * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...</br></p>
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion,
 * 1393/985, is the first example where the number of digits in the numerator exceeds the number
 * of digits in the denominator.</br>
 * In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
 * @author vuser
 *
 */
public class Problem057 {

	public static void main(String[] args) {
		assert calculate() == 153 : "failed";
	}

	/**
	 * @see <a href="http://en.wikipedia.org/wiki/Pell_number">Pell numbers</a>
	 * @return
	 */
	private static int calculate() {
		int sum = 0;

		List<BigInteger> pellNumbers = new ArrayList<BigInteger>();
		pellNumbers.add(BigInteger.ZERO);
		pellNumbers.add(BigInteger.ONE);

		for (int i = 2; i <= 1000; i++) {
			BigInteger pn = pellNumbers.get(i - 1).multiply(BigInteger.valueOf(2)).add(pellNumbers.get(i - 2));
			pellNumbers.add(pn);

			int numeratorLength = pellNumbers.get(i - 1).add(pn).toString().length();
			int denominatorLength = pn.toString().length();
			if (numeratorLength > denominatorLength) {
				++sum;
			}
		}

		return sum;
	}
}