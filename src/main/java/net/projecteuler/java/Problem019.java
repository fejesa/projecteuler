package net.projecteuler.java;

import org.joda.time.DateTime;

/**
 * You are given the following information, but you may prefer to do some research for yourself.
 * <ul><li>1 Jan 1900 was a Monday.</li>
 * <li>Thirty days has September, April, June and November.</li>
 * <li>All the rest have thirty-one,</li>
 * <li>Saving February alone, which has twenty-eight, rain or shine.</li>
 * <li>And on leap years, twenty-nine. A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.</li></ul>
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 *
 * @author vbox
 *
 */
public class Problem019 {

	private static int[] MONTHS = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public static void main(String[] args) {
		assert calculate(1901, 2000) == 171 : "failed";
	}

	private static int calculate(int start, int end) {
		int sundays = 0;
		DateTime dt = new DateTime(start, 1, 1, 0, 0, 0, 0);
		for (int year = start; year <= end; ++year) {
			for (int i = 0; i < MONTHS.length; i++) {
				dt = dt.plusDays(MONTHS[i]);
				if (i == 1 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
					dt = dt.plusDays(1);
				}
				if (dt.getDayOfWeek() == 7) {
					++sundays;
				}
			}
		}
		return sundays;
	}
}