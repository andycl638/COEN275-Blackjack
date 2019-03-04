package blackjack.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import blackjack.Card;
import blackjack.Hand;
import blackjack.Player;

/**
 * 
 * @author Aparna Gangwar
 * Class to create Player Panel
 */
public class PlayerPanel extends GamePanel {

	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//bet and option panel configurations
	private int betPanelWidth = 200, betPanelHeight = 200;
	private int pBetPanelLeft = 20; //left padding for bet panel 
	private int pBetPanelTop = 20;	//top padding for bet panel
	private int optionsPanelWidth = 100;
	private int optionsPanelHeight = 100;
	private int pOptionsPanelRight = 20;
	private int pOptionsPanelTop = 20;
	private int playerDetailsPanelWidth=150;
	private int playerDetailsPanelHeight = 50;
	
	//declaring components
	private JPanel playerDetailsPanel, betPanel, optionsPanel;
	private CustomButton betOne, betFive, betTen, betTwentyFive, betFifty;
	private HandPanel playerHandPanel;
	private CustomButton hit, stand, doubleDown, split, surrender;
	
	private Container contentPane;
	private Player player;
	
	public PlayerPanel(Container contentPane, Player player) {
		LOGGER.info("In Player Panel");
		this.contentPane = contentPane;
		this.player = player;
		initialize();
	}
	
	public void initialize() {
		LOGGER.info("In init Player method");
		
		this.setBackground(super.panelBackground);
		this.setLayout(null);
		
		//Initialize Player details
		initializePlayerDetailsPanel();
		
		//Initialize Player Hand
		playerHandPanel = new HandPanel();
		playerHandPanel.setBackground(super.panelBackground);
		//this.add(playerHandPanel);
		
		for (Card c : this.player.getHand().get(0).getHand()) {
			playerHandPanel.addCard(c.getImagePath());
		}
		
		this.add(playerHandPanel);
		
		initializeBetPanel();
		initializeOptionsPanel();
		
		// this will be used to manage all the bets and options the player uses
//		for(Hand hand : player.getHand()) {
//			while (hand != null) {
				playerActions(player.getHand().get(0));
//			}
//		}
	}
	
	public void initializePlayerDetailsPanel() {
		playerDetailsPanel = new JPanel();
		
		playerDetailsPanel.setLayout(null);
		playerDetailsPanel.setBackground(super.panelBackground);
		this.add(playerDetailsPanel);
		
		JLabel playerLbl = new JLabel("Player: Michael Scott");
		playerLbl.setBounds(6, 6, 135, 16);
		playerDetailsPanel.add(playerLbl);
		
		JLabel balanceLbl = new JLabel("Balance: $1000");
		balanceLbl.setBounds(6, 21, 99, 16);
		playerDetailsPanel.add(balanceLbl);
	}
	
	public void initializeBetPanel() {
		
		betPanel = new JPanel();
		
		betPanel.setBackground(super.panelBackground);
		this.add(betPanel);
		betPanel.setLayout(null);
		
		betOne = new CustomButton("resources/chips/bet1.png", 1);
		betOne.setSize(50, 50);
		betOne.setLocation(0, 0);
		
		betFive = new CustomButton("resources/chips/bet5.png", 5);
		betFive.setSize(50, 50);
		betFive.setLocation(55, 0);
		
		betTen = new CustomButton("resources/chips/bet10.png", 10);
		betTen.setSize(50, 50);
		betTen.setLocation(0, 55);
		
		betTwentyFive = new CustomButton("resources/chips/bet25.png", 25);
		betTwentyFive.setSize(50, 50);
		betTwentyFive.setLocation(55, 55);
		
		betFifty = new CustomButton("resources/chips/bet50.png", 50);
		betFifty.setSize(50, 50);
		betFifty.setLocation(0, 110);
		
		betPanel.add(betOne);
		betPanel.add(betFive);
		betPanel.add(betTen);
		betPanel.add(betTwentyFive);
		betPanel.add(betFifty);
	}
	
