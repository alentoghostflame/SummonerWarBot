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
		
		System.out.println("End!");
	}
}
