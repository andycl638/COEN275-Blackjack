package blackjack.GUI;

import java.awt.*;
import java.util.logging.Logger;

import javax.swing.*;

/**
 * 
 * @author Aparna Gangwar
 * 
 * Class to unify the way dealer and player panels are made.
 *
 */
public abstract class GamePanel extends JPanel {
	
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//Creating background color
	protected Color panelBackground = new Color(0, 102, 68);
	protected Color paleYellow = new Color(230,232,233);
	protected Color grey = new Color(65,79,88);
	
	public GamePanel() {
		//this.setBackground(panelBackground);
		this.setOpaque(false);
		this.setLayout(null);
	}

	public abstract void initialize();
	
	public abstract void placeAndResizeComponents();

	//public abstract void initializeChildComponents();
	
	//public abstract void initializeChildComponents();
}


