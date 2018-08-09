package net.projecteuler.scala

import scala.collection.mutable._

import net.projecteuler.scala.util.Partition

/**
 * Investigating the number of ways of replacing
 * square tiles with one of three colored tiles.
 *
 * @author vuser
 *
 */
object Problem116 {

    /**
     * Solution is based on the partition of a positive integer n.</br>
     * I.e. 5 can be produced as 2 + 1 + 1 + 1. Because of a number can be used
     * more than one in a given partition we have to use permutation with repetition of that number.
     */
    def main(args: Array[String]): Unit = {
        var counter = BigInt(1)
        // Produces all partition of the given number
        val partitions = Partition.partition(50)
        partitions.foreach {
            p => counter = updateCounter(counter, checkPartition(p))
        }
        assert(counter == 20492570929L)
    }

    private def updateCounter(counter: BigInt, map: Map[Int, Int]): BigInt = {
        if (map.isEmpty) {
            return counter
        }
        // Calculates the permutation with repetition of the numbers
        var numerator = BigInt(0)
        var denominator = BigInt(1)
        for ((_, value) <- map) {
            numerator += value
            denominator = denominator * factorial(BigInt(value))
        }
        counter + (factorial(numerator))./(denominator)
    }

    private def checkPartition(partition: Array[Int]): Map[Int, Int] = {
        val map = new HashMap[Int, Int]
        var count = 0
        partition.foreach {
            i => if (updateCaseInRange(i, map)) count += 1
        }

        if (!map.contains(1) || count != partition.length || map.size != 2) {
            return Map.empty[Int, Int]
        }
        map
    }

    /**
     * If the number is in the given range updates the map.
     */
    private def updateCaseInRange(i: Int, map: Map[Int, Int]) = i match {
        case x if (i >= 1 && i < 5) => update(i, map)
        case _ => false
    }

    private def update(i: Int, map: Map[Int, Int]): Boolean = {
        map get i match {
            case None => map += (i -> 1)
            case Some(x) => map(i) = x + 1
        }
        true
    }

    private def factorial(n: BigInt): BigInt = factorial2(n, 1)

    private def factorial2(n: BigInt, result: BigInt): BigInt = if (n == 0) result else factorial2(n - 1, n * result)
}