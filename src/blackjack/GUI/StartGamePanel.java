package blackjack.GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

import javax.swing.*;

public class StartGamePanel extends GamePanel{
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//Declare Panel Components
	JPanel controlPanel;
	CustomButton banner, startGame, rules, exit;
	
	//Declare placement variables
	CustomImage background = new CustomImage("resources/background.jpg");
	private int pControlButtonWidth = 80;
	private int pControlButtonHeight = 15;
	private int cPanelGap = 30;
	private int pBannerTop = 150;
	private int pBannerLeft =180;
	
	public StartGamePanel() {
		initialize();
	}
	@Override
	public void initialize() {
		controlPanel = new JPanel();
		controlPanel.setOpaque(false);
		controlPanel.setLayout(null);
	
		banner = new CustomButton("resources/startLabel.png");
		
		startGame = new CustomButton("Start game", false);
		//startGame.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 20));
		rules = new CustomButton("Rules", false);
		exit = new CustomButton("Exit", false);
		
		addListener();
		
		controlPanel.add(exit);
		controlPanel.add(rules);
		controlPanel.add(startGame);
		this.add(banner);
		this.add(controlPanel);
		//placeAndResizeComponents();
	}

	private void addListener() {
		startGame.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				LOGGER.info("Start Game Clicked");
				BlackjackGui.getInstance().showPlayerNameScreen();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0)  {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		rules.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				LOGGER.info("Rules Clicked");
				BlackjackGui.getInstance().showRulesScreen();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0)  {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		exit.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				BlackjackGui.getInstance().exitGame();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	@Override
	public void placeAndResizeComponents() {
		// TODO Auto-generated method stub
		background.setSize(this.getSize());
		banner.setSize(this.getWidth()/2,this.getHeight()/7);
		banner.setLocation((this.getWidth() - banner.getWidth())/2, pBannerTop);
		
		int cPanelHeight = 0;
		
		startGame.setSize((int)startGame.getPreferredSize().getWidth()+pControlButtonWidth, (int)startGame.getPreferredSize().getHeight()+pControlButtonHeight);
		startGame.setLocation(0,0);
		cPanelHeight += startGame.getHeight()+cPanelGap;
		
		rules.setSize(startGame.getSize());
		rules.setLocation(0, 50);
		cPanelHeight += rules.getHeight()+cPanelGap;
		
		exit.setSize(startGame.getSize());
		exit.setLocation(0, 100);
		cPanelHeight += exit.getHeight();
		
		controlPanel.setSize((int)startGame.getSize().getWidth(), cPanelHeight);
		controlPanel.setLocation(
				(getWidth() - controlPanel.getWidth())/2,
				banner.getY()+banner.getHeight()+cPanelGap
				);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background.getImage(), 0, 0, null);
		super.paintChildren(g);
	}
}
