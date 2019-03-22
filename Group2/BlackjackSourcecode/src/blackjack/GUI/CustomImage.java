package blackjack.GUI;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

//creates custom images which are used in custom buttons
public class CustomImage {
	private String filename;
	private BufferedImage image = null;
	private Image resizedImage;
	private int width;
	private int height;
	
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//constructor
	public CustomImage(String filename) {
		this.filename = filename;
		readImage();
	}
	
	//	reads image file and creates image
	private void readImage() {
		try {
			image = ImageIO.read(new File(filename));
			height = image.getHeight();
			width = image.getWidth();
			if(height == 0 || width == 0) {
				System.out.println("Culprit is " + filename);
			}
		} catch(IOException e) {
			LOGGER.severe("Unable to read" + filename);
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//	creates Image icon for image read by readImage()
	public ImageIcon getImageIcon() {
		return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	
	public Image getImage() {
		return resizedImage;
	}
	
	//	sets size for the image
	public CustomImage setSize(Dimension dimension) {
		if(width == 0 || height == 0) return this;
		height = dimension.height;
		width = dimension.width;
		resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return this;
	}
	
	//overloaded method for passing in width and height for resizing image
	public CustomImage setSize(int width, int height) {
		if(width == 0 || height == 0) return this;
		this.height = height;
		this.width = width;
		resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return this;
	}
}