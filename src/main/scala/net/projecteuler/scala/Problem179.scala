package net.projecteuler.scala

/**
 * Find the number of integers 1 < n < 10<sup>7</sup>, for which n and n + 1
 * have the same number of positive divisors.</br>
 * For example, 14 has the positive divisors 1, 2, 7, 14 while 15 has 1, 3, 5, 15.
 *
 * @author vuser
 *
 */
object Problem179 {

    def main(args: Array[String]): Unit = {
        val limit = 10000000
        val sieve = Array.fill(limit + 1)(2)
        for (i <- 2 to Math.sqrt(limit).toInt) {
            var j = i * i
            sieve(j) -= 1
            while (j <= limit) {
                sieve(j) += 2
                j += i
            }
        }

        val increment = (x: Int, a: Array[Int]) => if (a(x) == a(x + 1)) 1 else 0
        var answer = 0
        for (i <- 2 until limit) {
            answer += increment(i, sieve)
        }

        assert(answer == 986262)
    }
}