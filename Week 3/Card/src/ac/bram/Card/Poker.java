package ac.bram.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Poker {

	private CardRank r;

	private ArrayList<Card> cards = new ArrayList<>();
	private Map<Card, Boolean> deck = new HashMap<Card, Boolean>();

	private PokerHand playerHand, computerHand;
	private Deck d;

	private Boolean win = false;
	private Boolean play = true;

	private Scanner input = new Scanner(System.in);
	private String message = "";

	public Poker() {
		playerHand = new PokerHand();
		computerHand = new PokerHand();
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
		System.out.println();
		
		do {
			// PLAYER'S TURN
			System.out.println("draw? reveal? skip?");
			String ui = input.nextLine();
			
			if (ui.equals("draw") || ui.equals("d")) {				
				
				Card c = d.draw();
				playerHand.addCard(c);
				this.showHand();
				System.out.println();
				
				//discard a card
				System.out.println("discard a card (0 - " + (playerHand.handSize() - 1) +")?");
				ui = input.next();
				if(ui.matches("[0-9]+")) {
					int i = Integer.parseInt(ui);
					if (i >= 0 && i < playerHand.handSize()) {
						playerHand.discard(i);
						this.showHand();
						System.out.println();
					}
				} else if (ui.equals("reveal") || ui.equals("r")) {
					this.revealCards();
				} else {
					playerHand.discardSmallest();
				}
			} else if (ui.equals("reveal") || ui.equals("r")) {
				this.revealCards();
			}
			
		} while (play);

	}

	private void showHand() {
		System.out.println("In hand :");
		playerHand.print();
	}

	private void shuffle() {
		for (int a = 0; a < playerHand.minCard(); a++) {
			Card c = d.draw();
			playerHand.addCard(c);
		}
		
		for (int a = 0; a < computerHand.minCard(); a++) {
			Card c = d.draw();
			computerHand.addCard(c);
		}
		
	}

	private void revealCards() {
		System.out.println("------------");
		System.out.println("  REVEALED  ");
		System.out.println("------------");
		System.out.println("Player's hand :");
		playerHand.print();
		System.out.println();
		System.out.println("------------");
		this.conclusion();
	}
	
	private void conclusion() {
		int stateOfWin = playerHand.checkHand();
		switch (stateOfWin) {
		case 9:
			message = "STRAIGHT FLUSH!!!";
			break;
		case 8:
			message = "FOUR OF A KIND";
			break;
		case 7:
			message = "FULL HOUSE";
			break;
		case 6:
			message = "FLUSH";
			break;
		case 5:
			message = "STRAIGHT";
			break;
		case 4:
			message = "THREE OF A KIND";
			break;
		case 3:
			message = "TWO PAIR";
			break;
		case 2:
			message = "A PAIR";
			break;
		default:
			message = "NO LUCK";
		}
		System.out.println(message);
		System.out.println("------------");
		new Main();
	}

}