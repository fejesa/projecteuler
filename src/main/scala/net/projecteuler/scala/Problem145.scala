package net.projecteuler.scala

import scala.collection.mutable.BitSet

/**
 * Some positive integers n have the property that the sum [ n + reverse(n) ]
 * consists entirely of odd (decimal) digits. For instance, 36 + 63 = 99
 * and 409 + 904 = 1313. We will call such numbers <i>reversible</i>; so 36, 63, 409,
 * and 904 are reversible. Leading zeroes are not allowed in either n or reverse(n).</br>
 * There are 120 reversible numbers below one-thousand.</br>
 * How many reversible numbers are there below one-billion (10<sup>9</sup>)?
 *
 * @author vbox
 *
 */
object Problem145 {

    def main(args: Array[String]): Unit = {
        // There are 9 digit solution, because of leading zero - we calculate till 10^8
        val limit = 100000000
        // BitSet is used to mark the number and its reversed one in order to avoid
        // calculation twice, because of performance
        val numbers = new BitSet(limit)
        for (i <- 0 to limit by 10) {
            numbers.update(i, true)
        }
        var counter = 0
        for (i <- 1 to limit) {
            if (!numbers.contains(i)) {
                val reversedNumber = reverse(i, 0)
                // Sets the reversed value in the bit set - do not calculate that value twice
                numbers.update(reversedNumber, true)
                if (reversible(i + reversedNumber)) {
                    counter += 1
                }
            }
        }

        assert(counter * 2 == 608720)
    }

    /**
     * Produces the reverse of the given number using of tail recursion
     */
    def reverse(number: Int, reversedNumber: Int): Int = {
        if (number == 0)
            reversedNumber
        else
            reverse(number / 10, reversedNumber * 10 + number % 10)
    }

    /**
     * Check the given number reversible or not based on the sum.
     */
    def reversible(sum: Int): Boolean = {
        if (sum == 0)
            true
        else if (sum % 2 == 0) {
            false
        } else {
            reversible(sum / 10)
        }
    }
}