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
		
		ImageArrayMaker.Width();
		ImageArrayMaker.Height();
		
		
		try {
			File file = new File("screen.png");
			BufferedImage image = ImageIO.read(new File("screen.png"));
			
			//Log Array to file for debug
			//outputWriter = new BufferedWriter(new FileWriter("ArrayLog.txt"));
			
			for (int y = 0; y < ImageArrayMaker.Height(); y++) {
				for (int x = 0; x < ImageArrayMaker.Width(); x++) {
					//ArrayHolder.IMAGEARRAY[x][y] = Commands.ColorCheck(x, y);
					//outputWriter.write(ArrayHolder.IMAGEARRAY[x][y] + " ");
					int  clr   =  image.getRGB(x, y);
					int  red   = (clr & 0x00ff0000) >> 16;
					int  green = (clr & 0x0000ff00) >> 8;
					int  blue  =  clr & 0x000000ff;
					ArrayHolder.IMAGEARRAY[x][y] =  String.format("#%02x%02x%02x", red, green, blue);
					System.out.println("X = " + x);
				}
				System.out.println("Y = " + y);
				//outputWriter.newLine();
			}
			//Log Array to file for debug
			outputWriter.flush();  
			outputWriter.close();  
		} catch (IOException e) {
			System.out.println("IOException in ImageArrayMaker!");
			e.printStackTrace();
		}
		
		
		
		return 0;
	}

}
