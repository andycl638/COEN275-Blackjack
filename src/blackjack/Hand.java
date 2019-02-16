package blackjack;

import java.util.ArrayList;

// limit of two hands
public class Hand {
	private ArrayList<Object> hand = new ArrayList<Object>();
	private boolean aceValue = false;
	private int handValue = 0;
	private int bet = 0; // each hand has it's own bet
	
	// returns if there is an ace on the hand
	public boolean getAceValue() {
		return this.aceValue;
	}
	
	// determines if there is an ace on the hand
	public void setAceValue(boolean aceValue) {
		this.aceValue = aceValue;
	}
	
	// get the cards on the hand
	public ArrayList<Object> getHand() {
		return this.hand;
	}
	
	// add cards to the hand
	public void addCards(Object card) {
		this.hand.add(card);
	}
	
	public int getHandValue() {
		return this.handValue;
	}
	
	//Add the values of all the cards in the hand
	// if there is an ace then it can be either 1 or 11 depending on the rules
	public void addHandValue(ArrayList<Object> hand) {
		int totalValue = 0;
		for (Object card: hand ) {
			
			if (getAceValue() == true)
			{
				// there is an ace! do something special
			}
			else
			{
				//just add the cards
//				totalValue += card.value;
			}
		
		}
		this.handValue = totalValue;
	}
	
	public int getBet() {
		return this.bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
	}
}
