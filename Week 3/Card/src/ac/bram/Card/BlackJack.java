package ac.bram.Card;

import java.util.*;

public class BlackJack {

	private static int winning = 21;
	private CardRank r;

	private ArrayList<Card> cards = new ArrayList<>();
	private Map<Card, Boolean> deck = new HashMap<Card, Boolean>();

	private Hand playerHand, computerHand;
	private Deck d;

	private Card drawnCard, oldCard;

	private Boolean win = false;
	private Boolean play = true;
	private Boolean haveAce = false;

	private Scanner input = new Scanner(System.in);
	private int playerValue = 0, computerValue = 0;
	private String message = "";

	public BlackJack() {
		playerHand = new Hand(2);
		computerHand = new Hand(2);
		d = new Deck();
		d.forBlackJack();
		cards = d.getCards();
		deck = d.getDeck();
		this.deal();
	}

	private void deal() {

		System.out.println("Welcome to BLACKJACK");
		this.shuffle();
		this.showHand();
		System.out.println();
		do {
			// PLAYER'S TURN
			System.out.println("draw? reveal? skip?");
			String ui = input.next();
			
			if (ui.equals("draw")) {
				// player drawing a card
				playerHand.addCard(this.drawRandomCard());
				this.showHand();
				System.out.println();
				
				//discard a card
				System.out.println("(r)eveal? (s)kip? discard a card (0 - " + (playerHand.handSize() - 1) +")?");
				ui = input.next();
				if(ui.matches("[0-9]+")) {
					int i = Integer.parseInt(ui);
					if (i >= 0 && i < playerHand.handSize()) {
						playerHand.discard(i);
						
					}
				} else if (ui.equals("reveal") || ui.equals("r")) {
					this.revealCards();
				} else {
					
				}
				this.showHand();
				this.countPlayerValue();
				//System.out.println(playerValue);
				System.out.println();
				
			} else if (ui.equals("reveal") || ui.equals("r")) {
				this.revealCards();
			}
			
			// COMPUTER'S TURN
			for (int i = 0; i < computerHand.handSize(); i++) {
				if (computerHand.getCard(i).rank().equals(r.one)) {
					if (computerValue < 10) {
						computerValue -= 1;
						computerValue += 11;
					} else {
						computerValue -= 1;
						computerValue += 1;
					}
				}
			}
			
			if (computerValue == 21 ) {
				this.revealCards();
			} else if (computerValue < 17) {
				computerHand.addCard(this.drawRandomCard());
			} else if (computerValue > 21) {
				computerHand.discardSmallest();
			} else {
				if (Math.random() > 0.5) {
					this.revealCards();
				}
			}
			
		} while (play);
	}
	
	private void countPlayerValue() {
		playerValue = 0; haveAce = false;
		for (int i = 0; i < playerHand.handSize(); i++) {
			playerValue += playerHand.getCard(i).value();
			if (playerHand.getCard(i).rank().equals(r.one)) haveAce = true;
		}
		
		if (haveAce && playerValue <= 11) {
			playerValue += 10;
		}
		
	}

	private void countComputerValue() {
		computerValue = 0; haveAce = false;
		for (int i = 0; i < computerHand.handSize(); i++) {
			computerValue += computerHand.getCard(i).value();
			if (playerHand.getCard(i).rank().equals(r.one)) haveAce = true;
		}
		
		if (haveAce && computerValue <= 11) {
			playerValue += 10;
		}
		
	}
	
	private void revealCards() {
		System.out.println("------------");
		System.out.println("  REVEALED  ");
		System.out.println("------------");
		System.out.println("Dealer's hand :");
		computerHand.print();
		System.out.println(computerValue);
		System.out.println("------------");
		System.out.println("Player's hand :");
		playerHand.print();
		this.countPlayerValue();
		System.out.println(playerValue);
		System.out.println("------------");
		play = false;
		this.conclusion();
	}
	
	private void conclusion() {
		if (playerValue <= winning && playerValue > computerValue) {
			win = true;
			message = "You WIN!!";
		} else if (playerValue == computerValue) {
			message = "Its a tie";
		} else {
			win = false;
			message = "You LOSE!!";
		}
		System.out.println(message);
		System.out.println("------------");
		new Main();
	}

	private Card drawRandomCard() {
		Card draw = null;
		
		do {
			int i = (int)(Math.random() * cards.size());
			draw = cards.get(i);
		} while ((deck.get(draw)));
		deck.put(draw, true);
		cards.remove(draw);
		return draw;
	}

	private void showHand() {
		System.out.println("In hand :");
		playerHand.print();
	}

	private void shuffle() {
		
		Card draw = null;
		// shuffle for player
		
		for (int a = 0; a < playerHand.minCard(); a++) {
			/*
			do {
				int i = (int)(Math.random() * cards.size());
				draw = cards.get(i);
			} while ((deck.get(draw)));
			//System.out.println("card" + a + " " +draw.card() + " " + draw.value());
			deck.put(draw, true);
			playerHand.addCard(draw);
			cards.remove(draw);
			*/
			
			Card c = d.draw();
			playerHand.addCard(c);
		}
				
		//shuffle for computer
		for (int a = 0; a < computerHand.minCard(); a++) {
			/*
			do {
				int i = (int)(Math.random() * cards.size());
				draw = cards.get(i);
			} while ((deck.get(draw)));
			//System.out.println("card" + a + " " +draw.card() + " " + draw.value());
			deck.put(draw, true);
			computerHand.addCard(draw);
			this.computerValue += draw.value();
			cards.remove(draw);
			*/
			
			Card c = d.draw();
			computerHand.addCard(c);
		}
	}

}
