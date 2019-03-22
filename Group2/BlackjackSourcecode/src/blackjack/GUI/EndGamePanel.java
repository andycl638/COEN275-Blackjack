package blackjack.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class EndGamePanel extends GamePanel{
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			
		//declare components
		private JPanel dialogControl;
		protected CustomButton btn;
		private JScrollPane scrollPane; 
		private JLabel result;
		
		//declare component configuration
		int scrollBarWidth = 10;
		private int width = 200;
		private int height = 40;
		private String message = "";
		private String btnText = "";
		private ActionCallback callback;
		private int opacity = 255;
		private EndGamePanel egp;
		private Dimension d = new Dimension(0,0);
		private Timer tm = new Timer(50,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int targetHeight = getPreferredSize().height;
				int targetWidth = getPreferredSize().width;
				
				d.height += (targetHeight * 50 / 255);
				d.width += (targetWidth*50/255);
				if(d.height > targetHeight) d.height = targetHeight;
				if(d.width > targetWidth) d.width = targetWidth;
				//placeAndResizeComponents();
				egp.setSize(d);
				//pnp.setLocation((panel.width - d.width)/2, (panel.height - d.height)/2);
				//placeAndResizeComponents();
				if(egp.getHeight() == targetHeight && egp.getWidth() == targetWidth) {
					tm.stop();
				}
				
				egp.repaint();
			}
		});
		
		/**
		 * EndGamePanel is built
		 * @param message
		 * @param btnText
		 * @param callback
		 */
		public EndGamePanel(String message, String btnText, ActionCallback callback) {
			egp = this;
			LOGGER.info("In constructor 1 for endgame panel");
			this.callback = callback;
			this.message = message;
			this.btnText = btnText;
			this.setBackground(super.grey);
			this.setOpaque(false);
			this.setLayout(null);
			//this.setBorder(BorderFactory.createLineBorder(new Color(100, 102, 68)));
			
			initialize();
			tm.start();
		}
		/**
		 * Initializes the components of the EndGamePanel
		 */
		public void initialize() {
			result = new JLabel();
			String text = "<html>"+
								"<h3>"+
										this.message+
								"</h3>"+
							"</html>";
			result.setText(text);
			result.setForeground(paleYellow);
			result.setHorizontalAlignment(SwingConstants.CENTER);
			result.setVerticalAlignment(SwingConstants.CENTER);
			result.setOpaque(false);
			result.setBorder(javax.swing.BorderFactory.createMatteBorder(2,2,2,2,new Color(0,0,0,0))); //No Color
			
			this.add(result);
			dialogControl = new JPanel();
			dialogControl.setLayout(null);
			dialogControl.setBackground(super.grey);
			btn = new CustomButton(this.btnText, false);
			dialogControl.add(btn);
			
			addListener();
			
			this.add(dialogControl);
		}
	
		/**
		 * Adds the action listener to the components of the game panel
		 */
		private void addListener() {
			btn.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					if(callback != null)
						callback.callback();
				}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}});
		}
		
		/**
		 * Gets the preferred size of the components 
		 */
		public Dimension getPreferredSize() {
			return new Dimension(200,40 + btn.getPreferredSize().height + 60);
		}
		
		/**
		 * Sets the preferred size for the components
		 */
		public void setSize(Dimension d) {
			super.setSize(d);
			placeAndResizeComponents();
		}
		
		@Override
		/**
		 * Places and resizes the components dynamically as they are added to the screen
		 */
		public void placeAndResizeComponents() {
			LOGGER.info("in placeandresizecomponents");
			
			int padding = 10;
			result.setSize(getPreferredSize().width - 2*padding, result.getPreferredSize().height);
			result.setLocation(padding, padding);

			btn.setSize(((int)(btn.getPreferredSize().getWidth()+padding)), ((int)(btn.getPreferredSize().getHeight() + 10)));
			dialogControl.setSize(getPreferredSize().width, result.getHeight()+padding);
			btn.setLocation((dialogControl.getWidth()-btn.getWidth())/2, (dialogControl.getHeight()-btn.getHeight())/2);
			dialogControl.setLocation(0, height+padding);
		}
		
		/**
		 * Paints the JComponents according to the properties set
		 */
		public void paint(Graphics g) {
			super.paint(g); 
			g.setColor(super.grey);
			g.fillRoundRect(0, 0, getSize().width, getSize().height, 20, 20);
			super.paintChildren(g);
			g.setColor(Color.white);
			g.drawRoundRect(0, 0, getSize().width, getSize().height, 20, 20);			
		}
		
		public static interface ActionCallback {
			public void callback();
		}
}
