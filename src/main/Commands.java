package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Commands {
	void BasicCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			System.out.println(command);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	void ScreenShot() {
		this.BasicCommand("adb shell screencap /sdcard/screen.png");
		this.BasicCommand("adb pull /sdcard/screen.png");
	}
	
	String ColorCheck(int x, int y) throws IOException {
		ScreenShot();
		File file = new File("screen.png");
		BufferedImage image = ImageIO.read(file);
		int  clr   =  image.getRGB(x, y);
		int  red   = (clr & 0x00ff0000) >> 16;
		int  green = (clr & 0x0000ff00) >> 8;
		int  blue  =  clr & 0x000000ff;
		return String.format("#%02x%02x%02x", red, green, blue); 
	}
	boolean ColorCompare(int x, int y, int h) throws IOException { 
		String hex = ColorCheck(x, y);
		System.out.println("Hex detected: " + hex);
		System.out.println("Hex in Array: " + ArrayHolder.HEXCOLOR[h]);
		if (hex.equals(ArrayHolder.HEXCOLOR[h])) 
			System.out.println("Identical!");
		 else 
			System.out.println("Different!");
		return hex.equals(ArrayHolder.HEXCOLOR[h]);
		
	}
}
