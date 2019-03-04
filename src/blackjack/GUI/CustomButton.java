package blackjack.GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class CustomButton extends JLabel {
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
	private CustomImage img;
	private Color paleYellow = new Color(230,232,233); //button borders
	private Color grey = new Color(65,79,88,100); //Translucent grey color for buttons
	private Color darkgrey = new Color(65,79,88,200);
	
	private boolean isImage = true;
	private String text;
	private boolean isHighlighted, isPressed;
	
	public CustomButton(String path) {
		System.out.println(path);
		img = new CustomImage(path);
	}
	
	public CustomButton(String text, boolean isImage) {
		super(text.toUpperCase());
		this.isImage = isImage;
		this.setFont(new Font("Monospaced",Font.ITALIC, 14));
		this.setForeground(paleYellow);
		//this.setForeground(Color.WHITE);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		addMouseListener(null);
	}
	
	public void setSize(int width, int height) {
		super.setSize(width, height);
		if(isImage) {
			this.setIcon(img.setSize(width, height).getImageIcon());
			this.repaint();
		}
	}
	
	public void paint(Graphics g) {
		if(!isImage) {
			if(isHighlighted) {
				//g.translate(1, 1);
				g.setColor(darkgrey);
				
			}else {
				g.setColor(grey);
			}
			g.fillRoundRect(0, 0, getSize().width, getSize().height, 10,10);
			g.setColor(paleYellow);
			g.drawRoundRect(0, 0, getSize().width, getSize().height, 10,10);
			g.drawRoundRect(1, 1, getSize().width-2, getSize().height-2, 10,10);
			//g.drawRoundRect(2, 2, getSize().width-4, getSize().height-4, 10,10);
			//g.drawRoundRect(3, 3, getSize().width-6, getSize().height-6, 10,10);
			super.paint(g);
			if(isHighlighted) {
				//g.translate(-1, -1);
				g.setColor(grey);
			}
		}else {
			super.paint(g);
		}
	}
	
	
	
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
					LOGGER.info(cb.getFont().toString());
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
	
}
		
