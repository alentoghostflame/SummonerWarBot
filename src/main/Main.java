package main;

import java.io.IOException;

//This class is where the methods in other classes are called from, and should contain all of the
//higher logic that decides what is going on, and what to do based on the information given.

public class Main {

	public static void main(String[] args) throws IOException {
		//Announce Start!
		Logger.info("System Start! Logger!");
		//Setup Basics
		System.out.println(ArrayHolder.REFERENCEIMAGES.length + " Reference Images available.");
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		Commands.UpdateScreen();
		ImageArrayMaker.UpdateReference("CommunityFriendListGiftButton.png");
		int[] test = Commands.ColorCompare(2091, 537);
		Logger.info("X: " + test[0] + " Y: " + test[1]);
		//Commands.ReferenceImageCheck("CommunityFriendListGiftButton.png");
		
		
		
	}
	public static int logic() {
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		
		
		
		
		return 1;
	}
}
