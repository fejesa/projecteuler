package net.projecteuler.java;

import java.util.ArrayList;
import java.util.List;

/**
 * A unit fraction contains 1 in the numerator. The decimal representation of
 * the unit fractions with denominators 2 to 10 are given:</br>
 * 1/2 = 0.5</br>
 * 1/3 = 0.(3)</br>
 * 1/4 = 0.25</br>
 * 1/5 = 0.2</br>
 * 1/6 = 0.1(6)</br>
 * 1/7 = 0.(142857)</br>
 * 1/8 = 0.125</br>
 * 1/9 = 0.(1)</br>
 * 1/10	= 0.1</br>
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle.
 * It can be seen that 1/7 has a 6-digit recurring cycle.</br>
 * Find the value of d  1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 *
 * @author vuser
 *
 */
public class Problem026 {

	public static void main(String[] args) {
		assert calculate(1000) == 983 : "failed";
	}

	private static int calculate(int limit) {
		int d = 0;
		int recurringLength = 0;
		for (int i = 2; i < limit; i++) {
			int length = getRecurringLength(i);
			if (length > recurringLength) {
				recurringLength = length; 
				d = i;
			}
		}
		return d;
	}

	private static int getRecurringLength(int n) {
		List<Integer> reminders = new ArrayList<Integer>();
		int numerator = 1;
		for (int i = 0; i < n; i++) {
			int reminder = numerator % n;
			numerator = reminder * 10;
			if (reminder == 0 || reminders.contains(reminder)) {
				break;
			}
			reminders.add(reminder);
		}

		return reminders.size();
	}
}
