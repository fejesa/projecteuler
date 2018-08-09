package net.projecteuler.java;

/**
 * The following iterative sequence is defined for the set of positive integers:
 * <ul><li>n -> n/2 (n is even)</li>
 * <li>n -> 3n + 1 (n is odd)</li></ul>
 * Using the rule above and starting with 13, we generate the following sequence:</br>
 * 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1</br>
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
 * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * Which starting number, under one million, produces the longest chain?
 *
 * @author vuser
 *
 */
public class Problem014 {

	public static void main(String[] args) {
		assert calculate(1000000) == 837799L : "failed";
	}

	private static long calculate(long limit) {
		long length = 0;
		long n = 0;
		for (long i = limit; i > limit / 2; i--) {
			long number = i;
			long chain = 0;
			while (number > 1) {
				if (number % 2 == 0) {
					number /= 2;
				} else {
					number = 3 * number + 1;
				}
				++chain;
			}
			if (chain > length) {
				length = chain;
				n = i;
			}
		}
		return n;
	}
}
