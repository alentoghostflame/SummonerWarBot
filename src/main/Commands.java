package main;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Commands {
	void BasicCommand(String command) {
		try {
			System.out.println(command);
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	void ScreenShot() {
		//Basic Screenshot utility
		this.BasicCommand("adb shell screencap /sdcard/screen.png");
		this.BasicCommand("adb pull /sdcard/screen.png");
	}
	boolean ColorCompare(int x, int y, int h) throws IOException { 
		String hex = ArrayHolder.IMAGEARRAY[x][y];
		System.out.println("Hex detected: " + hex);
		System.out.println("Hex in Array: " + ArrayHolder.HEXCOLOR[h]);
		if (hex.equals(ArrayHolder.HEXCOLOR[h])) 
			System.out.println("Identical!");
		 else 
			System.out.println("Different!");
		return hex.equals(ArrayHolder.HEXCOLOR[h]);
		
	}
}
