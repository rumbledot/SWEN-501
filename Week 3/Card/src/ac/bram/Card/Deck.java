package ac.bram.Card;

import java.util.*;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<>();
	private Map<Card, Boolean> deck = new HashMap<Card, Boolean>();
	
	public Deck() {
		
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public Map<Card,Boolean> getDeck() {
		return deck;
	}
	
	public void forBlackJack() {
		defaultDeck();
		CardRank cr = null;
		for (Card c : cards) {
			if (c.rank().equals(cr.jack)) {
				c.setValue(10);
			} else if (c.rank().equals(cr.queen)) {
				c.setValue(10);
			} else if (c.rank().equals(cr.king)) {
				c.setValue(10);
			} else {
				c.setValue(Integer.parseInt(c.rank().s()));
			}
			deck.put(c, false);
		}
	}

	private void defaultDeck() {
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♠", rank);
			cards.add(c);
		});
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♣", rank);
			cards.add(c);
		});
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♥", rank);
			cards.add(c);
		});
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♦", rank);
			cards.add(c);
		});
	}
	
	
}
