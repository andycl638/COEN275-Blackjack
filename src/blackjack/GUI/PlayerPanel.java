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
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * 
 * @author Aparna Gangwar
 * Class to create Player Panel
 */
public class PlayerPanel extends GamePanel {

	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//bet and option panel configurations
	private int playerDetailsPanelWidth=150;
	private int playerDetailsPanelHeight = 50;
	private int betPanelWidth = 200, betPanelHeight = 200;
	private int pBetPanelLeft = 20; //left padding for bet panel 
	private int pBetPanelTop = 20;	//top padding for bet panel
	private int playerHandsGap = 50; //gap between two hands in case of split
	private int pBottom = 150;
	private boolean isSplit = true;
	
	
	//declaring components
	private JPanel playerDetailsPanel, betPanel, optionsPanel, playerHandsPanel;
	private CustomButton betOne, betFive, betTen, betTwentyFive, betFifty;
	private HandPanel playerHandPanel1, playerHandPanel2;
	private CustomButton hit, stand, doubleDown, split, surrender;
	private JLabel playerName, playerBet;
	
	private Container contentPane;
	
	public PlayerPanel(Container contentPane) {
		LOGGER.info("In Player Panel");
		this.contentPane = contentPane;
		initialize();
	}
	
	public void initialize() {
		LOGGER.info("In init Player method");
		
		this.setBorder(new BevelBorder(10));
		//this.setBackground(super.panelBackground);
		this.setLayout(null);
		
		//Initialize Player details
		initializePlayerDetailsPanel();
		initializePlayerHands();
		initializeBetPanel();
		initializeOptionsPanel();
	}
	
	/**
	 * Method to initialize the player details: name, balance
	 */
	public void initializePlayerDetailsPanel() {
		playerDetailsPanel = new JPanel();
		
		playerDetailsPanel.setLayout(null);
		//playerDetailsPanel.setBackground(super.panelBackground);
		playerDetailsPanel.setOpaque(false);
		playerDetailsPanel.setBorder(new BevelBorder(10));
		
		playerName = new JLabel("Michael Scott");
		playerName.setForeground(super.paleYellow);
		playerName.setFont(new Font("Helvetica Neue",Font.PLAIN, 16));
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setVerticalAlignment(SwingConstants.CENTER);
		playerDetailsPanel.add(playerName);
		
		/**balance = new JLabel("Balance: $1000");
		balance.setForeground(Color.WHITE);
		balance.setHorizontalAlignment(SwingConstants.CENTER);
		balance.setVerticalAlignment(SwingConstants.CENTER);
		playerDetailsPanel.add(balance);
		**/
		this.add(playerDetailsPanel);
	}
	
	/**
	 * Method to initialize bets buttons
	 * Bet options are $1, $5, $10, $25, $50
	 */
	public void initializeBetPanel() {
		betPanel = new JPanel();
		
		//betPanel.setBackground(super.panelBackground);
		betPanel.setOpaque(false);
		betPanel.setLayout(null);
		this.add(betPanel);
		
		betOne = new CustomButton("resources/chips/bet1.png");		
		betFive = new CustomButton("resources/chips/bet5.png");
		betTen = new CustomButton("resources/chips/bet10.png");
		betTwentyFive = new CustomButton("resources/chips/bet25.png");
		betFifty = new CustomButton("resources/chips/bet50.png");
		
		betPanel.add(betOne);
		betPanel.add(betFive);
		betPanel.add(betTen);
		betPanel.add(betTwentyFive);
		betPanel.add(betFifty);
		
		playerBet = new JLabel("Bet: $10");
		playerBet.setForeground(super.paleYellow);
		playerBet.setHorizontalAlignment(SwingConstants.CENTER);
		playerBet.setVerticalAlignment(SwingConstants.CENTER);
		playerBet.setFont(new Font("Monospace", Font.BOLD+Font.ITALIC, 25));
		betPanel.add(playerBet);
	}
	
	/**
	 * Method to initialize player hands.
	 * Playerhands is the main panel which will contain playerHandPanel1 and playerHandPanel2.
	 * Initially, playerHandPanel2 stays empty. 
	 * In case of a split, playerHandPanel2 gets card and a gap is inserted between the two hands.
	 * Split is checked through boolean isSplit.
	 */
	public void initializePlayerHands() {
		playerHandsPanel = new JPanel();
		playerHandsPanel.setLayout(null);
		playerHandsPanel.setOpaque(false);
		//playerHandsPanel.setBackground(super.panelBackground);
		
		
		playerHandPanel1 = new HandPanel();
		//this.add(playerHandPanel);
		
		playerHandPanel1.addCard("/cards/qs.gif");
		playerHandPanel1.addCard("/cards/tc.gif");
		
		playerHandPanel2 = new HandPanel();
		
		playerHandPanel2.addCard("/cards/qs.gif");
		playerHandPanel2.addCard("/cards/qd.gif");
		
		//Add playerHandPanel 1 & 2 to playerHandsPanel
		playerHandsPanel.add(playerHandPanel1);
		playerHandsPanel.add(playerHandPanel2);
		
		//Add playerHandsPanel to playerPanel
		this.add(playerHandsPanel);
	}
	
	public void initializeOptionsPanel() {
		optionsPanel = new JPanel();
		
		optionsPanel.setOpaque(false);
		optionsPanel.setLayout(null);
		this.add(optionsPanel);
		
		hit = new CustomButton("hit", false);
		hit.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				playerHandPanel1.addCard("/cards/qs.gif");
				placeAndResizeComponents();
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
		stand = new CustomButton("STAND", false);
		split = new CustomButton("SPLIT", false);
		surrender = new CustomButton("SURRENDER", false);
		doubleDown = new CustomButton("DOUBLE", false);
		
		optionsPanel.add(stand);
		optionsPanel.add(hit);
		optionsPanel.add(split);
		optionsPanel.add(doubleDown);
		optionsPanel.add(surrender);
	}
	
