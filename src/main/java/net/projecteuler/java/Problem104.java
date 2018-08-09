package net.projecteuler.java;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem104 {

	public static void main(String[] args) {
		System.out.println(calculate());
	}
	
	private static int calculate() {
		int i = 3;
		BigInteger fn1 = BigInteger.ONE;
		BigInteger fn2 = BigInteger.ONE;
		search: for (; ; i++) {
			BigInteger fn = fn2.add(fn1);
			String s = fn.toString();
			//System.out.println(i + ":" + s);
			
			if (s.length() >= 9 && checkDigits(s.substring(0, 9)) && checkDigits(s.substring(s.length() - 9))) {
				break search;
			}
			/*if (i > 100) {
				break search;
			}*/
			fn2 = fn1;
			fn1 = fn;
		}
		return i;
	}

	private static boolean checkDigits(String s) {
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < s.length(); i++) {
			set.add(s.charAt(i) - 48);
		}

		return set.size() == 9 && !set.contains(0);
	}
}
