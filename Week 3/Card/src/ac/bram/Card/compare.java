package ac.bram.Card;

import java.util.Comparator;

public class compare implements Comparator<Card>{

	@Override
	public int compare(Card o1, Card o2) {
		return (o1.value() - o2.value());
	}

}
