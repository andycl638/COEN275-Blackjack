package blackjack;

import java.util.ArrayList;

// limit of two hands
public class Hand {
	private ArrayList<Card> hand = new ArrayList<Card>();
	private boolean aceValue = false;
	private int handValue = 0;
	private int bet = 0; // each hand has it's own bet
	
	// returns if there is an ace on the hand
	public boolean getAceValue() {
		return this.aceValue;
	}
	
	//
	private void setAceValue() {
		this.aceValue = true;
	}
	
	// get the cards on the hand
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	// add cards to the hand
	public void addCards(Card card) {
		// if the card value is 11 then there is an ace on the hand
		if (card.getValue() == 11)
			setAceValue();
		
		this.hand.add(card);
		
		addCardValues(card);
	}
	
	public int getHandValue() {
		return this.handValue;
	}
	
	// Add the values of all the cards in the hand
	// if there is an ace then it can be either 1 or 11 depending on the rules
	public void addHandValue(ArrayList<Card> cards) {
		
		int totalValue = 0;
		for (Card card: cards ) {
			
			if (getAceValue() == true)
			{
				// there is an ace! do something special
			}
			else
			{
				// add the values of the cards.
				totalValue  += card.getValue();
			}
		
		}
		this.handValue = totalValue;
	}
	
	// the each card value to the hand value. Use per hand
	private void addCardValues(Card card) {
		
		//int totalValue = 0;
		
		// the card is an ace
		if (getAceValue() == true)
		{
			// hand value is 11 or over, ace is treated as 1 or you bust
			if(this.handValue >= 11) {
				this.handValue  += 1;
			}
			else {
				this.handValue  += card.getValue();
			}
			// there is an ace! do something special
		}
		else
		{
			// add the values of the cards.
			this.handValue  += card.getValue();
		}
		
		//this.handValue = totalValue;
	}
	
	public int getBet() {
		return this.bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	@Override
	public String toString() {
	
		return "Hand Value: " + this.handValue + " " + this.aceValue;
	}
}
