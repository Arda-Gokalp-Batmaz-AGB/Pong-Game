import java.awt.Font;

/**
 * This class provides Menus, button events, pregame settings
 * to the game.
 * @author Arda Gokalp Batmaz
 * @since Date:12.04.2021
 *
 */
public class Game_Menu {
	// Data fields
	private final static int CANVAS_HEIGHT = 800;
	private final static int CANVAS_WIDTH = 1000;
	private static double Buttonx;
	private static double Buttony;
	private static double halfWidth;
	private static double halfHeight;
	private static String gameMode;
	private static double difficulty;

	/**
	 * Returns game mode of game (Single or Multi)
	 * @return mod of the game
	 */
	public static String getGameMode() {
		return gameMode;
	}

	/**
	 * Difficulty of game
	 * @return difficulty
	 */
	public static double getDifficulty() {
		return difficulty;
	}

	/**
	 * Sets game mode
	 * @param gameMode specified gamemode
	 */
	public static void setGameMode(String gameMode) {
		Game_Menu.gameMode = gameMode;
	}

	/**
	 * Default constructor, initialize button's attribiutes 
	 */
	public Game_Menu()
	{
		Buttonx = 0.25;
		Buttony = 0.3;
		halfWidth = 0.24;
		halfHeight = 0.15;
	}