	public void initializeOptionsPanel() {
		optionsPanel = new JPanel();
		
		optionsPanel.setBackground(super.panelBackground);
		this.add(optionsPanel);
		//flow.setVgap(5);
		optionsPanel.setLayout(null);
		optionsPanel.setBorder(new BevelBorder(5));
		optionsPanel.setVisible(true);
		
		
		/**standBtn = new JButton("Stand");
		standBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked!");
			}
		});
		standBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		hitBtn = new JButton("Hit");
		hitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked!");
			}
		});
		hitBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		splitBtn = new JButton("Split");
		splitBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		doubleBtn = new JButton("Double");
		doubleBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		surrenderBtn = new JButton("Surrender");
		surrenderBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));**/
		
		hit = new CustomButton("resources/buttons/hit.png");
		hit.setSize(70, 30);
		hit.setLocation(0, 0);
		
		
		stand = new CustomButton("resources/buttons/stand.png");
		stand.setSize(70, 30);
		stand.setLocation(0, 35);
		
		split = new CustomButton("resources/buttons/split.png");
		split.setSize(70, 30);
		split.setLocation(0, 70);
		
		surrender = new CustomButton("resources/buttons/surrender.png");
		surrender.setSize(70, 30);
		surrender.setLocation(0,105);
		
		doubleDown = new CustomButton("resources/buttons/double.png");
		doubleDown.setSize(70, 30);
		doubleDown.setLocation(0, 140);
		
		optionsPanel.add(stand);
		optionsPanel.add(hit);
		optionsPanel.add(split);
		optionsPanel.add(doubleDown);
		optionsPanel.add(surrender);
	}
	
	private void playerActions(Hand hand) {
		betOne.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int currentBet = player.getHand().get(0).getBet();
				hand.setBet(currentBet + betOne.getValue());
				repaint();
				System.out.println("BET before click:" + currentBet);
				System.out.println("BET after click:" + hand.getBet());
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
		
		betFive.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int currentBet = player.getHand().get(0).getBet();
				hand.setBet(currentBet + betFive.getValue());
				repaint();
				System.out.println("BET before click:" + currentBet);
				System.out.println("BET after click:" + hand.getBet());
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
	
		betTen.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int currentBet = player.getHand().get(0).getBet();
				hand.setBet(currentBet + betTen.getValue());
				repaint();
				System.out.println("BET before click:" + currentBet);
				System.out.println("BET after click:" + hand.getBet());
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
		
		betTwentyFive.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int currentBet = player.getHand().get(0).getBet();
				hand.setBet(currentBet + betTwentyFive.getValue());
				repaint();
				System.out.println("BET before click:" + currentBet);
				System.out.println("BET after click:" + hand.getBet());
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
		
		betFifty.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int currentBet = player.getHand().get(0).getBet();
				hand.setBet(currentBet + betFifty.getValue());
				repaint();
				System.out.println("BET before click:" + currentBet);
				System.out.println("BET after click:" + hand.getBet());
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
		
		hit.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				try {
					System.out.println("player hand value: " + hand.getHandValue());
					Card c = BlackjackGui.deck.deal();
					System.out.println(c.toString());
					hand.addCards(c);
					playerHandPanel.addCard(c.getImagePath()); 
					System.out.println("player hand value: " + hand.getHandValue());
					if(BlackjackGui.dealer.bust(hand))
					{
						BlackjackGui.dealer.endGame(-hand.getBet(), -1);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
	}
	
	public void placeAndResizeComponents() {
		System.out.println("Player Panel Size" + this.getSize());
		
		playerDetailsPanel.setLocation(0,0);
		playerDetailsPanel.setSize(playerDetailsPanelWidth, playerDetailsPanelHeight);
		
		playerHandPanel.setLocation((this.getWidth()-playerHandPanel.width)/2, (this.getHeight()-playerHandPanel.height)/2);
		playerHandPanel.setSize(playerHandPanel.width, playerHandPanel.height);
		
		betPanel.setLocation(pBetPanelLeft, pBetPanelTop+playerDetailsPanel.getHeight());
		betPanel.setSize(this.getWidth()/2, this.getHeight()/2);
		
		
		//optionsPanel.setLocation(this.getWidth()-pOptionsPanelRight-optionsPanelWidth, this.getHeight()-pOptionsPanelTop-optionsPanelHeight);
		optionsPanel.setSize(80,200);
		optionsPanel.setLocation(1150,70);
		int optionsPanel_X = this.getWidth()-optionsPanelWidth-pOptionsPanelRight;
		int optionsPanel_Y = this.getHeight()-pOptionsPanelTop;
		//optionsPanel.setLocation(optionsPanel_X,optionsPanel_Y);
		
		//optionsPanel.setSize(this.getWidth()/2, this.getHeight()/2);
		LOGGER.info(optionsPanel.getSize().toString());
		LOGGER.info(optionsPanel.getLocation().toString());
	}
	
	//public void initializeChildComponents() {}
	
	public void paint(Graphics g) {
		placeAndResizeComponents();
		super.paint(g);
		//super.paintChildren(g);
	}
}
