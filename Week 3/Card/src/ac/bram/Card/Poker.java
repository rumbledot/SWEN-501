package ac.bram.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Poker {

	private CardRank r;

	private ArrayList<Card> cards = new ArrayList<>();
	private Map<Card, Boolean> deck = new HashMap<Card, Boolean>();

	private Hand playerHand, computerHand;
	private Hand str_flush, four_of_kind, full_house, flush, straight, three_of_kind, two_pair, pair, high_card;
	private Deck d;

	private Boolean win = false;
	private Boolean play = true;

	private Scanner input = new Scanner(System.in);
	private String message = "";

	public Poker() {
		playerHand = new Hand(5);
		computerHand = new Hand(5);
		d = new Deck();
		d.forPoker();
		cards = d.getCards();
		deck = d.getDeck();
		this.deal();
	}

	private void deal() {
		System.out.println("Welcome to POKER");
		this.shuffle();
		this.showHand();

	}

	private void showHand() {
		System.out.println("In hand :");
		playerHand.print();
	}

	private void shuffle() {
		for (int a = 0; a < playerHand.minCard(); a++) {
			Card c = d.draw();
			playerHand.addCard(c);
			System.out.println(c.card());
		}
		
		for (int a = 0; a < computerHand.minCard(); a++) {
			Card c = d.draw();
			computerHand.addCard(c);
			System.out.println(c.card());
		}
		
	}



}
