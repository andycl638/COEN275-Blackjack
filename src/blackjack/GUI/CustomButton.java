package blackjack.GUI;

import java.awt.*;
import javax.swing.*;

public class CustomButton extends JLabel {
	
	private CustomImage img;
	
	public CustomButton(String path) {
		System.out.println(path);
		img = new CustomImage(path);
	}
	
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.setIcon(img.setSize(width, height).getImageIcon());
		this.repaint();
	}
}
