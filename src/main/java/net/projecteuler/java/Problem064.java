package net.projecteuler.java;

import java.util.List;

import net.projecteuler.java.util.ContinuedFraction;
import net.projecteuler.java.util.Pattern;

/**
 * How many continued fractions for <i>N</i> <= 10000 have an odd period?
 *
 * @author vuser
 *
 */
public class Problem064 {

	public static void main(String[] args) {
		assert calculate(10000) == 1322 : "failed";
	}

	private static int calculate(int limit) {
		int counter = 0;
		for (int i = 2; i <= limit; i++) {
			List<Integer> fractions = ContinuedFraction.toContinuedFraction(i);
			if (fractions.size() > 1) {
				List<Integer> period = Pattern.getPattern(fractions.subList(1, fractions.size() - 1));
				if (period.size() % 2 != 0) {
					++counter;
				}
			}
		}
		return counter;
	}
}