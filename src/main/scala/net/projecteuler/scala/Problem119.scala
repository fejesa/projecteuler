package net.projecteuler.scala

import scala.collection.mutable.ListBuffer

/**
 * The number 512 is interesting because it is equal to the
 * sum of its digits raised to some power: 5 + 1 + 2 = 8, and 8<sup>3</sup> = 512.</br>
 * Another example of a number with this property is 614656 = 28<sup>4</sup>.</br>
 * We shall define an to be the n<i>th</i> term of this sequence
 * and insist that a number must contain at least two digits to have a sum.</br>
 * You are given that a<sub>2</sub> = 512 and a<sub>10</sub> = 614656.</br>
 * Find a<sub>30</sub>.
 *
 * @author vuser
 *
 */
object Problem119 {

    def main(args: Array[String]): Unit = {
        val list = new ListBuffer[Long]
        for (base <- 2 to 100) {
            for (pow <- 2 to 20) {
                val number = Math.pow(base, pow).toLong
                // Calculates the sum of the digits
                val digitSum = number.toString.map { _.asDigit }.sum
                if (digitSum.toLong == base) {
                    list += number
                }
            }
        }
        // Sorts the list and get the 30th element
        list.sorted
        assert(list(29) == 248155780267521L)
    }
}