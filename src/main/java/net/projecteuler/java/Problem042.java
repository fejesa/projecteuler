package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The n<sup>th</sup> term of the sequence of triangle numbers is given by, t<sub>n</sub> = n(n+1)/2;
 * so the first ten triangle numbers are:</br>
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...</br>
 * By converting each letter in a word to a number corresponding to its alphabetical position
 * and adding these values we form a word value.
 * For example, the word value for SKY is 19 + 11 + 25 = 55 = t<sub>10</sub>.
 * If the word value is a triangle number then we shall call the word a triangle word.
 * Using the given 16K text file containing nearly two-thousand common English words,
 * how many are triangle words?
 *
 * @author vuser
 *
 */
public class Problem042 {

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem042.txt") == 162 : "failed";
	}

	private static int calculate(String file) throws IOException {
		List<String> words = getWords(file);
		int counter = 0;
		for (String word : words) {
			int value = 0;
			for (int i = 0; i < word.length(); i++) {
				value += word.charAt(i) - 64;
			}
			for (int n = 1, number = 0; number < value; n++) {
				number = n * (n + 1) / 2;
				if (number == value) {
					counter++;
				}
			}
		}
		return counter;
	}

	private static List<String> getWords(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		List<String> lines = new ArrayList<String>();
		String str;
		while ((str = in.readLine()) != null) {
			lines.add(str);
		}
		in.close();
		List<String> words = new ArrayList<String>();
		for (String line : lines) {
			String[] row = line.split(",");
			for (String s : row) {
				words.add(s.substring(1, s.length() - 1));
			}
		}
		return words;
	}
}