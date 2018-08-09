package net.projecteuler.java;

/**
 * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral
 * is formed as follows:</br>
 * <p><span style="color:#ff0000;">21</span> 22 23 24 <span style="color:#ff0000;">25</span></br>
 * 20 <span style="color:#ff0000;">&nbsp; 7&nbsp;</span>&nbsp; 8&nbsp;<span style="color:#ff0000;">&nbsp; 9&nbsp;</span>10</br>
 * 19 &nbsp; 6&nbsp;<span style="color:#ff0000;">&nbsp; 1&nbsp;</span>&nbsp; 2 11</br>
 * 18 &nbsp;<span style="color:#ff0000;">&nbsp;5&nbsp;</span>&nbsp;4&nbsp; <span style="color:#ff0000;">&nbsp; 3</span> 12</br>
 * <span style="color:#ff0000;">17</span> 16 15 14 <span style="color:#ff0000;">13</span></p>
 * It can be verified that the sum of the numbers on the diagonals is 101.</br>
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 * @author vuser
 *
 */
public class Problem028 {

	public static void main(String[] args) {
		assert calculate(1001) == 669171001L : "failed";
	}

	/**
	 * The sum can be calculated by the following:</br>
	 * 1 + </br>
	 * (1 + 1 * 2) + (1 + 2 * 2) + (1 + 3 * 2) + (1 + 4 * 2) + </br>
	 * (1 + 3 * 4) + (1 + 4 * 4) + (1 + 5 * 4) + (1 + 6 * 4) + ...
	 * @param spiral
	 * @return
	 */
	private static long calculate(int spiral) {
		int center = 1;

		long sum = center;

		for (int i = 2; i < spiral; i = i + 2) {
			for (int j = i - 1; j < i + 3; j++) {
				sum += center + i * j;
			}
		}
		return sum;
	}
}