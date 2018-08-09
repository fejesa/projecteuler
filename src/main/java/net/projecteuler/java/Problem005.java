package net.projecteuler.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.Number;

/**
 * 2520 is the smallest number that can be divided by each of the
 * numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 *
 * @author vuser
 *
 */
public class Problem005 {

	public static void main(String[] args) {
		List<Long> numbers = new ArrayList<Long>();
		for (int i = 2; i < 21; i++) {
			numbers.add(Long.valueOf(i));
		}
		assert lcm(numbers) == 232792560 : "failed";
	}

	
	
	private static long lcm(List<Long> numbers) {
		Map<Long, Integer> factors = new HashMap<Long, Integer>();

		for (Long n : numbers) {
			Map<Long, Integer> map = Number.primeExponent(n);

			for (Map.Entry<Long, Integer> entry : map.entrySet()) {
				Long prime = entry.getKey();
				Integer power = entry.getValue();

				Integer primePower = factors.get(prime);
				if (primePower == null) {
					factors.put(prime, power);
				} else if (primePower.compareTo(power) < 0) {
					factors.put(prime, power);
				}
			}
		}

		long lcm = 1;
		for (Map.Entry<Long, Integer> entry : factors.entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				lcm *= entry.getKey();
			}
		}

		return lcm;
	}
}