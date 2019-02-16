package blackjack;

import javax.swing.*;

public class Card {
    private String imagePath;
    private int value;

    public Card(Character imagePath, int value) {
        this.imagePath = ".\\src\\cards\\"+value+imagePath+".gif";
        this.value = value;

    }

    public String getImagePath() {

        return imagePath;
    }

    public int getValue() {
        return value;
    }
}
