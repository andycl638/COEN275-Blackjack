package blackjack;

import blackjack.GUI.*;

public class BlackjackMain {
	private BlackjackGui window;
	private Player player;
	
	/**
	 * Main constructor that initiates the game
	 */
	public BlackjackMain() {
		player = Player.getInstance();
		Deck deck = new Deck(); //pass to dealer param
		Dealer dealer = new Dealer(player, deck);
		
		window = BlackjackGui.getInstance(dealer, player, deck);
	}
	
	public static void main(String[] args) {
		BlackjackMain tester = new BlackjackMain();


	}

}


