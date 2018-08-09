package net.projecteuler.scala

/**
 * Exploring the shortest path from one corner of a cuboid to another.
 */
object Problem086 {

    def main(args: Array[String]): Unit = {
        var counter = 0
        var m = 2
        while (counter < 1000000) {
            m += 1
            for (i <- 2 to 2 * m) {
                val d = Math.sqrt(m * m + i * i)
                if (d == Math.floor(d)) {
                    counter = counter + Math.min(i, m + 1) - (i + 1) / 2
                }
            }
        }
        assert (m == 1818)
    }
}