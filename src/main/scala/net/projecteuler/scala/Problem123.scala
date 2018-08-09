package net.projecteuler.scala

import net.projecteuler.java.util.PrimeGenerator

/**
 * Let p<sub>n</sub> be the n<i>th</i> prime: 2, 3, 5, 7, 11, ...,
 * and let r be the remainder when (p<sub>n</sub> - 1)<sup>n</sup> + (p<sub>n</sub> + 1)<sup>n</sup>
 * is divided by p<sub>n</sub><sup>2</sup>.</br>
 * For example, when n = 3, p<sub>3</sub> = 5, and 43 + 63 = 280 => 5 mod 25.</br>
 * The least value of n for which the remainder first exceeds 10<sup>9</sup> is 7037.</br>
 * Find the least value of n for which the remainder first exceeds 10<sup>10</sup>.
 *
 * @author vuser
 *
 */
object Problem123 {

    def main(args: Array[String]): Unit = {
        
        // Generates primes
        val sieve = new PrimeGenerator(10000000)
        
        val limit = BigInt(10000000000L)

        // We start the process from the 21000th prime - approximation
        var index = 21000

        var i = sieve.getPrimeByIndex(index)
        var foundIt = false
        while (!foundIt) {
            if (sieve.isPrime(i)) {
                // If the index is even the odd equals 2 - we can skip this calculation
                if (index % 2 != 0 && mod(i, index, limit)) {
                    foundIt = true
                }
                index = index + 1
            }
            i = i + 1
        }
        assert (index - 1 == 21035)
    }

    def mod(i: Int, index: Int, limit: BigInt) = {
        val mod = (BigInt(i - 1).pow(index) + BigInt(i + 1).pow(index)).mod((BigInt(i).pow(2)))
        mod > limit
    }
}