package net.projecteuler.java;

/**
 * Find the number of right angle triangles in the quadrant.
 *
 * @author vuser
 */
public class Problem091 {

	public static void main(String[] args) {
		assert calculate(50) == 14234 : "failed";
	}

	private static int calculate(int limit) {
		int counter = 0;
		for (int x1 = 0; x1 <= limit; ++x1) {
			for (int y1 = 0; y1 <= limit; ++y1) {
				if (x1 == 0 && y1 == 0) {
					continue;
				}
				for (int x2 = 0; x2 <= limit; ++x2) {
					for (int y2 = 0; y2 <= limit; ++y2) {
						if (x2 == 0 && y2 == 0) {
							continue;
						}
						if ((x1 == x2 && y1 != y2) || (x1 != x2 && y1 == y2) || (x1 != x2 && y1 != y2)) {
							if (isRightTriangle(x1, y1, x2, y2)) {
								++counter;
							}
						}
					}
				}
			}
		}
		// Because of each x1, x2, y1, y2 combinations are symmetric we have to divide the result
		return counter / 2;
	}

	/**
	 * Test if a<sup>2</sup>, b<sup>2</sup> and c<sup>2</sup> is a pythagorean triple or not.
	 */
	private static boolean isRightTriangle(int x1, int y1, int x2, int y2) {
		int a2 = x1 * x1 + y1 * y1;
		int b2 = x2 * x2 + y2 * y2;
		int c2 = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
		if (a2 + b2 == c2 || a2 + c2 == b2 || b2 + c2 == a2) {
			return true;
		}
		return false;
		
	}
}