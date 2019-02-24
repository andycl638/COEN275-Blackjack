package blackjack;

public class Dealer {
	private Hand dealersHand = new Hand();
	private Deck deck;
	private Player player;
	private int counter = 0;
	
	// Andy
	// this is called at the beginning of the game. It sets up the game
	// shuffle deck
	// deal hand to player and dealer
	// check if there is a blackjack or bust right away
	public Dealer (Player player, int bet, Deck deck) {
		// constructs the deck and "shuffle"
		this.deck = deck;
		
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
		if (blackjack(playerHand)) {
			System.out.println("BLACKJACK!");
		}
		// check if player bust
		else if(bust(playerHand)){
			System.out.println("BUST");
		}
		// no blackjack nor bust, continue with game
		else {
			System.out.println("No blackjack or bust, continue with play");
		}
		
	}
	
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
	// Param: Hand, can be player or dealer
	public void hit(Hand hand) {
		try {
			hand.addCards(this.deck.deal());
			System.out.println("player: second card");
			
			
		} catch (Exception e) {
		
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		// check if hand got 21
		if (blackjack(hand)) {
			System.out.println("won!");
			calculateBet(hand.getBet());
		}
		// check if hand bust
		else if(bust(hand)){
			System.out.println("BUST");
			calculateBet(-hand.getBet());
		}
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
	//remove comment
	// Andy
	private boolean blackjack(Hand playerHand) {
		//check for an ace and a 10-value card just after the cards are dealt
		if (playerHand.hasAceValue() && playerHand.getHandValue() == 21) {
			// bet won
			calculateBet(playerHand.getBet());
			return true;
		}
		return false;
	}
	
	// Andy - complete
	private boolean bust(Hand playerHand) {
		// hand value greater than 21 is a bust
		if (playerHand.getHandValue() > 21) {
			// bet lost
			calculateBet(-playerHand.getBet());
			return true;
		}
		return false;
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
	
	//Andy - complete
	// the amount won or lost should be calculated in each function else
	// pass in + or - amount so the balance can get set.
	private void calculateBet(int bet) {
		// surrender - loose half the bet
		// double - double the bet
		this.player.setBalance(this.player.getBalance() + bet);
		
	}
}
