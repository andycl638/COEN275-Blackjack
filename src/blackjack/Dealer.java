package blackjack;

public class Dealer {
	Hand dealersHand;
	Deck deck;
	Player player;
	private int counter = 0;
	
	// Andy
	// Constructor..set up game
	// need to check if it's the start of the game
	// need to deal twice and check for blackjack
	
	//not required, happens in the event handler
	public void pDecision() {
		//switch case for calling methods corresponding to player's decisions
	}
	
	// Mohak, Niranjan
	public void dDecision() {
		//switch case for calling methods corresponding to dealer's decisions
		//switch case needs to be called in a loop until game is won or 
		//dealer busts.
		
		// need to check if it's the start of the game
		// need to deal twice and check for blackjack
	}
	
	// Mohak, Niranjan
	public void stand() {
		//dealer hits and compareHands
	}
	
	//Andy 
	public void hit() {
		//draw cards and check for busts
	}
	
	// Mohak, Niranjan
	// first time
	public void doubleDown() {
		//double the bet
	}
	
	// Mohak, Niranjan
	//first time
	public void surrender() {
		//calculate bets
	}
	
	// Mohak, Niranjan
	//first time
	public void split() {
		//add a new hand to player
		//double bet
	}
	
	// Andy
	// first time
	public void blackjack() {
		//check for an ace and a 10-value card just after the cards are dealt
		//called only the first time and never again
		//dealing cards in the current game
	}
	
	// Mohak, Niranjan
	public void endGame() {
		//call to compareHands()
		//calculateBets()
	}
	
	// Mohak, Niranjan
	//Can return:
	//	1 for win
	//	0 for tie
	//	-1 for lose
	public int compareHands() {
		//comapre dealers and player's hands total value.
		//return a win, lose or tie.
		return 0;
	}
	
	//Andy
	//NOTE: Do we need a method to calculateBets?
	public int calculateBets() {
		return 0;
	}
}
