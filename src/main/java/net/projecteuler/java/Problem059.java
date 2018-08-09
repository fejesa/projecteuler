package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>Each character on a computer is assigned a unique code and the preferred standard is ASCII
 * (American Standard Code for Information Interchange). For example, uppercase A = 65,
 * asterisk (*) = 42, and lowercase k = 107.</p>
 * <p>A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each
 * byte with a given value, taken from a secret key. The advantage with the XOR function is that
 * using the same encryption key on the cipher text, restores the plain text;
 * for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.</p>
 * <p>For unbreakable encryption, the key is the same length as the plain text message,
 * and the key is made up of random bytes. The user would keep the encrypted message
 * and the encryption key in different locations, and without both "halves", it is
 * impossible to decrypt the message.</p>
 * <p>Unfortunately, this method is impractical for most users, so the modified method
 * is to use a password as a key. If the password is shorter than the message, which is likely,
 * the key is repeated cyclically throughout the message. The balance for this method is using
 * a sufficiently long password key for security, but short enough to be memorable.</p>
 * Your task has been made easy, as the encryption key consists of three lower case characters.
 * Using the given file containing the encrypted ASCII codes, and the knowledge that the
 * plain text must contain common English words, decrypt the message and find the
 * sum of the ASCII values in the original text.
 * 
 * @author vbox
 *
 */
public class Problem059 {

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem059.txt") == 107359L : "failed";
	}

	public static long calculate(String file) throws IOException {
		long sum = 0;

		int[] chiperArray = getChiper(file);

		String decrypted = decrypt(chiperArray);
		//System.out.println(decrypted);

		for (int i = 0; i < decrypted.length(); i++) {
			sum += (int) decrypted.charAt(i);
		}

		return sum;
	}

	/**
	 * Decrypts the encrypted plain text.
	 * Solution is based on finding the most frequency English characters.
	 * Find more information here: <a>href="http://millikeys.sourceforge.net/freqanalysis.html">Letter Frequency Counter</a>
	 * @param chiperArray The encrypted plain text in ASCII codes.
	 * @return The original plain text.
	 */
	private static String decrypt(int[] chiperArray) {

		String KEY_CHARS = "abcdefghijklmnopqrstuvwxyz";

		float frequencySpace = 0.1874F * chiperArray.length;
		float frequencyEChar = 0.0960F * chiperArray.length;
		float frequencyTChar = 0.0702F * chiperArray.length;

		float minDeviation = chiperArray.length;

		String decrypted = null;

		for (int i = 0; i < KEY_CHARS.length(); i++) {
			for (int j = 0; j < KEY_CHARS.length(); j++) {
				for (int k = 0; k < KEY_CHARS.length(); k++) {

					char[] encriptionKey = new char[] {KEY_CHARS.charAt(i), KEY_CHARS.charAt(j), KEY_CHARS.charAt(k)};

					StringBuilder sb = new StringBuilder();
					int space = 0; int eChar = 0; int tChar = 0;

					for (int m = 0; m < chiperArray.length; m++) {
						int t = chiperArray[m] ^ encriptionKey[m % encriptionKey.length];
						if (t == 32) {
							++space;
						} else if (t == 69 || t == 101) {
							++eChar;
						} else if (t == 84 || t == 116) {
							++tChar;
						}
						
						sb.append((char) t);
					}

					float deviation = Math.abs(frequencySpace - space) + Math.abs(frequencyEChar - eChar) + Math.abs(frequencyTChar - tChar);
					if (deviation < minDeviation) {
						minDeviation = deviation;
						decrypted = sb.toString();
					}
				}
			}
		}

		return decrypted;
	}

	/**
	 * Loads the given file into the array.
	 * @param file The file contains the encrypted plain text as ASCII codes.
	 * @return Array of ASCII codes.
	 * @throws IOException
	 */
	private static int[] getChiper(String file) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		while ((line = in.readLine()) != null) {
			builder.append(line);
		}
		in.close();

		String[] chars = builder.toString().split(",");

		int[] chiperArray = new int[chars.length];
		for (int i = 0; i < chiperArray.length; i++) {
			chiperArray[i] = Integer.parseInt(chars[i]);
		}

		return chiperArray;
	}
}