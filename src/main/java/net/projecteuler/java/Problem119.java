package net.projecteuler.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.projecteuler.java.util.Number;

/**
 * The number 512 is interesting because it is equal to the
 * sum of its digits raised to some power: 5 + 1 + 2 = 8, and 8<sup>3</sup> = 512.</br>
 * Another example of a number with this property is 614656 = 28<sup>4</sup>.</br>
 * We shall define an to be the n<i>th</i> term of this sequence
 * and insist that a number must contain at least two digits to have a sum.</br>
 * You are given that a<sub>2</sub> = 512 and a<sub>10</sub> = 614656.</br>
 * Find a<sub>30</sub>.
 *
 * @author vuser
 *
 */
public class Problem119 {

	public static void main(String ... args) {
		assert calculate() == 248155780267521L : "failed";
	}

	private static long calculate() {
		List<Long> list = new ArrayList<Long>();
		for (long base = 2; base < 100; base++) {
			for (int pow = 2; pow < 20; pow++) {
				long number = (long) Math.pow(base, pow);
				long digitSum = Number.getDigitSum(number);

				if (digitSum == base) {
					list.add(number);
				}
			}
		}
		Collections.sort(list);
		return list.get(29);
	}
}
