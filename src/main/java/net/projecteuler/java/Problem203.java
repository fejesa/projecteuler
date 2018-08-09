package net.projecteuler.java;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.projecteuler.java.util.Number;

/**
 * The binomial coefficients nCk can be arranged in triangular form, Pascal's triangle, like this:
 * 1</br>
 * 1   1</br>
 * 1   2   1</br>
 * 1   3   3   1</br>
 * 1   4   6   4   1</br>
 * 1   5   10   10   5   1</br>
 * 1   6   15   20   15   6   1</br>
 * 1   7   21   35   35   21   7   1</br>
 * .........</br>
 * It can be seen that the first eight rows of Pascal's triangle contain twelve distinct
 * numbers: 1, 2, 3, 4, 5, 6, 7, 10, 15, 20, 21 and 35.</br>
 * A positive integer n is called squarefree if no square of a prime divides n.
 * Of the twelve distinct numbers in the first eight rows of Pascal's triangle,
 * all except 4 and 20 are squarefree. The sum of the distinct squarefree numbers
 * in the first eight rows is 105.</br>
 * Find the sum of the distinct squarefree numbers in the first 51 rows of Pascal's triangle.
 *
 * @author vuser
 *
 */
public class Problem203 {

	public static void main(String... args) {
		assert solve() == 34029210557338L : "failed";
	}

	private static long solve() {
		long sum = 0;
		Set<Long> numbers = new HashSet<Long>();
		for (int n = 2; n < 51; n++) {
			BigInteger nfactor = factor(n);
			for (int k = 1; k <= n / 2 + 1; k++) {
				BigInteger kfactor = factor(k);
				BigInteger nkfactor = factor(n - k);
				long number = nfactor.divide(kfactor).divide(nkfactor).longValue();
				if (numbers.add(number) && isSquarefree(number)) {
					sum += number;
				}
			}
		}

		return sum;
	}

	private static boolean isSquarefree(long number) {
		Map<Long, Integer> primeExponents = Number.primeExponent(number);
		for (Map.Entry<Long, Integer> entry : primeExponents.entrySet()) {
			if (entry.getValue() >= 2) {
				return false;
			}
		}
		return true;
	}

	private static BigInteger factor(int n) {
		BigInteger factor = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			factor = factor.multiply(BigInteger.valueOf(i));
		}
		return factor;
	}
}