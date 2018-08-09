package net.projecteuler.java;

import java.math.BigInteger;

/**
 * A particular school offers cash rewards to children with good attendance
 * and punctuality. If they are absent for three consecutive days or late
 * on more than one occasion then they forfeit their prize.</br>
 * During an n-day period a trinary string is formed for each child
 * consisting of L's (late), O's (on time), and A's (absent).</br>
 * Although there are eighty-one trinary strings for a 4-day period that
 * can be formed, exactly forty-three strings would lead to a prize:
 * <p>
 * OOOO OOOA OOOL OOAO OOAA OOAL OOLO OOLA OAOO OAOA</br>
 * OAOL OAAO OAAL OALO OALA OLOO OLOA OLAO OLAA AOOO</br>
 * AOOA AOOL AOAO AOAA AOAL AOLO AOLA AAOO AAOA AAOL</br>
 * AALO AALA ALOO ALOA ALAO ALAA LOOO LOOA LOAO LOAA</br>
 * LAOO LAOA LAAO
 * </p>
 * How many "prize" strings exist over a 30-day period?
 *
 * @author vuser
 *
 */
public class Problem191 {

	public static void main(String ... args) {
		assert solve() == 1918080160 : "failed";
	}

	/**
	 * Solution is based on the followings:</br>
	 * 'O' char is like a barrier. For example if the n-day period is 4 and
	 * we have two 'O' barriers the 'A' (absent) days must be placed between the barriers.</br>
	 * The format is _ _ O _ _ O _ _. So we have to partition the two 'A' days to three parts: {2,0,0}.</br>
	 * Next we take all permutation of this partition; the output will be 'AAOO', 'OAAO', 'OOAA'.</br>
	 * Each 2-0 bags can be replaced with 1-1 bags. So {2,0,0} replaced with {1, 0, 1}
	 * and the output will be 'AOAO', 'AOOA', 'OAOA'.</br>
	 * The L (late) days mean that in each output one 'O' day can be replaced with an 'L'.
	 *
	 * @return
	 */
	private static long solve() {
		int count = 0;
		int limit = 30;

		// At least 10 'O' days must be exist in a 30-day period
		for (int o = 10; o <= limit; o++) {
			int[] p = partition(o, limit);
			while (p != null) {
				// If there is a partition we have to take its permutations
				int perm = getPermutations(p);
				 // (o + 1) means in each permutation one 'O' can be replaced with 'L'
				count += perm * (o + 1);

				// i.e. {2, 0} can be replaced with {1, 1}
				p = nextPartition(p);
			}
		}

		return count;
	}

	private static int getPermutations(int[] partition) {
		int zero = 0;
		int one = 0;
		int two = 0;
		for (int i = 0; i < partition.length; i++) {
			if (partition[i] == 0) {
				++zero;
			} else if (partition[i] == 1) {
				++one;
			} else {
				++two;
			}
		}
		BigInteger numerator = factor(partition.length);
		BigInteger denominator = factor(zero).multiply(factor(one)).multiply(factor(two));
		return numerator.divide(denominator).intValue();
	}

	private static BigInteger factor(int n) {
		BigInteger factor = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			factor = factor.multiply(BigInteger.valueOf(i));
		}
		return factor;
	}

	private static int[] nextPartition(int[] partition) {
		int[] next = partition.clone();
		for (int i = partition.length - 1; i >= 0; i--) {
			if (partition[i] == 0) {
				for (int j = 0; j < i; j++) {
					if (partition[j] == 2) {
						next[j] = 1;
						next[i] = 1;
						return next;
					}
				}
			}
		}
		return null;
	}

	private static int[] partition(int barrier, int limit) {
		if (barrier <= 0) {
			return null;
		}
		int[] bags = new int[barrier + 1];
		for (int i = 0; i < bags.length && limit > 0; i++) {
			if (limit - barrier >= 2) {
				bags[i] = 2;
			} else if (limit - barrier == 1) {
				bags[i] = 1;
			}
			limit -= 3;
			barrier -= 1;
		}

		return bags;
	}
}