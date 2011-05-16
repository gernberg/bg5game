
import java.awt.Color;
import java.awt.Rectangle;
/**
 * Plattformen är den som användaren styr.
 * Tar emot order från UserController och försöker
 * lyda så bra som möjligt
 */
public class Platform extends Entity {
	public Platform(int x, int y) {
		super(x, y);
		super.setShape(new Rectangle.Double(x, y, 100, 10));
		super.setColor(Color.CYAN);
	}
	/**
	 * Flyttar plattformen uppåt
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveUp(){}
	/**
	 * Flyttar plattformen nedåt
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveRight(){}
	/**
	 * Flyttar plattformen åt höger
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveDown(){}
	/**
	 * Flyttar plattformen åt vänster
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveLeft(){}
}
