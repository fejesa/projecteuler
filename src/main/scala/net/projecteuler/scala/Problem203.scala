package net.projecteuler.scala

import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.mutable.HashSet

import net.projecteuler.java.util.Number

/**
 * The binomial coefficients nCk can be arranged in triangular form, Pascal's triangle, like this:
 * 1</br>
 * 1   1</br>
 * 1   2   1</br>
 * 1   3   3   1</br>
 * 1   4   6   4   1</br>
 * 1   5   10   10   5   1</br>
 * 1   6   15   20   15   6   1</br>
 * 1   7   21   35   35   21   7   1</br>
 * .........</br>
 * It can be seen that the first eight rows of Pascal's triangle contain twelve distinct
 * numbers: 1, 2, 3, 4, 5, 6, 7, 10, 15, 20, 21 and 35.</br>
 * A positive integer n is called squarefree if no square of a prime divides n.
 * Of the twelve distinct numbers in the first eight rows of Pascal's triangle,
 * all except 4 and 20 are squarefree. The sum of the distinct squarefree numbers
 * in the first eight rows is 105.</br>
 * Find the sum of the distinct squarefree numbers in the first 51 rows of Pascal's triangle.
 *
 * @author vuser
 *
 */
object Problem203 {

    def main(args: Array[String]): Unit = {
        var sum = 0L
        val numbers = new HashSet[Long]
        for (n <- 2 until 51) {
            val nfactor = factor(n)
            for (k <- 1 to n / 2 + 1) {
                val kfactor = factor(k)
                val nkfactor = factor(n - k)
                val number = (nfactor / kfactor / nkfactor).longValue()
                if (numbers.add(number) && isSquarefree(number).equals(None)) {
                    sum += number
                }
            }
        }
        assert(sum == 34029210557338L)
    }

    private def isSquarefree(number: Long): Option[Integer] = {
        mapAsScalaMap(Number.primeExponent(number)).values find (_ >= 2)
    }

    private def factor(n: Int): BigInt = {
        var factor = BigInt(1)
        for (i <- 2 to n) {
            factor = factor * BigInt(i)
        }
        factor
    }
}