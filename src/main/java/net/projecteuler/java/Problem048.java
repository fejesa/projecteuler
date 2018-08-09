package net.projecteuler.java;

import java.math.BigInteger;

/**
 * The series, 1<sup>1</sup> + 2<sup>2</sup> + 3<sup>3</sup> + ... + 10<sup>10</sup> = 10405071317.</br>
 * Find the last ten digits of the series, 1<sup>1</sup> + 2<sup>2</sup> + 3<sup>3</sup> + ... + 1000<sup>1000</sup>.
 *
 * @author vuser
 *
 */
public class Problem048 {

	public static void main(String[] args) {
		assert calculate().equals("9110846700") : "failed";
	}

	private static String calculate() {
		BigInteger sum = BigInteger.ZERO;
		for (int i = 1; i <= 1000; i++) {
			BigInteger bi = BigInteger.valueOf(i);
			bi = bi.pow(i);
			sum = sum.add(bi);
		}
		String s = sum.toString();
		return s.substring(s.length() - 10);
	}
}
