package blackjack;

import java.util.*;


public class Player {
	private String name;
	private double balance;
	private ArrayList<Hand> playersHand;

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
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double d){
		this.balance = d;
	}
	
	public void addHand(Hand h){
		this.playersHand.add(h);
	}
	
	public ArrayList<Hand> getHand(){
		return this.playersHand;
	}
	
}
