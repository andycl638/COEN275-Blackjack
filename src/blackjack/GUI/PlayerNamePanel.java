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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class PlayerNamePanel extends GamePanel{
	//Setup Logging
		final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			
		//declare components
		private JPanel dialogControl;
		private CustomButton close;
		private JScrollPane scrollPane; 
		private JTextField name;
		
		//declare component configuration
		int scrollBarWidth = 10;
		private int width = 200;
		private int height = 40;
		private String placeholder = "Enter your name";
		
		public PlayerNamePanel() {
			LOGGER.info("In constructor 1 for playername panel");
			this.setBackground(super.grey);
			this.setOpaque(false);
			this.setLayout(null);
			//this.setBorder(BorderFactory.createLineBorder(new Color(100, 102, 68)));
			
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
			name = new JTextField() {
				private String placeholder = "Enter Your Name";
				
				public void paint(Graphics g) {
					
					g.setColor(Color.WHITE);
					g.fillRoundRect(0, 0, getSize().width, getSize().height, 10, 10);
					super.paint(g);
					 //Don't foreget this
					//Here will go that snippet
					if(this.placeholder == null || this.placeholder.length() == 0 || name.getText().length() > 0) {
						return;
					} else {
					
						final Graphics2D g2 = (Graphics2D) g;
				        g2.setRenderingHint(
				            RenderingHints.KEY_ANTIALIASING,
				            RenderingHints.VALUE_ANTIALIAS_ON);
				        g2.setColor(new Color(150,150,150));
				        g2.drawString(placeholder, getInsets().left, g.getFontMetrics()
				            .getMaxAscent() + getInsets().top);
					}
				}
			};
			name.setOpaque(false);
			name.setBorder(javax.swing.BorderFactory.createMatteBorder(2,2,2,2,new Color(0,0,0,0))); //No Color
			/*scrollPane = new JScrollPane(label);
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
			this.add(scrollPane);*/
			
			this.add(name);
			dialogControl = new JPanel();
			dialogControl.setLayout(null);
			dialogControl.setBackground(super.grey);
			close = new CustomButton("Start Playing", false);
			dialogControl.add(close);
			
			addListener();
			
			this.add(dialogControl);
			
			//initializeRules();
		}
		
		/*public void initializeRules() {
			LOGGER.info("in initializeRules");
			
			label.setText(getRulesText());
				
			close = new CustomButton("Close", false);
			dialogControl.add(close);
			
			addListener();
			
			this.add(dialogControl);
		}*/
		
		private void addListener() {
			close.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					BlackjackGui.getInstance().hidePlayerNameScreen();
					BlackjackGui.getInstance().showGameScreen();	
				}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}});
		}

		public Dimension getPreferredSize() {
			return new Dimension(width,height + close.getPreferredSize().height + 30);
		}
		
		public void setSize(Dimension d) {
			super.setSize(d);
			placeAndResizeComponents();
		}
		
		@Override
		public void placeAndResizeComponents() {
			LOGGER.info("in placeandresizecomponents");
			
			int padding = 15;
			name.setSize(getWidth() - 2*padding, name.getPreferredSize().height);
			name.setLocation(padding, padding);
			
			//label.setLocation(padding/2,0);
			//this.setSize(getSize().width, height);
			//this.setLocation(0, 0);

			close.setSize(((int)(close.getPreferredSize().getWidth()+padding)), ((int)(close.getPreferredSize().getHeight() + 10)));
			dialogControl.setSize(getWidth(), close.getHeight()+padding);
			close.setLocation((dialogControl.getWidth()-close.getWidth())/2, (dialogControl.getHeight()-close.getHeight())/2);
			dialogControl.setLocation(0, height);
		}
		
		public void paint(Graphics g) {
			super.paint(g); // This was missing
			g.setColor(super.grey);
			g.fillRoundRect(0, 0, getSize().width, getSize().height, 20, 20);
			super.paintChildren(g);
			g.setColor(Color.white);
			g.drawRoundRect(0, 0, getSize().width, getSize().height, 20, 20);
			
			// Below paint method was for JTextField not here 
			
		}
}
