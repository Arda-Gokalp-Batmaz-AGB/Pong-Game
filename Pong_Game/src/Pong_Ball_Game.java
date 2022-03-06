import java.awt.Color;
import java.util.Random;
/**
 * Modified modern version of popular arcade game Pong
 * Which has sound effects, better graphics and physics.
 * @author Arda Gokalp Batmaz
 * @since Date:24.03.2021
 */
public class  Pong_Ball_Game  {
	// Checks if game is finished or not
	public static boolean gameIsRunning = true;
	private static Random rGen = new Random(); 
	// Determines the ball's first direction
	private static int Side = rGen.nextInt(2);

	/**
	 * Main loop of the game to running the game for a specified amount of time
	 */
	public static void mainGameLoop()
	{
		// Creates new game and score object
		Game_Board game = new Game_Board();
		Game_Score score = new Game_Score();
		// Creates new ball object
		Ball gameBall = new Ball();
		game.countingBeforeStart(); // Game countdown before starting every time

		determineSide(gameBall);// Determines the initial direction of ball

		// Game's loop, if one of the players take a score its terminates after that
		// a new loop starts until one of the players reach 5 points
		while (gameIsRunning=true) 
		{
			// When one of the players get a score following statement runs
			if (Math.abs(gameBall.getRx() + gameBall.getVx()) > 1.0 - gameBall.getRadius()) 
			{
				StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
				score.gameRule(gameBall.getRx(),gameBall.getVx(),gameBall.getRadius());
				gameIsRunning=false;
				break;
			}
			gameBall.ballHit(gameBall,game); // Checks if ball hit or not

		}
	}
	/**
	 * Main loop of the game to running the game for a specified amount of time
	 * @param bot
	 */
	public static void mainGameLoop(Game_Bot bot)
	{
		// Creates new game and score object
		Game_Board game = new Game_Board(bot);
		Game_Score score = new Game_Score();
		//SoundCall sound = new SoundCall();
		Ball gameBall = new Ball();
		game.countingBeforeStart(); // Game countdown before starting every time

		determineSide(gameBall); // Determines the initial direction of ball

		// Game's loop, if one of the players take a score its terminates after that
		// a new loop starts until one of the players reach 5 points
		while (gameIsRunning=true) {
			// When one of the players get a score following statement runs
			if (Math.abs(gameBall.getRx() + gameBall.getVx()) > 1.0 - gameBall.getRadius()) 
			{
				StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
				score.gameRule(gameBall.getRx(),gameBall.getVx(),gameBall.getRadius());
				gameIsRunning=false;
				break;
			}

			bot.callBall(gameBall); // Bot gets ball's direction to determine it's movement
			gameBall.ballHit(gameBall,game); // Checks if ball hit or not

		}
	}
	
	/**
	 * Determines initial side of the ball.
	 * @param gameBall Ball object
	 */
	private static void determineSide(Ball gameBall)
	{
		if(Side==0)
		{
			gameBall.ballStart(0);
			Side=1;
		}
		else
		{
			gameBall.ballStart(1);
			Side=0;
		}
	}
}