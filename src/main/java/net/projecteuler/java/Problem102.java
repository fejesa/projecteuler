package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Three distinct points are plotted at random on a Cartesian plane, for which -1000 <= x, y <= 1000,
 * such that a triangle is formed. Consider the following two triangles:
 * <p>
 * A(-340,495), B(-153,-910), C(835,-947)</br>
 * X(-175,41), Y(-421,-714), Z(574,-645)
 * </p>
 * It can be verified that triangle ABC contains the origin, whereas triangle XYZ does not.</br>
 * Using the given file containing the co-ordinates of one thousand "random"
 * triangles, find the number of triangles for which the interior contains the origin.
 *
 * @author vuser
 *
 */
public class Problem102 {

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem102.txt") == 228 : "failed";
	}

	/**
	 * @see <a href="http://mathforum.org/library/drmath/view/54735.html">Test for Point Inside Triangle</a>
	 * @see <a href="http://2000clicks.com/mathhelp/GeometryTriangleAreaDeterminant.aspx">Using the determinant of a matrix to calculate the area of a triangle</a>
	 */
	private static int calculate(String file) throws IOException {
		List<List<Point>> triangles = getTriangles(file);
		int counter = 0;
		for (List<Point> triangle : triangles) {
			Point a = triangle.get(0);
			Point b = triangle.get(1);
			Point c = triangle.get(2);
			Point p = new Point(0, 0);
			if (getArea(a, b, c) == getArea(a, b, p) + getArea(b, c, p) + getArea(a, c, p)) {
				++ counter;
			}
		}

		return counter;
	}

	private static double getArea(Point a, Point b, Point c) {
		double determinant = a.x * b.y + b.x * c.y + c.x * a.y - a.x * c.y - b.x * a.y - c.x * b.y;	
		return Math.abs(determinant) * 0.5;
	}

	private static List<List<Point>> getTriangles(String file) throws IOException {
		List<List<Point>> triangles = new ArrayList<List<Point>>();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;
		while ((str = in.readLine()) != null) {
			String[] coordinates = str.split(",");
			List<Point> triangle = new ArrayList<Point>();
			triangle.add(new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
			triangle.add(new Point(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3])));
			triangle.add(new Point(Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5])));

			triangles.add(triangle);
		}
		in.close();

		return triangles;
	}

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}
}