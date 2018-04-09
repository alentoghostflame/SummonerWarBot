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
	int[] ColorCompare(int inputx, int inputy) { 
		//inputx and inputy tell it what X and Y coordinates to start checking at.
		//If it cant find a color, returns -1 at ColorCompare[0].
		//Else, it returns the X coordinate at ColorCompare[0], and the Y coordinate at ColorCompare[1].
		System.out.println("Starting ColorCompare!");
		//Integer array that returns the X and Y coordinates of the reference found.
		int[] ColorFound = new int[2];
		//Used to keep track of how wide and tall the actual reference image is inside the array.
		int referencewidth = 0, referenceheight = 0;
		//Used to check how many times the image matches the reference image, used with referencewidth.
		int ColorFoundCheck = 0;
		//get Width at the beginning as to not have to recalculate it.
		int width = ImageArrayMaker.Width();
		//get Height at the beginning as to not have to recalculate it.
		int height = ImageArrayMaker.Height();
		//Have to check how big the reference image actually is inside the array.
		for (int refy = 0; ArrayHolder.REFERENCEIMAGEARRAY[0][refy] != null; refy++) {
			referenceheight++;
		}
		for (int refx = 0; ArrayHolder.REFERENCEIMAGEARRAY[refx][0] != null; refx++) {
			referencewidth++;
		}
		System.out.println("Reference Width: " + referencewidth + " Reference Height: " + referenceheight);
		System.out.println("Reference First Hex: " + ArrayHolder.REFERENCEIMAGEARRAY[0][0]);
		//Main loop for determining if the reference matches
		for (int y = inputy; y < height; y++) {
			for (int x = inputx; x < width; x++) {
				//System.out.println("Checking X: " + x + " Y: " + y);
				//If currently selected pixel equals first pixel in the reference image...
				if (ArrayHolder.IMAGEARRAY[x][y].equals(ArrayHolder.REFERENCEIMAGEARRAY[0][0])) {
					System.out.println("Color Found!");
					for (int refy = 0; refy < referenceheight && ArrayHolder.IMAGEARRAY[x][y + refy].equals(ArrayHolder.REFERENCEIMAGEARRAY[0][refy]); refy++) {
						for (int refx = 0; refx < referencewidth && ArrayHolder.IMAGEARRAY[x + refx][y + refy].equals(ArrayHolder.REFERENCEIMAGEARRAY[refx][refy]); refx++) {
							if (ArrayHolder.REFERENCEIMAGEARRAY[refx][refy] != null) {
								ColorFoundCheck++;
								//Print if reference matches screen, along with how many matches been found
								//in a row already.
								//System.out.println(ColorFoundCheck + ") Match found!");
							}
						}
					}
					if (ColorFoundCheck == referencewidth * referenceheight) {
						System.out.println("Identical!");
						ColorFound[0] = x;
						ColorFound[1] = y;
						return ColorFound;
					}
					
					System.out.println("ColorFoundCheck = " + ColorFoundCheck + " Before reset.");
					
					ColorFoundCheck = 0;
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
	//This method takes in a image, and returns how many times it found it, and where it found it.
	public int[][] ReferenceImageCheck(String image) {
		//Output: MatchFound[i][] represents how many of that image was found on the screen.
		//MatchFound[i][0] represents the X value of the ith image found, MatchFound[i][1] represents
		//the Y value of the ith image found.
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		ImageArrayMaker.UpdateReference(image);
		//Beginning Variable for checking how many times the image has been found. Used in creation of final array.
		int TimesFound = 0;
		//Used to track where the array found an image, and where to start afterward.
		int startx = 0, starty = 0;
		boolean done = false;
		while (!done) {
			int[] ColorCompare = Commands.ColorCompare(startx, starty);
			if (ColorCompare[0] == -1) {
				done = true;
			} else {
				startx = ColorCompare[0] + 1; starty = ColorCompare[1];
				TimesFound++;
			}
		}
		Logger.info("Times " + image + " was found: " + TimesFound);
		int[][] MatchFound = new int[TimesFound][2];
		for (int i = 0; i < TimesFound; i++) {
			int[] ColorCompare = Commands.ColorCompare(startx, starty);
			if (ColorCompare[0] == -1) {
				done = true;
			} else {
				//Set the x and y variables for that specific image found in the final array.
				MatchFound[i][0] = ColorCompare[0]; MatchFound[i][1] = ColorCompare[1];
				//Set startx and starty for the next image.
				startx = ColorCompare[0] + 1; starty = ColorCompare[1];
			}
		}
		/*
		int[][] PreMatchFound = new int[999][2];
		int PreMatchFoundLength = 0;
		int startx = 0, starty = 0;
		boolean done = false;
		for (int i = 0; !done; i++) {
			int[] ColorCompare = Commands.ColorCompare(startx, starty);
			if (ColorCompare[0] == -1) {
				done = true;
			} else {
				PreMatchFound[i][0] = ColorCompare[0]; PreMatchFound[i][1] = ColorCompare[1];
				startx = ColorCompare[0] + 1; starty = ColorCompare[1];
			}
		}
		for (int i = 0; PreMatchFound[i][0] != 0; i++) {
			PreMatchFoundLength++;
		}
		int[][] MatchFound = new int[PreMatchFoundLength][2];
		for (int i = 0; i < PreMatchFoundLength; i++) {
		    System.arraycopy(PreMatchFound[i], 0, MatchFound[i], 0, PreMatchFound[0].length);
		}
		*/
		return MatchFound;
	}
	//This method is for an easy command to update the screen.
	public int UpdateScreen() {
		Commands Commands = new Commands();
		Commands.ScreenShot();
		String inputimage = "screen.png";
		int width = ImageArrayMaker.Width();
		int height = ImageArrayMaker.Height();
		ImageArrayMaker.Main(inputimage, "IMAGEARRAY", width, height);
		return 0;
	}
	//This Method runs ColorCompare with all the reference Images, then returns a number based on
	//what reference images it found.
	public int[][] FindAllReferenceImages() {
		//Output: AllReferenceImages[i] represents the image in i location of ArrayHolder.REFERENCEIMAGES[i].
		//If AllReferenceImages[i][0] == -1, that means that the reference image could not be found.
		//AllReferenceImages[i][0] represents the X value, AllReferenceImages[i][1] represents the Y value
		//of the reference image found.
		Commands Commands = new Commands();
		ImageArrayMaker ImageArrayMaker = new ImageArrayMaker();
		int[][] AllReferenceImages = new int[ArrayHolder.REFERENCEIMAGES.length][2];
		int[] ColorCompare;
		for (int i = 0; i < ArrayHolder.REFERENCEIMAGES.length; i++) {
			ImageArrayMaker.UpdateReference(ArrayHolder.REFERENCEIMAGES[i]);
			ColorCompare = Commands.ColorCompare(0, 0);
			if (ColorCompare[0] == -1) {
				AllReferenceImages[i][0] = -1;
			} else {
				AllReferenceImages[i][0] = ColorCompare[0];
				AllReferenceImages[i][1] = ColorCompare[1];
			}
		}
		return AllReferenceImages;
	}
	//This method acts as an easy shortcut to taping on the screen.
	public void Tap(int x, int y) {
		//Input x represents the X coordinate of where to tap. y represents Y coordinate of where to tap.
		//Note: Having X tap at a negative value(such as -1) seems to do nothing.
		Commands Commands = new Commands();
		Commands.BasicCommand("adb shell input tap " + x + " " + y);
	}
}
