package net.projecteuler.java;

/**
 * The sum of the squares of the first ten natural numbers is, 1<sup>2</sup> +
 * 2<sup>2</sup> + ... + 10<sup>2</sup> = 385</br> The square of the sum of the
 * first ten natural numbers is, (1 + 2 + ... + 10)<sup>2</sup> = 552 =
 * 3025</br> Hence the difference between the sum of the squares of the first
 * ten natural numbers and the square of the sum is 3025 - 385 = 2640.</br> Find
 * the difference between the sum of the squares of the first one hundred
 * natural numbers and the square of the sum.
 * 
 * @author vuser
 * 
 */
public class Problem006 {

	public static void main(String[] args) {
		assert calculate(100) == 25164150L : "failed";
		assert simpleCalculate(100) == 25164150L : "failed";
	}

	private static long calculate(int number) {
		long sum = 0;

		for (int i = 1; i <= number; i++) {
			for (int j = 1; j <= number; j++) {
				if (j > i) {
					sum += i * j;
				}
			}
		}

		return 2 * sum;
	}

	/**
	 * Based on the follwings:
	 * <ul><li>1<sup>2</sup> + 2<sup>2</sup> + ... + n<sup>2</sup> = n(n + 1)(2n + 1)/6</li>
	 * <li>(1 + 2 + ... + n)<sup>2</sup> = (n(n + 1)/2)<sup>2</sup></li></ul>
	 * @param number
	 * @return
	 */
	private static long simpleCalculate(int number) {
		long squareOfSum = (number * (number + 1) / 2) * (number * (number + 1) / 2);
		long sumOfSquare = number * (number + 1) * (2 * number + 1) / 6; 
		return squareOfSum - sumOfSquare;
	}
}
