package blackjack;


public class Card {
    private String imagePath;
    private int value;

    public Card(Character imagePath, int value) {
        this.imagePath = "/cards/back" + value + imagePath + ".gif"; //taking the value of the card from the file name
        if (value == 1) {

            this.value = 11;
        } else if (value > 10) {

            this.value = 10;
        } else {
            this.value = value;
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Card Path: " + getImagePath() + " Value: " + getValue();
    }
}
