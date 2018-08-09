package net.projecteuler.scala

import net.projecteuler.java.util.PrimeGenerator

/**
 * A number consisting entirely of ones is called a repunit.
 * We shall define R(k) to be a repunit of length k.</br>
 * For example, R(10) = 1111111111 = 11* 41* 271 * 9091,
 * and the sum of these prime factors is 9414.</br>
 * Find the sum of the first forty prime factors of R(10<sup>9</sup>).
 *
 * @author vuser
 *
 */
object Problem132 {

    /**
	 * Repunit can be represedted like this:
	 * R(k) = (10<sup>k</sup> - 1) / 9</br>
	 * repunit can be divisible p prime if (10<sup>k</sup> - 1) / 9 = 0 (mod p)</br>
	 * therefore</br>
	 * 10<sup>k</sup> - 1 = 0 (mod 9p)</br>
	 * and 10<sup>k</sup> = 1 (mod 9p)</br>
	 * So, if 10<sup>k</sup> mod 9p = 1 then we have found a divisor for R(k).
	 * To efficiently calculate this we can use Modular Exponentiation.
	 *
	 */
    def main(args: Array[String]): Unit = {
        val limit = 1000000000
        val sieve = new PrimeGenerator(limit)
        val base = BigInt(10)
        val exponent = BigInt(limit)

        var counter = 0
        var i = 0
        var sum = 0
        while (counter < 40 && i < sieve.getLimit()) {
            if (sieve.isPrime(i) && base.modPow(exponent, BigInt(i * 9)) == BigInt(1)) {
                sum += i
                counter += 1
            }
            i += 1
        }
        assert(sum == 843296)
    }
}