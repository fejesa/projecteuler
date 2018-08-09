package net.projecteuler.java;

import java.util.HashMap;
import java.util.Map;

/**
 * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c},
 * there are exactly three solutions for p = 120.</br>
 * {20,48,52}, {24,45,51}, {30,40,50}</br>
 * For which value of p <= 1000, is the number of solutions maximised?
 *
 * @author vuser
 *
 */
public class Problem039 {

	public static void main(String[] args) {
		assert calculate() == 840 : "failed";
	}

	public static int calculate() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int a = 1; a <= 1000; a++) {
			for (int b = a; b <= 1000; b++) {
				int square = a * a + b * b;
				for (int c = 1; a + b + c <= 1000; c++) {
					if (c * c == square) {
						int perimeter = a + b + c;
						Integer value = map.get(perimeter);
						if (value == null) {
							map.put(perimeter, 1);
						} else {
							map.put(perimeter, value.intValue() + 1);
						}
					}
				}
			}
		}
		int solutions = 0; int perimeter = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (solutions < entry.getValue()) {
				solutions = entry.getValue();
				perimeter = entry.getKey();
			}
		}

		return perimeter;
	}
}