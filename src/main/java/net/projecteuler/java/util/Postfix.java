package net.projecteuler.java.util;

import java.util.Stack;
import java.util.StringTokenizer;

public class Postfix {

	private static final String DELIMS = "+-*/ ";

	private static String operate(String op, double lhs, double rhs){
		if (op.equals("+")) {
			return Double.toString(lhs + rhs);
		}
		if (op.equals("*")){
			return Double.toString(lhs * rhs);
		}
		if (op.equals("-")){
			return Double.toString(lhs - rhs);
		}
		if (op.equals("/")){
			return Double.toString(lhs / rhs);
		}

		throw new IllegalArgumentException("unrecognized operator " + op);
	}

	public static double evaluate(String s) {
		Stack<String> stack = new Stack<String>();
		StringTokenizer tokens = new StringTokenizer(s, DELIMS, true);
		while (tokens.hasMoreTokens()) {
			String t = tokens.nextToken();
			if (DELIMS.indexOf(t) >= 0) {
				if (!t.equals(" ")) {
					double right = Double.parseDouble(stack.pop());
					double left = Double.parseDouble(stack.pop());
					stack.push(operate(t, left, right));
				}
			} else {
				stack.push(t);
			}
		}

		double result = Double.parseDouble(stack.pop());
		if (stack.size() > 0) {
			throw new IllegalArgumentException("non empty stack on " + s);
		}
		return result;
	}

	public static void main(String[] args) {

		String expression = "4 3 * 2 * 1 -";
		System.out.println("value of " + expression + " = " + evaluate(expression));
		expression = "1 3 /";
		System.out.println("value of " + expression + " = " + evaluate(expression));

	}
}