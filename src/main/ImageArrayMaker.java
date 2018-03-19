package main;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

//This class is meant to deal with methods that directly read and/or write to either various arrays that
//contain data related to images, or read the images themselves.

public class ImageArrayMaker {
	//Method to figure out the Width of screen.png, and only screen.png.
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
	//Method to figure out the Height of screen.png, and only screen.png.
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
	//Main method for uploading an image to an Array.
	public static int Main(String inputimage, String array, int width, int height) {
		//Arguments, inputimage can be any image in the images folder + screen.png.
		//Array can currently be IMAGEARRAY exclusively for screen.png, or REFERENCEIMAGEARRAY for everything else.
		System.out.println("Starting ImageArrayMaker!");
		//Log Array to file for debug
		BufferedWriter outputWriter = null;
		try {
			BufferedImage image;
			//If reading screen.png, use method to view files outside the Jar.
			if (inputimage == "screen.png")
				image = ImageIO.read(new File(inputimage));
			else //if reading anything else, use method to view files inside the Jar.
				image = ImageIO.read(ImageArrayMaker.class.getResource(inputimage));
			//Log Array to file for debug. Either creates new, or overwrites old log.
			if (array == "IMAGEARRAY")
				outputWriter = new BufferedWriter(new FileWriter("ArrayLog.txt"));
			else
				outputWriter = new BufferedWriter(new FileWriter("ReferenceArrayLog.txt"));
			
			//For the Height of the Image...
			for (int y = 0; y < height; y++) {
				//For the Width of the Image...
				for (int x = 0; x < width; x++) {
					
					String hexColor = Integer.toHexString(image.getRGB(x, y) & 0xffffff);
					if (hexColor.length() < 6) {
					    hexColor = "000000".substring(0, 6 - hexColor.length()) + hexColor;
					}
					//Put the hexColor into IMAGEARRAY at specific coordinates.
					//If inserting into main image array, insert into the main image array.
					if (array == "IMAGEARRAY")
						ArrayHolder.IMAGEARRAY[x][y] = hexColor;
					else //if inserting into anything else, insert into the reference image array.
						ArrayHolder.REFERENCEIMAGEARRAY[x][y] = hexColor;
					//Print X, Y, and Hex for Debug Purposes. Slows down program considerably.
					//System.out.println("X: " + x + " Y: " + y + " Hex: " + hexColor);
					//Log to file for Debug Purposes. Slows down program slightly.
					if (array == "IMAGEARRAY")
						outputWriter.write(ArrayHolder.IMAGEARRAY[x][y] + " ");
					else
						outputWriter.write(ArrayHolder.REFERENCEIMAGEARRAY[x][y] + " ");
				}
				//Print Y for Debug Reasons, not to be used with Line 70. Slows down program slightly.
				//System.out.println("Y: " + y);
				//Go to the next line in ArrayLog.txt.
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
	public int UpdateReference(String referenceimage) {
		//This method is for an easy command to change what reference image is being compared to the Screen.
		String imageloc = "/images/" + referenceimage;
		System.out.println(imageloc);
		try {
			BufferedImage image;
			image = ImageIO.read(getClass().getResource(imageloc));
			int width = image.getWidth();
			int height = image.getHeight();
			ImageArrayMaker.Main(imageloc, "REFERENCEIMAGEARRAY", width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
