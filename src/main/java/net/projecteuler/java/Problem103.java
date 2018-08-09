package net.projecteuler.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.CombinationGenerator;

/**
 * Let S(A) represent the sum of elements in set A of size n.
 * We shall call it a special sum set if for any two non-empty disjoint subsets,
 * B and C, the following properties are true:
 * <ol>
 * <li>S(B)<> S(C); that is, sums of subsets cannot be equal.</li>
 * <li>If B contains more elements than C then S(B) > S(C).</li>
 * </ol>
 * If S(A) is minimised for a given <i>n</i>, we shall call it an optimum
 * special sum set. The first five optimum special sum sets are given below.
 * <p>
 * n = 1: {1}</br>
 * n = 2: {1, 2}</br>
 * n = 3: {2, 3, 4}</br>
 * n = 4: {3, 5, 6, 7}</br>
 * n = 5: {6, 9, 11, 12, 13}
 * </p>
 * It seems that for a given optimum set, A = <i>{a<sub>1</sub>, a<sub>2</sub>, ... , a<sub>n</sub>}</i>,
 * the next optimum set is of the form B = <i>{b, a<sub>1</sub>+b, a<sub>2</sub>+b, ... ,a<sub>n</sub>+b}</i>,
 * where b is the "middle" element on the previous row.</br>
 * By applying this "rule" we would expect the optimum set for <i>n</i> = 6
 * to be A = {11, 17, 20, 22, 23, 24}, with S(A) = 117.
 * However, this is not the optimum set, as we have merely applied an algorithm to provide a near optimum set.
 * The optimum set for <i>n</i> = 6 is A = {11, 18, 19, 20, 22, 25}, with S(A) = 115 and
 * corresponding set string: 111819202225.</br>
 * Given that A is an optimum special sum set for n = 7, find its set string.
 *
 * @author vuser
 *
 */
public class Problem103 {

	public static void main(String[] args) {
		assert calculate().equals("20313839404245") : "failed";
	}

	/**
	 * NOTE: in case of <i>n</i> = 6 the minimal solution maybe A = {7, 8, 9, 11, 14}, with S(A) = 49
	 * @return
	 */
	private static String calculate() {

		// Contains the optimal solutions mapped by the given n number
		Map<Integer, List<Integer>> solutions = new HashMap<Integer, List<Integer>>();

		// p is used for double checking that means if we found
		// a given minimum solution we double check in the next iteration
		for (int i = 4, p = 0, n = 3; n < 8; i++) {

			// Creates a list of numbers up to i
			List<Integer> elements = getElements(i);

			// Our assumption is the the sum of the minimal solution should be
			// higher than the average
			int avg = (int) Math.floor(getSum(elements) / elements.size());

			// Produces the combinations of elements - n-group
			CombinationGenerator cg = new CombinationGenerator(i, n);

			check: while (cg.hasMore()) {
				int[] next = cg.getNext();
				List<Integer> c = new ArrayList<Integer>();
				for (int j = 0; j < next.length; j++) {
					int e = elements.get(next[j]);
					// The minimal solution does not contain the lower elements
					if (e < i / 3) {
						continue check;
					}
					c.add(e);
				}

				// The minimal solution should contain the highest elem
				if (!c.contains(i) || getSum(c) < avg) {
					continue check;
				}

				// Creates the subsets of the given combination
				List<List<Integer>> subsets = getSubsets(c);

				if (isSpecial(elements, subsets)) {
					List<Integer> previous = solutions.get(n);
					if (previous == null || getSum(previous) > getSum(c)) {
						solutions.put(n, c);
					}
					if (p == 0) {
						p = i;
					}
				}
			}

			// If given minimal solution of the n is found and double checked
			// increments the n value
			if (p + 1 == i) {
				++n;
				p = 0;
			}
		}

		StringBuilder builder = new StringBuilder();
		List<Integer> list = solutions.get(7);
		for (int i : list) {
			builder.append(i);
		}
		return builder.toString();
	}

	private static boolean isSpecial(List<Integer> elements, List<List<Integer>> subsets) {

		List<Integer> sumList = new ArrayList<Integer>();
		boolean passed = true;
		check: for (List<Integer> ms : subsets) {
			int sum = getSum(ms);
			if (elements.contains(sum) || sumList.contains(sum)) {
				passed = false;
				break check;
			}
			sumList.add(sum);
		}

		return passed;
	}
	
	private static List<Integer> getElements(int n) {
		List<Integer> elements = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			elements.add(i);
		}
		return elements;
	}

	private static int getSum(List<Integer> list) {
		int sum = 0;
		for (int i : list) {
			sum += i;
		}
		return sum;
	}

	/**
	 * Produces all subsets of the given elements.
	 */
	private static List<List<Integer>> getSubsets(List<Integer> elements) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		for (int r = 2; r <= elements.size(); r++) {
			list.addAll(getCombinations(r, elements));
		}

		List<List<Integer>> subsets = new ArrayList<List<Integer>>();
		for (List<Integer> l : list) {
			if (l.size() <= elements.size() - 1) {
				subsets.add(l);
			}
		}

		return subsets;
	}

	private static List<List<Integer>> getCombinations(int n, List<Integer> elements) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		CombinationGenerator cg = new CombinationGenerator(elements.size(), n);
		while (cg.hasMore()) {
			int[] next = cg.getNext();
			List<Integer> l = new ArrayList<Integer>();
			for (int i = 0; i < next.length; i++) {
				l.add(next[i]);
			}
			list.add(l);

		}

		List<List<Integer>> combinations = new ArrayList<List<Integer>>();

		for (List<Integer> c : list) {
			List<Integer> l = new ArrayList<Integer>();
			for (int i : c) {
				l.add(elements.get(i));
			}
			combinations.add(l);
		}

		return combinations;
	}
}