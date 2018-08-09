package net.projecteuler.java;

/**
 * The positive integers, x, y, and z, are consecutive terms of an arithmetic
 * progression. Given that n is a positive integer, the equation,
 * x<sup>2</sup> - y<sup>2</sup> - z<sup>2</sup> = n, has exactly one solution when n = 20:</br>
 * 13<sup>2</sup> - 10<sup>2</sup> - 7<sup>2</sup> = 20</br>
 * In fact there are twenty-five values of n below one hundred for
 * which the equation has a unique solution.</br>
 * How many values of n less than fifty million have exactly one solution?
 *
 * @author vuser
 */
public class Problem136 {

	public static void main(String ... args) {
		assert solve() == 2544559 : "failed";
	}

	/**
	 * Solution is based on {@link Problem135}
	 */
	private static int solve() {
		int limit = 50000000;
		int[] array = new int[limit];
		for (int x = 1; x < limit; x++) {
			int j = 4 - x % 4;
			while (j < 3 * x && x * j < limit) {
				++array[j * x];
				j += 4;
			}
		}
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				++count;
			}
		}
		return count;
	}
}