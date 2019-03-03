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
	protected Color panelBackground = new Color(0, 128, 0);
	
	public GamePanel() {
		this.setBackground(panelBackground);
		this.setLayout(null);
	}

	public abstract void initialize();
	
	public abstract void placeAndResizeComponents();

	//public abstract void initializeChildComponents();
	
	//public abstract void initializeChildComponents();
}


