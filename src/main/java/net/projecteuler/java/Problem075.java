package net.projecteuler.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It turns out that 12 cm is the smallest length of wire that can be bent
 * to form an integer sided right angle triangle in exactly one way, but there
 * are many more examples.
 * <p>
 * 12 cm: (3,4,5)</br>
 * 24 cm: (6,8,10)</br>
 * 30 cm: (5,12,13)</br>
 * 36 cm: (9,12,15)</br>
 * 40 cm: (8,15,17)</br>
 * 48 cm: (12,16,20)
 * </p>
 * In contrast, some lengths of wire, like 20 cm, cannot be bent to form an
 * integer sided right angle triangle, and other lengths allow more than one
 * solution to be found; for example, using 120 cm it is possible to form
 * exactly three different integer sided right angle triangles.
 * <p>
 * 120 cm: (30,40,50), (20,48,52), (24,45,51)
 * </p>
 * Given that L is the length of the wire, for how many values of L <= 1,500,000 can
 * exactly one integer sided right angle triangle be formed?
 *
 * @author vuser
 *
 */
public class Problem075 {

	public static void main(String[] args) {
		assert calculate(1500000) == 161667 : "failed";
	}

	private static int calculate(int limit) {

		// Produces all primitive triplets up to the limit
		List<List<Integer>> tree = new ArrayList<List<Integer>>();
		nextTriples(3, 4, 5, limit, tree);

		int[] summas = new int[limit];

		for (List<Integer> list : tree) {
			int a = list.get(0);
			int b = list.get(1);
			int c = list.get(2);

			// Multiples of coprime triples;
			for (int i = 1; ; i++) {
				int sum = i * (a + b + c);
				if (sum >= summas.length) {
					break;
				}
				++summas[sum];
			}
		}

		int counter = 0;
		for (int i = 0; i < summas.length; i++) {
			// exactly one integer sided right angle triangle be formed
			if (summas[i] == 1) {
				++counter;
			}
		}

		return counter;
	}

	/**
	 * Calculates the next primitive triplets based on the input triplet.</br>
	 * The calculation is based on
	 * <a>href="http://en.wikipedia.org/wiki/Pythagorean_triple#Parent.2Fchild_relationships">3 linear transformation</a>
	 */
    private static void nextTriples(int a, int b, int c, int limit, List<List<Integer>> tree) {
    	if (a + b + c >= limit) {
    		return;
    	}

    	tree.add(Arrays.asList(a, b, c));

		List<Integer> t1 = t1(a, b, c);
		nextTriples(t1.get(0), t1.get(1), t1.get(2), limit, tree);

		List<Integer> t2 = t2(a, b, c);
		nextTriples(t2.get(0), t2.get(1), t2.get(2), limit, tree);

		List<Integer> t3 = t3(a, b, c);
		nextTriples(t3.get(0), t3.get(1), t3.get(2), limit, tree);
	}

    private static List<Integer> t1(int a, int b, int c) {
		return Arrays.asList(a - 2 * b + 2 * c, 2 * a - b + 2 * c, 2 * a - 2 * b + 3 * c);
    }

    private static List<Integer> t2(int a, int b, int c) {
    	return Arrays.asList(a + 2 * b + 2 * c, 2 * a + b + 2 * c, 2 * a + 2 * b + 3 * c);
    }

    private static List<Integer> t3(int a, int b, int c) {
    	return Arrays.asList(-a + 2 * b + 2 * c, -2 * a + b + 2 * c, -2 * a + 2 * b + 3 * c);
    }
}