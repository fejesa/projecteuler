package net.projecteuler.scala

import scala.collection.JavaConversions.asScalaBuffer
import scala.util.control.Breaks.break
import scala.util.control.Breaks.breakable

import net.projecteuler.java.util.PrimeGenerator

/**
 * A composite is a number containing at least two prime factors.
 * For example, 15 = 3 * 5; 9 = 3 * 3; 12 = 2 * 2 * 3.</br>
 * There are ten composites below thirty containing precisely two,
 * not necessarily distinct, prime factors: 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.</br>
 * How many composite integers, n < 10<sup>8</sup>, have precisely two,
 * not necessarily distinct, prime factors?
 *
 */
object Problem187 {

    def main(args: Array[String]): Unit = {
        val limit = 100000000L
        val sieve = new PrimeGenerator(limit.toInt / 2);
        val array = asScalaBuffer(sieve.getPrimeList()).toArray

        var counter = 0
        for (i <- 0 until array.length) {
            breakable {
                for (j <- i until array.length) {
                    // Cast must be required because of overflow
                    if (array(i).toLong * array(j) > limit) {
                        break
                    }
                    counter += 1
                }
            }
        }
        assert(counter == 17427258)
    }
}