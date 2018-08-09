package net.projecteuler.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.projecteuler.java.util.Tuple;

/**
 * In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:</br>
 * <ul><li><b>High Card</b>: Highest value card.</li>
 * <li><b>One Pair</b>: Two cards of the same value.</li>
 * <li><b>Two Pairs</b>: Two different pairs.</li>
 * <li><b>Three of a Kind</b>: Three cards of the same value.</li>
 * <li><b>Straight</b>: All cards are consecutive values.</li>
 * <li><b>Flush</b>: All cards of the same suit.</li>
 * <li><b>Full House</b>: Three of a kind and a pair.</li>
 * <li><b>Four of a Kind</b>: Four cards of the same value.</li>
 * <li><b>Straight Flush</b>: All cards are consecutive values of same suit.</li>
 * <li><b>Royal Flush</b>: Ten, Jack, Queen, King, Ace, in same suit.</li></ul>
 * <p>The cards are valued in the order:<br />2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.</p>
 * <p>If two players have the same ranked hands then the rank made up of the highest value wins;
 * for example, a pair of eights beats a pair of fives (see example 1 below).
 * But if two ranks tie, for example, both players have a pair of queens, then highest cards
 * in each hand are compared (see example 4 below); if the highest cards tie then
 * the next highest cards are compared, and so on.</p>
 * <p>Consider the following five hands dealt to two players:</p>
 * <table>
 * <tr><td><b>Hand</b></td><td>&nbsp;</td><td><b>Player 1</b></td><td>&nbsp;</td><td><b>Player 2</b></td><td>&nbsp;</td><td><b>Winner</b></td></tr>
 * <tr>
 * <td style="vertical-align:top;"><b>1</b></td><td>&nbsp;</td><td>5H 5C 6S 7S KD<br/>
 * Pair of Fives</td><td>&nbsp;</td><td>2C 3S 8S 8D TD<br/>
 * Pair of Eights</td><td>&nbsp;</td><td style="vertical-align:top;">Player 2</td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>2</b></td><td>&nbsp;</td><td>5D 8C 9S JS AC<br/>
 * Highest card Ace</td><td>&nbsp;</td><td>2C 5C 7D 8S QH<br/>
 * Highest card Queen</td><td>&nbsp;</td><td style="vertical-align:top;">Player 1</td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>3</b></td><td>&nbsp;</td><td>2D 9C AS AH AC<br/>
 * Three Aces</td><td>&nbsp;</td><td>3D 6D 7D TD QD<br/>
 * Flush with Diamonds</td><td>&nbsp;</td><td style="vertical-align:top;">Player 2</td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>4</b></td><td>&nbsp;</td><td>4D 6S 9H QH QC<br/>
 * Pair of Queens<br/>Highest card Nine</td><td>&nbsp;</td><td>3D 6D 7H QD QS<br/>
 * Pair of Queens<br/>Highest card Seven</td><td>&nbsp;</td><td style="vertical-align:top;">Player 1</td>
 * </tr>
 * <tr>
 * <td style="vertical-align:top;"><b>5</b></td><td>&nbsp;</td><td>2H 2D 4C 4D 4S<br/>
 * Full House<br/>With Three Fours</td><td>&nbsp;</td><td>3C 3D 3S 9S 9D<br/>
 * Full House<br/>with Three Threes</td><td>&nbsp;</td><td style="vertical-align:top;">Player 1</td>
 * </tr>
 * </table>
 * <p>The given file, contains one-thousand random hands dealt to two players.
 * Each line of the file contains ten cards (separated by a single space): the first five are
 * Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid
 * (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.</p>
 * How many hands does Player 1 win?
 *
 * @author vuser
 *
 */
public class Problem054 {

	private final static int P1 = 1;
	private final static int P2 = -1;

	public static void main(String[] args) throws Exception {
		assert calculate("src/main/resources/problem054.txt") == 376 : "failed";
	}

	private static int calculate(String file) throws IOException {
		int counter = 0;
		List<String> lines = read(file);

		for (String line : lines) {
			if (compare(getCards(line, 1), getCards(line, 2)) == P1) {
				++counter;
			}
		}

		return counter;
	}

