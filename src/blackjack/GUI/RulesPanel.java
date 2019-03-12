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
					"<br/><br/>"+
					"<h1>"+
					"&nbsp;&nbsp;&nbsp;&nbsp;Game Rules"+
				"</h1>"+
				"<ol>"+
				"<li>Aces may be counted as 1 or 11.\r\n</li>" + 
				"<li>Number cards hold their value.\r\n</li>" + 
				"<li>Face cards have a value of 10.\r\n</li>" + 
				"<li>After the players have bet, the dealer will give two cards to each player and two cards to himself. One of the<br/>"+
				" dealer cards is dealt face up. The facedown card is called the \"hole card.\"\r\n</li>" + 
				"<li>The following are the choices available to the player: \r\n" + 
					"<ul>"+
						"<li>Blackjack: Player is deal a hand of value 21 (Ace + 10 value card). Wins \r\n</li>" + 
						"<li>Stand: Player ends his turn\r\n</li>" + 
						"<li>Hit: Player draws another card.\r\n</li>" + 
						"<li>Bust: Player hand value exceed 21, loses\r\n</li>" + 
						"<li>Double: Player doubles his bet.\r\n</li>" + 
						"<li>Surrender: The player forfeits half his wager, keeping the other half. Game ends\r\n</li>" + 
					"</ul>"+
				"</li>"+
				"<li>After player has had his turn. If dealer hand equals player hand and value is less than 18. Dealer will draw a card..\r\n</li>" + 
				"<li>If the dealer goes over 21 points, then any player who didn't already bust will win.\r\n</li>" + 
				"</ol>"+
				"</html>";
		return text;
	}
}