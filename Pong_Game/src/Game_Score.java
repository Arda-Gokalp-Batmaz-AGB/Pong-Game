import java.awt.Font;

/**
 * Updates score board of the game, checks the points of the players
 * and finishes the game if necessary conditions provided.
 * @author Arda Gokalp Batmaz
 * @since Date:12.04.2021
 */
public class Game_Score {

	// Initial scores of players
	private static int player1Score=0;
	private static int player2Score=0;
	// Necessary score to win the game
	private static int winScore=5;
	/**
	 * Default constructor
	 */
	public Game_Score()
	{

	}
	/**
	 * Redraws the scoreboard depending on players scores.
	 */
	public static void updateScoreTable()
	{
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.setFont( new Font("Helvetica", Font.BOLD, 80) );
		StdDraw.text(-0.1, 0.87, String.valueOf(player1Score));
		StdDraw.text(0.1, 0.87, String.valueOf(player2Score));
	}
	/**
	 * Method checks if one of the players take a score
	 * and adds a secore.
	 * @param rx ball's x coordinate
	 * @param vx ball's y coordinate
	 * @param radius ball's radius
	 */
	public void gameRule(double rx, double vx,double radius)
	{
		SoundCall sound = new SoundCall();
		Game_Board game = new Game_Board();
		// Updates board
		game.canvas(StdDraw.RED, 0, 0, 0,Game_Menu.getGameMode());
		StdDraw.text(0, 0, "SCORE!!!");
		StdDraw.show();
		// Plays sound effect
		sound.playSound("ScoreEffect");

		Game_Board.waitTime(1500);
		// If blocks check if one of the players take a score
		if ((rx + vx) > 1.0 - radius) // If ball is on the edge of right corner first player get a score
		{
			player1Score++;
		}
		else // If ball is on the edge of left corner second player get a score
		{
			player2Score++;
		}

	}
	/**
	 * Method checks player's scores and when one of the side is reach 5 score
	 * game finishes and players transferring to main menu
	 * @return game is over or not
	 */
	public static boolean gameOver()
	{
		Game_Board game = new Game_Board();
		SoundCall sound = new SoundCall();
		// If game is singleplayer and bot's score is 5 bot wins
		if(player1Score==winScore && Game_Menu.getGameMode().equals("SinglePlayer"))
		{
			game.canvas(StdDraw.RED, 0, 0, 0,Game_Menu.getGameMode());
			pcIcon();
			StdDraw.text(0, 0, "Computer Wins");
			StdDraw.show();
			sound.playSound("WinSound");
			player1Score=0;
			player2Score=0;

			return true;
		}
		// If game is singleplayer and player's score is 5 player wins
		if(player2Score==winScore && Game_Menu.getGameMode().equals("SinglePlayer"))
		{
			game.canvas(StdDraw.RED, 0, 0, 0,Game_Menu.getGameMode());
			winnerCup();
			//Game_Board.writingColor();
			StdDraw.text(0, 0, "You Win!");
			StdDraw.show();
			sound.playSound("WinSound");
			player1Score=0;
			player2Score=0;

			return true;
		}
		// If game is multiplayer and player1's score is 5 player1 wins
		if(player1Score==winScore)
		{
			game.canvas(StdDraw.RED, 0, 0, 0,Game_Menu.getGameMode());
			winnerCup();
			//Game_Board.writingColor();
			StdDraw.text(0, 0, "Player 1 Wins");
			StdDraw.show();
			sound.playSound("WinSound");
			player1Score=0;
			player2Score=0;

			return true;
		}
		// If game is multiplayer and player2's score is 5 player2 wins
		if(player2Score==winScore)
		{
			game.canvas(StdDraw.RED, 0, 0, 0,Game_Menu.getGameMode());
			winnerCup();
			//Game_Board.writingColor();
			StdDraw.text(0, 0, "Player 2 Wins");
			StdDraw.show();
			sound.playSound("WinSound");;
			player1Score=0;
			player2Score=0;

			return true;
		}

		return false;
	}
	/**
	 * Picture of winner cup
	 */
	public static void winnerCup()
	{
		StdDraw.picture(0, 0, "pictures/winnerCup.png",1,1);
	}
	/**
	 * Picture of bot's icon
	 */
	public static void pcIcon()
	{
		StdDraw.picture(0, 0, "pictures/pcIcon.png",1,1);
	}

}