	/**
	 * Compares the two hands (in each hand there is a clear winner).
	 * @param player1 The one player.
	 * @param player2 The second player.
	 * @return
	 */
	private static int compare(Card[] player1, Card[] player2) {

		if (isRoyalFlush(player1)) {
			return P1;
		}
		if (isRoyalFlush(player2)) {
			return P2;
		}

		int sf1 = isStraightFlush(player1);
		int sf2 = isStraightFlush(player2);
		if (sf1 > 0 && sf2 > 0) {
			return sf1 > sf2 ? P1 : P2;
		} else if (sf1 > 0) {
			return P1;
		} else if (sf2 > 0) {
			return P2;
		}

		int fk1 = isFourOfKind(player1);
		int fk2 = isFourOfKind(player2);
		if (fk1 > 0 && fk2 > 0) {
			return fk1 > fk2 ? P1 : P2;
		} else if (fk1 > 0) {
			return P1;
		} else if (fk2 > 0) {
			return P2;
		}

		Tuple<Integer, Integer> fh1 = isFullHouse(player1);
		Tuple<Integer, Integer> fh2 = isFullHouse(player2);
		if (fh1.getLefty() > 0 && fh2.getLefty() > 0) {
			if (fh1.getLefty() > fh2.getLefty()) {
				return P1;
			} else if (fh1.getLefty() < fh2.getLefty()) {
				return P2;
			} else if (fh1.getRighty() > fh2.getRighty()) {
				return P1;
			} else {
				return P2;
			}
		} else if (fh1.getLefty() > 0) {
			return P1;
		} else if (fh2.getLefty() > 0) {
			return P2;
		}

		if (isFlush(player1)) {
			return P1;
		}
		if (isFlush(player2)) {
			return P2;
		}

		int s1 = isStraight(player1);
		int s2 = isStraight(player2);
		if (s1 > 0 && s2 > 0) {
			 return s1 > s2 ? P1 : P2;
		} else if (s1 > 0) {
				return P1;
		} else if (s2 > 0) {
			return P2;
		}

		Tuple<Integer, Integer> tk1 = isThreeOfKind(player1);
		Tuple<Integer, Integer> tk2 = isThreeOfKind(player2);
		if (tk1.getLefty() > 0 && tk2.getLefty() > 0) {
			 return tk1.getLefty() > tk2.getLefty() ? P1 : P2;
		} else if (tk1.getLefty() > 0) {
			return P1;
		} else if (tk2.getLefty() > 0) {
			return P2;
		}

		Tuple<Integer, Integer> tp1 = isTwoPair(player1);
		Tuple<Integer, Integer> tp2 = isTwoPair(player2);
		if (tp1.getLefty() > 0 && tp2.getLefty() > 0) {
			return tp1.getLefty() > tp2.getLefty() ? P1 : (tp1.getRighty() > tp2.getRighty() ? P1 : P2);
		} else if (tp1.getLefty() > 0) {
			return P1;
		} else if (tp2.getLefty() > 0) {
			return P2;
		}

		Tuple<Integer, Integer> op1 = isOnePair(player1);
		Tuple<Integer, Integer> op2 = isOnePair(player2);
		if (op1.getLefty() > 0 && op2.getLefty() > 0) {
			return op1.getLefty() > op2.getLefty() ? P1 : P2;
		} else if (op1.getLefty() > 0) {
			return P1;
		} else if (op2.getLefty() > 0) {
			return P2;
		}

		return getHighCard(player1) > getHighCard(player2) ? P1 : P2;
	}

	private static int getHighCard(Card[] cards) {
		return cards[4].getCardValue();
	}

	private static Tuple<Integer, Integer> isOnePair(Card[] cards) {
		if (cards[0].compareTo(cards[1]) == 0) {
			return new Tuple<Integer, Integer>(cards[0].getCardValue(), cards[4].getCardValue());
		}
		if (cards[1].compareTo(cards[2]) == 0) {
			return new Tuple<Integer, Integer>(cards[1].getCardValue(), cards[4].getCardValue());
		}
		if (cards[2].compareTo(cards[3]) == 0) {
			return new Tuple<Integer, Integer>(cards[2].getCardValue(), cards[4].getCardValue());
		}
		if (cards[3].compareTo(cards[4]) == 0) {
			return new Tuple<Integer, Integer>(cards[3].getCardValue(), cards[2].getCardValue());
		}

		return new Tuple<Integer, Integer>(0, 0);
	}

	private static Tuple<Integer, Integer> isTwoPair(Card[] cards) {
		if (cards[0].compareTo(cards[1]) == 0 && cards[2].compareTo(cards[3]) == 0) {
			return new Tuple<Integer, Integer>(cards[0].getCardValue(), cards[2].getCardValue());
		}
		if (cards[0].compareTo(cards[1]) == 0 && cards[3].compareTo(cards[4]) == 0) {
			return new Tuple<Integer, Integer>(cards[0].getCardValue(), cards[3].getCardValue());
		}
		if (cards[1].compareTo(cards[2]) == 0 && cards[3].compareTo(cards[4]) == 0) {
			return new Tuple<Integer, Integer>(cards[1].getCardValue(), cards[3].getCardValue());
		}

		return new Tuple<Integer, Integer>(0, 0);
	}

	private static Tuple<Integer, Integer> isThreeOfKind(Card[] cards) {
		if (cards[0].compareTo(cards[1]) == 0 && cards[1].compareTo(cards[2]) == 0) {
			return new Tuple<Integer, Integer>(cards[0].getCardValue(), cards[4].getCardValue());
		}
		if (cards[1].compareTo(cards[2]) == 0 && cards[2].compareTo(cards[3]) == 0) {
			return new Tuple<Integer, Integer>(cards[1].getCardValue(), cards[4].getCardValue());
		}
		if (cards[2].compareTo(cards[3]) == 0 && cards[3].compareTo(cards[4]) == 0) {
			return new Tuple<Integer, Integer>(cards[2].getCardValue(), cards[1].getCardValue());
		}

		return new Tuple<Integer, Integer>(0, 0);
	}

