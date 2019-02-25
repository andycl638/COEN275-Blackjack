package blackjack;


public class Card {
    private String imagePath;
    private int value;

    public Card(Character imagePath, int value) {
        this.imagePath = "/cards/back"+value+imagePath+".gif"; //taking the value of the card from the file name
        this.value = value;
    }

    public String getImagePath() {

        return imagePath;
    }

    public int getValue() {
    	
    	if (this.value> 10){
    		
    		return
    		this.value=10;
    	}
    	else
    		return value;
    }
    
    @Override
    public String toString() {
		return "Card Path: " + getImagePath() + " Value: " + getValue();
    	
    }
}
