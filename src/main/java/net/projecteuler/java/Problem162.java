package net.projecteuler.java;

import java.math.BigInteger;

/**
 * In the hexadecimal number system numbers are represented using 16 different digits:
 * <p>0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F</p>
 * The hexadecimal number AF when written in the decimal number system equals 10x16+15=175.</br>
 * In the 3-digit hexadecimal numbers 10A, 1A0, A10, and A01 the digits 0,1 and A are all present.
 * Like numbers written in base ten we write hexadecimal numbers without leading zeroes.
 * <p>How many hexadecimal numbers containing at most sixteen hexadecimal digits exist with all of
 * the digits 0,1, and A present at least once? Give your answer as a hexadecimal number.</p>
 * (A,B,C,D,E and F in upper case, without any leading or trailing code that marks the number
 * as hexadecimal and without leading zeroes, e.g. 1A3F and not: 1a3f and not 0x1a3f and not $1A3F
 * and not #1A3F and not 0000001A3F)
 *
 * @author vbox
 *
 */
public class Problem162 {

    public static void main(String ... args) {
        assert solve().equals("3D58725572C62302") : "failed";
    }

    /**
     * Solution is based on the Inclusion�exclusion principle.
     * <ol>
     * <li>total = 15 * 16<sup>i - 1</sup></li> (first digit cannot be ZERO)
     * <li>let A is a set of number that does not contain ZERO = 15<sup>i</sup></li>
     * <li>let B is a set of number that does not contain ONE = 14 * 15<sup>i - 1</sup></li>
     * <li>let C is a set of number that does not contain A = 14 * 15<sup>i - 1</sup></li>
     * <li>does not contain ZERO & ONE = 14<sup>i</sup></li>
     * <li>does not contain ZERO & A = 14<sup>i</sup></li>
     * <li>does not contain ONE & A = 13 * 14<sup>i - 1</sup></li>
     * <li>does not contain ZERO & ONE & A = 13<sup>i</sup></li>
     * <li>contain ZERO & ONE & A = total - (A + B + C) + A&#8746;B + A&#8746;C + B&#8746;C - A&#8746;B&#8746;C</li>
     * </ol>
     * @see <a href="https://en.wikipedia.org/wiki/Inclusion%E2%80%93exclusion_principle</a>
     * @return
     */
    private static String solve() {

        BigInteger c = new BigInteger("13");
        BigInteger d = new BigInteger("14");
        BigInteger e = new BigInteger("15");
        BigInteger f = new BigInteger("16");

        // Represents the number of solution (initially are 4 = {10A, 1A0, A10, A01})
        BigInteger counter = new BigInteger("4");

        for (int i = 4; i <= 16; i++) {
            BigInteger total = e.multiply(f.pow(i - 1));
            BigInteger noZero = e.pow(i);
            BigInteger noOne = d.multiply(e.pow(i - 1));
            BigInteger noA = d.multiply(e.pow(i - 1));
            BigInteger noZeroOne = d.pow(i);
            BigInteger noZeroA = d.pow(i);
            BigInteger noOneA = c.multiply(d.pow(i - 1));
            BigInteger noZeroOneA = c.pow(i);
            BigInteger h = total.subtract(noZero).subtract(noOne).subtract(noA).add(noZeroOne).add(noZeroA).add(noOneA).subtract(noZeroOneA);
            counter = counter.add(h);
        }

        return counter.toString(16).toUpperCase();
    }
}