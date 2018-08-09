package net.projecteuler.scala

/**
 * How many 20 digit numbers n (without any leading zero)
 * exist such that no three consecutive digits of n have a sum greater than 9?
 *
 * @author andrian
 *
 */
object Problem164 {

    val data = Array.ofDim[Long](10, 10, 19)

    def main(args: Array[String]): Unit = {
        for (i <- 0 until 19) {
            for (j <- 0 until 10) {
                for (k <- 0 until 10) {
                    fill(k, j, i)
                }
            }
        }
        var sum = 0L
        for (i <- 1 until 10) {
            for (j <- 0 until 10 - i) {
                sum += data(i)(j)(18)
            }
        }

        assert(sum == 378158756814587L)
        
    }

    def fill(x1: Int, x2: Int, l: Int) = {
        if (l == 0) {
            data(x1)(x2)(l) = 1
        } else if (l == 1) {
            data(x1)(x2)(l) = 10 - x1 - x2
        } else {
            for (i <- 0 until 10 - x1 - x2) {
                data(x1)(x2)(l) += data(x2)(i)(l - 1)
            }
        }
    }
}