import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.security.spec.EllipticCurve;

/**
 * Plattformen är den som användaren styr. Tar emot order från UserController
 * och försöker lyda så bra som möjligt
 */
public class Platform extends Entity {
	private double acceleratorX = 20;
	private double acceleratorY = 20;
	private double movingX = 0;
	private double movingY = 0;
	public boolean isStroboPop = false;

	public Platform(int x, int y) {
		super(x, y);
		setWeight(50);
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
		return new Ellipse2D.Double(getX(), getY(), 100, 100);
	}

	public void poll() {
		speedX += acceleratorX * movingX;
		speedY += acceleratorY * movingY + 9.82;
		super.poll();
	}

	public Platform clone(){
		return this.clone();
	}
}
