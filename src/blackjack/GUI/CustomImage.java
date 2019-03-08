package blackjack.GUI;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
//import java.util.*;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class CustomImage {
	private String filename;
	private BufferedImage image = null;
	private Image resizedImage;
	private int width;
	private int height;
	
	final private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public CustomImage(String filename) {
		this.filename = filename;
		readImage();
	}
	
	private void readImage() {
		try {
			image = ImageIO.read(new File(filename));
			height = image.getHeight();
			width = image.getWidth();
		} catch(IOException e) {
			LOGGER.severe("Unable to read" + filename);
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getImageIcon() {
		return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	
	public Image getImage() {
		return resizedImage;
	}
	
	public CustomImage setSize(Dimension dimension) {
		height = dimension.height;
		width = dimension.width;
		resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return this;
	}
	
	public CustomImage setSize(int width, int height) {
		this.height = height;
		this.width = width;
		resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return this;
	}
}