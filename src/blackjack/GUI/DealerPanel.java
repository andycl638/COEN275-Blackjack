package blackjack.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

import javax.swing.*;

import blackjack.Card;

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
	private JPanel deckPanel, controlPanel;
	private CustomButton deck, exit, rules;
	public static HandPanel dealerHandPanel;
	private JLabel dealerLabel, playerBalance;
	
	//initialize dimensions and padding
	private int pRightDeck = 100;
	private int pTopDeck = 50;
	private int pLeftLabel = 20;
	private int pControlPanel = 10;

	
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
		
		playerBalance = new JLabel("Balance: $1000");
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
		deckPanel.setOpaque(false);
		//deckPanel.setBackground(super.panelBackground);
		
		this.add(deckPanel);
		
		deck = new CustomButton("resources/cards/back2.png");
		deckPanel.add(deck);
		
		initializeControlPanel();
		initializeHandPanel();
	}
	
	public void initializeControlPanel() {
		controlPanel = new JPanel();
		controlPanel.setLayout(null);
		controlPanel.setOpaque(false);
		this.add(controlPanel);
		
		rules = new CustomButton("Rules", false);
		controlPanel.add(rules);
		
		exit = new CustomButton("Exit", false);
		controlPanel.add(exit);
	}
	
	public void initializeHandPanel() {
		//Dealer Hand Panel
		dealerHandPanel = new HandPanel();
		LOGGER.info("Size of the Dealer Panel is: "+this.getSize().toString());
		dealerHandPanel.setBackground(dealerHandPanel.c1);
		this.add(dealerHandPanel);
		dealerHandPanel.setLayout(null);
		
		// displaying initial two cards
		for (Card c: BlackjackGui.dealer.getDealerHand().getHand()) {
			dealerHandPanel.addCard(c.getImagePath());
		}

	}
	
	public void placeAndResizeComponents() {
		System.out.println("Dealer Panel Size" + this.getSize());
		
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
		LOGGER.info(dealerLabel.getBounds().toString());
		LOGGER.info(deckPanel.getBounds().toString());
		LOGGER.info(this.getBounds().toString());	
	}
	
	public void paint(Graphics g) {
		//placeAndResizeComponents();
		super.paint(g);
	}
}
