package net.projecteuler.java;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * The Fibonacci sequence is defined by the recurrence relation:</br>
 * Fn = Fn1 + Fn2, where F1 = 1 and F2 = 1.</br>
 * Hence the first 12 terms will be:
 * F1 = 1</br>
 * F2 = 1</br>
 * F3 = 2</br>
 * F4 = 3</br>
 * F5 = 5</br>
 * F6 = 8</br>
 * F7 = 13</br>
 * F8 = 21</br>
 * F9 = 34</br>
 * F10 = 55</br>
 * F11 = 89</br>
 * F12 = 144</br>
 * The 12th term, F12, is the first term to contain three digits. What is the first term in the Fibonacci sequence to contain 1000 digits?
 *
 * @author vbox
 *
 */
public class Problem025 {

	public static void main(String[] args) {
		assert calculate(1000) == 4782 : "failed";
	}

	private static int calculate(int limit) {
		for (int i = 1;; ++i) {
			String digits = fibonacci(i).toString();
			if (digits.length() >= limit) {
				return i;
			}
		}
	}

	// Memoised Fibonacci operation, useful if making many calculations, will
	// raise a heap exception if the size of fibList gets too big however
	private static ArrayList<BigInteger> list = new ArrayList<BigInteger>();

	static {
		list.add(BigInteger.ZERO);
		list.add(BigInteger.ONE);
	}

	private static BigInteger fibonacci(int num) {
		if (num <= 0) {
			return list.get(0);
		} else if (num == 1) {
			return list.get(1);
		}

		while (list.size() <= num) {
			list.add(list.get(list.size() - 1).add(list.get(list.size() - 2)));
		}

		return list.get(num);
	}
}