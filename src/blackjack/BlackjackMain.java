package blackjack;

import blackjack.GUI.*;

public class BlackjackMain {
	private BlackjackGui window;
	private Player player;
	
	/**
	 * Main constructor that initiates the game
	 */
	public BlackjackMain() {
		player = Player.getInstance();
		Deck deck = new Deck(); //pass to dealer param
		Dealer dealer = new Dealer(player, deck);
		
		window = BlackjackGui.getInstance(dealer, player, deck);
	}
	
	public void testDeckAndHand() {
		//Currently this is a tester class. We can use it to test backend functionality
		Deck deck = new Deck();
		System.out.println(deck.toString());
	
		Hand hand = new Hand();
		
		try {
			System.out.println("dealing card");
			Card c = deck.deal();
			System.out.println(c.toString());
			hand.addCards(c);
			System.out.println(hand.toString());
			
			System.out.println("dealing card 2");
			Card c2 = deck.deal();
			System.out.println(c2.toString());
			hand.addCards(c2);
			System.out.println(hand.toString());
			System.out.println(deck.toString());
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void testAce() {
		//Currently this is a tester class. We can use it to test backend functionality
		Deck deck = new Deck();
		System.out.println(deck.toString());
	
		Hand hand = new Hand();
		
		try {
			System.out.println("dealing card");
			Card c = deck.deal();
			System.out.println("Card: " + c.toString());
			hand.addCards(c);
			System.out.println("Hand: " +hand.toString());
			System.out.println("Hand Value: " + hand.getHandValue());
			System.out.println("Ace counter: " + hand.hasAceValue());
			
			System.out.println("dealing card 2");
			Card c2 = deck.deal();
			System.out.println("Card: " + c2.toString());
			hand.addCards(c2);
			System.out.println("Hand: " +hand.toString());
			System.out.println("Hand Value: " + hand.getHandValue());
			System.out.println("Ace counter: " + hand.hasAceValue());
			
			System.out.println(deck.toString());
			
			System.out.println("dealing card 3");
			Card c3 = deck.deal();
			System.out.println("Card: " + c3.toString());
			hand.addCards(c3);
			System.out.println("Hand: " +hand.toString());
			System.out.println("Hand Value: " + hand.getHandValue());
			System.out.println("Ace counter: " + hand.hasAceValue());
			
			System.out.println("dealing card 4");
			Card c4 = deck.deal();
			System.out.println("Card: " + c4.toString());
			hand.addCards(c4);
			System.out.println("Hand: " +hand.toString());
			System.out.println("Hand Value: " + hand.getHandValue());
			System.out.println("Ace counter: " + hand.hasAceValue());
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		BlackjackMain tester = new BlackjackMain();


	}

}


