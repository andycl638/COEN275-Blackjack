package blackjack.GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Logger;

//Class is used to create custom Button used in game
public class CustomButton extends JLabel {
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
	private CustomImage img;

	private Color paleYellow = new Color(230,232,233); //button borders
	private Color grey = new Color(65,79,88,100); //Translucent grey color for buttons
	private Color darkgrey = new Color(65,79,88,200);
	
	private boolean isImage = true;
	private String text, path;
	private boolean isHighlighted, isPressed;
	private int value = 0;
	
	//	overloaded constructor
	//creates buttons with images on it
	public CustomButton(String path) {
		this.path = path;
		img = new CustomImage(path);
	}
	
	//overloaded constructor
	//creates normal customized buttons with text
	public CustomButton(String text, boolean isImage) {
		super(text.toUpperCase());
		this.isImage = isImage;
		this.setFont(new Font("Monospaced",Font.ITALIC, 14));
		this.setForeground(paleYellow);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(null);
	}
	
	//overloaded constructor
	//creates buttons with images and an associated value, like, bet buttons
	public CustomButton(String path, int value) {
		img = new CustomImage(path);
		this.value = value;
	}
	
	//accessor for value
	public int getValue() {
		return this.value;
	}
	
	// sets the the size for custom button
	public void setSize(int width, int height) {
		super.setSize(width, height);
		if(isImage) {
			if(width == 0 || height == 0) {
				try {
					throw new Exception();
				} catch(Exception e) {
					System.out.println(path);
					e.printStackTrace();
				}
			}
			this.setIcon(img.setSize(width, height).getImageIcon());
			this.repaint();
		}
	}
	
	// implements the mouse listeners for custom button
	public void addMouseListener(MouseListener ml) {
		super.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				CustomButton cb = (CustomButton)e.getSource();
				cb.isPressed = false;
				cb.isHighlighted = false;
				cb.repaint();
				if(ml != null) {
					ml.mouseClicked(e);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if( e.getSource() instanceof CustomButton ) {
					CustomButton cb = (CustomButton)e.getSource();
					cb.isPressed = true;
					cb.repaint();
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if( e.getSource() instanceof CustomButton ) {
					CustomButton cb = (CustomButton)e.getSource();
					cb.isPressed = false;
					cb.repaint();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if( e.getSource() instanceof CustomButton ) {
					CustomButton cb = (CustomButton)e.getSource();
					cb.isHighlighted = true;
					cb.setFont(new Font("Monospaced", Font.BOLD+Font.ITALIC, 14));
					cb.repaint();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if( e.getSource() instanceof CustomButton ) {
						CustomButton cb = (CustomButton)e.getSource();
						cb.isHighlighted = false;
						cb.setFont(new Font("Monospaced", Font.ITALIC, 14));
						cb.repaint();
				}
			}
		});
	}
	
	//highlights the button on mouseover and creates a rounded rectangle around it to give a 3-d feel
		public void paint(Graphics g) {
			if(!isImage) {
				if(isHighlighted) {
					g.setColor(darkgrey);
					
				}else {
					g.setColor(grey);
				}
				g.fillRoundRect(0, 0, getSize().width, getSize().height, 10,10);
				g.setColor(paleYellow);
				g.drawRoundRect(0, 0, getSize().width, getSize().height, 10,10);
				g.drawRoundRect(1, 1, getSize().width-2, getSize().height-2, 10,10);
				super.paint(g);
				if(isHighlighted) {
					g.setColor(grey);
				}
			}else {
				super.paint(g);
			}
		}
}
		
