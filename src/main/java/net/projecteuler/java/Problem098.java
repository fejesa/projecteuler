package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.projecteuler.java.util.PermutationGenerator;

/**
 * By replacing each of the letters in the word CARE with 1, 2, 9, and 6 respectively,
 * we form a square number: 1296 = 36<sup>2</sup>. What is remarkable is that, by using
 * the same digital substitutions, the anagram, RACE, also forms a square number:
 * 9216 = 96<sup>2</sup>. We shall call CARE (and RACE) a square anagram word pair
 * and specify further that leading zeroes are not permitted, neither may a different
 * letter have the same digital value as another letter.</br>
 * Using the given text file containing nearly two-thousand common English words,
 * find all the square anagram word pairs (a palindromic word is NOT considered to
 * be an anagram of itself).</br>
 * What is the largest square number formed by any member of such a pair?
 *
 * @author vuser
 *
 */
public class Problem098 {

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem098.txt") == 18769 : "failed";
	}

	/**
	 * 
	 * NOTE: this is a very slow implementation of the problem :-(
	 */
	private static int calculate(String file) throws IOException {
		Map<String, List<String>> words = read(file);

		int max = 0;
		for (Map.Entry<String, List<String>> entry : words.entrySet()) {
			if (entry.getKey().length() > max) {
				max = entry.getKey().length();
			}
		}

		Map<String, List<Integer>> squares = squares(max);

		int maxSquare = 0;
		for (Map.Entry<String, List<String>> wentry : words.entrySet()) {
			for (Map.Entry<String, List<Integer>> sentry : squares.entrySet()) {
				if (sentry.getKey().length() == wentry.getKey().length()) {
					int square = match(wentry.getKey(), wentry.getValue(), sentry.getKey(), sentry.getValue());
					if (square > maxSquare) {
						maxSquare = square;
					}
				}
			}
		}

		return maxSquare;
	}

	private static int match(String letters, List<String> words, String digits, List<Integer> squares) {

		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < digits.length(); i++) {
			String digit = String.valueOf(digits.charAt(i) - 48);
			String letter =  String.valueOf(letters.charAt(i));
			String value = map.put(Integer.valueOf(digit), letter);
			if (value != null && !value.equals(letter)) {
				return 0;
			}
		}

		PermutationGenerator permutations = new PermutationGenerator(letters.length());

		while (permutations.hasMore()) {
			int[] p = permutations.getNext();
			Map<String, Integer> digitMap = new HashMap<String, Integer>();
			for (int i = 0; i < p.length; i++) {
				digitMap.put(String.valueOf(letters.charAt(i)), digits.charAt(p[i]) - 48);
			}

			int counter = 0;
			int max = 0;
			for (int i = 0; i < words.size(); i++) {
				String word = words.get(i);
				b: for (int j = 0; j < squares.size(); j++) {
					int square = squares.get(j);
					if (match(word, square, digitMap)) {
						++counter;
						if (max < square) {
							max = square;
						}
						if (counter == words.size()) {
							System.out.println(words + ":" + max + ":" + digitMap);
							return max;
						}
						break b;
					}
				}
			}
		}

		return 0;
	}

	private static boolean match(String word, int square, Map<String, Integer> map) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			builder.append(map.get(String.valueOf(word.charAt(i))));
		}

		return Integer.parseInt(builder.toString()) == square;
	}

	private static Map<String, List<String>> read(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));

		Map<String, List<String>> map = new HashMap<String, List<String>>();

		try {

			List<String> lines = new ArrayList<String>();
			String str;
			while ((str = in.readLine()) != null) {
				lines.add(str);
			}
			List<String> words = new ArrayList<String>();
			for (String line : lines) {
				String[] w = line.split(",");
				for (String s : w) {
					words.add(s.substring(1, s.length() - 1));
				}
			}

			for (String word : words) {
				String ordered = sort(word);
				List<String> anagrams = map.get(ordered);
				if (anagrams == null) {
					anagrams = new ArrayList<String>();
					map.put(ordered, anagrams);
				}
				anagrams.add(word);
			}

			Map<String, List<String>> m = new HashMap<String, List<String>>();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				// We investigate only the pairs,
				// but the input file contains only one triple (POST, STOP, SPOT)
				if (entry.getValue().size() == 2) {
					m.put(entry.getKey(), entry.getValue());
				}
			}

			return m;

		} finally {
			in.close();
		}

	}

	private static Map<String, List<Integer>> squares(int max) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		
		search: for (int i = 1; ; i++) {
			int square = (int)Math.pow(i, 2);
			int length = (int) Math.log10(square) + 1;
			if (length > max) {
				break search;
			}

			String ordered = sort(String.valueOf(square));
			List<Integer> sqs = map.get(ordered);
			if (sqs == null) {
				sqs = new ArrayList<Integer>();
				map.put(ordered, sqs);
			}
			sqs.add(square);
		}

		Map<String, List<Integer>> m = new HashMap<String, List<Integer>>();
		for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
			if (entry.getValue().size() > 1) {
				m.put(entry.getKey(), entry.getValue());
			}
		}

		return m;
	}

	private static String sort(String s) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < s.length(); i++) {
			String c = String.valueOf(s.charAt(i));
			Integer count = map.get(c);
			if (count == null) {
				map.put(c, Integer.valueOf(1));
				continue;
			}
			map.put(c, Integer.valueOf(++count));
		}

		Map<String, Integer> sorted = sortByValue(map);
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				builder.append(entry.getKey());
			}
		}

		return builder.toString();
	}

	private static <K extends Comparable<? super K>, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				int c = o2.getValue().compareTo(o1.getValue());;
				if (c == 0) {
					return o2.getKey().compareTo(o1.getKey());
				}
				return c;
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}
}