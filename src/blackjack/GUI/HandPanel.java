package blackjack.GUI;

import java.util.*;
import java.util.logging.Logger;
import java.awt.*;
import javax.swing.*;

public class HandPanel extends JPanel{
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public ArrayList<JLabel> cards = new ArrayList<JLabel>(); 
	public int height = 97;
	public int width = 0;
	public int gap = 10;
	public Color c1 = new Color(0, 128, 0); //green background color
	
	HandPanel() {
		initialize();
	}
	
	public void initialize() {
		setLayout(null);
		setOpaque(false);
	}
	
	public JLabel addCard(String path) {
		JLabel card = new JLabel();
		//card.setHorizontalAlignment(SwingConstants.TRAILING);
		card.setBounds(0, 0, 73, 97);
		card.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage()));
		this.cards.add(card);
		add(card);
		placeAndResizeComponents();
		return card;
	}
	
	public JLabel addBackCard(String path) {
//		JLabel card = new JLabel();
//		LOGGER.info("For baack card: "+img.getImageIcon().getIconWidth()+", "+img.getImageIcon().getIconHeight());
//		img.setSize(120,120);
//		LOGGER.info("For baack card: "+img.getImageIcon().getIconWidth()+", "+img.getImageIcon().getIconHeight());
//		card.setIcon(img.getImageIcon());
//		card.setBounds(0,0,120,120);
//		this.cards.add(card);
		JLabel card = new JLabel();
		card.setBounds(0, 0, 73, 97);
		card.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage()));
		add(card);
		placeAndResizeComponents();
		return card;
	}
	
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
