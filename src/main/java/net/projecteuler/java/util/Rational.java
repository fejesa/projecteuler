package net.projecteuler.java.util;

import java.math.BigInteger;

public class Rational implements Comparable<Rational>{

	private final BigInteger numerator;
	private final BigInteger denominator;

	public Rational(BigInteger num, BigInteger denom) {
		if (denom.equals(BigInteger.ZERO)) {
			throw new RuntimeException("0 is set");
		}
		numerator = num;
		denominator = denom;
	}

	public Rational(long num, long denom) {
		if (denom == 0) {
			throw new RuntimeException("0 is set");
		}
		numerator = BigInteger.valueOf(num);
		denominator = BigInteger.valueOf(denom);
	}

	public Rational(long num) {
		this(BigInteger.valueOf(num), BigInteger.ONE);
	}

	public static final Rational toRational(double number) {
		return null;
	}

	public final Rational add(Rational that) {
		BigInteger newNum = numerator.multiply(that.denominator);
		BigInteger otherNum = denominator.multiply(that.numerator);
		BigInteger newDen = denominator.multiply(that.denominator);

		newNum = newNum.add(otherNum);

		return reduce(new Rational(newNum, newDen));
	}

	public final Rational subtract(Rational that) {
		BigInteger newNum = numerator.multiply(that.denominator);
		BigInteger otherNum = denominator.multiply(that.numerator);
		BigInteger newDen = denominator.multiply(that.denominator);

		newNum = newNum.subtract(otherNum);

		return reduce(new Rational(newNum, newDen));
	}

	public final Rational multiply(Rational that) {
		return reduce(new Rational(numerator.multiply(that.numerator), denominator.multiply(that.denominator)));
	}

	public final Rational divide(Rational that) {
		return reduce(new Rational(numerator.multiply(that.denominator), denominator.multiply(that.numerator)));
	}

	public final static Rational reduce(Rational rational) {
		BigInteger gcd = rational.numerator.gcd(rational.denominator);
		if (gcd.equals(BigInteger.ONE)) {
			return rational;
		}

		BigInteger rn = rational.numerator.divide(gcd);
		BigInteger rd = rational.denominator.divide(gcd);
		return new Rational(rn, rd);
	}

	public final Rational reciprocal() {
		return new Rational(denominator, numerator);
	}

	public BigInteger numerator() {
		return numerator;
	}

	public BigInteger denominator() {
		return denominator;
	}

	public double doubleValue() {
		return numerator.divide(denominator).doubleValue();
	}

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + denominator.hashCode();
	    result = prime * result + numerator.hashCode();
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj) {
		    return true;
	    }
	    if (obj == null) {
		    return false;
	    }
	    if (getClass() != obj.getClass()) {
		    return false;
	    }

	    Rational other = (Rational) obj;

	    Rational r1 = reduce(this);
	    Rational r2 = reduce(other);

	    if (!r1.numerator.equals(r2.numerator) && !r1.denominator.equals(r2.denominator)) {
		    return false;
	    }

	    return true;
    }

	@Override
    public String toString() {
		return "" + numerator + "/" + denominator;
    }

	@Override
    public int compareTo(Rational o) {
		BigInteger n = numerator.multiply(o.denominator);
		BigInteger d = denominator.multiply(o.numerator);
		return n.compareTo(d);
    }
}