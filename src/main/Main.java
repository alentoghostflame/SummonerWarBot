package main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//Announce Start!
		System.out.println("System Start!");
		//Setup Basics
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		
		ImageArrayMaker.Main();
		
		
		
		
		
		/*
		try {
			AdCheck();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		System.out.println("End!");
	}
	
	static void AdCheck() throws IOException {
		Commands Commands = new Commands();
		System.out.println("Checking for Ads!");
		while (Commands.ColorCompare(1200, 1300, 0))
			Commands.BasicCommand("adb shell input tap 1200 1300");
		while (Commands.ColorCompare(1850, 500, 1)) {
			Commands.BasicCommand("adb shell input tap 1850 500");
		}
	}
}
