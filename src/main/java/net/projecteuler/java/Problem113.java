package net.projecteuler.java;

import net.projecteuler.java.util.CombinationGenerator;

/**
 * Working from left-to-right if no digit is exceeded by the digit to its left
 * it is called an increasing number; for example, 134468.</br>
 * Similarly if no digit is exceeded by the digit to its right it is called a
 * decreasing number; for example, 66420.</br>
 * We shall call a positive integer that is neither increasing nor decreasing
 * a "bouncy" number; for example, 155349.</br>
 * As n increases, the proportion of bouncy numbers below n increases such that
 * there are only 12951 numbers below one-million that are not bouncy and only
 * 277032 non-bouncy numbers below 10<sup>10</sup>.</br>
 * How many numbers below a googol (10<sup>100</sup>) are not bouncy?
 *
 * @author vuser
 * @see <a href="http://compalg.inf.elte.hu/~zslang/KombinatorikaPeldatar.pdf">KOMBINATORIKA</a>
 */
public class Problem113 {

	public static void main(String[] args) {
		assert solve() == 51161058134250L : "failed";
	}

	/**
	 * Solution is based on combination with repetition.
	 * Calculation is based on the followings:
	 * <ul>
	 * <li>number of monotonically increases cases equals C<sub>n</sub><sup>k,i</sup> = C<sub>n + k -1</sub><sup>k</sup>;
	 * in this case only 1..9 digits can be used</li>
	 * <li>number of monotonically decreases cases equals C<sub>n</sub><sup>k,i</sup> - 1 = C<sub>n + k -1</sub><sup>k</sup> - 1;
	 * in this case all digits can be used. We have to subtract 1 because the case that contains just 0 is not a solution</li>
	 * <li>the number that contains same digits is calculated twice (monotonically increases and monotonically decreases),
	 * so we have to reduce the solution with 9</li>
	 * </up>
	 * @return
	 */
	private static long solve() {
		// one-digit numbers are not bouncy
		long sum = 9;
		for (int i = 1; i < 100; i++) {
			CombinationGenerator cg1 = new CombinationGenerator(i + 9, i + 1);
			CombinationGenerator cg2 = new CombinationGenerator(i + 10, i + 1);
			sum += cg1.getTotal().longValue() + cg2.getTotal().longValue() - 1 - 9;
		}

		return sum;
	}
}