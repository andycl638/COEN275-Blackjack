package blackjack;

import blackjack.GUI.*;

public class BlackjackMain {
	private BlackjackGui window;
	private Player player;
	
	public BlackjackMain() {
		player = Player.getInstance();
		// set the initial bet. should be passed in to dealer
		// deck might need to be created outside so that the dealer can have the same deck when a game is finished
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		
	//	Player player = new Player("player 1");
	//	int bet = 10;
		// set the initial bet. should be passed in to dealer
		// deck might need to be created outside so that the dealer can have the same deck when a game is finished
	//	Deck deck = new Deck(); //pass to dealer param
		//Dealer dealer = new Dealer(player, bet, deck);
		BlackjackMain tester = new BlackjackMain();

	//	tester.testDeckAndHand();
		
		// the dealer is passed in to the GUI/event handler
	}

}