	private static int isStraight(Card[] cards) {
		if ((cards[1].isNext(cards[0])
				&& cards[2].isNext(cards[1])
				&& cards[3].isNext(cards[2])
				&& cards[4].isNext(cards[3]))) {
			return cards[4].getCardValue();
		}
		if (cards[4].getValue() == Value.ACE && cards[0].getValue() == Value.TWO
				&& cards[1].isNext(cards[0])
				&& cards[2].isNext(cards[1])
				&& cards[3].isNext(cards[2])) {
			return cards[3].getCardValue();
		}

		return 0;
	}

	private static boolean isFlush(Card[] cards) {
		return isSameColor(cards);
	}

	private static Tuple<Integer, Integer> isFullHouse(Card[] cards) {
		if (cards[0].compareTo(cards[1]) == 0
				&& cards[1].compareTo(cards[2]) == 0
				&& cards[3].compareTo(cards[4]) == 0) {
			return new Tuple<Integer, Integer>(cards[0].getCardValue(), cards[3].getCardValue());
		}
		if (cards[0].compareTo(cards[1]) == 0
				&& cards[2].compareTo(cards[3]) == 0
				&& cards[3].compareTo(cards[4]) == 0) {
			return new Tuple<Integer, Integer>(cards[2].getCardValue(), cards[0].getCardValue());
		}
		return new Tuple<Integer, Integer>(0, 0);
	}

	private static int isFourOfKind(Card[] cards) {
		if (cards[1].compareTo(cards[2]) == 0
				&& cards[2].compareTo(cards[3]) == 0) {
			if (cards[0].compareTo(cards[1]) == 0) {
				cards[0].getCardValue();
			}
			if (cards[3].compareTo(cards[4]) == 0) {
				return cards[4].getCardValue();
			}
		}

		return 0;
	}

	private static int isStraightFlush(Card[] cards) {
		if (isSameColor(cards)) {
			return isStraight(cards);
		}
		return 0;
	}

	private static boolean isRoyalFlush(Card[] cards) {
		return isSameColor(cards)
			&& cards[0].getValue() == Value.TEN
			&& cards[1].getValue() == Value.JACK
			&& cards[2].getValue() == Value.QUEEN
			&& cards[3].getValue() == Value.KING
			&& cards[4].getValue() == Value.ACE;
	}

	private static boolean isSameColor(Card[] cards) {
		return cards[0].getColor().equals(cards[1].getColor())
		 && cards[1].getColor().equals(cards[2].getColor())
		 && cards[2].getColor().equals(cards[3].getColor())
		 && cards[3].getColor().equals(cards[4].getColor());
	}

	/**
	 * Produces the given player cards and ordered them.
	 * @param line Contains the list of the cards in the players's hands.
	 * @param player 1 or 2.
	 * @return
	 */
	private static Card[] getCards(String line, int player) {
		String[] s = line.split(" ");

		// Reads the player cards
		int start = player == 1 ? 0 : 5;
		List<Card> cards = new ArrayList<Card>();
		for (int i = start; i < start + 5; i++) {
			cards.add(new Card(s[i]));
		}

		// Orders the cards by their value
		Collections.sort(cards);

		return cards.toArray(new Card[0]);
	}

	/**
	 * Reads the the given file by lines.
	 * @param file The file.
	 * @return List of lines of the file.
	 * @throws IOException
	 */
	private static List<String> read(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		List<String> lines = new ArrayList<String>();

		String str;
		while ((str = in.readLine()) != null) {
			lines.add(str);
		}
		in.close();

		return lines;
	}

	static class Card implements Comparable<Card> {

		final Value value;
		final String color;

		Card(String card) {
			value = Value.valueOfCard(String.valueOf(card.charAt(0)));
			color = String.valueOf(card.charAt(1));
		}

		public final Value getValue() {
			return value;
		}

		public final String getColor() {
			return color;
		}

		public final int getCardValue() {
			return value.ordinal() + 2;
		}

		/**
		 * <tt>true</tt> if this card is next to (higher) the given card.
		 * @param card The compared card.
		 * @return
		 */
		public final boolean isNext(Card card) {
			return getCardValue() - card.getCardValue() == 1 ? true : false;
		}

		@Override
		public final int hashCode() {
            return color.hashCode() ^ value.hashCode();
        }

		@Override
		public final boolean equals(Object o) {
			if (!(o instanceof Card)) {
				return false;
			}
			Card c = (Card) o;
			if (c.getValue().equals(value)) {
				return true;
			}
			return false;
		}

		@Override
		public final int compareTo(Card o) {
			return (getCardValue() < o.getCardValue() ? -1 : (getCardValue() == o.getCardValue() ? 0 : 1));
		}
		
		@Override
		public final String toString() {
			return value + color;
		}
	}

	enum Value {

		TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
		SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("T"),
		JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

		private String value;
		
		Value(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return value;
		}

		public static Value valueOfCard(String v) {
			Value cv = null;

			for (Value c : Value.values()) {
				if (v.equals(c.getValue())) {
					cv = c;
					break;
				}
			}

			return cv;
		}
	}
}