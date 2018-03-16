package main;

public class ArrayHolder {
	//Need complete rework to act as shortcuts to places where UI elements are.
	public static final int[][] COORDS = {
			//Community Button
			{2180, 1300},
			//Friend Tab
			{330, 250},
			//Friends 1-5
			//{2180, 620}
	};
	
	//Holds all the hex color values of an image, exclusively for screen.png.
	public static String[][] IMAGEARRAY = new String[ImageArrayMaker.Width()][ImageArrayMaker.Height()];
	//Holds all the hex color values of an image, used and replaced by all reference images.
	public static String[][] REFERENCEIMAGEARRAY = new String[ImageArrayMaker.Width()][2];
			
}
	