	/**
	 * Credits and version of game
	 */
	public static void creditsAndVersion()
	{
		StdDraw.filledRectangle(0.88, -0.94,0.12, 0.06);
		StdDraw.filledRectangle(-0.88, -0.94,0.12, 0.06);
		StdDraw.setFont( new Font("Arial", Font.BOLD, 30) );
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.88, -0.94, "V 1.0.0");
		StdDraw.text(-0.88, -0.94, "By AGB");
	}
	/**
	 * Lists game modes of the game
	 */
	public void gameModes()
	{
		Game_Board.texture(); // Updates background.

		// Draws buttons of main menu.
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(Buttonx,Buttony,halfWidth,halfHeight);
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(-Buttonx,Buttony,halfWidth,halfHeight);
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.setFont( new Font("Helvetica", Font.BOLD, 40) );
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(Buttonx,Buttony, "SinglePlayer");
		StdDraw.text(-Buttonx, Buttony, "MultiPlayer");

		guideLine(); // Guide texts of game
		creditsAndVersion(); // Credits
		StdDraw.show(); // Show drawings
	}
	public void difficultyList()
	{
		Game_Board.texture(); // Updates background.

		// Draws difficulty buttons
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(0,Buttony+Buttony/1.2,halfWidth,halfHeight/2);
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(0,Buttony/1.2,halfWidth,halfHeight/2);
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledRectangle(0,0,halfWidth,halfHeight/2);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.filledRectangle(0,-Buttony,halfWidth,halfHeight/2);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(0,-2*Buttony,halfWidth,halfHeight/2);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont( new Font("Helvetica", Font.BOLD, 40) );
		StdDraw.text(0,Buttony+Buttony/1.2, "Easy");
		StdDraw.text(0,Buttony/1.2, "Medium");
		StdDraw.text(0,0, "Hard");
		StdDraw.text(0,-Buttony, "Hardest");
		StdDraw.text(0,-2*Buttony, "IMPOSSIBLE");

		StdDraw.show(); // Show drawings
	}

	/**
	 * If game is singleplayer, sets difficulty of game,
	 * there are five different difficulties
	 * @param dif determined difficulty for game
	 * @return difficulty of game
	 */
	public double setBotDifficulty(String dif)
	{
		// Every if block stands for a difficulty
		if(dif.equals("Easy"))
			difficulty=5;
		if(dif.equals("Medium"))
			difficulty= 4;
		if(dif.equals("Hard"))
			difficulty= 3;
		if(dif.equals("Hardest"))
			difficulty= 2;
		if(dif.equals("Impossible"))
			difficulty= 1;
		return 0;

	}

	/**
	 * Provide user to choosing the game difficulty with clicking difficulty buttons
	 */
	public void chooseDifficulty()
	{
		double x = 0, y = 0;
		// Holds that difficulty is chose or not
		boolean difChoosed = false;
		// This loop proceed until player choose a difficulty
		while (difChoosed==false) 
		{
			if (StdDraw.isMousePressed()) // Checks if mouse pressed or not
			{ 
				x = StdDraw.mouseX(); // Get x coordinate of point
				y = StdDraw.mouseY(); // Get y coordinate of point

				// If blocks checks the which button is pressed, if user pressed a button sets difficulty
				// of the game depending on clicked button.
				if(x<=0+halfWidth && x>=0-halfWidth && y<=Buttony+Buttony/1.2+halfHeight && y>=Buttony+Buttony/1.2-halfHeight)
				{
					difChoosed=true;
					setBotDifficulty("Easy");
				}
				if(x<=-0+halfWidth && x>=-0-halfWidth && y<=Buttony/1.2+halfHeight && y>=Buttony/1.2-halfHeight)
				{
					difChoosed=true;
					setBotDifficulty("Medium");
				}
				if(x<=-0+halfWidth && x>=-0-halfWidth && y<=0+halfHeight && y>=0-halfHeight)
				{
					difChoosed=true;
					setBotDifficulty("Hard");
				}
				if(x<=-0+halfWidth && x>=-0-halfWidth && y<=-Buttony+halfHeight && y>=-Buttony-halfHeight)
				{
					difChoosed=true;
					setBotDifficulty("Hardest");
				}				
				if(x<=-0+halfWidth && x>=-0-halfWidth && y<=-2*Buttony+halfHeight && y>=-2*Buttony-halfHeight)
				{
					difChoosed=true;
					setBotDifficulty("Impossible");
				}
			}
		}
		// Calls a sound effect after user click a button
		SoundCall sound = new SoundCall();
		sound.playSound("ButtonEffect");
		Game_Board.waitTime(500);
	}
	/**
	 * Method allows the user to select a game mode
	 */
	public void clickEvent()
	{
		double x = 0, y = 0;
		// Holds that mode is chose or not
		boolean modChoosed = false;
		// This loop proceed until player choose a gamemode
		while (modChoosed==false) 
		{
			if (StdDraw.isMousePressed()) // Checks if mouse pressed or not
			{ 
				x = StdDraw.mouseX(); // Get x coordinate of point
				y = StdDraw.mouseY(); // Get y coordinate of point

				// If blocks checks the which button is pressed, if user pressed a button sets gamemode
				// of the game depending on clicked button.
				if(x<=Buttonx+halfWidth && x>=Buttonx-halfWidth && y<=Buttony+halfHeight && y>=Buttony-halfHeight)
				{
					modChoosed=true;
					setGameMode("SinglePlayer");
				}
				if(x<=-Buttonx+halfWidth && x>=-Buttonx-halfWidth && y<=Buttony+halfHeight && y>=Buttony-halfHeight)
				{
					modChoosed=true;
					setGameMode("MultiPlayer");
				}
			}
		}
		// Calls a sound effect after user click a button
		SoundCall sound = new SoundCall();
		sound.playSound("ButtonEffect");
		Game_Board.waitTime(200);
	}
	/**
	 * Shows player the guideline of game
	 */
	public static void guideLine()
	{
		// Guide texts and images
		StdDraw.picture(0.25, -0.30, "pictures/keyboard_key_up.PNG",0.2,0.2);
		StdDraw.picture(0.25, -0.5, "pictures/keyboard_key_down.PNG",0.2,0.2);
		StdDraw.picture(-0.25, -0.30, "pictures/keyboard_key_w.PNG",0.2,0.2);
		StdDraw.picture(-0.25, -0.5, "pictures/keyboard_key_s.PNG",0.2,0.2);
		StdDraw.picture(0, -0.7, "pictures/keyboard_key_empty.PNG",0.8,0.2);

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont( new Font("Helvetica", Font.BOLD, 60) );
		StdDraw.text(0,-0.7, "Pause");
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.setFont( new Font("Helvetica", Font.BOLD, 40) );
		StdDraw.text(0,0.87, "PONG BALL GAME");
		StdDraw.text(0,-0.90, "The side that scores 5 points wins");
	}
	/**
	 * Main method of game
	 * @param args
	 */
	public static void main(String[] args) {
		boolean gameLoaded = false;

		// Creates window of the game.
		StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);
		StdDraw.enableDoubleBuffering();
		StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);

		// Loop of the main program.
		while(true)
		{
			Game_Menu menu = new Game_Menu(); // Game_Menu object
			// Game waits 6000 ms every time a game finish to transfer
			// player to main menu of game.
			if(gameLoaded==true)
			{
				Game_Board.waitTime(6000);
			}
			menu.gameModes(); // Draws buttons and gamemodes
			menu.clickEvent(); // Waiting user to choose a gamemode
			String mode = getGameMode(); // sets gamemode

			Game_Board.waitTime(100);

			if(mode.equals("MultiPlayer"))  // Starts the multiplayer game
			{
				while(Game_Score.gameOver()==false) // Game continiues after each score until game finishes
				{
					Pong_Ball_Game.mainGameLoop(); // Calls mainGameLoop
				}
			}

			if(mode.equals("SinglePlayer")) // Starts the singleplayer game
			{ 
				// If player chose singleplayer program calls following methods.
				menu.difficultyList(); // Lists and draws difficulty buttons
				menu.chooseDifficulty(); // Let user to choose a difficulty
				while(Game_Score.gameOver()==false) // Game continiues after each score until game finishes
				{
					// Creates a bot object with determined difficulty
					Game_Bot Bot = new Game_Bot((getDifficulty()));
					// Calls mainGameLoop with Bot parameter
					Pong_Ball_Game.mainGameLoop(Bot);
				}
			}
			// The text which appears while player transfering to main menu
			StdDraw.setFont( new Font("Helvetica", Font.BOLD, 40) );
			StdDraw.text(0,-0.5, "You will be in MainMenu in 6 seconds");
			StdDraw.show();
			// Variable changes to true after player transfered the menu
			gameLoaded=true;
		}
	}
}
