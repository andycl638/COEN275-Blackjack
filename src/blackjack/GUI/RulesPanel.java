package blackjack.GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.util.logging.Logger;

import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RulesPanel extends GamePanel{
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
	//declare components
	private JPanel overlay;
	private JPanel dialogBox;
	private JPanel dialogControl;
	private CustomButton close;
	private JScrollPane scrollPane; 
	private JLabel label;
	private JTextField name;
	
	private Color overlayColor = new Color(0,0,0,100);
	private String rulesFile = "resources/rules.html";
	
	//declare component configuration
	private int padding = 10;
	private String option = "rules";
	
	public RulesPanel() {
		LOGGER.info("In constructor 1 for rules panel");
		this.setBackground(overlayColor);
		this.setOpaque(true);
		
		initialize();
	}
	
	public RulesPanel(String option) {
		LOGGER.info("In constructor 2 for rules panel");
		this.option = option;
		
		this.setBackground(overlayColor);
		this.setOpaque(true);
		
		initialize();
		
	}

	public void initialize() {
		dialogBox = new JPanel();
		/*{
			public void paint(Graphics g) {
				super.paint(g);
				Dimension d = dialogBox.getSize();
				Point p = dialogBox.getLocation();
				g.setColor(Color.RED);
				g.drawRoundRect(p.x, p.y, d.width, d.height, 20, 20);
				super.paintComponent(g);
			}
		};*/
		dialogBox.setLayout(null);
		dialogBox.setBackground(super.grey);
		dialogBox.setOpaque(true);
		label = new JLabel();
		label.setForeground(super.paleYellow);
		scrollPane = new JScrollPane(label);
		//scrollPane.setOpaque(false);
		//scrollPane.getViewport().setOpaque(false);
		//scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		int scrollBarWidth = 10;
		scrollPane.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
				);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setOpaque(false);

		scrollPane.setOpaque(false);

		scrollPane.getVerticalScrollBar().setSize(
				10,
				scrollPane.getVerticalScrollBar().getSize().height
				);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.getVerticalScrollBar().setUI(new ScrollbarUI());
		scrollPane.getVerticalScrollBar().setPreferredSize(
				new Dimension(scrollBarWidth, 0)
				);
		dialogBox.add(scrollPane);
		
		dialogControl = new JPanel();
		dialogControl.setLayout(null);
		dialogControl.setBackground(super.greyAlpha);
		
		switch(option) {
			case "rules":
				initializeRules();
				break;
			case "playerName":
				intializePlayerName();
			case "endGame":
				initializeEndGame();
		}
	}
	
	public void initializeRules() {
		LOGGER.info("in initializeRules");
		
		label.setText(getRulesText());
			
		close = new CustomButton("Close", false);
		dialogControl.add(close);
		
		addListener();
		
		this.add(dialogControl);
		this.add(dialogBox);
	}
	
	private void intializePlayerName() {
		LOGGER.info("in initializePlayerName");
		name = new JTextField();
		
	}
	
	private void initializeEndGame() {
		
	}
	
	private void addListener() {
		RulesPanel that = this;
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0)  {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}});
		
		close.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				BlackjackGui.getInstance().hideRulesScreen();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}});
	}

	@Override
	public void placeAndResizeComponents() {
		LOGGER.info("in placeandresizecomponents");
		dialogBox.setSize((int)label.getPreferredSize().getWidth(), this.getHeight()/2);
		dialogBox.setLocation((this.getWidth() - dialogBox.getWidth())/2, (this.getHeight()-dialogBox.getHeight())/2);
		
		int padding = 20;
		label.setSize(dialogBox.getWidth() - padding, label.getPreferredSize().height);
		label.setPreferredSize(label.getSize());
		
		//label.setLocation(padding/2,0);
		scrollPane.setSize(dialogBox.getSize());
		scrollPane.setLocation(0, 0);

		close.setSize(((int)(close.getPreferredSize().getWidth()+padding)), ((int)(close.getPreferredSize().getHeight()+padding)));
		dialogControl.setSize(dialogBox.getWidth(), close.getHeight()+padding);
		close.setLocation((dialogControl.getWidth()-close.getWidth())/2, (dialogControl.getHeight()-close.getHeight())/2);
		dialogControl.setLocation(dialogBox.getX(), dialogBox.getY()+dialogBox.getHeight());
		
		System.out.println("Bounds of dialog box are: "+dialogBox.getBounds().toString());
		System.out.println("Bounds of close are: "+close.getBounds().toString());
		System.out.println("Bounds of dialogControl are: "+dialogControl.getBounds().toString());
	}
	
	public String getRulesText() {
		String text = "<html>"+
						"<br/><br/>"
						+ "<h1>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;Game Rules"+
						"</h1>"+
						"<ol>"+
							"<li>Blackjack may be played with one to eight decks of 52-card decks.</li>"+
							"<li>Aces may be counted as 1 or 11 points, 2 to 9 according to pip value, and tens and face cards <br/>"+
							"count as ten points.</li>"+
							"<li>The value of a hand is the sum of the point values of the individual cards. Except, a <br/>"+
							"'blackjack' is the highest hand, consisting of an ace and any 10-point card, and it outranks all<br/>"+
							" other 21-point hands.</li>"+
							"<li>After the players have bet, the dealer will give two cards to each player and two cards to<br/> "+
							"himself. One of the dealer cards is dealt face up. The facedown card is called the 'hole card.'</li>"+
							"<li>If the dealer has a ten or an ace showing (after offering insurance with an ace showing), then<br/>"+
							" he will peek at his facedown card to see if he has a blackjack. If he does, then he will turn it over<br/>"+
							" immediately.</li>"+
							"<li>If the dealer has a ten or an ace showing (after offering insurance with an ace showing), then he <br/>"+
							"will peek at his facedown card to see if he has a blackjack. If he does, then he will turn it over immediately.</li>"+
							"<li>If the dealer does have a blackjack, then all wagers (except insurance) will lose, unless the <br/>"+
							"player also has a blackjack, which will result in a push. The dealer will resolve insurance wagers at<br/>"+
							" this time.</li>"+
							"<li>Play begins with the player to the dealer's left. The following are the choices available to the <br/>"+
							"player:"+
							"<ul>"+
								"<li><b><u>Stand</u> :</b> Player stands pat with his cards.</li>"+
								"<li><b><u>Hit</u> :</b> Player draws another card (and more if he wishes). If this card causes the <br/>"+
								"player's total points to exceed 21 (known as 'breaking' or 'busting') then he loses.</li>"+
								"<li><b><u>Double</u> :</b> Player doubles his bet and gets one, and only one, more card.</li>"+
								"<li><b><u>Split</u> :</b> If the player has a pair, or any two 10-point cards, then he may double his <br/>"+
								"bet and separate his cards into two individual hands. The dealer will automatically give each card a <br/>"+
								"second card. Then, the player may hit, stand, or double normally. However, when splitting aces, each ace<br/>"+
								" gets only one card. Sometimes doubling after splitting is not allowed. If the player gets a ten and ace <br/>"+
								"after splitting, then it counts as 21 points, not a blackjack. Usually the player may keep re-splitting up <br/>"+
								"to a total of four hands. Sometimes re-splitting aces is not allowed.</li>"+
								"<li><b><u>Surrender</u> :</b> The player forfeits half his wager, keeping the other half, and does not play<br/>"+
								" out his hand. This option is only available on the initial two cards, and depending on casino rules, <br/>"+
								"sometimes it is not allowed at all.</li>"+
							"</ul>"+
							"</li>"+
							"<li>If the dealer goes over 21 points, then any player who didn't already bust will win.</li>"+
							"<li>If the dealer does not bust, then the higher point total between the player and dealer will win.</li>"+
							"<li>Winning wagers pay even money, except a winning player blackjack usually pays 3 to 2. Some casinos have <br/>"+
							"been short-paying blackjacks, which is a rule strongly in the casino's favor.</li>"+
						"</ol>"+
					"</html>";	
		
		return text;
	}
}
