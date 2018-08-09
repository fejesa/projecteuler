package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Using the given files containing over five-thousand first names, begin by sorting it into alphabetical order.
 * Then working out the alphabetical value for each name, multiply this value by its alphabetical position
 * in the list to obtain a name score.</br>
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth
 * 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 * 53 = 49714.
 * What is the total of all the name scores in the file?
 *
 * @author vbox
 *
 */
public class Problem022 {

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem022.txt") == 871198282L : "failed";
	}

	private static long calculate(String file) throws IOException {
		List<String> names = getNames(file);
		long sum = 0;
		for (int i = 0; i < names.size(); i++) {
			String name = names.get(i);
			int nameValue = 0;
			for (int j = 0; j < name.length(); j++) {
				nameValue += name.charAt(j) - 64;
			}
			sum += (i + 1) * nameValue;
		}
		return sum;
	}

	private static List<String> getNames(String file) throws IOException {
		List<String> names = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		while ((line = in.readLine()) != null) {
			String[] n = line.split(",");
			for (String s : n) {
				names.add(s.substring(1, s.length() - 1));
			}
		}
		in.close();

		// Sorts the list
		Collections.sort(names);

		return names;
	}
}
