
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * This class controls the drawings on the board,
 * checks player's keyboard inputs and depending on game's
 * status updates game's board.
 * @author Arda Gokalp Batmaz
 * @since Date:12.04.2021
 *
 */
public class Game_Board {

	// Creates object reference variables which is going to hold
	// bot, player, ball's informations
	Game_Bot bot;
	private double[][] players;
	private double[] ballLastLoc;

	/**
	 * Returns players information
	 * @return player's information
	 */
	public double[][] getPlayers() {
		return players;
	}

	/**
	 * Default Constructor for creating a new Game_Board object
	 */
	public Game_Board()
	{
		// Calls createPlayers method to initialize padel's with, height and coordinates
		createPlayers();
		ballLastLoc = new double[3]; // Initializes ball's attributes
	}

	/**
	 * If game is a singleplayer game, this constructor used for create a computer bot.
	 * @param bot A bot object which has a pre determined difficulity and attributes.
	 */
	public Game_Board(Game_Bot bot)
	{
		this(); // Calls default instructor to initialize padel's attributes
		if(Game_Menu.getGameMode()=="SinglePlayer") // If game is singleplayer initializes bot variable
		{
			this.bot = bot;
		}
	}

	/**
	 * Method Creates two padels with specified with, height, coordinates
	 * and stores them in a 2d double array.
	 * @return a 2d array contains values of padels
	 */
	private double[][] createPlayers()
	{
		// Pre determined coordinates and sizes
		double x = 0.85;
		double y = 0;
		double rectHalfSize = 0.1;
		// A 1d array for each padel
		double[] player1 = new double[3];
		double[] player2 = new double[3];
		// Fills the slots of padels in following structure:
		// First element x, second y , third recthalfsize
		player1[0]=x;
		player1[1]=y;
		player1[2]=rectHalfSize;
		player2[0]=x*-1;
		player2[1]=y;
		player2[2]=rectHalfSize;
		players = new double[2][2];
		players[0]=player1;
		players[1]=player2;

		return players;
	}

	/**
	 * Updates the ball's x location
	 * @param x new x value of ball
	 * @param y new y value of ball
	 * @param R new R value of ball
	 */
	public void updateBallLoc(double x, double y, double R)
	{
		ballLastLoc[0]=x;
		ballLastLoc[1]=y;
		ballLastLoc[2]=R;
	}

	/**
	 * Draws the background of the game board.
	 */
	public static void texture()
	{
		StdDraw.picture(0, 0, "pictures/pong_Back.PNG",2,2);
	}

	/**
	 * Controlls all of the graphics of the game,
	 * updates all drawings of board.
	 * @param currentColor Current color of ball and padels
	 * @param ballx Ball's x coordinate
	 * @param bally Ball's y coordinate
	 * @param ballR Ball's R value
	 * @param gamemode Specifies Game is Singleplayer or Multiplayer
	 */
	public void canvas(Color currentColor,double ballx,double bally,double ballR,String gamemode)
	{
		// Clears the board
		StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
		// Calls keyboardController method to get keyboard input from the user.
		keyboardController(gamemode,ballx,bally,ballR);
		texture(); // Updates background

		updateBallLoc(ballx,bally,ballR); // Updates locaton of ball
		Game_Score.updateScoreTable(); // Updates scores of players
		StdDraw.setPenColor(currentColor); // Updates color
		StdDraw.filledCircle(ballx, bally, ballR); // Draws the ball
		// Draws padels
		StdDraw.filledRectangle(players[0][0], players[0][1], players[0][2]/5, players[0][2]);
		StdDraw.filledRectangle(players[1][0], players[1][1], players[1][2]/5, players[1][2]);

		StdDraw.show(); // Show the drawings
		StdDraw.pause(30); // Pause for 30 ms
	}

	/**
	 * Takes user's keyboard input and depending on input updates padel's location
	 * If game is singleplayer updates bot's location.
	 * @param gamemode Single or multi player
	 * @param ballx X value of ball
	 * @param bally Y value of ball
	 * @param ballR R value of ball
	 */
	private void keyboardController(String gamemode,double ballx,double bally,double ballR) 
	{
		int keyboardPauseDuration = 0;// No duration between keyboard clicks

		// If upper arrow clicked the right padel's y value increases
		if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && players[0][1]+1.8*players[0][2]<1) {
			// Move up
			StdDraw.pause(keyboardPauseDuration);
			players[0][1] = players[0][1] + players[0][2]/2.0;
		} 
		// If down arrow clicked the right padel's y value decreases
		if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && players[0][1]-1.8*players[0][2]>-1) {
			// Move down
			StdDraw.pause(keyboardPauseDuration);
			players[0][1] = players[0][1] - 1*players[0][2]/2.0;
		}
		// If game is multiplayer second player can play with W and S buttons
		if(gamemode.equalsIgnoreCase("MultiPlayer"))
		{
			// If W clicked the left padel's y value increases
			if (StdDraw.isKeyPressed(KeyEvent.VK_W) && players[1][1]+1.8*players[1][2]<1) {
				// Move up
				StdDraw.pause(keyboardPauseDuration);
				players[1][1] = players[1][1] + 1*players[1][2]/2.0;
			} 
			// If S clicked the left padel's y value decreases
			if (StdDraw.isKeyPressed(KeyEvent.VK_S) && players[1][1]-1.8*players[1][2]>-1) {
				// Move down
				StdDraw.pause(keyboardPauseDuration);
				players[1][1] = players[1][1] - 1*players[1][2]/2.0;
			}
		}
		// If game is singleplayer, allows bot to control left padel
		if(gamemode.equalsIgnoreCase("SinglePlayer") && bot!=null)
		{
			players[1]=bot.botMovement(players[1][0],players[1][1],players[1][2],ballx,bally,ballR);;

			//players[1][1] = players[1][1] + 1*players[1][2]/2.0;
			//players[1][1] = players[1][1] - 1*players[1][2]/2.0;
		}
		// Game pauses with clicking space button
		if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
		{
			StdDraw.pause(10);
			waitTime(100);
			pause_Game();
		}
	}

	/**
	 * Game countdown before every new game starts
	 */
	public void countingBeforeStart()
	{
		// Calls sound effect
		SoundCall sound = new SoundCall();
		sound.playSound("CountDownEffect");

		// Counts from 3 to 0
		for(int i = 3; 0<i ; i--)
		{
			// Calls canvas and updates game board
			canvas(StdDraw.RED, 0, 0, 0,Game_Menu.getGameMode());
			Game_Board.writingColor();
			StdDraw.text(0, 0, String.valueOf(i));
			StdDraw.show();
			waitTime(750);
		}
		// After 0, updates game board and game starts
		canvas(StdDraw.RED, 0, 0, 0,Game_Menu.getGameMode());
		StdDraw.text(0, 0, "GO");
		StdDraw.show();

		waitTime(1000);

	}

	/**
	 * This method stops the code for determined miliseconds
	 * @param ms Time in miliseconds
	 */
	public static void waitTime(int ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Pauses the game
	 */
	public void pause_Game()
	{
		// Updates canvas
		canvas(StdDraw.RED, ballLastLoc[0], ballLastLoc[1], ballLastLoc[2],Game_Menu.getGameMode());
		StdDraw.text(0, 0, "Game Paused");
		StdDraw.show();
		// Pause loop
		while(true)
		{
			if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) 
			{
				StdDraw.pause(50);
				break;
			}
		}

	}

	/**
	 * Color of and font of standard texts.
	 */
	public static void writingColor()
	{
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setFont( new Font("Helvetica", Font.BOLD, 80) );
	}

}
