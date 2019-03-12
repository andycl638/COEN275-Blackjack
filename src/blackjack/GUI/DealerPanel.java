package blackjack.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.*;

import blackjack.Card;
import blackjack.Player;

/**
 * 
 * @author Aparna Gangwar
 *	Class to create Dealer Panel.
 */
public class DealerPanel extends GamePanel{
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//Declare main components
	private JPanel deckPanel, controlPanel;
	private CustomButton deck, exit, rules;
	private static HandPanel dealerHandPanel;
	private JLabel dealerLabel, playerBalance;
	
	//initialize dimensions and padding
	private int pRightDeck = 100;
	private int pTopDeck = 50;
	private int pLeftLabel = 20;
	private int pControlPanel = 10;
	public boolean showRules = false;
	
	public DealerPanel() {		
		initialize();
	}
	
	public void initialize() {
		//Initialize player's current balance
		playerBalance = new JLabel("Balance: $" + Player.getInstance().getBalance());
		playerBalance.setForeground(super.paleYellow);
		playerBalance.setHorizontalAlignment(SwingConstants.CENTER);
		playerBalance.setVerticalAlignment(SwingConstants.CENTER);
		playerBalance.setFont(new Font("Monospace", Font.BOLD+Font.ITALIC, 30));
		this.add(playerBalance);
		
		//Initialize player name
		dealerLabel = new JLabel("Dealer");
		dealerLabel.setForeground(super.paleYellow);
		dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dealerLabel.setVerticalAlignment(SwingConstants.CENTER);
		dealerLabel.setFont(new Font("Helvetica Neue",Font.PLAIN, 16));
		this.add(dealerLabel);
		
		//Creating Deck Panel
		deckPanel = new JPanel();
		deckPanel.setLayout(null);

		deckPanel.setOpaque(false);	
		this.add(deckPanel);
		
		deck = new CustomButton("resources/cards/back2.png");
		deckPanel.add(deck);
		
		initializeControlPanel();
		initializeHandPanel();
	}
	public void setBalance() {
		playerBalance.setText("Balance: $" + Player.getInstance().getBalance());
	}
	
	public void initializeControlPanel() {
		controlPanel = new JPanel();
		controlPanel.setLayout(null);
		controlPanel.setOpaque(false);
		this.add(controlPanel);
		
		rules = new CustomButton("Rules", false);
		controlPanel.add(rules);
		
		exit = new CustomButton("Exit", false);
		
		addListener();
		
		controlPanel.add(exit);
	}
	
	public void initializeHandPanel() {
		//Dealer Hand Panel
		LOGGER.info("INITIALIZING DEALER HAND PANEL");
		
		dealerHandPanel = new HandPanel();
		System.out.println(dealerHandPanel.getBackground());
		dealerHandPanel.setBackground(dealerHandPanel.c1);
		this.add(dealerHandPanel);
		dealerHandPanel.setLayout(null);
		System.out.println(dealerHandPanel.getBackground());
		System.out.println(DealerPanel.getDealerHand().toString());

		dealerHandPanel.addCard("/cards/back.gif");
		dealerHandPanel.addCard(BlackjackGui.dealer.getDealerHand().getHand().get(1).getImagePath());
		dealerHandPanel.setVisible(false);
		

	}
	
	public static void initializeHandPanel2() {
		//Dealer Hand Panel
		System.out.println(DealerPanel.getDealerHand().getSize().toString());
		DealerPanel.getDealerHand().removeAll();
		dealerHandPanel.removeAll();
		
		dealerHandPanel.cards = new ArrayList<JLabel>();
		LOGGER.info("INITIALIZING DEALER HAND PANEL");
	
		System.out.println(DealerPanel.getDealerHand().toString());
		//displaying initial two cards
		for (Card c: BlackjackGui.dealer.getDealerHand().getHand()) {
			dealerHandPanel.addCard(c.getImagePath());
		}
		System.out.println(DealerPanel.getDealerHand().getSize().toString());
		DealerPanel.getDealerHand().revalidate();
		
		// this will show 1 more card
		DealerPanel.getDealerHand().setSize(new Dimension(312, 97));
		DealerPanel.getDealerHand().repaint();
		
		System.out.println(DealerPanel.getDealerHand().toString());
		dealerHandPanel.setVisible(true);

	}
	
	public static HandPanel getDealerHand() {
		return dealerHandPanel;
	}
	
	public static void addDealerCards() {
		dealerHandPanel.addCard("/cards/back.gif");
		dealerHandPanel.setVisible(true);
	}
	private void addListener() {
		rules.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				BlackjackGui.getInstance().showRulesScreen();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0)  {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		exit.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				BlackjackGui.getInstance().exitGame();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	public void placeAndResizeComponents() {		
		playerBalance.setLocation(0,0);
		playerBalance.setSize(playerBalance.getPreferredSize());
		
		rules.setSize((int)rules.getPreferredSize().getWidth()+pControlPanel,(int)rules.getPreferredSize().getHeight()+pControlPanel);
		rules.setLocation(0,0);
		exit.setSize(rules.getWidth(), (int)exit.getPreferredSize().getHeight()+pControlPanel);
		exit.setLocation(rules.getWidth()+pControlPanel, 0);
		controlPanel.setSize(rules.getWidth()+exit.getWidth()+pControlPanel, rules.getHeight());
		int controlPanelHeight = (this.getHeight()-rules.getHeight()-exit.getHeight()-pControlPanel*2)/2;
		controlPanel.setLocation(this.getWidth()-controlPanel.getWidth(), 0);
		
		dealerHandPanel.setLocation((this.getWidth()-dealerHandPanel.width)/2, (this.getHeight()-dealerHandPanel.height)/2);
		dealerHandPanel.setSize(dealerHandPanel.width, dealerHandPanel.height);
		dealerHandPanel.placeAndResizeComponents();
		
		deck.setLocation(0,0);
		deck.setSize(120,120);
		int deckPanel_X = this.getWidth()-pRightDeck-deck.getWidth();
		int deckPanel_Y = (this.getHeight()-deck.getHeight())/2;
		deckPanel.setLocation(deckPanel_X, deckPanel_Y);
		deckPanel.setSize(deck.getSize());
		
		dealerLabel.setSize(dealerLabel.getPreferredSize());
		dealerLabel.setLocation((this.getWidth()-dealerLabel.getWidth())/2,(this.getHeight()-(int)deckPanel.getLocation().getY()+10));
	}
	
}
