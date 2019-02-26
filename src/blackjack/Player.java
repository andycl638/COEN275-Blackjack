package blackjack;

import java.util.*;


public class Player {
	private String name;
	private int balance;
	private ArrayList<Hand> playersHand;
<<<<<<< HEAD
	private int playerBet=0;// added player bet for calculating calculateBet() in Dealer class

	public int getPlayerBet() {
		return playerBet;
	}

	public void setPlayerBet(int playerBet) { // when player sets bet , playerBet is set and balcnce
		this.playerBet = playerBet;
		setBalance(getBalance()-this.playerBet);
	}

	// This is a overloaded method called when doubleDown() is used in Dealer Class
	public void setPlayerBet(int playerBet,int oldBet) {
		this.playerBet = playerBet;
		setBalance(getBalance()-oldBet);
	}

=======
	
>>>>>>> refs/remotes/origin/master
	public Player(String name) {
		this.balance = 0;
		this.name = name;
		playersHand = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public void addHand(Hand h){
		this.playersHand.add(h);
	}
	
	public ArrayList<Hand> getHand(){
		return this.playersHand;
	}
	
}
