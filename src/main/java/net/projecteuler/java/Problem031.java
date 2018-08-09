package net.projecteuler.java;

/**
 * In England the currency is made up of pound, &pound;, and pence, p,
 * and there are eight coins in general circulation:</br>
 * 1p, 2p, 5p, 10p, 20p, 50p, &pound;1 (100p) and &pound;2 (200p).</br>
 * It is possible to make &pound;2 in the following way:</br>
 * 1&pound; + 150p + 220p + 15p + 12p + 31p</br>
 * How many different ways can &pound;2 be made using any number of coins?
 *
 * @author vuser
 *
 */
public class Problem031 {

	public static void main(String[] args) {
		assert p200(200) == 73682 : "failed";
	}

	private static int p1(int n) {
		return 1;
	}

	private static int p2(int n) {
		if (n >= 0) {
			return p2(n - 2) + p1(n);
		}
	    return 0;
	}

	private static int p5(int n) {
		if (n >= 0) {
			return p5(n - 5) + p2(n);
		}
	    return 0;
	}

	private static int p10(int n) {
		if (n >= 0) {
			return p10(n - 10) + p5(n);
		}
	    return 0;
	}

	private static int p20(int n) {
		if (n >= 0) {
			return p20(n - 20) + p10(n);
		}
	    return 0;
	}

	private static int p50(int n) {
		if (n >= 0) {
			return p50(n - 50) + p20(n);
		}
	    return 0;
	}

	private static int p100(int n) {
		if (n >= 0) {
			return p100(n - 100) + p50(n);
		}
	    return 0;
	}

	private static int p200(int n) {
		if (n >= 0) {
			return p200(n - 200) + p100(n);
		}
	    return 0;
	}
}