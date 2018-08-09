package net.projecteuler.java.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Number {

	private static PrimeGenerator primeGenerator;

	public static void generatePrimes(int max) {
		primeGenerator = new PrimeGenerator(max);
	}

	public static long getDigitSum(long number) {
		int[] digits = getDigits(number);
		long sum = 0;
		for (int i : digits) {
			sum += i;
		}
		return sum;
	}

	public static int[] getDigits(long number) {
		if (number >= 0 && number < 10) {
			return new int[] {(int) number};
		}
		int numDigits = (int) Math.log10(number) + 1;
		int[] digits = new int[numDigits];
		for (int i = 0; i < numDigits; i++) {
			digits[i] = (int)(number % 10);
			number /= 10; 
		}

		return digits;
	}

	public static int[] getDigits(java.lang.Number number) {
		String s = number.toString();
		s = s.replaceAll("\\D+", ""); // remove all non digit chars
		int[] digits = new int[s.length()];
		for (int i = 0; i < digits.length; i++) {
			if (s.charAt(i) != '.') {
				digits[i] = s.charAt(i) - 48;
			}
		}

		return digits;
	}
	
	public static long toNumber(int[] digits) {
		long number = 0; long factor = 1;
		for (int i = 0; i < digits.length; i++) {
			number += digits[i] * factor;
			factor *= 10;
		}
		return number;
	}

	public static List<Long> factor(long n) {
		List<Long> primeFactors = new ArrayList<Long>();
		return factor(n, primeFactors);
	}

	private static char[] toBinary(int i, int length) {
		char[] digits = {'0', '1'};
		char[] buf = new char[length];
		for (int k = 0; k < buf.length; k++) {
			buf[k] = digits[0];
		}
		int mask = 1;
		do {
		    buf[--length] = digits[i & mask];
		    i >>>= 1;
		} while (i != 0);
		return buf;
	}

	/**
	 * Gets all divisors of the given number. 
	 * @param number
	 * @return
	 */
	public static Set<Long> getDivisors(long number) {
		Set<Long> divisors = new HashSet<Long>();

		List<Long> primeFactors = Number.factor(number);		

		int length = primeFactors.size();
		if (length == 0) {
			return divisors;
		}

		for (int i = 0; i < Math.pow(2, length); i++) {
			char[] c = toBinary(i, length);
			
			long divisor = 1;
			for (int j = 0; j < c.length; j++) {
				if (c[j] == '1') {
					divisor *= primeFactors.get(j);
				}
			}
			divisors.add(divisor);
		}

		divisors.remove(number);

		return divisors;
	}

	public static long getTotient(long number) {
		Map<Long, Integer> map = Number.primeExponent(number);
		long totient = 1;
		for (Map.Entry<Long, Integer> entry : map.entrySet()) {
			// The formula for coprimes of n is
			// n * (1 - 1/p1) * (1 - 1/p2) * ... * (1 - 1/px)
			// where px are the prime factors of n
			for (int j = 0; j < entry.getValue() - 1; j++) {
				totient *= entry.getKey();
			}
			totient *= entry.getKey() - 1;
		}
		return totient;
	}

	/**
	 * Calculates the number of divisors of the given number.
	 * Solution is based on the following: the exponents of the prime
	 * factors must be incremented by 1, and then multiplies them,
	 * so we get the number of divisors. For example:</br>
	 * 28 = 2<sup>2</sup> * 7<sup>1</sup>, therefore 3 * 2 = 6 divisors has the number of 28. 
	 * @param factor
	 * @return
	 */
	public static long getDivisorsNumber(long number) {
		Map<Long, Integer> map = primeExponent(number);
		long product = 1;
		for (long power : map.values()) {
			product *= power + 1;
		}
		return product;
	}

	/**
	 * @see #primeExponent(List)
	 */
	public static Map<Long, Integer> primeExponent(long number) {
		return primeExponent(factor(number));
	}

	/**
	 * Mapping the primes and their power's.
	 * @param primeFactors
	 * @return
	 */
	public static Map<Long, Integer> primeExponent(List<Long> primeFactors) {
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		for (Long p : primeFactors) {
			Integer power = map.get(p);
			if (power == null) {
				map.put(p, 1);
			} else {
				map.put(p, ++power);
			}
		}
		return map;
	}

	public static boolean isPalindrome(long number) {
		return isPalindrome(BigInteger.valueOf(number));
	}

	public static boolean isPalindrome(BigInteger number) {
		if (number.intValue() >= 0 && number.intValue() < 10) {
			return true;
		}
		String s = number.toString();
		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) - s.charAt(s.length() - i - 1) != 0) {
				return false;
			}
		}
		return true;
	}

	public static int getNDigitPandigital(long number) {
		int[] digits = getDigits(number);
		if (digits.length > 1) {
			int[] pandigits = new int[digits.length];
			for (int i = 0; i < digits.length; i++) {
				int d = digits[i];
				if (d > 0 && d <= pandigits.length && pandigits[d - 1] == 0) {
					pandigits[d - 1] = d;
				} else {
					return 0;
				}
			}
		}
		return digits.length;
	}

	public static long divisorsSum(Set<Long> divisors) {
		long sum = 0;
		for (Long div : divisors) {
			sum += div;
		}
		return sum;
	}

	public static boolean isPrime(long n) {

		if (primeGenerator != null) {
			return primeGenerator.isPrime((int) n);
		}

		if (n <= 1) {
			return false;
		}

		if (n == 2) {
			return true;
		}

		if (n % 2 == 0) {
			return false;
		}

		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	public static Set<Integer> getDigitSet(long number) {
		int[] digits = getDigits(number);
		Set<Integer> set = new HashSet<Integer>();
		for (int j = 0; j < digits.length; j++) {
			set.add(digits[j]);
		}
		return set;
	}

	/**
	 * Produces the reverse of the given number.
	 * @param number The number that should be reversed.
	 * @return The reversed number.
	 */
	public static long reverse(long number) {
		return reverse(BigInteger.valueOf(number)).longValue();
	}

	public static BigInteger reverse(BigInteger number) {
		String s = number.toString();
		StringBuilder builder = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			builder.append(s.charAt(i));
		}
		return new BigInteger(builder.toString());
	}

	public static boolean isPermutation(long a, long b) {
		return orderDigits(a).equals(orderDigits(b));
	}

	public static List<Integer> orderDigits(long number) {
		List<Integer> list = new ArrayList<Integer>();

		int[] digits = Number.getDigits(number);
		for (int i = 0; i < digits.length; i++) {
			list.add(digits[i]);
		}

		Collections.sort(list);

		return list;
	}

	/**
	 * Produces the permutations of the digits of the given number.
	 * @param number The number.
	 * @return List of permutated number.
	 */
	public static Set<Integer> getPermutations(int number) {
		Set<Integer> permutations = new HashSet<Integer>();

		List<Integer> d = Number.orderDigits(number);
		PermutationGenerator pg = new PermutationGenerator(d.size());
		while(pg.hasMore()) {
			int[] next = pg.getNext();
			if (next[0] > 0) { // Number does not start 0
				int n = 0; int factor = 1;
				for (int i : next) {
					n += d.get(i) * factor;
					factor *= 10;
				}
				permutations.add(n);
			}
		}
		return permutations;
	}

	/**
	 * Checks the number if it is "bouncy".
	 */
	public static boolean isBouncy(BigInteger number) {
		int length = number.toString().length();
		boolean bouncy = false;
		BigInteger ten = BigInteger.valueOf(10);
		check: for (int i = 0, last = 0, lastDelta = 0; i < length; i++) {
			BigInteger[] dr = number.divideAndRemainder(ten);
			number = dr[0];
			int d = dr[1].intValue();
			if (i == 0) {
				last = d;
				continue;
			}

			// Compares with the last digit
			int delta = 0;
			if(last < d) {
				delta = 1;
			} else if (last > d) {
				delta = -1;
			}

			// Compares with the least delta
			if (delta != 0) {
				if (lastDelta != 0 && delta != lastDelta) {
					bouncy = true;
					break check;
				}
				lastDelta = delta;
			}
			last = d;
		}

		return bouncy;
	}
	
	/**
	 * Checks the number if it is "bouncy".
	 */
	public static boolean isBouncy(int number) {
		int length = (int) Math.log10(number) + 1;
		boolean bouncy = false;
		check: for (int i = 0, last = 0, lastDelta = 0; i < length; i++) {
			int d = number % 10;
			number /= 10;
			if (i == 0) {
				last = d;
				continue;
			}

			// Compares with the last digit
			int delta = 0;
			if(last < d) {
				delta = 1;
			} else if (last > d) {
				delta = -1;
			}

			// Compares with the least delta
			if (delta != 0) {
				if (lastDelta != 0 && delta != lastDelta) {
					bouncy = true;
					break check;
				}
				lastDelta = delta;
			}
			last = d;
		}

		return bouncy;
	}

	// Returns floor(sqrt(x)).
	public static int sqrt(int x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		int y = 0;
		for (int i = 32768; i != 0; i >>>= 1) {
			y |= i;
			if (y > 46340 || y * y > x)
				y ^= i;
		}
		return y;
	}

	// Returns floor(sqrt(x)).
	public static long sqrt(long x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		long y = 0;
		for (long i = 1L << 31; i != 0; i >>>= 1) {
			y |= i;
			if (y > 3037000499L || y * y > x)
				y ^= i;
		}
		return y;
	}

	private static List<Long> factor(long n, List<Long> factor) {
		if (Number.isPrime(n)) {
			factor.add(n);
		} else {
			for (int i = 2; i <= n; ++i) {
				if (n % i == 0) {
					factor(i, factor);
					n = n / i;
					i = 1;
				}
			}
		}

		return factor;
	}
}
