package blackjack;

import javax.swing.JOptionPane;

import blackjack.GUI.DealerPanel;
import java.awt.Component;

public class Dealer {
    private Hand dealersHand = new Hand();
    private static Deck deck;
    private Player player;
    private boolean playerStand = false;
    private boolean isBlackjack = false;
    private int result = Integer.MIN_VALUE;

    // this is called at the beginning of the game. It sets up the game
    // shuffle deck
    // deal hand to player and dealer
    // check if there is a blackjack or bust right away
    public Dealer(Player player, int bet, Deck deck) {
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
          //  double amount = playerHand.getBet() * 1.5;
            isBlackjack = true;
           // endGame(amount, 1);
           // JOptionPane.showMessageDialog(null, "BLACKJACK!");
            
            // need to end game since player won
        }
        // no blackjack, continue with game
        else {
            System.out.println("No blackjack, continue with play");
        }

    }
    
    public boolean getIsBlackjack() {
    	return this.isBlackjack;
    }
    
    public Hand getDealerHand() {
    	return this.dealersHand;
    }

    // pass in arraylist to compare both player hands and give result
    public void dDecision(Hand playerHand) {
        int result = 0;
        
        while (true) {
        	result = compareHands(playerHand);
        	
        	if (result == 0){
        		endGame(0, result); // tie, hand values are 18 or higher
        		System.out.println("dDecision: tie");
        		break;
        	}
        	else if (result == -1) {
        		endGame(-playerHand.getBet(), result); // player lost, dealer has higher hand value
        		System.out.println("dDecision: dealer win");
        		break;
        	}
        	else if (result == 1) {
        		Card c = hit(this.dealersHand); // deal a card to dealer hand 
        		System.out.println("dDecision: dealer hits");
        		DealerPanel.dealerHandPanel.addCard(c.getImagePath());
        		System.out.println("new dealer value: " + this.dealersHand.getHandValue());
        		
        		DealerPanel.dealerHandPanel.placeAndResizeComponents();
        		
        		System.out.println("display card: " + c.getImagePath());
        		DealerPanel.dealerHandPanel.repaint();
        		
            	if(bust(this.dealersHand)) {
            		endGame(playerHand.getBet(), result); // player wins because dealer busted
            		System.out.println("dDecision: dealer bust");
            		break;
            	}
            	
        	}
        	else {
    			System.out.println("game broke");
    			break;
    		}
	
        }
        
        // need to check if it's the start of the game
        // need to deal twice and check for blackjack
    }

    // Param: Hand, can be player or dealer
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
    
    // first time
    public void doubleDown(Hand playerHand) {
        //double the bet
        playerHand.setBet(playerHand.getBet() * 2);
    }

    //first time
    public void surrender(Hand playerHand) {
        double amount = -(playerHand.getBet() / 2);
    	 // the player gets half of his bet amount back and it is added to the balance
        endGame(amount, -1);
    }

    //first time
    public void split(Hand playerHand) {
        //add a new hand to player
        Hand newHand = new Hand(); //creating a new hand object
        Card splitCard = playerHand.getHand().remove(0);
        
        newHand.addCards(splitCard); //adding the previous card to the new hand
        
        try {
        	// dealing a new card to both hands
			newHand.addCards(this.deck.deal());
			playerHand.addCards(this.deck.deal());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        
        this.player.addHand(newHand); //adding the new hand to the player 
    }

    public boolean is21(Hand hand) {
        if (hand.getHandValue() == 21) {
           return true;
        }
        return false;
    }


    public boolean bust(Hand hand) {
        // hand value greater than 21 is a bust
        if (hand.getHandValue() > 21) {
            return true;
        }
        return false;
    }

    //Can return:
    //	1 for continue
    //	0 for tie
    //	-1 for stop
    // this is only called by the dealer
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

    // the amount won or lost should be calculated in each function else
    // pass in + or - amount so the balance can get set.
	private void updateBalance(double amount) {
		this.player.setBalance(this.player.getBalance() + amount);
	}
	
	public void endGame(double amount, int result) {
		updateBalance(amount);
		String message = "";
		if (result == 0) {
			System.out.println("It's a tie"); 
			message = "It's a tie";
		}
		else if (result == 1) {
			System.out.println("Player Won: $" + amount); 
			message = "Player Won: $" + amount;
		} else if (result == -1) {
			System.out.println("Player Lost: $" + amount); 
			message = "Player Lost: $" + amount;
		}
		else {
			System.out.println("game broke");
			message = "Game broke. Please start a new game.";
		}
		
		// show dialog box with result and give user option to play again
		JOptionPane.showMessageDialog(null, message);
	}
}
