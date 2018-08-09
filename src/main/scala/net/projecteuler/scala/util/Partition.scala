package net.projecteuler.scala.util

import scala.collection.mutable._

/**
 * Produces the partition of a positive integer n.
 * 
 * @author vuser
 * 
 */
object Partition {

    def main(args: Array[String]): Unit = {
        partition(6).foreach(l => println(l.length))
    }

    def partition(n: Int): Buffer[Array[Int]] = {
        val partitions = partition(n, n, "", new ListBuffer[String])
        val list = new ListBuffer[Array[Int]]
        // Splits the string array - representation of a partition - and converts it as Integer
        partitions.foreach(s => list += s.split(",").map(_ toInt))
        list
    }

    private def partition(n: Int, max: Int, prefix: String, list: ListBuffer[String]): Buffer[String] = {
        if (n == 0) {
            list += prefix.substring(1)
        } else {
            for (i <- Math.min(n, max) to 1 by -1) {
                partition(n - i, i, prefix + "," + i, list)
            }
            list
        }
    }
}