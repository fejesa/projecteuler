package net.projecteuler.java.util;

public class Counter {

	private long value = 0;

	public long getValue() {
		return value;
	}

	public void increment() {
		++value;
	}

	@Override
	public String toString() {
		return "" + value;
	}
}
