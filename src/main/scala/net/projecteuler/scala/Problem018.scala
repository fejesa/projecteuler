package net.projecteuler.scala

import scala.io.Source

/**
 * By starting at the top of the triangle below and moving to adjacent numbers
 * on the row below, the maximum total from top to bottom is 23.
 * <p style="text-align:center;font-family:courier new">
 * <span style="color:#ff0000;"><b>3</b></span><br />
 * <span style="color:#ff0000;"><b>7</b></span> 4<br />
 * 2 <span style="color:#ff0000;"><b>4</b></span> 6<br />
 * 8 5 <span style="color:#ff0000;"><b>9</b></span> 3
 * </p>
 * <p>
 * That is, 3 + 7 + 4 + 9 = 23.
 * </p>
 * Find the maximum total from top to bottom of the triangle below:
 * <p style="text-align:center;font-family:courier new;">
 * 75<br />
 * 95 64<br />
 * 17 47 82<br />
 * 18 35 87 10<br />
 * 20 04 82 47 65<br />
 * 19 01 23 75 03 34<br />
 * 88 02 77 73 07 63 67<br />
 * 99 65 04 28 06 16 70 92<br />
 * 41 41 26 56 83 40 80 70 33<br />
 * 41 48 72 33 47 32 37 16 94 29<br />
 * 53 71 44 65 25 43 91 52 97 51 14<br />
 * 70 11 33 28 77 73 17 78 39 68 17 57<br />
 * 91 71 52 38 17 14 91 43 58 50 27 29 48<br />
 * 63 66 04 68 89 53 67 30 73 16 69 87 40 31<br />
 * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 * </p>
 * 
 * @author vuser
 * 
 */
object Problem018 {

    def main(args: Array[String]): Unit = {
        val matrix = readData("src/main/resources/problem018.txt")
        assert(solve(matrix) == 1074)
    }

    def solve(matrix: Array[Array[Int]]): Int = {
        for (i <- matrix(0).length - 1 until 0 by -1) {
            for (j <- 0 until i) {
                matrix(i - 1)(j) = Math.max(matrix(i)(j), matrix(i)(j + 1)) + matrix(i - 1)(j)
            }
        }
        matrix(0)(0)
    }

    def readData(file: String) = {
        // Read lines
        val lines = Source.fromFile(file).getLines().toList
        val matrix = Array.ofDim[Int](lines.length, lines.last.split(" ").length)

        // Fill a two dim array with values
        var rowIndex = 0
        for (line <- lines) {
            var columnIndex = 0
            for (n <- line.split(" ")) {
                matrix(rowIndex)(columnIndex) = n.toInt
                columnIndex += 1
            }
            rowIndex += 1
        }
        matrix
    }
}