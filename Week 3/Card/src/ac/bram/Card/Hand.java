package ac.bram.Card;

import java.util.ArrayList;

public class Hand {

	private ArrayList<Card> inHand;
	private int minCard;

	public Hand(int min) {
		this.minCard = min;
		inHand = new ArrayList<Card>(); // for card drawn card
	}

	public void addCard(Card c) {
			this.inHand.add(c);
	}

	public Card getCard(int i) {
		int index = 0;
		for (Card c : inHand) {
			if (index == i) {
				return c;
			}
			index++;
		}
		return null;
	}

	public int minCard() {
		return this.minCard;
	}
	
	public int handSize() {
		return inHand.size();
	}

	public void print() {
		for (Card c : inHand) {
			if (c != null) {
				System.out.print(c.card() + " ");
			} else {
				System.out.print("  ");
			}
		}
	}

	public void discard(int i) {
		inHand.remove(i);
	}

	public void discardSmallest() {
		Card smallest = inHand.get(0);
		compare cc;
		for (Card c : inHand) {
			if (cc(smallest, c) == 1) {
				smallest = c;
			}
		}
		inHand.remove(smallest);
	}

	private int cc(Card smallest, Card c) {
		// TODO Auto-generated method stub
		return 0;
	}
}
