package blackjack.GUI;

import java.awt.*;
import javax.swing.*;

public class CustomButton extends JLabel {
	
	private CustomImage img;
	private int value = 0;
	
	public CustomButton(String path) {
		System.out.println(path);
		img = new CustomImage(path);
	}
	
	public CustomButton(String path, int value) {
		System.out.println(path);
		img = new CustomImage(path);
		this.value = value;
	}
	
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.setIcon(img.setSize(width, height).getImageIcon());
		this.repaint();
	}
	
	public int getValue() {
		return this.value;
	}
}
