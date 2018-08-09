package net.projecteuler.java;

import net.projecteuler.java.util.Counter;

/**
 * Investigating the number of ways to fill a row with
 * separated blocks that are at least three units long.
 *
 * @author vuser
 *
 */
public class Problem114 {

	public static void main(String ... args) {
		assert solve() == 16475640049L : "failed";
	}

	private static long solve() {
		Counter counter = new Counter();

		fill(50, 0, counter);

		return counter.getValue();
	}

	/**
	 * Solution is based on building a binary tree recursively.</br>
	 * Let Red = 1 and Black = 0.</br>
	 * The following pattern must be applied:
	 * <ul>
	 * <li>if the length <=1 the row cannot be end with 01</li>
	 * <li>the row cannot be end with 010 and 0110</li>
	 * </ul>
	 * Method is called recursively.
	 *
	 * @param length The number of units. This value is decremented in the execution.
	 * @param row
	 * @param counter
	 */
	private static void fill(int length, long row, Counter counter) {
		if (length <= 1 && (row & (1L << 0)) != 0 && (row & (1L << 1)) == 0) {
			return; // br - 01
		}
		if ((row & (1L << 0)) == 0 && (row & (1L << 1)) != 0 && (row & (1L << 2)) == 0) {
			return; // brb - 010
		}
		if ((row & (1L << 0)) == 0 && (row & (1L << 1)) != 0 && (row & (1L << 2)) != 0 && (row & (1L << 3)) == 0) {
			return; // brrb - 0110
		}
		
		if (length == 0) {
			counter.increment();
		} else {
			long l = Long.rotateLeft(row, 1);
			fill(length - 1, l, counter);
			fill(length - 1, l + 1, counter);
		}
	}
}