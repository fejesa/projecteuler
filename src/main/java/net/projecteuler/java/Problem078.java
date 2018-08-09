package net.projecteuler.java;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Let p(<i>n</i>) represent the number of different ways in which n coins
 * can be separated into piles. For example, five coins can separated into piles
 * in exactly seven different ways, so p(<i>5</i>) = 7.
 * <p>
 * <table cellspacing='0' cellpadding='5'>
 * <tr><td>OOOOO</td></tr>
 * <tr><td>OOOO&nbsp; &nbsp;O</td></tr>
 * <tr><td>OOO&nbsp; &nbsp;OO</td></tr>
 * <tr><td>OOO&nbsp; &nbsp;O&nbsp; &nbsp;O</td></tr>
 * <tr><td>OO&nbsp; &nbsp;OO&nbsp; &nbsp;O</td></tr>
 * <tr><td>OO&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O</td></tr>
 * <tr><td>O&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O</td></tr>
 * </table></p>
 * Find the least value of n for which p(<i>n</i>) is divisible by one million.
 *
 * @author vuser
 *
 */
public class Problem078 {

	public static void main(String[] args) {
		assert calculate() == 55374 : "failed";
	}

	/**
	 * Solution is based on
	 * <a href="http://en.wikipedia.org/wiki/Pentagonal_number_theorem">Pentagonal number theorem</a>
	 * @see <a href="http://www.numericana.com/answer/numbers.htm#partitions">Partition Function</a>
	 * @return
	 */
	private static int calculate() {

		BigInteger MILLION = BigInteger.valueOf(1000000);
		BigInteger MINUS_ONE = BigInteger.valueOf(-1);

		List<BigInteger> p = new ArrayList<BigInteger>();
		p.add(BigInteger.ONE);

		int number = 1;
		search: for (; ; number++) {
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
			if (s.divideAndRemainder(MILLION)[1].equals(BigInteger.ZERO)) {
				break search;
			}
			p.add(s);
		}

		return number;
	}
}
/*
 * j=1 : k=1 : s=0
  while j>0
    j = i-(3*k*k+k)\2
    if j>=0 then s = s - (-1)^k*P(j)
    j = i-(3*k*k-k)\2
    if j>=0 then s = s - (-1)^k*P(j)
    k = k+1
  wend
  p(i) = s
 * */
