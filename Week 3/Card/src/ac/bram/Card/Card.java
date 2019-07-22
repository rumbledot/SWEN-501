package ac.bram.Card;

public class Card {

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
	
	public CardRank rank() {
		return this.e;
	}
	
	public void setValue(int v) {
		this.value = v;
	}
	
	public int value() {
		return this.value;
	}
}
