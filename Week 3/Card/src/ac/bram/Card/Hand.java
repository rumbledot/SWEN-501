package ac.bram.Card;

import java.util.ArrayList;

public class Hand {

	protected ArrayList<Card> inHand;
	private int minCard;

	public Hand(int min) {
		this.minCard = min;
		inHand = new ArrayList<Card>(); // for card drawn card
	}

	public void addCard(Card c) {
			this.inHand.add(c);
	}
	
	public void discard(int i) {
		inHand.remove(i);
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

	public void discardSmallest() {
		Card smallest = inHand.get(0);
		Compare cc = new Compare();
		for (Card c : inHand) {
			if (cc.compare(smallest, c) == 1) {
				smallest = c;
			}
		}
		System.out.println(smallest.card());
		inHand.remove(smallest);
	}

}