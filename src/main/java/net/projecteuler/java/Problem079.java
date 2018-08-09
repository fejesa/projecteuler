package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A common security method used for online banking is to ask the user for three
 * random characters from a passcode. For example, if the passcode was 531278,
 * they may ask for the 2nd, 3rd, and 5th characters; the expected reply would
 * be: 317. The given text file, contains fifty successful login attempts.</br>
 * Given that the three characters are always asked for in order, analyse the
 * file so as to determine the shortest possible secret passcode of unknown
 * length.
 * 
 * @author vuser
 * 
 */
public class Problem079 {

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem079.txt") == 73162890L : "failed";
	}

	private static long calculate(String file) throws IOException {
		List<String> keylogs = getKeylogs(file);

		List<Integer> passcode = new ArrayList<Integer>();
		for (String key : keylogs) { // Iterates through the keylogs
			passcode = apply(passcode, key);
		}

		StringBuilder builder = new StringBuilder();
		for (int i : passcode) {
			builder.append(i);
		}

		return Long.parseLong(builder.toString());
	}

	/**
	 * Insert or change the order of the key characters int the list (sequence).
	 * @param old Contains the result of the previous apply process; initially is empty.
	 * @param key The three digit sequence.
	 * @return The updated sequence of the digits.
	 */
	private static List<Integer> apply(List<Integer> old, String key) {

		// Splits the key into digits
		List<Integer> random = new ArrayList<Integer>();
		for (int i = 0; i < key.length(); i++) {
			random.add(key.charAt(i) - 48);
		}

		List<Integer> tmp = new ArrayList<Integer>();

		// Marks the actual key digit position
		int pos = 0;
		// Iterates through the previous sequence
		for (int index = 0; index < old.size(); index++) {
			int elem = old.get(index);

			// Elem is not a new digit - inserts the sequence 
			if (!random.contains(elem)) {
				tmp.add(elem);
			} else {
				int keyElem = random.get(pos);
				if (keyElem == elem) { // Previous sequence already contains the digit at the 'right' position 
					tmp.add(elem);
				} else if (!old.contains(keyElem)) {
					// New digit, inserts it into the sequence
					tmp.add(keyElem);
					--index;
				} else {
					tmp.add(keyElem);
				}
				++pos;
			}
		}

		// If there are some remaining digits appends the tail of the sequence 
		for (; pos < random.size(); pos++) {
			tmp.add(random.get(pos));
		}

		return tmp;
	}

	private static List<String> getKeylogs(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));

		List<String> keylogs = new ArrayList<String>();
		String str;
		while ((str = in.readLine()) != null) {
			keylogs.add(str);
		}
		in.close();

		return keylogs;
	}
}