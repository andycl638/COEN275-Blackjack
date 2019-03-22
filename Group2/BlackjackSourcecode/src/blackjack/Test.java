package blackjack;

public class Test {
	private Hand dealersHand = new Hand();
    private Deck deck;
    private Player player;
    private boolean playerStand = false;
    boolean tempRes;
	
	public void testDealer(Player player, int bet, Deck deck) {
        // constructs the deck and "shuffle"
		Dealer dealer=new Dealer(player,deck);
		

        this.deck= deck;

        // deal hand to player first
        this.player = player;

        //create player hand and set the bet
        Hand playerHand = new Hand();
        playerHand.setBet(bet);

        //deal two hards to player
        try {
            playerHand.addCards(this.deck.deal());
            System.out.println("player: first card");
            playerHand.addCards(this.deck.deal());
            System.out.println("player: second card");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        //add the hand to the player
        this.player.addHand(playerHand);

        //deal two hards to dealer
        try {
            dealersHand.addCards(this.deck.deal());
            System.out.println("dealer: first card");
            dealersHand.addCards(this.deck.deal());
            System.out.println("dealer: second card");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        // check if player got blackjack
        if (dealer.is21(playerHand)) {
            System.out.println("BLACKJACK!");
            double amount = playerHand.getBet() * 1.5;
            dealer.endGame(amount, 1);
            // need to end game since player won
        }
        // no blackjack, continue with game
        
        else {
            System.out.println("No blackjack, continue with play");
            Dealer.hit(playerHand);
            tempRes=dealer.bust(playerHand);
            if( tempRes==true) {
            	dealer.endGame(bet, -1);
            }
                                          
        }

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
}
