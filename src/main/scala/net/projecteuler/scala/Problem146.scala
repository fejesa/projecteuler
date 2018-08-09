package net.projecteuler.scala

/**
 * The smallest positive integer n for which the numbers n<sup>2</sup>+1,
 * n<sup>2</sup>+3, n<sup>2</sup>+7, n<sup>2</sup>+9, n<sup>2</sup>+13,
 * and n<sup>2</sup>+27 are consecutive primes is 10.
 * The sum of all such integers n below one-million is 1242490.
 * What is the sum of all such integers n below 150 million?
 *
 * @author vuser
 */
object Problem146 {

    def main(args: Array[String]) : Unit = {
        // Analysis:
		// n = 0 (mod 2, 5) so number is multiple of 10
		// if n^2 = 0 (mod 3, 7, 13) then n^2 + (3, 7, 13) is composite so number is not multiple of 3, 7, 13
		// The number n must be a multiple of 10, but not of 3, 7, 13
        var sum: Long = 0
        for (i <- 10L to 150000000 by 10) {
            if (i % 3 != 0 && i % 7 != 0 && i % 13 != 0) {
                val square = i * i
                val certainty = 1
                if (BigInt(square + 1).isProbablePrime(1)
                        && BigInt(square + 3).isProbablePrime(1)
                        && BigInt(square + 7).isProbablePrime(1)
                        && BigInt(square + 9).isProbablePrime(1)
                        && BigInt(square + 13).isProbablePrime(1)
                        && BigInt(square + 27).isProbablePrime(1)) {
                    // Double checking
                    if (check(square)) sum += i
                }
            }
        }
        assert (sum == 676333270)
    }

    def check(square: Long): Boolean = {
        // The primes must be consecutive that means between n^2 + 1 and n^2 + 27
		// only six primes should be there. So iterate the range and checks them.
        var count = 0
        for (i <- 1 to 28) {
            if (BigInt(square + i).isProbablePrime(1)) {
                count += 1
            }
        }
        count == 6
    }
}