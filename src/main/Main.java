package main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//Announce Start!
		System.out.println("System Start!");
		//Setup Basics
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		
		main.ImageArrayMaker.UpdateScreen();
		ImageArrayMaker.UpdateReference("OKButton.png");
		
		try {
			int[] check = Commands.ColorCompare(0, 0);
			if (check[0] == -1)
				System.out.println("No Match Found!");
			else
				System.out.println("Match found at X: " + check[0] + " Y: " + check[1]);
			Commands.BasicCommand("adb shell input tap " + check[0] + " " + check[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End!");
	}
}
