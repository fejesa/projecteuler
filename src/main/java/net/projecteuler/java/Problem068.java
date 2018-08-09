package net.projecteuler.java;

import java.util.Arrays;
import java.util.List;

import net.projecteuler.java.util.PermutationGenerator;

/**
 * What is the maximum 16-digit string for a "magic" 5-gon ring?"
 *
 * @author vuser
 *
 */
public class Problem068 {

	public static void main(String[] args) {
		assert calculate() == 6531031914842725L : "failed";
	}

	private static long calculate() {
		// Creates all permutation of 10 numbers
		PermutationGenerator pg = new PermutationGenerator(10);

		String max = "0";

		while (pg.hasMore()) {
			int[] next = pg.getNext();
			int[] p = next.clone();
			// Converts 0 to 10
			for (int i = 0; i < p.length; i++) {
				if (p[i] == 0) {
					p[i] = 10;
					break;
				}
			}
			// Because we looking for 16-digits solution number 10 must be appear once in the chain
			if (p[0] != 10 && p[1] != 10 && p[2] != 10 && p[3] != 10 && p[4] != 10) {
				continue;
			}

			int sumg1 = p[0] + p[5] + p[6];
			int sumg2 = p[1] + p[6] + p[7];
			int sumg3 = p[2] + p[7] + p[8];
			int sumg4 = p[3] + p[8] + p[9];
			int sumg5 = p[4] + p[9] + p[5];

			// All sum in the 5-gon must be equal
			if (sumg1 == sumg2 && sumg2 == sumg3 && sumg3 == sumg4 && sumg4 == sumg5) {
				String number = getNumber(p);
				if (max.compareTo(number) < 0) {
					max = number;
				}
			}
		}

		return Long.valueOf(max);
	}

	private static String getNumber(int[] p) {

		// Produces the groups
        List<Integer> groups = Arrays.asList(
				toGroup(p[0], p[5], p[6]),
				toGroup(p[1], p[6], p[7]),
				toGroup(p[2], p[7], p[8]),
				toGroup(p[3], p[8], p[9]),
				toGroup(p[4], p[9], p[5]));

        // Finds the minimum, because we start from the lowest external node
        int begin = 0;
        for (int i = 0, min = 1500; i < groups.size(); i++) {
        	int e = groups.get(i);
        	if (min > e) {
        		min = e;
        		begin = i;
        	}
        }

        // Produces the chain
        StringBuilder builder = new StringBuilder();
        for (int i = begin, counter = 0; counter < groups.size(); ++i) {
        	if (i < groups.size()) {
        		builder.append(groups.get(i));
        		++counter;
        	} else {
        		i = -1;
        	}
        }

		return builder.toString();
	}

	private static int toGroup(int a, int b, int c) {
		return a * 100 + b * 10 + c;
	}
}