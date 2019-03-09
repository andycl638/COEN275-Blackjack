package blackjack.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class EndGamePanel extends GamePanel{
	//Setup Logging
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			
		//declare components
		private JPanel dialogControl;
		private CustomButton exit;
		private JScrollPane scrollPane; 
		private JLabel result;
		
		//declare component configuration
		int scrollBarWidth = 10;
		private int width = 200;
		private int height = 40;
		
		public EndGamePanel() {
			LOGGER.info("In constructor 1 for endgame panel");
			this.setBackground(super.grey);
			this.setOpaque(false);
			this.setLayout(null);
			//this.setBorder(BorderFactory.createLineBorder(new Color(100, 102, 68)));
			
			initialize();
		}

		public void initialize() {
			result = new JLabel();
			String text = "<html>"+
								"<h3>"+
										"Player 1 Won!!!"+
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
			exit = new CustomButton("Exit", false);
			dialogControl.add(exit);
			
			addListener();
			
			this.add(dialogControl);
		}
	
		private void addListener() {
			exit.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					BlackjackGui.getInstance().exitGame();
				}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}});
		}

		public Dimension getPreferredSize() {
			return new Dimension(width,height + exit.getPreferredSize().height + 30);
		}
		
		public void setSize(Dimension d) {
			super.setSize(d);
			placeAndResizeComponents();
		}
		
		@Override
		public void placeAndResizeComponents() {
			LOGGER.info("in placeandresizecomponents");
			
			int padding = 10;
			result.setSize(getWidth() - 2*padding, result.getPreferredSize().height);
			result.setLocation(padding, padding);

			exit.setSize(((int)(exit.getPreferredSize().getWidth()+padding)), ((int)(exit.getPreferredSize().getHeight() + 10)));
			dialogControl.setSize(getWidth(), result.getHeight()+padding);
			exit.setLocation((dialogControl.getWidth()-exit.getWidth())/2, (dialogControl.getHeight()-exit.getHeight())/2);
			dialogControl.setLocation(0, height);
		}
		
		public void paint(Graphics g) {
			super.paint(g); 
			g.setColor(super.grey);
			g.fillRoundRect(0, 0, getSize().width, getSize().height, 20, 20);
			super.paintChildren(g);
			g.setColor(Color.white);
			g.drawRoundRect(0, 0, getSize().width, getSize().height, 20, 20);			
		}
}
