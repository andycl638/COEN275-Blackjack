package blackjack;


public class Card {
    private String imagePath;
    private int value;

    /**
     * Card constructor
     * @param imagePath - path of the image
     * @param value - value of the card
     */
    public Card(Character imagePath, int value) {
        this.imagePath = "/cards/" + value + imagePath + ".gif"; //taking the value of the card from the file name
        if (value == 1) {

            this.value = 11;
        } else if (value > 10) {

            this.value = 10;
        } else {
            this.value = value;
        }
    }

    /**
     * Get image path
     * @return path of the image
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * get value of the card
     * @return value of the card
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Card Path: " + getImagePath() + " Value: " + getValue();
    }
}
