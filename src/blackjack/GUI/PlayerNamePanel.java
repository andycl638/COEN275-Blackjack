package blackjack.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Timer;
import javax.swing.border.Border;


import blackjack.Player;

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
		private int width = 0;
		private int height = 0;
		private String placeholder = "Enter your name";
		private int opacity = 255;
		private PlayerNamePanel pnp;
		private Dimension d = new Dimension(0,0);
		private Timer tm = new Timer(50,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int targetHeight = 40 + close.getPreferredSize().height + 30;
				pnp.opacity -= 50;
				d.height += (targetHeight * 50 / 255);
				d.width += (200*50/255);
				if(d.height > targetHeight) d.height = targetHeight;
				if(d.width > 200) d.width = 200;
				placeAndResizeComponents();
				pnp.setSize(d);
				//pnp.setLocation((panel.width - d.width)/2, (panel.height - d.height)/2);
				//placeAndResizeComponents();
				if(pnp.opacity < 0) {
					pnp.opacity = 0;
				}
				if(pnp.opacity == 0 && pnp.getHeight() == targetHeight && pnp.getWidth() == 200) {
					tm.stop();
				}
				
				pnp.repaint();
			}
			
		});
		
		public PlayerNamePanel() {
			pnp = this;
			LOGGER.info("In constructor 1 for playername panel");
			this.setOpaque(false);
			this.setLayout(null);
			//this.setBorder(BorderFactory.createLineBorder(new Color(100, 102, 68)));
			
			initialize();
			tm.start();
		}

		public void initialize() {
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
			
			this.add(name);
			dialogControl = new JPanel();
			dialogControl.setLayout(null);
			dialogControl.setBackground(super.grey);
			dialogControl.setOpaque(false);
			close = new CustomButton("Start Playing", false);
			dialogControl.add(close);
			
			addListener();
			
			this.add(dialogControl);
			
			
		}
		
		private void addListener() {
			close.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					BlackjackGui.getInstance().hidePlayerNameScreen();
					
					Player.getInstance().setName(name.getText());
					
					BlackjackGui.getInstance().playerPanel.setPlayerNameLabel(name.getText());
					BlackjackGui.getInstance().showGameScreen();
				}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}});
		}

		public Dimension getPreferredSize() {
			return new Dimension(200,40 + close.getPreferredSize().height + 30);
		}
		
		public void setSize(Dimension d) {
			super.setSize(d);
			placeAndResizeComponents();
		}
		
		@Override
		public void placeAndResizeComponents() {
			LOGGER.info("in placeandresizecomponents");
			
			int padding = 15;
			name.setSize(getPreferredSize().width - 2*padding, name.getPreferredSize().height);
			name.setLocation(padding, padding);

			close.setSize(((int)(close.getPreferredSize().getWidth()+padding)), ((int)(close.getPreferredSize().getHeight() + 10)));
			dialogControl.setSize(getPreferredSize().width, close.getHeight()+padding);
			close.setLocation(
					(dialogControl.getSize().width-close.getWidth())/2, 
					(dialogControl.getSize().height-close.getHeight())/2
					);
			dialogControl.setLocation(0, 40);
		}
		
		public void paint(Graphics g) {
			super.paint(g); // This was missing
			g.setColor(super.grey);
			g.fillRoundRect(0, 0, getSize().width, getSize().height, 20, 20);
			super.paintChildren(g);
			g.setColor(Color.white);
			g.drawRoundRect(0, 0, getSize().width, getSize().height, 20, 20);
			if(opacity > 0) {
				Color color = new Color(super.grey.getRed(),
						super.grey.getGreen(),
						super.grey.getBlue(),
						opacity
						);
				System.out.println(opacity);
				g.setColor(color);
				g.fillRoundRect(0, 0, getSize().width, getSize().height, 20, 20);
			}
			// Below paint method was for JTextField not here 
			
		}
}
