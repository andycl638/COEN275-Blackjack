package blackjack;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int aceValue =0;
	private int handValue = 0;
	private int bet = 0; // each hand has it's own bet
	
	/**
	 * the bet is defaulted to 10 in case player does not want to set the bet
	 */
	public Hand() {
		this.bet = 10;
	}
	
	/**
	 * 
	 * @return if there is an ace on the hand
	 */
	public int hasAceValue() {
		return this.aceValue;
	}
	
	/**
	 * counter to determine ace is hand
	 * @param count 
	 */
	private void setAceValue(int count) {
		this.aceValue+= count;
	}
	
	/**
	 * get the cards on the hand
	 * @return - ArrayList of cards
	 */
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	/**
	 * add cards to the hand and calculate the value
	 * @param card - card is dealt from the deck
	 */
	public void addCards(Card card) {
		// if the card value is 11 then there is an ace on the hand
		if (card.getValue() == 11)
			setAceValue(1);

		this.hand.add(card);
		
		addCardValues(card);
	}
	
	/**
	 * 
	 * @return hand value
	 */
	public int getHandValue() {
		return this.handValue;
	}
	
	/**
	 * Add the values of all the cards in the hand
	 * if there is an ace then it can be either 1 or 11 depending on the rules
	 * @param card - Pass in a card to get the value
	 */
	private void addCardValues(Card card) {
		// the card is an ace
		int temp = this.handValue + card.getValue();
		if (hasAceValue() >0 && temp > 21) {		
			// hand value is 11 or over, ace is treated as 1 or you bust
			this.handValue  = temp - 10;
			setAceValue(-1);;	
		}
		else {
			// add the values of the cards.
			this.handValue  += card.getValue();
		}
	}
	
	/**
	 * get the bet value
	 * @return the bet
	 */
	public int getBet() {
		return this.bet;
	}
	
	/**
	 * set the bet amount
	 * @param bet - pass in the bet amount
	 */
	public void setBet(int bet) {
		this.bet = bet;
		
	}
	
	@Override
	public String toString() {
	
		return "Hand Value: " + this.handValue + " " + this.aceValue;
	}
}
