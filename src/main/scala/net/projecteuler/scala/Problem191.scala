package net.projecteuler.scala

/**
 * A particular school offers cash rewards to children with good attendance
 * and punctuality. If they are absent for three consecutive days or late
 * on more than one occasion then they forfeit their prize.</br>
 * During an n-day period a trinary string is formed for each child
 * consisting of L's (late), O's (on time), and A's (absent).</br>
 * Although there are eighty-one trinary strings for a 4-day period that
 * can be formed, exactly forty-three strings would lead to a prize:
 * <p>
 * OOOO OOOA OOOL OOAO OOAA OOAL OOLO OOLA OAOO OAOA</br>
 * OAOL OAAO OAAL OALO OALA OLOO OLOA OLAO OLAA AOOO</br>
 * AOOA AOOL AOAO AOAA AOAL AOLO AOLA AAOO AAOA AAOL</br>
 * AALO AALA ALOO ALOA ALAO ALAA LOOO LOOA LOAO LOAA</br>
 * LAOO LAOA LAAO
 * </p>
 * How many "prize" strings exist over a 30-day period?
 *
 * @author vuser
 *
 */
object Problem191 {

    def main(args: Array[String]): Unit = {
        var count = 0
        val limit = 30
        // At least 10 'O' days must be exist in a 30-day period
        for (o <- 10 to limit) {
            var p = partition(o, limit)
            while (p != null) {
                // If there is a partition we have to take its permutations
                val perm = permutations(p)
                // (o + 1) means in each permutation one 'O' can be replaced with 'L'
                count += perm * (o + 1)
                p = nextPartition(p)
            }
        }
        assert(count == 1918080160)
    }

    private def permutations(partition: Array[Int]): Int = {
        var zero = 0
        var one = 0
        var two = 0
        for (i <- 0 until partition.length) {
            if (partition(i) == 0) zero +=1
            else if (partition(i) == 1) one +=1
            else two +=1
        }
        val numerator = factor(partition.length)
        val denominator = factor(zero) * factor (one) * factor(two)
        (numerator / denominator).intValue()
    }

    private def factor(n: Int): BigInt = {
        var factor = BigInt(1)
        for (i <- 2 to n) {
            factor = factor * BigInt(i)
        }
        factor
    }

    private def nextPartition(partition: Array[Int]): Array[Int] = {
        val next = partition.clone()
        for (i <- partition.length - 1 to 0 by -1) {
            if (partition(i) == 0) {
                for (j <- 0 until i) {
                    if (partition(j) == 2) {
                        next(j) = 1
                        next(i) = 1
                        return next
                    }
                }
            }
        }
        null
    }

    private def partition(barrier: Int, limit: Int): Array[Int] = {
        if (barrier <= 0) {
            return null
        }

        var lLimit = limit
        var lBarrier = barrier
        val bags = Array.ofDim[Int](barrier + 1)
        var i = 0

        while (lLimit > 0) {
            if (lLimit - lBarrier >= 2) {
                bags(i) = 2
            } else if (lLimit - lBarrier == 1) {
                bags(i) = 1
            }
            lLimit -= 3
            lBarrier -= 1
            i += 1
        }
        bags
    }
}