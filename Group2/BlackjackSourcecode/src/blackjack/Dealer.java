package blackjack;

import blackjack.GUI.BlackjackGui;
import blackjack.GUI.DealerPanel;

public class Dealer {
    private Hand dealersHand = new Hand();
    private static Deck deck;
    private Player player;
    private boolean isBlackjack = false;

    // this is called at the beginning of the game. 
    // check if there is a blackjack or bust right away
    /**
     * It sets up the game. Deal hand to player and dealer
     * @param player - The player object
     * @param deck - The deck object
     */
    public Dealer(Player player, Deck deck) {
        // constructs the deck and "shuffle"
        this.deck = deck;

        // deal hand to player first
        this.player = player;

        //create player hand and set the bet
        Hand playerHand = new Hand();
        
        playerHand.setBet(10);

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
        if (is21(playerHand)) {
            System.out.println("BLACKJACK!");
        
            isBlackjack = true;
        }
        // no blackjack, continue with game
        else {
            System.out.println("No blackjack, continue with play");
        }

    }
    
    /**
     * Get the value of blackjack flag
     * @return blackjack exist flag
     */
    public boolean getIsBlackjack() {
    	return this.isBlackjack;
    }
    
    /**
     * Get hand object for dealer
     * @return dealers hand
     */
    public Hand getDealerHand() {
    	return this.dealersHand;
    }

    // pass in arraylist to 
    /**
     * compare player and dealer hand and give result
     * @param playerHand Players hand
     * @return 0 = tie; 1 = player win; -1 = player lost;
     */
    public int dDecision(Hand playerHand) {
        int result = 0;
        
        while (true) {
        	result = compareHands(playerHand);
        	
        	if (result == 0){
        		// tie, hand values are 18 or higher
        		System.out.println("dDecision: tie");
        		return result;
        	}
        	else if (result == -1) {
        	    // player lost, dealer has higher hand value
        		System.out.println("dDecision: dealer win");
        		return result;
        	}
        	else if (result == 1) {
        		Card c = hit(this.dealersHand); // deal a card to dealer hand 
        		System.out.println("dDecision: dealer hits");
        		DealerPanel.getDealerHand().addCard(c.getImagePath());
        		System.out.println(DealerPanel.getDealerHand().toString());
        		System.out.println("new dealer value: " + this.dealersHand.getHandValue());
        		
        		System.out.println("display card: " + c.getImagePath());
   
            	if(bust(this.dealersHand)) {
            		System.out.println("dDecision: dealer bust");
            		return result;
            	}
        	}
        	else {			
    			return -2;
    		}
        }
    }

    /**
     * Deal a new card to the hand
     * @param hand can be player or dealer
     * @return a card object
     */
    public static Card hit(Hand hand) {
    	Card c = null;
        try {
        	c = deck.deal();
            hand.addCards(c);
          
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        return c;
    }
    
    /**
     * double the bet from the player
     * @param playerHand - update the bet of the hand
     */
    public void doubleDown(Hand playerHand) {
        //double the bet
        playerHand.setBet(playerHand.getBet() * 2);
    }

    /**
     * Player loses the game
     * @param playerHand - update the bet of the hand
     */
    public void surrender(Hand playerHand) {
        double amount = -(playerHand.getBet() / 2);
    	 // the player gets half of his bet amount back and it is added to the balance
        endGame(amount, -1);
    }

    /**
     * check if the hand value is 21
     * @param hand - get the hand value
     * @return true if it is 21
     */
    public boolean is21(Hand hand) {
    	// hand value is 21
        if (hand.getHandValue() == 21) {
           return true;
        }
        return false;
    }

    /**
     * check if hand value is over 21
     * @param hand - get the hand value
     * @return true if it is over 21
     */
    public boolean bust(Hand hand) {
        // hand value greater than 21 is a bust
        if (hand.getHandValue() > 21) {
            return true;
        }
        return false;
    }

    /**
     * this is only called by the dealer
     * @param playerHand hand object of the player
     * @return 1 = continue; 0 = tie; -1 = stop
     */
    private int compareHands(Hand playerHand) {
        //compare dealers and player's hands total value.
        //return whether dealer needs to continue playing or not.
        int result = 0;
       
        // dealer will stand if both hand values are the same and dealer's hand value is greater than or equal to 18
        if (this.dealersHand.getHandValue() == playerHand.getHandValue() && this.dealersHand.getHandValue() >=18) {
            result = 0; // tie
           
        } else if (this.dealersHand.getHandValue() <= playerHand.getHandValue()) {
            result = 1; // dealer continues
        } else if (this.dealersHand.getHandValue() > playerHand.getHandValue()) {
            result = -1; // dealer stops
        }
        System.out.println("dealer hand: " + this.dealersHand.getHandValue());
        System.out.println("player hand: " + playerHand.getHandValue());
        
        return result;
    }

    /**
     * the amount won or lost
     * @param amount - pass in + or - amount so the balance can get set.
     */
	private void updateBalance(double amount) {
		// set player's balance with new amount
		this.player.setBalance(this.player.getBalance() + amount);
	}
	
	/**
	 * Determine if the game should end or not.
	 * @param amount - amount won or lost
	 * @param result - 0 = tie; 1 = player win; -1 = player lost;
	 */
	public void endGame(double amount, int result) {
		updateBalance(amount);
		String message = "";
		if (result == 0) {
			System.out.println("It's a tie"); 
			message = "It's a tie";
		}
		else if (result == 1) {
			System.out.println("Player Won: $" + amount);
			message = "Congartulations!! You Won: $" + amount;
		} else if (result == -1) {
			System.out.println("Player Lost: $" + amount); 
			message = "You lost: $" + amount;
		}
		else {
			System.out.println("game broke");
			message = "Game broke. Please start a new game.";
		}
		
		// show dialog box with result and give user option to play again
		BlackjackGui.getInstance().showEndGameScreen(message, "Play Again", () -> BlackjackGui.getInstance().restartGame());
	}

}
