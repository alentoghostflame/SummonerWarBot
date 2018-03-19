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
	//If it cant find a color, returns -1 at ColorCompare[0].
	//Else, it returns the X coordinate at ColorCompare[0], and the Y coordinate at ColorCompare[1].
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
	public int[][] ReferenceImageCheck(String image) {
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		ImageArrayMaker.UpdateReference(image);
		int[][] matchfound = new int[ArrayHolder.REFERENCEIMAGES.length + 1][2];
		int startx = 0, starty = 0;
		for (int i = 0; matchfound[0][0] != -1; i++) {
			try {
				int[] check = Commands.ColorCompare(startx, starty);
				
				/*
				int[] check = Commands.ColorCompare(startx, starty);
				if (check[0] == -1) {
					System.out.println("No Match Found!");
					matchfound[ArrayHolder.REFERENCEIMAGES.length][0] = -1;
					return matchfound;
				}
				else {
					System.out.println("Match found at X: " + check[0] + " Y: " + check[1]);
					startx = check[0] + 1;
					starty = check[1] + 1;
					matchfound[i][0] = check[0];
					matchfound[i][1] = check[1];
					//return true;
				}
				*/
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return matchfound;
	}
	public int UpdateScreen() {
		//This method is for an easy command to update the screen.
		Commands Commands = new Commands();
		Commands.ScreenShot();
		String inputimage = "screen.png";
		int width = ImageArrayMaker.Width();
		int height = ImageArrayMaker.Height();
		ImageArrayMaker.Main(inputimage, "IMAGEARRAY", width, height);
		return 0;
	}
}
