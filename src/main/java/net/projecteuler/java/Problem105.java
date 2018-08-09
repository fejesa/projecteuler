package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.projecteuler.java.util.CombinationGenerator;

/**
 * Let S(A) represent the sum of elements in set A of size n.
 * We shall call it a special sum set if for any two non-empty disjoint
 * subsets, B and C, the following properties are true:
 * <ol>
 * <li>S(B)<> S(C); that is, sums of subsets cannot be equal.</li>
 * <li>If B contains more elements than C then S(B) > S(C).</li>
 * </ol>
 * For example, {81, 88, 75, 42, 87, 84, 86, 65} is not a special sum set
 * because 65 + 87 + 88 = 75 + 81 + 84, whereas {157, 150, 164, 119, 79, 159, 161, 139, 158}
 * satisfies both rules for all possible subset pair combinations and S(A) = 1286.</br>
 * Using the given text file with one-hundred sets containing seven to
 * twelve elements (the two examples given above are the first two sets in the file),
 * identify all the special sum sets, A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>k</sub>,
 * and find the value of S(A<sub>1</sub>) + S(A<sub>2</sub>) + ... + S(A<sub>k</sub>).
 *
 * @author vuser
 *
 */
public class Problem105 {

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem105.txt") == 73702 : "failed";
	}

	private static int calculate(String file) throws IOException {
		int sum = 0;
		List<List<Integer>> sets = read(file);
		for (List<Integer> set : sets) {
			// Creates the subsets of the given combination
			List<List<Integer>> subsets = getSubsets(set);
			if (isSpecial(set, subsets)) {
				sum += getSum(set);
			}
		}
		return sum;
	}

	/**
	 * Checks the two properties of the subsets.
	 */
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

			// Checks the second property (If B contains more elements than C then S(B) > S(C))
			// ie. applying for the set {348,329,343,344,338,315,169,359,375,271} the subset
			// n = 4 {343, 348, 359, 375} => sum = 1425
			// n = 5 {169, 271, 315, 329, 338} => sum = 1422
			int s = 0;
			for (int i = 0; i < ms.size() + 1; i++) {
				s += elements.get(i);
			}
			if (sum >= s) {
				passed = false;
				break check;
			}
		}

		return passed;
	}

	/**
	 * Calulates the sum of the elements.
	 */
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

	/**
	 * Reads the list of numbers per line.
	 */
	private static List<List<Integer>> read(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		List<List<Integer>> sets = new ArrayList<List<Integer>>();
		try {
			String line;
			while ((line = in.readLine()) != null) {
				String[] numbers = line.split(",");
				List<Integer> set = new ArrayList<Integer>();
				for (String n : numbers) {
					set.add(Integer.parseInt(n.trim()));
				}
				// Sorts the elements because of easier checking
				Collections.sort(set);
				sets.add(set);
			}

			return sets;

		} finally {
			in.close();
		}
	}
}