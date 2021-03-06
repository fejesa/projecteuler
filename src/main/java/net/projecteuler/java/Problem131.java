package net.projecteuler.java;

import net.projecteuler.java.util.PrimeGenerator;

/**
 * There are some prime values, p, for which there exists a positive integer,
 * n, such that the expression n<sup>3</sup> + n<sup>2</sup>*p is a perfect cube.
 * <p>For example, when p = 19, 8<sup>3</sup> + 8<sup>2</sup>*19 = 12<sup>3</sup>.</p>
 * What is perhaps most surprising is that for each prime with this property
 * the value of n is unique, and there are only four such primes below one-hundred.</br>
 * How many primes below one million have this remarkable property?
 *
 * @author vuser
 *
 */
public class Problem131 {

	public static void main(String ... args) {
		assert solve() == 173 : "failed";
	}

	/**
	 * Solution
	 * <p>
	 * n<sup>3</sup> + n<sup>2</sup>*p = m<sup>3</sup></br>
	 * n<sup>2</sup>(n + p) = m<sup>3</sup></br>
	 * As p is prime then the only way that n<sup>2</sup>(n+p) is a cube is that both n<sup>2</sup> and (n+p).
	 * So p is the difference of 2 cubes:</br>
	 * a<sup>3</sup> - b<sup>3</sup> = p</br>
	 * (a - b)(a<sup>2</sup> + ab + b<sup>2</sup>) = p</br>
	 * p prime therefore a - b = 1.
	 * </p>
	 * @see<a href="http://en.wikipedia.org/wiki/Centered_hexagonal_number">Centered hexagonal number</a>
	 * @see<a href="http://en.wikipedia.org/wiki/Cuban_prime">Cuban prime</a>
	 * @see<a href="http://oeis.org/A002407">OEIS - Cuban prime</a>
	 *
	 * @return
	 */
	private static int solve() {
		PrimeGenerator sieve = new PrimeGenerator(1000000);
		int counter = 0;

		for (int i = 1; ; i++) {
			int n = (int)(Math.pow(i + 1, 3) - Math.pow(i, 3));
			if (n > sieve.getLimit()) {
				break;
			}
			if (sieve.isPrime(n)) {
				++counter;
			}
		}

		return counter;
	}
}
