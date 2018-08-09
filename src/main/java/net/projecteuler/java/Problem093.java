package net.projecteuler.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.projecteuler.java.util.Postfix;
import net.projecteuler.java.util.VariationGenerator;

/**
 * By using each of the digits from the set, {1, 2, 3, 4}, exactly once,
 * and making use of the four arithmetic operations (+, - , *, /) and brackets/parentheses,
 * it is possible to form different positive integer targets.
 * <p>
 * For example,</br>
 * 8 = (4 * (1 + 3)) / 2</br>
 * 14 = 4 * (3 + 1 / 2)</br>
 * 19 = 4 * (2 + 3) - 1</br>
 * 36 = 3 * 4 * (2 + 1)</br>
 * </p>
 * Note that concatenations of the digits, like 12 + 34, are not allowed.</br>
 * Using the set, {1, 2, 3, 4}, it is possible to obtain thirty-one different
 * target numbers of which 36 is the maximum, and each of the numbers 1 to 28
 * can be obtained before encountering the first non-expressible number.</br>
 * Find the set of four distinct digits, a < b < c < d, for which the longest
 * set of consecutive positive integers, 1 to n, can be obtained, giving your
 * answer as a string: <i>abcd</i>.
 *
 **/
public class Problem093 {

	public static void main(String[] args) {
		assert calculate() == 1258 : "failed";
	}

	private static int calculate() {

		List<String> masks = getMasks();

		List<String> operators = getOperators();

		// Produces all variations of the numbers
		List<List<Integer>> variations = new VariationGenerator(9, 4).getVariations();

		// Contains list of the results of a given digits set
		Map<String, Set<Integer>> map = new HashMap<String, Set<Integer>>();

		for (List<Integer> v : variations) {
			if (v.contains(0)) {
				continue;
			}

			String s = listToString(v);

			Set<Integer> result = map.get(s);
			if (result == null) {
				result = new TreeSet<Integer>();
				map.put(s, result);
			}

			for (String op : operators) {

				for (String mask : masks) {
					int oIndex = 0;
					int dIndex = 0;
					StringBuilder postfix = new StringBuilder();
					for (int i = 0; i < mask.length(); i++) {
						if (mask.charAt(i) == '0') {
							postfix.append(v.get(dIndex)).append(" ");
							++dIndex;
						} else {
							postfix.append(op.charAt(oIndex)).append(" ");
							++oIndex;
						}
					}

					try {
						double r = Postfix.evaluate(postfix.toString());
						if (r > 0 && r != Double.POSITIVE_INFINITY &&  r == Math.floor(r)) {
							result.add((int) r);
						}
					} catch (Exception e) {}
				}
			}
		}

		int max = 0;
		String digits = null;
		for (Map.Entry<String, Set<Integer>> entry : map.entrySet()) {
			int length = getConsecutiveLength(entry.getValue());
			if (max < length) {
				max = length;
				digits = entry.getKey();
			}
		}

		return Integer.parseInt(digits);
	}

	/**
	 * A mask represents operators and operands combinations in postfix format. For example:
	 * <p>
	 * 0000111 means <i>a b c d + - /</i>
	 * </p>
	 * where a, b, c, d are different numbers.
	 */
	private static List<String> getMasks() {
		return Arrays.asList(
				"0000111", "0001011", "0001101",
				"0010011", "0010101", "0011001",
				"0100011", "0100101", "0101001", "0110001");
	}

	/**
	 * Produces all m-combination with repetition of the operators (where m = 3).
	 */
	private static List<String> getOperators() {
		String delims = "+-*/";
		List<String> combinations = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					combinations.add("" + delims.charAt(i) + delims.charAt(j) + delims.charAt(k));
				}
			}
		}

		return combinations;
	}

	/**
	 * Calculates the max consecutive length of the given result set.
	 */
	private static int getConsecutiveLength(Set<Integer> set) {
		int length = 0;
		int prev = -1;
		for (Iterator<Integer> i = set.iterator(); i.hasNext();) {
			int next = i.next();
			if (prev < 0) {
				prev = next;
				continue;
			}
			if (next - prev == 1) {
				++length;
			} else {
				break;
			}
			prev = next;
		}
		return length + 1;
	}

	private static String listToString(List<Integer> list) {
		Set<Integer> s = new TreeSet<Integer>();
		s.addAll(list);
		StringBuilder builder = new StringBuilder();
		for (int i : s) {
			builder.append(i);
		}
		return builder.toString();
	}
}