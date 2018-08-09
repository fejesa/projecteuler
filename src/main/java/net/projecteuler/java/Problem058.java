package net.projecteuler.java;

import net.projecteuler.java.util.Number;

/**
 * Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.
 * <p><span style='color:#ff0000;'>37</span> 36 35 34 33 32 <span style='color:#ff0000;'>31</span><br/>
 * 38 <span style='color:#ff0000;'>17</span> 16 15 14 <span style='color:#ff0000;'>13</span> 30<br/>
 * 39 18 <span style='color:#ff0000;'>&nbsp;5&nbsp;</span> &nbsp;4 &nbsp;<span style='color:#ff0000;'>&nbsp;3</span> &nbsp;12 29<br/>
 * 40 19 &nbsp;6 &nbsp;&nbsp;1 &nbsp;&nbsp;2 &nbsp;11 28<br/>
 * 41 20 <span style='color:#ff0000;'>&nbsp;7</span> &nbsp;&nbsp;8 &nbsp;&nbsp;9 &nbsp;10 27<br/>
 * 42 21 22 23 24 25 26<br/>
 * <span style='color:#ff0000;'>43</span> 44 45 46 47 48 49</p>
 * It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more
 * interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ~ 62%.</br>
 * If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed.</br>
 * If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?
 *
 * @author vbox
 *
 */
public class Problem058 {

	public static void main(String[] args) {
		assert calculate() == 26241 : "failed";
	}

	private static int calculate() {

		float ratio = 0.1F;
		float numbers = 1;
		float primes = 0;

		search: for (int i = 2; ; i = i + 2) {
			for (int j = i - 1; j < i + 3; j++) {
				long number = 1 + i * j;
				if (Number.isPrime(number)) {
					++primes;
				}
				++numbers;
				
			}
			if (primes/ numbers < ratio) {
				break search;
			}
		}
		return ((int) numbers + 1) / 2;
	}
}