package blackjack;

import java.util.*;


public class Player {
	private String name;
	private double balance = 1000;
	private ArrayList<Hand> playersHand;
	private static Player player;

	private Player() {
		playersHand = new ArrayList<>();
	}
	
	public static Player getInstance() {
		if (player == null) {
			player = new Player();
		}
		return player;
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
