import java.awt.Color;
import java.util.Random;

/**
 * A class which holds the properties of the ball, checks ball's velocity,
 * coordinates, if ball hit the wall and padel or not and has some methods to
 * controll ball.
 * @author Arda Gokalp Batmaz
 * @since Date:12.04.2021
 *
 */

public class Ball {
	// Data fields which holds ball's attributes
	private Color current_Color;
	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private double radius;
	//private boolean firstTime=true;
	private static Random rGen = new Random(); 
	// Creates a ball object
	public Ball()
	{
		rx = 0;
		ry = 0.860;
		vx = 0.025;
		vy = 0.027;
		radius = 0.035;
		current_Color = StdDraw.RED;

	}
	/**
	 * Method provides the initial direction of the ball in the first time
	 * chooses a random direction, after that ball changes it's direction in a order
	 * every time a new game starts.
	 * @param Side A random side 0 stands for left and 1 stands for right
	 */
	public void ballStart(int Side)
	{
		// These if blocks changes the direction of ball in x direction
		if(Side==0)
		{
			vx = -Math.abs(vx);

		}
		if(Side==1)
		{
			vx = Math.abs(vx);

		}
		// These codes changes ball's initial y coordinates
		// So ball starts in a different y location in every different game
		double random = new Random().nextDouble();
		double miny = -ry;
		double maxy = ry;
		double result = miny + (random * (maxy - miny));
		ry=result;
	}

	/**
	 * Method checks if ball is hit a wall or padel. And if
	 * it's hit a wall or padel it's velocity increases and changes direction
	 * @param gameBall
	 * @param game
	 */
	public void ballHit(Ball gameBall,Game_Board game)
	{
		SoundCall sound = new SoundCall(); // Creates a sound object
		// Takes two random values in a interval to increase velocity every time ball hits a object.
		double rand1 = (rGen.nextInt((10 ) + 1) + 0)/700.0;
		double rand2 = (rGen.nextInt((10 ) + 1) + 0)/500.0;

		// Checks if ball hit the left padel or not
		if ((gameBall.getRx() + gameBall.getVx())> game.getPlayers()[0][0]-game.getPlayers()[0][2]/5 && gameBall.getRy() + gameBall.getVy()  - gameBall.getRadius()< game.getPlayers()[0][1] + game.getPlayers()[0][2] && gameBall.getRy() + gameBall.getVy() + gameBall.getRadius()  > game.getPlayers()[0][1] - game.getPlayers()[0][2])
		{//
			// Changes ball's direction and velocity depend on it's hitting angel and plays a sound effect.
			gameBall.setVx(-gameBall.getVx()-rand1);
			if(gameBall.getVy()>0)
				gameBall.setVy(gameBall.getVy()+rand2);
			if(gameBall.getVy()<0)
				gameBall.setVy(gameBall.getVy()-rand2);
			sound.playSound("HitEffect");

			current_Color = changeColor(); // updates ball's color with a random color
		}
		// Checks if ball hit the right padel or not
		if ((gameBall.getRx() + gameBall.getVx())< game.getPlayers()[1][0]+game.getPlayers()[1][2]/5 && gameBall.getRy() + gameBall.getVy()  - gameBall.getRadius()< game.getPlayers()[1][1] + game.getPlayers()[1][2] && gameBall.getRy() + gameBall.getVy() + gameBall.getRadius()  > game.getPlayers()[1][1] - game.getPlayers()[1][2]) 
		{
			// Changes ball's direction and velocity depend on it's hitting angel and plays a sound effect.
			gameBall.setVx(-gameBall.getVx()+rand1);
			if(gameBall.getVy()>0)
				gameBall.setVy(gameBall.getVy()+rand2);
			if(gameBall.getVy()<0)
				gameBall.setVy(gameBall.getVy()-rand2);
			sound.playSound("HitEffect");

			current_Color = changeColor();; // updates ball's color with a random color
		}

		// Checks if ball hit wall or not.
		if (Math.abs(gameBall.getRy() + gameBall.getVy()) > 1.0 - gameBall.getRadius())
		{
			// If it hit a wall changes it's y direction and increases y velocity and calls a sound effect.
			gameBall.setVy(-gameBall.getVy()+rand2);
			sound.playSound("WallHitEffect");

			current_Color = changeColor(); // updates ball's color with a random color
		}
		// Updates new coordinates of ball.
		gameBall.setRx(gameBall.getRx()+gameBall.getVx());
		gameBall.setRy(gameBall.getRy()+gameBall.getVy());

		// Calls canvas method and draws the new coordinates of every object.
		game.canvas(current_Color, gameBall.getRx(), gameBall.getRy(), gameBall.getRadius(),Game_Menu.getGameMode());

		// Clears the window
		StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);

	}

	/**
	 * Changes color of the padels and ball with a random color
	 * @return Random color
	 */
	public Color changeColor()
	{
		// Chooses a random color from the colors array
		Color[] colors = { StdDraw.RED, StdDraw.GREEN, StdDraw.BLUE, StdDraw.MAGENTA, StdDraw.ORANGE, StdDraw.PINK };
		int randomIndex = rGen.nextInt(colors.length);
		Color current_Color = colors[randomIndex];
		return current_Color;
	}
	/**
	 * Returns Ball's coordinate in x axis
	 * @return rx value of ball
	 */
	public double getRx() {
		return rx;
	}
	/**
	 * Changes ball's coordinate in x axis
	 * @param rx new rx value of ball
	 */
	public void setRx(double rx) {
		this.rx = rx;
	}
	/**
	 * Returns Ball's coordinate in y axis
	 * @return ry value of ball
	 */
	public double getRy() {
		return ry;
	}
	/**
	 * Changes ball's coordinate in y axis
	 * @param ry new ry value of ball
	 */
	public void setRy(double ry) {
		this.ry = ry;
	}
	/**
	 * Returns Ball's velocity in x axis
	 * @return vx velocity of ball
	 */
	public double getVx() {
		return vx;
	}
	/**
	 * Changes ball's velocity in x axis
	 * @param vx new x velocity of ball
	 */
	public void setVx(double vx) {
		this.vx = vx;
	}
	/**
	 * Returns Ball's velocity in y axis
	 * @return y velocity of ball
	 */
	public double getVy() {
		return vy;
	}
	/**
	 * Changes ball's velocity int y axis
	 * @param vy new y velocity of ball
	 */
	public void setVy(double vy) {
		this.vy = vy;
	}
	/**
	 * Returns Ball's radius
	 * @return radius value of ball
	 */
	public double getRadius() {
		return radius;
	}


}