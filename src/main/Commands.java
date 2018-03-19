package main;

import java.io.IOException;

//This class is meant to hold general commands that often add as shortcuts for the Main class file.

public class Commands {
	//This method is for an easy way to execute system commands.
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
	//This Method is to compare colors of the screen to a reference image.
	int[] ColorCompare(int inputx, int inputy) throws IOException { 
		System.out.println("Starting ColorCompare!");
		//Integer array that returns the X and Y coordinates of the reference found.
		int[] ColorFound = new int[2];
		//Used to keep track of how wide the actual reference image is inside the array.
		int referencewidth = 0;
		//Used to check how many times the image matches the reference image, used with referencewidth.
		int ColorFoundCheck = 0;
		//get Width at the beginning as to not have to recalculate it.
		int width = ImageArrayMaker.Width();
		//get Height at the beginning as to not have to recalculate it.
		int height = ImageArrayMaker.Height();
		//Have to check how big the reference image actually is inside the array.
		for (int refx = 0; ArrayHolder.REFERENCEIMAGEARRAY[refx][0] != null; refx++) {
			referencewidth++;
		}
		System.out.println("Reference Width: " + referencewidth);
		System.out.println("Reference First Hex: " + ArrayHolder.REFERENCEIMAGEARRAY[0][0]);
		//Main loop for determining if the reference matches
		for (int y = inputy; y < height; y++) {
			for (int x = inputx; x < width; x++) {
				//If currently selected pixel equals first pixel in the reference image...
				if (ArrayHolder.IMAGEARRAY[x][y].equals(ArrayHolder.REFERENCEIMAGEARRAY[0][0])) {
					System.out.println("Color Found!");
					//If currently selected pixel equals first pixel in the reference image...
					for (int refx = 0; ArrayHolder.IMAGEARRAY[x + refx][y].equals(ArrayHolder.REFERENCEIMAGEARRAY[refx][0]); refx++) {
						//Add 1 to the color check counter, assuming that the reference array isn't null.
						if (ArrayHolder.REFERENCEIMAGEARRAY != null) {
							ColorFoundCheck++;
							//Print if reference matches screen, along with how many matches been found
							//in a row already.
							//System.out.println(ColorFoundCheck + ") Match found!");
						}
						//If the color check counter is equal to the width of the reference
						//image, return the first X and Y coordinates it found it at.
						if (ColorFoundCheck == referencewidth) {
							System.out.println("Identical!");
							ColorFound[0] = x;
							ColorFound[1] = y;
							return ColorFound;
						}
					}
					ColorFoundCheck = 1;
				}
				//Print X, and Y for Debug Purposes. Slows down program considerably.
				//System.out.println("Checking X: " + x + " Y: " + y);
			}
			//Print Y for Debug Reasons, not to be used with Line 69. Slows down program slightly.
			//System.out.println("Checking Y: " + y);
		}
		//If no match is found, return -1 on the X value of the array. 
		ColorFound[0] = -1;
		return ColorFound;		
	}
	public boolean ReferenceImageCheck(String image) {
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		main.ImageArrayMaker.UpdateScreen();
		ImageArrayMaker.UpdateReference(image);
		try {
			int[] check = Commands.ColorCompare(0, 0);
			if (check[0] == -1) {
				System.out.println("No Match Found!");
				return false;
			}
			else {
				System.out.println("Match found at X: " + check[0] + " Y: " + check[1]);
				Commands.BasicCommand("adb shell input tap " + check[0] + " " + check[1]);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
