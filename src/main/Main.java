package main;

public class Main {

	public static void main(String[] args) {
		//Announce Start!
		System.out.println("System Start!");
		//Setup Basics
		Commands Commands = new Commands();
		//ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		do {
			Commands.ReferenceImageCheck("OKButton.png");
		} while (Commands.ReferenceImageCheck("OKButton.png"));
		do {
			Commands.ReferenceImageCheck("AdDontShowAgain.png");
		} while (Commands.ReferenceImageCheck("AdDontShowAgain.png"));
		do {
			Commands.ReferenceImageCheck("CloseButton.png");
		} while (Commands.ReferenceImageCheck("CloseButton.png"));
		
		
		
	}
}
