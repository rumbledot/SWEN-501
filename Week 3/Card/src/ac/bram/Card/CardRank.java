package ac.bram.Card;

public enum CardRank {
	one("1"), 
	two("2"), 
	three("3"), 
	four("4"), 
	five("5"), 
	six("6"), 
	seven("7"), 
	eight("8"), 
	nine("9"), 
	ten("10"),
	jack("J"),
	queen("Q"),
	king("K");
	
	private String symbol;
	
	CardRank(String symbol) {
		this.symbol = symbol;
	}
	
	public String s() {
		return this.symbol;
	}
}
