package blackjack.GUI;

import blackjack.Card;
import blackjack.Dealer;
import blackjack.Hand;
import blackjack.Player;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

/**
 * Class to create Player Panel
 * Has aggregation relationship with backend's Player object
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
	
	
	//declaring components
	private JPanel playerDetailsPanel, betPanel, optionsPanel, playerHandsPanel;
	private CustomButton betOne, betFive, betTen, betTwentyFive, betFifty;
	private HandPanel playerHandPanel1, playerHandPanel2;
	private CustomButton hit, stand, doubleDown, deal, surrender;
	private JLabel playerName, playerBet, playerHandValue;
	
	private Player player;
	
	public PlayerPanel(Player player) {
		this.player = player;
		initialize();
	}
	
	/**
	 * Initializes the player panel
	 */
	public void initialize() {
		
		//Initialize Player details
		initializePlayerDetailsPanel();
		initializePlayerHands();
		initializeBetPanel();
		initializeOptionsPanel();
		
		// this will be used to manage all the bets and options the player uses
		playerActions(player.getHand().get(0));

	}
	
	/**
	 * Method to initialize the player details: name, balance
	 */
	public void initializePlayerDetailsPanel() {
		playerDetailsPanel = new JPanel();
		
		playerDetailsPanel.setLayout(null);
		playerDetailsPanel.setOpaque(false);
		playerDetailsPanel.setBorder(new BevelBorder(10));
		
		playerName = new JLabel();
		playerName.setForeground(super.paleYellow);
		playerName.setFont(new Font("Helvetica Neue",Font.PLAIN, 16));
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setVerticalAlignment(SwingConstants.CENTER);
		
		playerDetailsPanel.add(playerName);
		
		this.add(playerDetailsPanel);
	}
	
	public void setPlayerNameLabel(String inputName) {
		playerName.setText(inputName + ": ");
	}
	
	public String getPlayerNameLabel() {
		return playerName.getText();
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
		
		betOne = new CustomButton("resources/chips/bet1.png", 1);		
		betFive = new CustomButton("resources/chips/bet5.png", 5);
		betTen = new CustomButton("resources/chips/bet10.png", 10);
		betTwentyFive = new CustomButton("resources/chips/bet25.png", 25);
		betFifty = new CustomButton("resources/chips/bet50.png", 50);

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
	 * Playerhands is the main panel which will contain playerHandPanel1
	 */
	public void initializePlayerHands() {
		playerHandsPanel = new JPanel();
		playerHandsPanel.setLayout(null);
		playerHandsPanel.setOpaque(false);
		//playerHandsPanel.setBackground(super.panelBackground);
		
		playerHandPanel1 = new HandPanel();
	
		//Add playerHandsPanel to playerPanel
		this.add(playerHandsPanel);
	}
	
	/**
	 * Initialize options panel
	 * The panel on bottom right which has hit, stand, deal, surrender and double buttons
	 */
	public void initializeOptionsPanel() {
		optionsPanel = new JPanel();
		
		optionsPanel.setOpaque(false);
		optionsPanel.setLayout(null);
		this.add(optionsPanel);
		
		hit = new CustomButton("hit", false);
		stand = new CustomButton("STAND", false);
		deal = new CustomButton("DEAL", false);
		surrender = new CustomButton("SURRENDER", false);
		doubleDown = new CustomButton("DOUBLE", false);
		
		optionsPanel.add(deal);
		optionsPanel.add(stand);
		optionsPanel.add(hit);
		optionsPanel.add(doubleDown);
		optionsPanel.add(surrender);
	}
	
	/**
	 * Adds action listeners to all the buttons in player panel like bet buttons and action buttons
	 * @param hand
	 */
	private void playerActions(Hand hand) {
		
		//Add mouse listener to all the bet buttons
		//On clicking any bet button, their value gets added to current bet
		//Current bet is displayed in the playerBet label
		betOne.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(deal.isEnabled()) {
					int currentBet = player.getHand().get(0).getBet();
					hand.setBet(currentBet + betOne.getValue());
					playerBet.setText("Bet: $" + hand.getBet());
					repaint();
					System.out.println("BET before click:" + currentBet);
					System.out.println("BET after click:" + hand.getBet());
				}
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
				if(deal.isEnabled()) {
					int currentBet = player.getHand().get(0).getBet();
					hand.setBet(currentBet + betFive.getValue());
					playerBet.setText("Bet: $" + hand.getBet());
					repaint();
					System.out.println("BET before click:" + currentBet);
					System.out.println("BET after click:" + hand.getBet());
				}
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
				if(deal.isEnabled()) {
					int currentBet = player.getHand().get(0).getBet();
					hand.setBet(currentBet + betTen.getValue());
					playerBet.setText("Bet: $" + hand.getBet());
					repaint();
					System.out.println("BET before click:" + currentBet);
					System.out.println("BET after click:" + hand.getBet());
				}
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
				if(deal.isEnabled()) {
					int currentBet = player.getHand().get(0).getBet();
					hand.setBet(currentBet + betTwentyFive.getValue());
					playerBet.setText("Bet: $" + hand.getBet());
					repaint();
					System.out.println("BET before click:" + currentBet);
					System.out.println("BET after click:" + hand.getBet());
				}
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
				if(deal.isEnabled()) {
					int currentBet = player.getHand().get(0).getBet();
					hand.setBet(currentBet + betFifty.getValue());
					playerBet.setText("Bet: $" + hand.getBet());
					repaint();
					System.out.println("BET before click:" + currentBet);
					System.out.println("BET after click:" + hand.getBet());
				}
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
		
		//Deal can only be clicked once
		//On clicking deal, it gets disabled along with the bet buttons
		//The cards for dealer and player's panel are added to their respective hand panels
		//All the components are placed, resized and repainted
		deal.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(deal.isEnabled()) {
						System.out.println("show cards " + hand.getHandValue());
						deal.setEnabled(false);
						betOne.setEnabled(false);
						betFive.setEnabled(false);
						betTen.setEnabled(false);
						betTwentyFive.setEnabled(false);
						betFifty.setEnabled(false);
						for (Card c : player.getHand().get(0).getHand()) {
							playerHandPanel1.addCard(c.getImagePath());
						}
						
						//Add playerHandPanel 1 & 2 to playerHandsPanel
						playerHandsPanel.add(playerHandPanel1);
						playerName.setText(player.getName() + ": " + player.getHand().get(0).getHandValue());
						DealerPanel.getInstance().showDealerLabel();
						
						// display dealers hand
						DealerPanel.getDealerHand().setVisible(true);
						placeAndResizeComponents();
						repaint();
					}
       			} catch (Exception e) {
					e.printStackTrace();
				}
				
				//if the player gets blackjack, the dialog box with congratulatory message shows up
				//on clicking the button in the dialog, it is hidden and we use multi-threading to display a second information 
				//dialog box with the amount the player has won
				if (BlackjackGui.dealer.getIsBlackjack()) {
					double amount = BlackjackGui.player.getHand().get(0).getBet() * 1.5;
					BlackjackGui.getInstance().showEndGameScreen("Congratulations it's a BLACKJACK!", "OKAY", new EndGamePanel.ActionCallback() {
							@Override
							public void callback() {
								BlackjackGui.getInstance().hideEndGameScreen();
								
								// Multi-threading for painting end game screen
								new Thread() {
									public void run() {
										try {
											Thread.sleep(1000);
											BlackjackGui.dealer.endGame(amount, 1);
											BlackjackGui.getInstance().repaint();
										} catch(Exception e) {}
									}
								}.start();
								BlackjackGui.getInstance().repaint();
							}
					});
				}
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
		
		//disables doubleDown and surrender as they can be clicked only until first hit
		//add the card that was dealt to players hand
		//check for bust, if yes, call Dealer's endGame
		hit.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					if (deal.isEnabled()==false){
						System.out.println("Disable bets and doubledown and surrender button");

						doubleDown.setEnabled(false);
						surrender.setEnabled(false);

						System.out.println("player hand value: " + hand.getHandValue());
						Card c = Dealer.hit(hand);
						System.out.println(c.toString());


						playerHandPanel1.addCard(c.getImagePath());
						playerName.setText(player.getName() + ": " + hand.getHandValue());
						System.out.println("player hand value: " + hand.getHandValue());
						placeAndResizeComponents();
						repaint();
						if(BlackjackGui.dealer.bust(hand))
						{
							BlackjackGui.dealer.endGame(-hand.getBet(), -1);
							BlackjackGui.getInstance().dealerPanel.setBalance();
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
		
		//the player stops playing
		//dealer gets the play and starts hitting
		//based on what the game result is, result is populated and it is used to call end game
		stand.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (deal.isEnabled()==false){
						System.out.println("player stops and gives control to dealer: " + hand.getHandValue());
						int result=BlackjackGui.dealer.dDecision(hand);

						System.out.println("show dealer hand");
						DealerPanel.getInstance().reinitializeHandPanel();
						if (result == 0){
							BlackjackGui.dealer.endGame(0, result);	// tie, hand values are 18 or higher
						}else if (result == -1) {
							BlackjackGui.dealer.endGame(-player.getHand().get(0).getBet(), result); // player lost, dealer has higher hand value
						}else if (result == 1){
							BlackjackGui.dealer.endGame(player.getHand().get(0).getBet(), result);// dealer bust
						}else {
							BlackjackGui.dealer.endGame(0, result);	//System.out.println("Game Broke");
						}
					}
					BlackjackGui.getInstance().dealerPanel.setBalance();
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		
		//Doubles the players bet and resets the playerBet label
		doubleDown.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					
				try {
					// disable buttons on player panel
					//check bust for dealer hand
					if(deal.isEnabled()==false && player.getHand().get(0).getHand().size()<=2){
							if(hand.getHand().size()<=2) {
							System.out.println("player doubles bet(before): " + hand.getBet());
							BlackjackGui.dealer.doubleDown(hand);
							playerBet.setText("Bet: $" + hand.getBet());
							repaint();
							System.out.println("player doubles bet(after): " + hand.getBet());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		
		//Calls dealer's surrender method
		surrender.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (deal.isEnabled()==false && player.getHand().get(0).getHand().size()<=2 ){
						System.out.println("player stops and gives control to dealer: " + hand.getHandValue());
						BlackjackGui.dealer.surrender(hand);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		
		/*playerHandPanel2.setSize(playerHandPanel2.width, playerHandPanel2.height);
		playerHandPanel2.setLocation(playerHandPanel1.width + (isSplit ? playerHandsGap : 0), 0);		
		playerHandPanel2.placeAndResizeComponents();
		LOGGER.info(playerHandPanel2.getBounds()+"");
		LOGGER.info(playerHandPanel1.getBounds()+"");*/
		
		int playerHandsPanelWidth =  playerHandPanel1.width;
		int playerHandsPanelHeight = playerHandPanel1.height;
		playerHandsPanel.setLocation((this.getWidth()-playerHandsPanelWidth)/2, (this.getHeight()-playerHandsPanelHeight-pBottom)/2);
		playerHandsPanel.setSize(playerHandsPanelWidth, playerHandsPanelHeight);
		LOGGER.info(playerHandsPanel.getBounds()+"");
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
		deal.setSize(surrender.getSize());
		
		//set button locations
		deal.setLocation(0,0);
		optionsPanelHeight += (hit.getHeight()+padding);
		hit.setLocation(0, optionsPanelHeight);
		optionsPanelHeight += (stand.getHeight()+padding);
		stand.setLocation(0, optionsPanelHeight);
		optionsPanelHeight += (deal.getHeight()+padding);
		doubleDown.setLocation(0, optionsPanelHeight);
		optionsPanelHeight += (doubleDown.getHeight()+padding);
		surrender.setLocation(0, optionsPanelHeight);
		optionsPanelHeight += (surrender.getHeight()+padding);
		
		optionsPanel.setSize(surrender.getWidth(),optionsPanelHeight);
		optionsPanel.setLocation(this.getWidth()-surrender.getWidth()-pOptionsPanelRight,pOptionsPanelTop);
	}
	
	
}
