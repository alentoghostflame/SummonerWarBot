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
		
		System.out.println("End!");
	}
}