	public void placeAndResizeComponents() {
		
		System.out.println("Player Panel Size" + this.getSize());
		
		/*playerName.setSize(playerName.getPreferredSize());
		playerName.setLocation(0, 0);
		balance.setSize(playerName.getPreferredSize());
		balance.setLocation(0, playerName.getHeight()+5);
		int playerDetailsPanelWidth = Math.max(playerName.getWidth(), balance.getWidth());
		int playerDetailsPanelHeight = playerName.getHeight()+balance.getHeight()+5;
		playerDetailsPanel.setLocation((this.getWidth()-playerDetailsPanelWidth)/2,0);
		playerDetailsPanel.setSize(playerDetailsPanelWidth, playerDetailsPanelHeight);*/
		
		playerName.setSize(playerName.getPreferredSize());
		playerName.setLocation(0,0);
		playerDetailsPanel.setLocation((this.getWidth()-playerName.getWidth())/2, 0);
		playerDetailsPanel.setSize(playerName.getSize());
		
		placePlayerHands();
		placeBetPanel();
		placeOptionsPanel();
	}
	
	public void placePlayerHands() {
		//Place playerHandsPanel
		playerHandPanel1.setSize(playerHandPanel1.width, playerHandPanel1.height);
		playerHandPanel1.setLocation(0, 0);
		playerHandPanel1.placeAndResizeComponents();
		
		playerHandPanel2.setSize(playerHandPanel2.width, playerHandPanel2.height);
		playerHandPanel2.setLocation(playerHandPanel1.width + (isSplit ? playerHandsGap : 0), 0);		
		playerHandPanel2.placeAndResizeComponents();
		
		int playerHandsPanelWidth =  playerHandPanel1.width+playerHandPanel2.width+(isSplit ? playerHandsGap : 0);
		int playerHandsPanelHeight = playerHandPanel1.height;
		playerHandsPanel.setLocation((this.getWidth()-playerHandsPanelWidth)/2, (this.getHeight()-playerHandsPanelHeight-pBottom)/2);
		playerHandsPanel.setSize(playerHandsPanelWidth, playerHandsPanelHeight);
	}
	
	public void placeBetPanel() {
		int gap = 5;
		int largeSize = 70;
		int smallSize = 50;
		int betPanelWidth = 0;
		int betPanelHeight = 0;
		
		//Placing first row of buttons with large size
		betOne.setSize(largeSize,largeSize);
		betOne.setLocation(0,0);
		betFive.setSize(largeSize,largeSize);
		betFive.setLocation(betOne.getWidth()+gap, 0);
		
		//Placing second row of buttons with small size
		betTen.setSize(60, 60);
		betTen.setLocation(((int)betOne.getLocation().getX())+betOne.getWidth()/2, betOne.getHeight()+gap);
		
		//Placing third row with large size
		betTwentyFive.setSize(largeSize, largeSize);
		betTwentyFive.setLocation(0, (betOne.getHeight()+betTen.getHeight()+gap*2));
		betFifty.setSize(largeSize, largeSize);
		betFifty.setLocation(betTwentyFive.getWidth()+gap, (betOne.getHeight()+betTen.getHeight()+gap*2));
		
		playerBet.setSize(largeSize*2, (int)playerBet.getPreferredSize().getHeight());
		
		betPanelWidth = ((int)betFifty.getLocation().getX())+betFifty.getWidth();
		//height of bet panel is (y-coordinate of chip in last row+ht of chip in last row+gap+ ht of betLabel+gap)
		betPanelHeight = ((int)betFifty.getLocation().getY())+betFifty.getHeight()+playerBet.getHeight()+gap*2; 
		
		betPanel.setLocation(pBetPanelLeft, pBetPanelTop);
		betPanel.setSize(betPanelWidth, betPanelHeight);
		
		
		playerBet.setLocation(0, (betPanelHeight-playerBet.getHeight()-gap));
	}
	
	public void placeOptionsPanel() {
		int optionsPanelHeight = 0;
		int padding = 15;
		int pOptionsPanelRight = 50;
		int pOptionsPanelTop = 20;
		
		//set button sizes
		surrender.setSize((int)surrender.getPreferredSize().getWidth()+padding, (int)surrender.getPreferredSize().getHeight()+padding);
		hit.setSize(surrender.getSize());
		stand.setSize(surrender.getSize());
		doubleDown.setSize(surrender.getSize());
		split.setSize(surrender.getSize());
		
		//set button locations
		hit.setLocation(0,0);
		optionsPanelHeight += (hit.getHeight()+padding);
		stand.setLocation(0, optionsPanelHeight);
		optionsPanelHeight += (stand.getHeight()+padding);
		split.setLocation(0, optionsPanelHeight);
		optionsPanelHeight += (split.getHeight()+padding);
		doubleDown.setLocation(0, optionsPanelHeight);
		optionsPanelHeight += (doubleDown.getHeight()+padding);
		surrender.setLocation(0, optionsPanelHeight);
		optionsPanelHeight += (surrender.getHeight()+padding);
		
		optionsPanel.setSize(surrender.getWidth(),optionsPanelHeight);
		optionsPanel.setLocation(this.getWidth()-surrender.getWidth()-pOptionsPanelRight,pOptionsPanelTop);
	}
	
	public void paint(Graphics g) {
		//placeAndResizeComponents();
		super.paint(g);
	}
}
