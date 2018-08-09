package net.projecteuler.java.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Produces the partition of a positive integer n.
 * 
 * @author vuser
 * 
 */
public class Partition {

	public static List<List<Integer>> partition(int n) {
		// The partitions is a list of string
		List<String> list = new ArrayList<String>();
		partition(n, n, "", list);

		// The strings should be converted int values
		List<List<Integer>> parts = new ArrayList<List<Integer>>();
		for (String p : list) {
			List<Integer> plist = new ArrayList<Integer>();
			for (String s : p.split(",")) {
				try {
					plist.add(Integer.valueOf(s));
				} catch (NumberFormatException nfe) {
				}
			}
			parts.add(plist);
		}
		return parts;
	}

	private static void partition(int n, int max, String prefix, List<String> list) {
		if (n == 0) {
			list.add(prefix);
			return;
		}

		for (int i = Math.min(max, n); i >= 1; i--) {
			partition(n - i, i, prefix + "," + i, list);
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> list = partition(9);
		System.out.println(list);
	}
}