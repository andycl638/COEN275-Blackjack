package blackjack.GUI;

import java.awt.*;

import javax.swing.*;


import blackjack.Dealer;
import blackjack.Deck;
import blackjack.Player;

import java.awt.event.*;
import java.util.logging.Logger;

/**
 * 
 * Main GUI class that calls and places the components on the frame
 * Singleton class: there can only be one instance of this class through the game
 */
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
	protected DealerPanel dealerPanel;
	protected PlayerPanel playerPanel;
	private StartGamePanel startPanel;
	//private RulesPanel rulesPanel, playerNamePanel, endGamePanel;
	
	CustomImage background = new CustomImage("resources/background.jpg");
	
	//Declare background and foreground colors
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
	
	// Declare backend objects
	protected static Dealer dealer;
	protected static Player player;
	protected static Deck deck;

	/**
	 * Creates the main frame.
	 */
	private BlackjackGui(Dealer dealer, Player player, Deck deck) {
		super("BlackJack- COEN 275");
		try {
			BlackjackGui.dealer = dealer;
			BlackjackGui.player = player;
			BlackjackGui.deck = deck;
			JFrame window = this;
			initialize();
			window.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns instance of blackjackGui
	 * @return
	 */
	public static BlackjackGui getInstance() {
		return instance;
	}
	public static void reinit(Dealer dealer, Player player, Deck deck) {
		instance = null;
		getInstance(dealer,player,deck);	
	}
	
	/**
	 * 
	 * Creates an instance of BlackjackGui, if it doesn't exist already
	 * @param dealer
	 * @param player
	 * @param deck
	 * @return
	 */
	public static BlackjackGui getInstance(Dealer dealer, Player player, Deck deck) {
		if(instance == null)
			instance = new BlackjackGui(dealer, player, deck);
		return instance;
	}
	
	/**
	 * Configure the main frame and initialize the dealer and player panels
	 *
	 */
	private void initialize() {
		
		//frame = new JFrame();
		contentPane = this.getContentPane();
		contentPane.addComponentListener(new ComponentListener() {
			public void componentHidden(ComponentEvent arg0) {}
			public void componentMoved(ComponentEvent arg0) {}
			public void componentResized(ComponentEvent arg0) {
				placeAndResizeComponents();
				startPanel.placeAndResizeComponents();
				dealerPanel.placeAndResizeComponents();
				playerPanel.placeAndResizeComponents();
			}
			public void componentShown(ComponentEvent arg0) {}
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

		layeredPane = new JLayeredPane() {
			public void paint(Graphics g) {
				System.out.println(Thread.currentThread().getName() + "LayeredPane paint");
				super.paint(g);
			}
		};
		contentPane.add(layeredPane);
		
		initializeChildComponents();
		if(!isStart) {
			showGameScreen();
		}
		else {
			showStartScreen();
		}
	}
	
	/**
	 * Initialized the start panel, dealer panel, player panel and adds them to layered pane on the same level
	 */
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
		dealerPanel = DealerPanel.getInstance();
		mainContent.add(dealerPanel);
		LOGGER.info("Initializing Player");
		playerPanel = new PlayerPanel(this.player);
		mainContent.add(playerPanel);
		
		LOGGER.info("Initializing Rules");
		layeredPane.add(startPanel, 1);
		layeredPane.add(mainContent, 1);
	}
	
	/**
	 * Shows start panel and hides main panel
	 */
	public void showStartScreen() {
		LOGGER.info("Initializing start panel");
		startPanel.setVisible(true);
		mainContent.setVisible(false);
	}
	
	/**
	 * Shows main panel(game panel) and hides start panel 
	 */
	public void showGameScreen() {
		LOGGER.info("Initializing game panels");
		startPanel.setVisible(false);
		mainContent.setVisible(true);
	}
	
	/**
	 * Shows the screen with the player name dialog box if the player doesn't exist already
	 * If the player is already existing, show the game screen
	 */
	public void showPlayerNameScreen() {
		if(Player.getInstance().getName().length() > 0) {
			showGameScreen();
		} else {
			showOverlay();
			//playerNamePanel.setVisible(true);
			
			PlayerNamePanel pnp = new PlayerNamePanel();
			pnp.setSize(0,0);
			//pnp.setPanelSize(getSize());
			pnp.setLocation((getSize().width - pnp.getPreferredSize().width)/2,
					(getSize().height - pnp.getPreferredSize().height)/2);
			LOGGER.info(pnp.getBounds().toString());
			overlay.add(pnp);
			this.repaint();
		}
	}
	
	/**
	 * Hides player name screen
	 * Removes the overlay from layered pane and repaints the base screen 
	 */
	public void hidePlayerNameScreen() {
		layeredPane.remove(overlay);
		overlay = null;
		this.repaint();
	}
	
	/**
	 * Shows rules dialog box by adding overlay on top of the base screen on the layered pane.
	 * Adds rules dialog box on top of the overlay.
	 * Called on click of rules button.
	 */
	public void showRulesScreen() {
		showOverlay();
		
		RulesPanel rp = new RulesPanel();
		rp.setSize(rp.getPreferredSize());
		rp.setLocation((getSize().width - rp.getSize().width)/2,
				(getSize().height - rp.getSize().height)/2);
		overlay.add(rp);		
		
		this.repaint();
	}
	
	/**
	 * Hides the rules dialog box by removing overlay from layered pane.
	 * Repaints to show the background screen
	 */
	public void hideRulesScreen() {
		layeredPane.remove(overlay);
		overlay = null;
		//rulesPanel.setVisible(false);
		this.repaint();
	}
	
	/**
	 * Called on clicking exit buttons
	 */
	public void exitGame() {
		System.exit(0);
		
	}
	
	/**
	 * Calls the endGameScreen
	 * @param message: The message that needs to be displayed in the dialog box
	 * @param btnText: the btn text that needs to be shown
	 * @param callback the method that needs to be called on the press of the button
	 */
	public void showEndGameScreen(String message, String btnText, EndGamePanel.ActionCallback callback) {
		showOverlay();
		if(callback == null) {
			callback = ()->BlackjackGui.getInstance().exitGame();
		}
		
		EndGamePanel egp = new EndGamePanel(message, btnText, callback);
		//egp.setSize(egp.getPreferredSize());
		egp.setSize(0,0);
		egp.setLocation((getSize().width - egp.getPreferredSize().width)/2,
				(getSize().height - egp.getPreferredSize().height)/2);
		overlay.add(egp);		
		this.repaint();
	}
	
	/**
	 * Hides the end game dialog. Only used in case of blackjack when we show two dialog boxes
	 */
	public void hideEndGameScreen() {
		layeredPane.remove(overlay);
		overlay.setVisible(false);
		overlay = null;
		//rulesPanel.setVisible(false);
		layeredPane.repaint();
	}
	
	/**
	 * Called when player clicks on "Play Again" in end game dialog box
	 */
	public void restartGame() {
		this.setVisible(false);
		player = Player.getInstance();
		
		// set the initial bet. should be passed in to dealer
		// deck might need to be created outside so that the dealer can have the same deck when a game is finished
		Player.reinit();
		Deck deck = new Deck(); //pass to dealer param
		Dealer dealer = new Dealer(player, deck);
		
		// Reinitialize all singleton
		DealerPanel.reinit();
		
		BlackjackGui.reinit(dealer, player, deck);
		//System.exit(0);
	}
	
	/**
	 * Creates a translucent JPanel to be displayed on top of the current screen
	 * Adds it on a higher level on layered pane to display it over the base screen.
	 * All the dialog box type screens: rules panel, playername panel, end game panel; are added on top of it.
	 */
	public void showOverlay() {
		overlay = new JPanel();
		overlay.setLayout(null);
		overlay.setOpaque(true);
		overlay.setBackground(overlayColor);
		overlay.setSize(getSize());
		overlay.setLocation(0, 0);
		layeredPane.add(overlay, 200);
		layeredPane.moveToFront(overlay);
		
		overlay.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}			
		});
	}
	
	/**
	 * Resizes and places the components initialized to the class
	 */
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
