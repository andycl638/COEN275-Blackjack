package blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    private ArrayList<Card> deckOfCards = new ArrayList<Card>();
    int counter = 0;
    
    public Deck() {
        HashMap<Integer, Character> intSuitMapping=new HashMap<Integer, Character>();
        intSuitMapping.put(0,'s'); //spade card
        intSuitMapping.put(1,'h'); //heart card
        intSuitMapping.put(2,'c'); //club card
        intSuitMapping.put(3,'d'); //diamond card
        for (int i=0 ; i<4;i++){
                for (int j=1; j<14;j++){
                    this.deckOfCards.add(new Card(intSuitMapping.get(i),j));
                }
            }
        System.out.println("Created deck of "+deckOfCards.size()+" cards");
    }
    
    public Card deal() throws Exception {
    
        if(this.deckOfCards.size()<1){
            System.out.println("Deck is empty !!");
            throw new Exception();
        }
        
        //This is used to test blackjack case
        /*int randomNum = 0;
    //    int randomNum = ThreadLocalRandom.current().nextInt(0, this.deckOfCards.size() - 1);
        if (counter == 0) {
        	 randomNum = 0;
        	 counter++;
        }
        else if (counter == 1) {
       	 randomNum = 11;
       	 counter++;
       }
        else if (counter == 2) {
       	 randomNum = 24;
       	 counter++;
       }
        else if (counter == 3) {
        	randomNum = 9;
        	counter++;
        }
*/
        int randomNum = ThreadLocalRandom.current().nextInt(0, this.deckOfCards.size() - 1);

        System.out.println("random Card num: " + (randomNum));
        
        return this.deckOfCards.remove(randomNum);
    }
    
    @Override
    public String toString() {
    	return "Deck size: " + this.deckOfCards.size();
    }
}
