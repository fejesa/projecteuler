package net.projecteuler.java;

import java.util.ArrayList;
import java.util.List;

import net.projecteuler.java.util.Number;

/**
 * The number 145 is well known for the property that the sum of the factorial of
 * its digits is equal to 145:<br>
 * 1! + 4! + 5! = 1 + 24 + 120 = 145</br>
 * Perhaps less well known is 169, in that it produces the longest chain of
 * numbers that link back to 169; it turns out that there are only
 * three such loops that exist:</br>
 * 169 -> 363601 -> 1454 -> 169</br>
 * 871 -> 45361 -> 871</br>
 * 872 -> 45362 -> 872</br>
 * It is not difficult to prove that EVERY starting number will eventually
 * get stuck in a loop. For example,</br>
 * 69 -> 363600 -> 1454 -> 169 -> 363601 (-> 1454)</br>
 * 78 -> 45360 -> 871 -> 45361 (-> 871)</br>
 * 540 -> 145 (-> 145)</br>
 * Starting with 69 produces a chain of five non-repeating terms, but the longest
 * non-repeating chain with a starting number below one million is sixty terms.</br>
 * How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?
 *
 * @author vuser
 *
 */
public class Problem074 {

	public static void main(String[] args) {
		assert calculate() == 402 : "failed";
	}

	private static int[] factorials = new int[] {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

	private static int calculate() {
		int counter = 0;

		for (int i = 1; i < 1000000; i++) {
			List<Integer> chain = new ArrayList<Integer>();
			chain.add(i);

			chain: for (int number = i; ;) {
				int next = getNext(number); // Produces the next number in the chain

				if (chain.contains(next)) {
					if (chain.size() == 60) {
						++counter;
					}
					break chain;
				}

				chain.add(next);
				number = next;

			}
		}

		return counter;
	}

	private static int getNext(int number) {
		int[] digits = Number.getDigits(number);
		int next = 0;
		for (int d : digits) {
			next += factorials[d];
		}
		return next;
	}
}