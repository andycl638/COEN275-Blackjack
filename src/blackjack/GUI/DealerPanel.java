package blackjack.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

import javax.swing.*;

/**
 * 
 * @author Aparna Gangwar
 *	Class to create Dealer Panel.
 */
public class DealerPanel extends GamePanel{
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//Declare main components
	private Container contentPane;
	private JPanel deckPanel;
	private HandPanel dealerHandPanel;
	
	//initialize dimensions and padding
	private int pRightDeck = 100;
	private int pTopDeck = 50;
	private int pLeftLabel = 20;
	private int deckPanelHeight = 100;
	private int deckPanelWidth = 90;
	
	private JLabel card4;
	private int special_x;
	private int special_y;
	boolean isSpecialAnimating;
	
	
	public DealerPanel(Container contentPane) {
		LOGGER.info("In Dealer Panel");
		this.contentPane = contentPane;
		initialize();
	}
	
	
	
	public void initialize() {
		LOGGER.info("In init Dealer Panel");
		//dealerPanel = new JPanel();
		//dealerPanel.setBounds(0, 0, dPanelDim.width-10, (dPanelDim.height/2)-10);
		//contentPane.add(dealerPanel);
		
		//Creating Deck Panel
		deckPanel = new JPanel();
		deckPanel.setBackground(new Color(0, 128, 0));
		
		deckPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dealerHandPanel.addCard("/cards/qs.gif");
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		this.add(deckPanel);
		
		JLabel deckLbl = new JLabel();
		deckPanel.add(deckLbl);
		deckLbl.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/back.gif")).getImage()));
		
		JLabel dealerLbl = new JLabel("Dealer");
		dealerLbl.setBounds(6, 6, 60, 16);
		this.add(dealerLbl);
		
		//Dealer Hand Panel
		dealerHandPanel = new HandPanel();
		LOGGER.info("Size of the Dealer Panel is: "+this.getSize().toString());
		dealerHandPanel.setBackground(dealerHandPanel.c1);
		this.add(dealerHandPanel);
		dealerHandPanel.setLayout(null);
		dealerHandPanel.setBorder(BorderFactory.createBevelBorder(1));
		
		dealerHandPanel.addCard("/cards/qs.gif");
		dealerHandPanel.addCard("/cards/tc.gif");

	}
	
	public void startAnimation() {
		int startX = card4.getLocation().x;
		int endX = 213;
		
		special_x = startX;
		System.out.println(startX);
		
		/**Timer smallAnimation = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isSpecialAnimating = true;
				System.out.println(special_x);
				special_x = special_x - 10;
				if(special_x < endX) {
					Timer currentTimer = (Timer)arg0.getSource();
					currentTimer.stop();
				}
				repaint();
			}
			
		});
		smallAnimation.start();**/
	}
	
	public void placeAndResizeComponents() {
		System.out.println("Dealer Panel Size" + this.getSize());

		dealerHandPanel.setBounds((this.getWidth()-dealerHandPanel.width)/2, (this.getHeight()-dealerHandPanel.height)/2, dealerHandPanel.width, dealerHandPanel.height);
		deckPanel.setBounds((this.getWidth()-pRightDeck), (this.getHeight()-deckPanelHeight)/2, deckPanelWidth, deckPanelHeight);

		// Animation Effect
		if(!isSpecialAnimating)
			special_x = this.getWidth() - 2*(pRightDeck) - 73;
		special_y = (this.getHeight() - 97)/2;
		//card4.setBounds(special_x, special_y, 73, 97);
		
	}
	
	//public void initializeChildComponents() {}
	
	public void paint(Graphics g) {
		placeAndResizeComponents();
		super.paint(g);
		//Graphics2D g2d = (Graphics2D) g;
		//super.paintChildren(g);
	}
}
