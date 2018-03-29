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
	//Images in this array are meant to find out what location the screen is at in Summoners War, rather
	//than the find where to perform actions.
	//Examples include the Guild tab, Monthly Event popup, Weekly Arena Award popup, ETC.
	public static final String[] REFERENCEIMAGETITLES = {
			//Logo's that appear Pre-Login.
			"SummonersWarLogo.png", //The main logo that appears right before login.
			//Logo's that appear in the Hub World.
			"HubWorldWeeklyArenaRewardLogo.png", //Appears Weekly, if rewards are gained from the arena.
			"HubWorldMonthlyEventLogo.png" //Appears on first login a day.
			//Logo's that appear in the Community Menu.
			
	};
	//Images in this array are meant to find where to perform an action, rather than find out
	//exactly what location the screen is at in Summoners War.
	//Examples include the X button, Collect buttons, Battle buttons, ETC.
	public static final String[] REFERENCEIMAGEBUTTONS = {
			//Buttons that appear in the Hub World.
			"HubWorldBattleButton.png",
			"HubWorldMonsterButton.png",
			"HubWorldChallengeButton.png",
			"HubWorldCommunityButton.png",
			"HubWorldShopButton.png",
			//Buttons that appear in the Community Menu.
			"CommunityFriendList.png", //Tab that you press to switch between Friend List and Guild.
			"CommunityGuild.png", //Tab that you press to switch between Guild and Friend List.
			"Community"
	};
	
	//Holds all the hex color values of an image, exclusively for screen.png.
	public static String[][] IMAGEARRAY = new String[ImageArrayMaker.Width()][ImageArrayMaker.Height()];
	//Holds all the hex color values of an image, used and replaced by all reference images.
	public static String[][] REFERENCEIMAGEARRAY = new String[ImageArrayMaker.Width()][ImageArrayMaker.Height()];
			
}
	

