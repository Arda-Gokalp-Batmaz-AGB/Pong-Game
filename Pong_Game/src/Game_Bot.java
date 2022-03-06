import java.util.Random;

/**
 * This class provides a bot opponent which has a determined
 * difficulity and has some methods to move bot's padel.
 * @author Arda Gokalp Batmaz
 * @since Date:12.04.2021
 *
 */
public class Game_Bot {
	// Data fields which holds ball object and difficulity
	private static Ball gameBall;
	private double difficulity;

	/**
	 * Creates a bot object which has a difficulity
	 * @param difficulity determined difficulity 1 to 5
	 */
	public Game_Bot(double difficulity)
	{
		this.difficulity=difficulity;
	}

	/**
	 * Calls the ball to use it in bot movement
	 * @param gb Ball object
	 */
	public void callBall(Ball gb)
	{
		gameBall=gb;
	}

	/**
	 * Takes 6 paremeters and using gameBall object to determine bot's movement
	 * When difficulity increases bot's movements are becomes faster.
	 * @param x x coordinate of bot
	 * @param y y coordinate of bot
	 * @param h height of bot
	 * @param ballx x coordinate of ball
	 * @param bally y coordinate of ball
	 * @param ballR R coordinate of ball
	 * @return bot's new coordinates
	 */
	public double[] botMovement(double x , double y, double h,double ballx,double bally,double ballR)
	{
		// Holds bot's new coordinates
		double[] temp = new double[3];
		temp[0]=x;
		temp[1]=y;
		temp[2]=h;

		// These if statements checks ball's location and depending on location changes position of bot.
		if(y<bally && gameBall.getVx()<0 && !(gameBall.getRy() + gameBall.getVy()  - gameBall.getRadius()< temp[1] + temp[2] && gameBall.getRy() + gameBall.getVy() + gameBall.getRadius()  > temp[1] - temp[2]))
		{
			temp[1]=y+temp[2]/(difficulity/1.5);
		}
		else if(y>bally && gameBall.getVx()<0 && !(gameBall.getRy() + gameBall.getVy()  - gameBall.getRadius()< temp[1] + temp[2] && gameBall.getRy() + gameBall.getVy() + gameBall.getRadius()  > temp[1] - temp[2]))
		{
			temp[1]=y-temp[2]/(difficulity/1.5);
		}
		return temp;
	}
	/**
	 * Returns bot's difficulity (1 to 5)
	 * @return difficulity of bot
	 */
	public double getDifficulity() {
		return difficulity;
	}
}
