package net.projecteuler.java;

/**
 * Let r be the remainder when (a - 1)<sup>n</sup> + (a + 1)<sup>n</sup> is divided by a<sup>2</sup>.
 * <p>
 * For example, if a = 7 and n = 3, then r = 42: 6<sup>3</sup> + 8<sup>3</sup> = 728, remainder is 42 (mod 49).
 * And as n varies, so too will r, but for a = 7 it turns out that r<sub>max</sub> = 42.
 * </p>
 * For 3 <= a <= 1000, find sum(r<sub>max</sub>).
 *
 * @author vbox
 *
 */
public class Problem120 {

	public static void main(String[] args) {
		assert calculate() == 333082500 : "failed";
	}

	/**
	 * We have to use the Binomial theorem.</br>
	 * <ol>
	 * <li>in case n = 1: (a - 1)<sup>1</sup> + (a + 1)<sup>1</sup> = 2a, reminder is 2a.</li>
	 * <li>in case n = 2: (a - 1)<sup>2</sup> + (a + 1)<sup>2</sup> = 2a<sup>2</sup> + 2, reminder is 2</li>
	 * <li>in case n = 3: (a - 1)<sup>3</sup> + (a + 1)<sup>3</sup> = 2a<sup>3</sup> + 6a, reminder is 2na</li>
	 * <li>etc</li>
	 * </ol>
	 * This pattern is followed for all of the odd values of n. Only the lowest element
	 * in the expansion, 2an, is significant as the leading elements will always evenly divisible by a<sup>2</sup>.</br>
	 * So we only need to deal with odd values of n and the remainder calculation
	 * is just 2an % a<sup>2</sup>. The value of this function is obviously minimized when 2an = m*a<sup>2</sup>
	 * for all values of m. Ignoring m and rearranging that we get n = a/2 - this means that the function
	 * will be maximized for n = (a - 1) / 2.
	 *
	 * @return
	 */
	private static int calculate() {
		int sum = 0;

		for (int a = 3; a <= 1000; a++) {
			int n = (a - 1) / 2;
			int reminder = 2 * n * a;
			sum += reminder;
		}

		return sum;
	}
}