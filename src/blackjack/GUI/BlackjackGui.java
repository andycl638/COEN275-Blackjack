package blackjack.GUI;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlackjackGui extends JFrame {
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//Declare components
	private Dimension dimension;
	private Container contentPane;
	private DealerPanel dealerPanel;
	private PlayerPanel playerPanel;
	private JPanel startPanel;
	CustomImage background = new CustomImage("resources/background.jpg");
	
	//Declare background and foreground colors
	private Color frameBackground = new Color(102, 0, 34); //brown color
	private Color panelBackground = new Color(0, 102, 68); //green color
	
	//Dealer and Player panel dimensions
	protected boolean isStart = false;
	private double fwidth, fheight; //Frame width and height
	protected int pTop = 10;
	protected int pLeft = 10;
	protected int pBottom = 10;
	protected int pRight = 10;
	protected int gap = 10;
	protected int pheight, pwidth; //Panels width and height
	

	/**
	 * Launch the application.
	 */
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGGER.info("In try");
					//JWindow window = new BlackjackGui();
					JFrame window = new BlackjackGui();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}**/

	/**
	 * Create the main frame.
	 */
	public BlackjackGui() {
		super("BlackJack- COEN 275");
		try {
			JFrame window = this;
			initialize();
			window.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the main frame and initialize the dealer and player panels
	 */
	private void initialize() {
		
		//frame = new JFrame();
		contentPane = this.getContentPane();
		//background = Toolkit.getDefaultToolkit().createImage("resources/background.jpg");
		//try {
		//	this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/background.jpg")))));
		
			LOGGER.info(background.toString());
		//contentPane.setBackground(frameBackground);
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fwidth = screenSize.getWidth();
		fheight = screenSize.getHeight();
		
		//Set Default Dimension of Application window
		dimension = this.getSize();
		dimension.height = (int)fheight;
		dimension.width = (int)fwidth;
		
		//Set Minimum Dimension of Application window, resizing below this 
		//threshold will activate scroll bars
		/*minDimension = new Dimension();
		minDimension.height = 640;
		minDimension.width = 800;*/
		
		// Configuration
		this.setSize(dimension); //Frame Size
		//contentPanel.setPreferredSize(minDimension); - Use this when using scroll pane
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setBackground(panelBackground);
		
		initializeChildComponents();
		if(!isStart) {
			showGameScreen();
		}
		
	}
	
	public void initializeChildComponents() {
		LOGGER.info("Start screen");
		startPanel = new JPanel();
		LOGGER.info("Initializing Dealer");
		dealerPanel = new DealerPanel(getContentPane());
		LOGGER.info("Initializing Player");
		playerPanel = new PlayerPanel(getContentPane());
		placeAndResizeComponents();
		//((DealerPanel) dealerPanel).startAnimation();
		//contentPane.setLayout(null); // For Absolute positioning
	}
	
	public void startScreen() {
		contentPane.removeAll();
		contentPane.add(startPanel);

	}
	
	public void showGameScreen() {
		contentPane.removeAll();
		JPanel mainContent = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(background.setSize(getSize().width, getSize().height).getImage(), 0, 0, null);
				super.paintChildren(g);
			}
		};
		mainContent.setLayout(null);
		contentPane.add(mainContent);
		//contentPane.add(layeredPane);
		//layeredPane.add(mainContentPane, JLayeredPane.DEFAULT_LAYER)
		//Use layered pane to show components like dialog box(on popup pane) over the base content(which is on default layer).
		mainContent.add(dealerPanel);
		mainContent.add(playerPanel);
	}
		
	public void placeAndResizeComponents() {
		Dimension dPanelDim = contentPane.getSize();
		System.out.println(dPanelDim.toString());
		pwidth = (dPanelDim.width - pLeft - pRight);
		pheight = (dPanelDim.height - pTop - pBottom - gap)/2;
		dealerPanel.setLocation(pTop, pLeft);
		dealerPanel.setSize(pwidth,pheight);
		System.out.println("Dealer Panel Size" + dealerPanel.getSize());
		playerPanel.setLocation(pLeft, pTop + pheight + gap);
		playerPanel.setSize(pwidth, pheight);
		dealerPanel.placeAndResizeComponents();
		playerPanel.placeAndResizeComponents();
	}
	
	public void paint(Graphics g) {
		LOGGER.info("Painting BlackJackGUI");
		placeAndResizeComponents();
		super.paint(g);
		//g.drawImage(background, 0, 0, null);
		//super.paintChildren(g);
		//g.drawImage(background.setSize(getSize().width, getSize().height).getImage(), 0, 0, null);
	}
}
