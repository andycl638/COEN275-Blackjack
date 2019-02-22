package blackjack;

public class BlackjackMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Currently this is a tester class. We can use it to test backend functionality
		Deck deck = new Deck();
		System.out.println(deck.toString());
	
		Hand hand = new Hand();
		
		
		try {
			System.out.println("dealing card");
			Card c = deck.deal();
			System.out.println(c.toString());
			hand.addCards(c);
			System.out.println(hand.toString());
			
			System.out.println("dealing card 2");
			Card c2 = deck.deal();
			System.out.println(c2.toString());
			hand.addCards(c2);
			System.out.println(hand.toString());
			System.out.println(deck.toString());
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}
