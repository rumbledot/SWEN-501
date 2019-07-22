package ac.bram.Card;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

public class Main {

	private ArrayList<Card> deck = new ArrayList<>();
	Scanner input = new Scanner(System.in);

	public Main() {
		this.createDeck();
		this.mainMenu();
	}

	private void mainMenu() {
		int i;
		do {
			System.out.println("-- CARD GAME --");
			System.out.println("1 > BlackJack");
			System.out.println("2 > Poker");
			System.out.println("3 > Klondike");
			i = input.nextInt();
			switch(i) {
			case 1:
				new BlackJack();
				break;
			case 2:
				new Poker();
				break;
			case 3:
				new Klondike();
				break;
			}
		} while (i != 0);
	}

	private void printDeck() {
		for (Card c : deck) {
			System.out.println(c.card());
		}
	}

	public void createDeck() {
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♠", rank);
			deck.add(c);
		});
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♣", rank);
			deck.add(c);
		});
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♥", rank);
			deck.add(c);
		});
		EnumSet.allOf(CardRank.class)
		.forEach(rank -> {
			Card c = new Card("♦", rank);
			deck.add(c);
		});
	}

	public static void main(String[] args) {
		new Main();
	}

}
