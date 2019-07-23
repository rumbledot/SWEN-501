package ac.bram.Card;

import java.util.*;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<>();
	private Map<Card, Boolean> deck = new HashMap<Card, Boolean>();
	
	private int decksize;
	private String[] suits = { "♠", "♣", "♥", "♦" };
	
	public Deck() {
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public Map<Card,Boolean> getDeck() {
		return deck;
	}
	
	public void forPoker() {
		defaultDeck();
		CardRank cr = null;
		for (Card c : cards) {
			if (c.rank().equals(cr.jack)) {
				c.setValue(11);
			} else if (c.rank().equals(cr.queen)) {
				c.setValue(12);
			} else if (c.rank().equals(cr.king)) {
				c.setValue(13);
			} else {
				c.setValue(Integer.parseInt(c.rank().s()));
			}
			deck.put(c, false);
		}
	}
	
	public void forBlackJack() {
		defaultDeck();
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
		this.shuffle();
	}

	private void defaultDeck() {
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♠", rank);
			cards.add(c);
			c = new Card("♣", rank);
			cards.add(c);
			c = new Card("♥", rank);
			cards.add(c);
			c = new Card("♦", rank);
			cards.add(c);
		});
		this.shuffle();
	}
	
	private void shuffle() {
		Card temp = null;
		for (int i = 0; i < cards.size(); i++) {
			int key = (int)(Math.random()*(cards.size() - i)) + i;
			temp = cards.get(i);
			cards.set(i, cards.get(key));
			cards.set(key, temp);
		}
		this.decksize = cards.size();
	}
	
	public Card draw() {
		this.decksize--;
		
		deck.put(cards.get(this.decksize), true);
		return cards.get(this.decksize);
	}
	
	public int deckLeft() {
		return decksize;
	}
	
}
