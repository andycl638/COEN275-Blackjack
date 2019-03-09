package blackjack.GUI;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import blackjack.BlackjackEventHandler;
import blackjack.Dealer;
import blackjack.Deck;
import blackjack.Player;

import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlackjackGui extends JFrame {
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private static BlackjackGui instance;
	
	//Declare components
	private Dimension dimension;
	private Container contentPane;
	private JLayeredPane layeredPane;
	private JPanel mainContent;
	private JPanel overlay;
	private DealerPanel dealerPanel;
	private PlayerPanel playerPanel;
	private StartGamePanel startPanel;
	//private RulesPanel rulesPanel, playerNamePanel, endGamePanel;
	
	CustomImage background = new CustomImage("resources/background.jpg");
	
	//Declare background and foreground colors
	private Color frameBackground = new Color(102, 0, 34); //brown color
	private Color panelBackground = new Color(0, 102, 68); //green color
	private Color overlayColor = new Color(0,0,0,100); //transparent grey
	
	//Dealer and Player panel dimensions
	protected boolean isStart = true;
	private double fwidth, fheight; //Frame width and height
	protected int pTop = 10;
	protected int pLeft = 10;
	protected int pBottom = 10;
	protected int pRight = 10;
	protected int gap = 10;
	protected int pheight, pwidth; //Panels width and height
	
	// Declare Event Handler
	protected BlackjackEventHandler eventHandler = new BlackjackEventHandler();
	
	// Declare backend objects
	protected static Dealer dealer;
	protected Player player;
	protected static Deck deck;

	/**
	 * Create the main frame.
	 */
	private BlackjackGui(Dealer dealer, Player player, Deck deck) {
		super("BlackJack- COEN 275");
		try {
			BlackjackGui.dealer = dealer;
			this.player = player;
			BlackjackGui.deck = deck;
			JFrame window = this;
			initialize();
			window.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BlackjackGui getInstance() {
		return instance;
	}
	
	public static BlackjackGui getInstance(Dealer dealer, Player player, Deck deck) {
		if(instance == null)
			instance = new BlackjackGui(dealer, player, deck);
		return instance;
	}
	
	/**
	 * Configure the main frame and initialize the dealer and player panels
	 */
	private void initialize() {
		
		//frame = new JFrame();
		contentPane = this.getContentPane();
		contentPane.addComponentListener(new ComponentListener() {
			@Override public void componentHidden(ComponentEvent arg0) {}
			@Override public void componentMoved(ComponentEvent arg0) {}
			@Override public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				placeAndResizeComponents();
				startPanel.placeAndResizeComponents();
				dealerPanel.placeAndResizeComponents();
				playerPanel.placeAndResizeComponents();
				//rulesPanel.placeAndResizeComponents();
			}
			@Override public void componentShown(ComponentEvent arg0) {}
		});
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fwidth = screenSize.getWidth();
		fheight = screenSize.getHeight();
		
		//Set Default Dimension of Application window
		dimension = this.getSize();
		dimension.height = (int)fheight;
		dimension.width = (int)fwidth;
		
		// Configuration
		this.setSize(dimension); //Frame Size
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.setBackground(panelBackground);
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		
		initializeChildComponents();
		if(!isStart) {
			showGameScreen();
			//showRulesScreen();
		}
		else {
			showStartScreen();
		}
		//placeAndResizeComponents();
	}
	
	public void initializeChildComponents() {
		startPanel = new StartGamePanel();
		startPanel.setVisible(false);
		
		mainContent = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(background.getImage(), 0, 0, null);
				super.paintChildren(g);
			}
		};
		mainContent.setLayout(null);
		mainContent.setVisible(false);
		
		LOGGER.info("Initializing Dealer");
		dealerPanel = new DealerPanel();
		mainContent.add(dealerPanel);
		LOGGER.info("Initializing Player");
		playerPanel = new PlayerPanel(this.player);
		mainContent.add(playerPanel);
		
		LOGGER.info("Initializing Rules");
		/*rulesPanel = new RulesPanel("rules");
		rulesPanel.setVisible(false);
		
		playerNamePanel = new RulesPanel("playerName");
		playerNamePanel.setVisible(false);
		
		endGamePanel = new RulesPanel("endGame");
		endGamePanel.setVisible(false);*/

		layeredPane.add(startPanel, 1);
		layeredPane.add(mainContent, 1);
	}
	
	public void showStartScreen() {
		LOGGER.info("Initializing start panel");
		startPanel.setVisible(true);
		mainContent.setVisible(false);
	}
	
	public void showGameScreen() {
		LOGGER.info("Initializing game panels");
		startPanel.setVisible(false);
		mainContent.setVisible(true);
	}
	
	public void showPlayerNameScreen() {
		showOverlay();
		//playerNamePanel.setVisible(true);
		
		PlayerNamePanel pnp = new PlayerNamePanel();
		pnp.setSize(pnp.getPreferredSize());
		pnp.setLocation((getSize().width - pnp.getSize().width)/2,
				(getSize().height - pnp.getSize().height)/2);
		LOGGER.info(pnp.getBounds().toString());
		overlay.add(pnp);
		this.repaint();
	}
	
	public void hidePlayerNameScreen() {
		layeredPane.remove(overlay);
		overlay = null;
		//playerNamePanel.setVisible(false);
		this.repaint();
	}
	
	public void showRulesScreen() {
		showOverlay();
		
		RulesPanel rp = new RulesPanel();
		rp.setSize(rp.getPreferredSize());
		rp.setLocation((getSize().width - rp.getSize().width)/2,
				(getSize().height - rp.getSize().height)/2);
		overlay.add(rp);		
		
		this.repaint();
	}
	
	public void hideRulesScreen() {
		layeredPane.remove(overlay);
		overlay = null;
		//rulesPanel.setVisible(false);
		this.repaint();
	}
	
	public void showEndGameScreen() {
		showOverlay();
		EndGamePanel egp = new EndGamePanel();
		egp.setSize(egp.getPreferredSize());
		egp.setLocation((getSize().width - egp.getSize().width)/2,
				(getSize().height - egp.getSize().height)/2);
		overlay.add(egp);		
		this.repaint();
	}
	
	public void hideEndGameScreen() {
		layeredPane.remove(overlay);
		overlay = null;
		//rulesPanel.setVisible(false);
		this.repaint();
	}
	
	public void exitGame() {
		System.exit(0);
	}
	
	public void showOverlay() {
		overlay = new JPanel();
		overlay.setLayout(null);
		overlay.setOpaque(true);
		overlay.setBackground(overlayColor);
		overlay.setSize(getSize());
		overlay.setLocation(0, 0);
		layeredPane.add(overlay, 200);
		layeredPane.moveToFront(overlay);
	}
	
	public void placeAndResizeComponents() {
		LOGGER.info("Size of main frame: "+this.getSize().toString());
		
		layeredPane.setSize(getContentPane().getWidth(), getContentPane().getHeight());
		
		startPanel.setSize(layeredPane.getWidth(), layeredPane.getHeight());
		mainContent.setSize(layeredPane.getWidth(), layeredPane.getHeight());
		background.setSize(mainContent.getSize());
		
		Dimension dPanelDim = contentPane.getSize();
		pwidth = (dPanelDim.width - pLeft - pRight);
		pheight = (dPanelDim.height - pTop - pBottom - gap)/2;
		dealerPanel.setLocation(pTop, pLeft);
		dealerPanel.setSize(pwidth,pheight);
		playerPanel.setLocation(pLeft, pTop + pheight + gap);
		playerPanel.setSize(pwidth, pheight);
	}
	
}
