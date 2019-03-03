package blackjack;

import blackjack.GUI.*;

public class BlackjackMain {
	private BlackjackGui window;
	
	public BlackjackMain() {
		window = new BlackjackGui();
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
	
	public static void main(String[] args) {
		
		
		Player player = new Player("player 1");
		int bet = 10;
		// set the initial bet. should be passed in to dealer
		// deck might need to be created outside so that the dealer can have the same deck when a game is finished
		Deck deck = new Deck(); //pass to dealer param
		Dealer dealer = new Dealer(player, bet, deck);
		
		BlackjackMain tester = new BlackjackMain();
	//	tester.testDeckAndHand();
		
		// the dealer is passed in to the GUI/event handler
	}

}


