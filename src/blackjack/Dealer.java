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
		player.setPlayerBet(2*player.getPlayerBet(),player.getPlayerBet());
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
//		player.addHand();
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
		int result=compareHands();//call to compareHands()
		calculateBets(result);//calculateBets()
	}
	
	// Mohak, Niranjan
	//Can return:
	//	1 for win
	//	0 for tie
	//	-1 for lose
	public int compareHands() {
		//comapre dealers and player's hands total value.
		//return a win, lose or tie.
		int playerWin=0;
		int playerLose=0;
		int tie=0;
		for(int i=0;i<player.getHand().size();i++){
			if(this.dealersHand.getHandValue()==player.getHand().get(i).getHandValue()){
				tie=1;
			}else if(this.dealersHand.getHandValue()<player.getHand().get(i).getHandValue()){
				playerWin=1;

			}else if (this.dealersHand.getHandValue()>player.getHand().get(i).getHandValue()){
				playerLose=-1;
			}
		}
		if (playerWin==1){
			return playerWin;// if Player wins return 1
		}else if (tie==1){// If there is a tie return 0
			return 0;
		}
		return playerLose;// If player lose return -1
	}
	
	//Andy
	//NOTE: Do we need a method to calculateBets?
	public int calculateBets(int result) {
		if (result==1){ // If player wins he gets twice his bet back
			player.setBalance(player.getBalance()+(2*player.getPlayerBet()));
			return player.getBalance();
		}else if (result==-1){ // If player lose , while setting the bet , bet has already been subtracted from balance
			return player.getBalance();
		}
		// if there is tie , player gets his bet back
		player.setBalance(player.getBalance()+player.getPlayerBet());
		return player.getBalance();

	}
}
