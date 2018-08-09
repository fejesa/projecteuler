package net.projecteuler.java;

import java.math.BigInteger;

/**
 * Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
 * where each "_" is a single digit.
 *
 * @author vuser
 *
 */
public class Problem206 {

	public static void main(String... args) {
		assert solve() == 1389019170L : "failed";
	}

	private static long solve() {
		long min = 1010101010L; // sqrt(1020304050607080900)
		long max = 1389026624L; // sqrt(1929394959697989990)

		// Because of the last digits end with 0 the number must be divided by 10.
		// Additionally the square ends with 9x0 means the number must be end with 30 or 70.
		long number = min + 20;
		for (long index = 0; number <= max; ++index) {
			// Produce the square
			String s = BigInteger.valueOf(number).pow(2).toString();
			// Checks the digits @ the given positions
			if (s.charAt(2) == '2' && s.charAt(4) == '3' && s.charAt(6) == '4'
			        && s.charAt(8) == '5' && s.charAt(10) == '6'
			        && s.charAt(12) == '7' && s.charAt(14) == '8') {
				break;
			}
			if (index % 2 == 0) {
				number += 40;
			} else {
				number += 60;
			}
		}

		return number;
	}
}