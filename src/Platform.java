import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Plattformen är den som användaren styr. Tar emot order från UserController
 * och försöker lyda så bra som möjligt
 */
public class Platform extends Entity {
	private double acceleratorX = 10;
	private double acceleratorY = 20;
	private double movingX = 0;
	private double movingY = 0;
	public boolean isStroboPop = false;

	public Platform(int x, int y) {
		super(x, y);
		super.setColor(Color.CYAN);
	}

	/**
	 * Flyttar plattformen uppåt
	 * 
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveUp() {
		moveUp(true);
	}

	/**
	 * Flyttar plattformen åt höger
	 * 
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveDown() {
		moveDown(true);
	}

	/**
	 * Flyttar plattformen nedåt
	 * 
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveRight() {
		moveRight(true);
	}

	/**
	 * Flyttar plattformen åt vänster
	 * 
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveLeft() {
		moveLeft(true);
	}

	public void moveUp(boolean b) {
		if (b)
			movingY = -1;
		else
			movingY = 0;
	}

	public void moveDown(boolean b) {
		if (b)
			movingY = 1;
		else
			movingY = 0;
	}

	public void moveLeft(boolean b) {
		if (b)
			movingX = -1;
		else
			movingX = 0;
	}

	public void moveRight(boolean b) {
		if (b)
			movingX = 1;
		else
			movingX = 0;
	}

	@Override
	public Shape getShape() {
		return new Rectangle(xkord, ykord, 100, 10);
	}

	public void poll() {
		speedX += acceleratorX * movingX;
		speedY += acceleratorY * movingY + 9.82;
		super.poll();
	}
}
