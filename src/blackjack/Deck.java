package blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    private ArrayList<Card> deckOfCards = new ArrayList<Card>();

    public Deck() {
        HashMap<Integer, Character> intSuitMapping=new HashMap<Integer, Character>();
        intSuitMapping.put(0,'s');
        intSuitMapping.put(1,'h');
        intSuitMapping.put(2,'c');
        intSuitMapping.put(3,'d');
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

        int randomNum = ThreadLocalRandom.current().nextInt(0, this.deckOfCards.size() + 1);
        return this.deckOfCards.remove(randomNum);
    }
}
