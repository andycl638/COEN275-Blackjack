package blackjack.GUI;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class HandPanel extends JPanel{
	
	public ArrayList<JLabel> cards = new ArrayList<JLabel>(); 
	public int height = 97;
	public int width = 0;
	public int gap = 10;
	public Color c1 = new Color(0, 128, 0); //green background color
	
	
	/**
	 * Builds the HandPanel
	 */
	HandPanel() {
		initialize();
	}
	
	public void initialize() {
		setLayout(null);
		setOpaque(false);
	}
	
	public JLabel addCard(String path) {
		JLabel card = new JLabel();
		card.setHorizontalAlignment(SwingConstants.TRAILING);
		card.setBounds(0, 0, 73, 97);
		card.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage()));
		this.cards.add(card);
		//this could be the the issue
		this.add(card);
		placeAndResizeComponents();
		return card;
	}
	
	
	public String toString() {
		return "hand size: " + this.cards.size();
	}
	
	/**
	 * Places and resizes the components dynamically as they are added to the screen
	 */
	public void placeAndResizeComponents() {
		int x = 0;
		int y = 0;
		width = 0;
		
		for(JLabel card : cards) {
			card.setLocation(x,y);
			if(x != 0) {
				width += gap;
			}
			x += card.getSize().width + gap;
			
			width += card.getSize().width;
		}
	}
	

}
