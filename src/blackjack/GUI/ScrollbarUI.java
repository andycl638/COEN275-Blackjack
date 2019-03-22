package blackjack.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Class to create custom scrollbar
 * @author Aparna Gangwar
 *
 */
public class ScrollbarUI extends BasicScrollBarUI {
		
		/**
		 * constructor
		 */
		  public ScrollbarUI  () {}
		  
		  /**
		   *  Hides Decrease Button
		   */
	      @Override
	      protected JButton createDecreaseButton(int orientation) {
	    	  return new JButton() {
	              @Override public Dimension getPreferredSize() {
	                return new Dimension();
	              }
	            };
	      }
	      
	      /**
	       *  Hides Increase Button
	       */
	      @Override protected JButton createIncreaseButton(int orientation) {
	    	  return new JButton() {
	              @Override public Dimension getPreferredSize() {
	                return new Dimension();
	              }
	            };
	      }
	      
	      @Override
	      protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
	    	  // For transparent track
	      }
	      
	      @Override
	      protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
	        Graphics2D g2 = (Graphics2D)g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        Color color = null;
	        JScrollBar sb = (JScrollBar)c;
	        
	        if(isDragging) {
	          color = new Color(200,200,100,200);
	          color = new Color(61, 82, 155);
	        }else if(isThumbRollover()) {
	          color = new Color(255,255,100,200);
	          color = new Color(61, 82, 255);
	        }else {
	          color = new Color(61, 82, 255);
	          color = new Color(178, 190, 181);
	        }
	        g2.setPaint(color);
	        g2.fillRoundRect(r.x,r.y,r.width-2,r.height,10,10);
	        g2.setPaint(new Color(191, 180, 188));
	        g2.drawRoundRect(r.x,r.y,r.width-2,r.height,10,10);
	        g2.dispose();
	      }
	      
	      @Override
	      protected void setThumbBounds(int x, int y, int width, int height) {
	        super.setThumbBounds(x, y, width, height);
	        scrollbar.repaint();
	      }
}