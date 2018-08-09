package net.projecteuler.scala

object Problem032 {
    def main(args: Array[String]): Unit = {
        val ms = for {
            a <- 2 to 10000; b <- 2 to 10000 / a
            m = a * b; s = a.toString + b + m
            if s.length == 9 && (1 to 9).mkString.forall(s.contains(_))
        } yield m
        val r = ms.distinct.sum
    }
}