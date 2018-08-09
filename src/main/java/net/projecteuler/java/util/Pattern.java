package net.projecteuler.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		System.out.println(getPattern(Arrays.asList(1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2)));

		/*System.out.println(getPattern(Arrays.asList(2, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4)));
		
		System.out.println(getPattern(Arrays.asList(1, 2, 3, 4)));

		System.out.println(getPattern(Arrays.asList(2, 1, 1, 2, 2, 1)));
		System.out.println(getPattern(Arrays.asList(1, 2, 3, 4)));
		System.out.println(getPattern(Arrays.asList(1, 2, 2, 2, 2)));
		System.out.println(getPattern(Arrays.asList(2, 1, 1, 2, 1, 1)));		
		System.out.println(getPattern(Arrays.asList(2, 1, 1, 2, 1, 1, 2, 1)));
		System.out.println(getPattern(Arrays.asList(1, 2, 3, 4, 1)));
		System.out.println(getPattern(Arrays.asList(7, 1, 4, 3, 1, 2, 2, 1, 3, 4, 1, 14, 7, 1, 4, 3, 1, 2, 2, 1, 3, 4, 1, 14, 7, 1, 4, 3, 1, 2)));
		System.out.println(getPattern(Arrays.asList("a", "b", "c", "a", "b")));*/
	}

	/**
	 * Finds the longest pattern in the sequence.
	 * @param <T> The type of the sequence element.
	 * @param list The sequence.
	 * @return The longest pattern is found.
	 */
	public static <T extends Comparable <? super T >> List<T> getPattern(List<T> list) {
		List<T> pattern = new ArrayList<T>();

		search: for (int position = 0, begin = 0; position < list.size(); ) {

			// Gets the next same comparable in the sequence
			int next = next(position, list);
			if (next == position) { // There is no same comparable in the remain - skips it and continue with the next one in the sequence
				++position;
				begin = position;
				continue;
			}

			pattern.addAll(list.subList(position, next));

			// The given pattern covers all elements in the sequence
			if (match(list, begin, pattern)) {
				break search;
			}

			// Continue with the next part
			position = next;
		}

		return pattern;
	}

	private static <T extends Comparable <? super T >> int next(int pos, List<T> list) {
		for (int i = pos + 1; i < list.size(); i++) {
			if (list.get(pos).compareTo(list.get(i)) == 0) {
				return i;
			} 
		}
		return pos;
	}

	private static <T extends Comparable <? super T >> boolean match(List<T> list, int pos, List<T> pattern) {
		for (int i = pos; i < list.size(); i += pattern.size()) {
			for (int j = 0; j < pattern.size() && i + j < list.size(); j++) {
				if (list.get(i + j).compareTo(pattern.get(j)) != 0) {
					return false;
				}
			}
		}

		return true;
	}
}
