package net.projecteuler.scala

/**
 * Given the positive integers, x, y, and z, are consecutive terms of an arithmetic progression,
 * the least value of the positive integer, n, for which the equation,
 * x<sup>2</sup> - y<sup>2</sup> - z<sup>2</sup> = n, has exactly two solutions is n = 27:</br>
 * 34<sup>2</sup> - 27<sup>2</sup> 20<sup>2</sup> = 12<sup>2</sup> - 9<sup>2</sup> - 6<sup>2</sup> = 27</br>
 * It turns out that n = 1155 is the least value which has exactly ten solutions.</br>
 * How many values of n less than one million have exactly ten distinct solutions?
 *
 * @author vuser
 */
object Problem135 {

    /**
     * <ol>
     * <li>(x + k)<sup>2</sup> - x<sup>2</sup> - (x - k)<sup>2</sup> = n</li>
     * <li>x > k</li>
     * <li>transform the first statement: x * (4 * k - x) = n</li>
     * <li>Let's j = 4k - x and 3x > 4k - x</li>
     * <li>j * k < 1000000</li>
     * </ol>
     */
    def main(args: Array[String]): Unit = {
        val limit = 1000000
        val array = new Array[Int](limit)
        for (x <- 1 until limit) {
            var j = 4 - x % 4
            while (j < 3 * x && j * x < limit) {
                array(j * x) += 1
                j += 4
            }
        }
        assert(array.count(i => i == 10) == 4989)
    }
}