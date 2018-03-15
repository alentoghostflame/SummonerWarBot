package main;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ImageArrayMaker {
	
	public static final int Width() {
		try {
			File file = new File("screen.png");
			BufferedImage image = ImageIO.read(file);
			int width = image.getWidth();
			return width;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public static final int Height() {
		try {
			File file = new File("screen.png");
			BufferedImage image = ImageIO.read(file);
			int Height = image.getHeight();
			return Height;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public void WidthHeightChecker() {
		
	}
	public int Main() {
		System.out.println("Starting ImageArrayMaker!");
		Commands Commands = new Commands();
		Commands.ScreenShot();
		//Log Array to file for debug
		BufferedWriter outputWriter = null;
		//Get Width and Height for this entire function, as well as execute those methods for the
		//IMAGEARRAY.
		int width = ImageArrayMaker.Width();
		int height = ImageArrayMaker.Height();
		
		try {
			//Read the file "screen.png"
			BufferedImage image = ImageIO.read(new File("screen.png"));
			//Log Array to file for debug. Either creates new, or overwrites old log.
			outputWriter = new BufferedWriter(new FileWriter("ArrayLog.txt"));
			
			//For the Height of the Image...
			for (int y = 0; y < height; y++) {
				//For the Width of the Image...
				for (int x = 0; x < width; x++) {
					
					String hexColor = Integer.toHexString(image.getRGB(x, y) & 0xffffff);
					if (hexColor.length() < 6) {
					    hexColor = "000000".substring(0, 6 - hexColor.length()) + hexColor;
					}
					//Put the hexColor into IMAGEARRAY at specific coordinates
					ArrayHolder.IMAGEARRAY[x][y] = hexColor;
					//Print X, Y, and Hex for Debug Purposes. Slows down program considerably.
					//System.out.println("X: " + x + " Y: " + y + " Hex: " + hexColor);
					//Log to file for Debug Purposes. Slows down program slightly.
					outputWriter.write(ArrayHolder.IMAGEARRAY[x][y] + " ");
				}
				//Print Y for Debug Reasons, not to be used with Line 70. Slows down program slightly.
				//System.out.println("Y: " + y);
				//Go to the next line in ArrayLog.txt
				outputWriter.newLine();
			}
			//Finish up ArrayLog.txt
			outputWriter.flush();  
			outputWriter.close();
		} catch (IOException e) {
			System.out.println("IOException in ImageArrayMaker!");
			e.printStackTrace();
		}	
		return 0;
	}

}
