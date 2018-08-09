package net.projecteuler.java;

import net.projecteuler.java.util.Number;
import net.projecteuler.java.util.PermutationGenerator;

/**
 * The number, 1406357289, is a 0 to 9 pandigital number because it is
 * made up of each of the digits 0 to 9 in some order, but it also has
 * a rather interesting sub-string divisibility property.</br>
 * Let d<sub>1</sub> be the 1<sup>st</sup> digit,  d<sub>2</sub> be the 2<sup>nd</sup> digit,
 * and so on. In this way, we note the following:
 * <ul>
 * <li>d<sub>2</sub>d<sub>3</sub>d<sub>4</sub>=406 is divisible by 2</li>
 * <li>d<sub>3</sub>d<sub>4</sub>d<sub>5</sub>=063 is divisible by 3</li>
 * <li>d<sub>4</sub>d<sub>5</sub>d<sub>6</sub>=635 is divisible by 5</li>
 * <li>d<sub>5</sub>d<sub>6</sub>d<sub>7</sub>=357 is divisible by 7</li>
 * <li>d<sub>6</sub>d<sub>7</sub>d<sub>8</sub>=572 is divisible by 11</li>
 * <li>d<sub>7</sub>d<sub>8</sub>d<sub>9</sub>=728 is divisible by 13</li>
 * <li>d<sub>8</sub>d<sub>9</sub>d<sub>10</sub>=289 is divisible by 17</li>
 * </ul>
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 *
 * @author vuser
 *
 */
public class Problem043 {

	public static void main(String[] args) {
		assert calculate() == 16695334890L : "failed";
	}

	public static long calculate() {
		long sum = 0;
		PermutationGenerator permutations = new PermutationGenerator(10);
		while (permutations.hasMore()) {
			int[] p = permutations.getNext();
			if (p[0] != 0) {
				if (getPartialNumber(p, 2, 4) % 2 == 0) {
					if (getPartialNumber(p, 3, 5) % 3 == 0) {
						if (getPartialNumber(p, 4, 6) % 5 == 0) {
							if (getPartialNumber(p, 5, 7) % 7 == 0) {
								if (getPartialNumber(p, 6, 8) % 11 == 0) {
									if (getPartialNumber(p, 7, 9) % 13 == 0) {
										if (getPartialNumber(p, 8, 10) % 17 == 0) {
											sum += Number.toNumber(p);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return sum;
	}

	private static int getPartialNumber(int[] digits, int begin, int end) {
		int number = 0; int factor = 1;
		for (int i = digits.length - end; i <= digits.length - begin; i++) {
			number += digits[i] * factor;
			factor *= 10;
		}
		return number;
	}
}