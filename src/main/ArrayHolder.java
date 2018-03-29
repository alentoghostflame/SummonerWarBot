package main;

//This class is meant hold arrays/variables that are shared across other class files.

public class ArrayHolder {
	//Need rework to act as shortcuts to places where UI elements are.
	public static final int[][] COORDS = {
			//Community Button
			{2180, 1300},
			//Friend Tab
			{330, 250},
			//Friends 1-5
			//{2180, 620}
	};
	public static final String[] REFERENCEIMAGES = {
			//Sort images based on occurrence. Top image should be the most common, bottom the rarest.
			//Put comment before image with places of occurrence.
			//Shows up at: Monthly Bonus Package (Post Login Screen)
			//"CloseButton.png",
			//Shows up at: Ad screens (Pre Login Screen)
			//"AdDontShowAgain.png",
			//Shows up at: Update screen (Pre Login Screen)
			"OKButton.png",
			//Shows up at: Title Screen
			"SummonersWarLogo.png",
			//Shows up at: Title Screen (Ads)
			"DontShowThisAgainToday.png"
	};
	//Images in this array are meant to find out where the Script is in the 
	public static final String[] REFERENCEIMAGETITLES = {
			
	};
	//Images in this array are meant to find where to perform an action, rather than find out
	//exactly what location the Script is at in Summoners War.
	//Examples include the X button, Collect buttons, Battle buttons, ETC.
	public static final String[] REFERENCEIMAGEBUTTONS = {
			
	};
	
	//Holds all the hex color values of an image, exclusively for screen.png.
	public static String[][] IMAGEARRAY = new String[ImageArrayMaker.Width()][ImageArrayMaker.Height()];
	//Holds all the hex color values of an image, used and replaced by all reference images.
	public static String[][] REFERENCEIMAGEARRAY = new String[ImageArrayMaker.Width()][ImageArrayMaker.Height()];
			
}
	

