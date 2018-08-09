package net.projecteuler.scala

import scala.collection.JavaConversions._
import scala.collection.mutable._
import java.lang.{ Long => JLong }
import java.lang.{ Integer => JInt }

import net.projecteuler.java.util.Number

/**
 * Determining the k<sup>th</sup> element of the sorted radical function.
 */
object Problem124 {

    def main(args: Array[String]): Unit = {

        var list = new ListBuffer[Map[JLong, JInt]]
        // Insert one
        list += Map(JLong.valueOf(1) -> 1);

        // Do the prime factorization
        for (i <- 2 to 100000) {
            list += mapAsScalaMap(Number.primeExponent(i))
        }

        // Sorts the list using the custom comparator
        var n = 1D
        for ((key, value) <- list.sortWith(compare)(9999)) {
            n *= Math.pow(key.toDouble, value.toDouble)
        }
        assert(n == 21417)

    }

    /**
     * Custom sorting function
     */
    def compare(pe1: Map[JLong, JInt], pe2: Map[JLong, JInt]) = {
        var n1Rad = 1L;
        var n2Rad = 1L;

        var n1 = 1D;
        var n2 = 1D;

        for ((key, value) <- pe1) {
            n1Rad *= key
            n1 *= Math.pow(key.toDouble, value.toDouble)
        }

        for ((key, value) <- pe2) {
            n2Rad *= key
            n2 *= Math.pow(key.toDouble, value.toDouble)
        }

        if (n1Rad == n2Rad)
            n1 < n2
        else
            n1Rad < n2Rad
    }
}