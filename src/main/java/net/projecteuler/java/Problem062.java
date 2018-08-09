package net.projecteuler.java;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.Number;

/**
 * The cube, 41063625 (345<sup>3</sup>), can be permuted to produce two other
 * cubes: 56623104 (384<sup>3</sup>) and 66430125 (405<sup>3</sup>). In fact,
 * 41063625 is the smallest cube which has exactly three permutations of its
 * digits which are also cube.</br> Find the smallest cube for which exactly
 * five permutations of its digits are cube.
 * 
 * @author vbox
 * 
 */
public class Problem062 {

	public static void main(String[] args) {
		assert calculate().equals(new BigInteger("127035954683")) : "failed";
	}

	private static BigInteger calculate() {

		// Contains the char sequence of the cube as key and the list of cubes
		// as value
		Map<String, List<BigInteger>> map = new HashMap<String, List<BigInteger>>();

		BigInteger result = null;

		search: for (int i = 1;; i++) {
			BigInteger bi = BigInteger.valueOf(i);
			BigInteger cube = bi.multiply(bi).multiply(bi);

			String order = order(cube);

			List<BigInteger> cubes = map.get(order);
			if (cubes == null) {
				cubes = new ArrayList<BigInteger>();
				map.put(order, cubes);
			}

			if (cubes.size() >= 4) {
				result = cubes.get(0); // Gets the smallest value
				break search;
			}

			cubes.add(cube);
		}

		return result;
	}

	/**
	 * Produces the ordered list of the given number as char sequence.
	 * @param number The number.
	 * @return
	 */
	private static String order(BigInteger number) {
		List<Integer> list = new ArrayList<Integer>();

		int[] digits = Number.getDigits(number);
		for (int i = 0; i < digits.length; i++) {
			list.add(digits[i]);
		}

		Collections.sort(list);

		StringBuilder builder = new StringBuilder();
		for (int i : list) {
			builder.append(i);
		}

		return builder.toString();
	}
}