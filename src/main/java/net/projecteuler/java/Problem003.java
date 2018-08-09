package net.projecteuler.java;

import java.util.List;

import net.projecteuler.java.util.Number;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29. What is the largest prime
 * factor of the number 600851475143 ?
 * 
 * @author vuser
 * 
 */
public class Problem003 {

	private static long getLargest(List<Long> primeFactors) {
		long largest = 1;
		for (Long prime : primeFactors) {
			if (prime > largest) {
				largest = prime;
			}
		}
		return largest;
	}

	public static void main(String[] args) {
		List<Long> primeFactors = Number.factor(600851475143L);
		assert getLargest(primeFactors) == 6857 : "failed";
	}
}