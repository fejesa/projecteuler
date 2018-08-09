package net.projecteuler.java;

/**
 * Investigating the number of rectangles in a rectangular grid.
 *
 * @author vuser
 *
 */
public class Problem085 {

	public static void main(String[] args) {
		assert calculate() == 2772 : "failed";
	}

	/**
	 * A rectangle is define by its 4 edges. The number ways to choose 2 vertical edges
	 * is C(w + 1, 2) = (w + 1) * w / 2 (where <i>w</i> is the width and <i>h</i> is
	 * the height of the rectangle). The number ways to choose 2 horizontal
	 * edges is C(h + 1, 2) = (h + 1) * h / 2. So the total rectangles is:</br>
	 * TotalRectangles = (w + 1) * w * (h + 1) * h / 4.
	 */
	private static int calculate() {
		int max = 2 * 1000000;

		int min = Integer.MAX_VALUE;
		int minx = 0;
		int miny = 0;

		for (int x = 1, y = max; y >= x; x++) {
			y = (int) Math.sqrt(4 * max / (x * x + x));
			double rectangles = x * (x + 1) * y * (y + 1) / 4;
			double diff = Math.abs(max - rectangles);
			if (diff < min) {
				min = (int) diff;
				minx = x;
				miny = y;
			}
		}

		return minx * miny;
	}
}
