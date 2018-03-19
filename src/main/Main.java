package main;

//This class is where the methods in other classes are called from, and should contain all of the higher
//logic that decides what is going on, and what to do based on the information given.

public class Main {

	public static void main(String[] args) {
		//Announce Start!
		System.out.println("System Start!");
		//Setup Basics
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		System.out.println(ArrayHolder.REFERENCEIMAGES.length + " Reference Images available.");
		//This int array indicates how many of each image is on the screen.
		int[] onscreen = new int[ArrayHolder.REFERENCEIMAGES.length];
		//ImageArrayMaker.Main("CloseButton.png", "REFERENCEIMAGEARRAY", 2560, 1440);
		//int test[][] = Commands.ReferenceImageCheck("CloseButton.png");
		//System.out.println("Matches: " + test.length);
		ImageArrayMaker.UpdateReference("CloseButton.png");
	}
}
