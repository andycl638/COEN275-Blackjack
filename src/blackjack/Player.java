package blackjack;

import java.util.ArrayList;

public class Player {
	String name;
	int balance;
	ArrayList<Hand> playersHand;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
