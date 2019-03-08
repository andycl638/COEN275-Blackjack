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
	private JPanel dialogControl;
	private CustomButton close;
	private JScrollPane scrollPane; 
	private JLabel label;
	
	//declare component configuration
	int scrollBarWidth = 10;
	private int width = 800;
	private int height = 400;
	
	public RulesPanel() {
		LOGGER.info("In constructor 1 for rules panel");
		this.setBackground(grey);
		this.setOpaque(true);
		this.setLayout(null);
		
		initialize();
	}

	public void initialize() {
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
		label = new JLabel();
		label.setForeground(super.paleYellow);
		scrollPane = new JScrollPane(label);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
				);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.getVerticalScrollBar().setSize(
				this.scrollBarWidth, 
				scrollPane.getVerticalScrollBar().getSize().height
				);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.getVerticalScrollBar().setUI(new ScrollbarUI());
		scrollPane.getVerticalScrollBar().setPreferredSize(
				new Dimension(scrollBarWidth, 0)
				);
		this.add(scrollPane);
		
		dialogControl = new JPanel();
		dialogControl.setLayout(null);
		dialogControl.setBackground(super.greyAlpha);
		
		initializeRules();
	}
	
	public void initializeRules() {
		LOGGER.info("in initializeRules");
		
		label.setText(getRulesText());
			
		close = new CustomButton("Close", false);
		dialogControl.add(close);
		
		addListener();
		
		this.add(dialogControl);
	}
	
	private void addListener() {
		RulesPanel that = this;
		
		close.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				BlackjackGui.getInstance().hideRulesScreen();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}});
	}

	public Dimension getPreferredSize() {
		return new Dimension(width,height + close.getPreferredSize().height + 40);
	}
	
	public void setSize(Dimension d) {
		super.setSize(d);
		placeAndResizeComponents();
	}
	@Override
	public void placeAndResizeComponents() {
		LOGGER.info("in placeandresizecomponents");
		
		int padding = 20;
		label.setSize(getWidth() - padding, label.getPreferredSize().height);
		label.setPreferredSize(label.getSize());
		
		//label.setLocation(padding/2,0);
		scrollPane.setSize(getSize().width, height);
		scrollPane.setLocation(0, 0);

		close.setSize(((int)(close.getPreferredSize().getWidth()+padding)), ((int)(close.getPreferredSize().getHeight() + 10)));
		dialogControl.setSize(getWidth(), close.getHeight()+2*padding);
		close.setLocation((dialogControl.getWidth()-close.getWidth())/2, (dialogControl.getHeight()-close.getHeight())/2);
		dialogControl.setLocation(0, height);
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