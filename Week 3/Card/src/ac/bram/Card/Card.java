package ac.bram.Card;

import java.util.Comparator;

public class Card implements Comparable<Card>{

	private String suit;
	private CardRank e;
	private int value;
	
	public Card (String s, CardRank e) {
		this.suit = s;
		this.e = e;
	}
	
	public String card() {
		return this.e.s() + this.suit;
	}
	
	public String suit() {
		return this.suit;
	}
	
	public CardRank rank() {
		return this.e;
	}
	
	public void setValue(int v) {
		this.value = v;
	}
	
	public int value() {
		return this.value;
	}

	@Override
	public int compareTo(Card o) {
		return (o.value - this.value);
	}
	
	public static Comparator<Card> BySuit = new Comparator<Card>() {
		
		@Override
        public int compare(Card c1, Card c2) {
        	return c1.suit().compareTo(c2.suit());
        }
	};
	
	public static Comparator<Card> ByValueDescending = new Comparator<Card>() {
		
		@Override
        public int compare(Card c1, Card c2) {
        	return (c1.value - c2.value);
        }
	};
}
