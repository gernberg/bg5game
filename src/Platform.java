
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
/**
 * Plattformen är den som användaren styr.
 * Tar emot order från UserController och försöker
 * lyda så bra som möjligt
 */
public class Platform extends Entity {
	private int accelerator = 2;
	public Platform(int x, int y) {
		super(x, y);
		super.setColor(Color.CYAN);
	}
	/**
	 * Flyttar plattformen uppåt
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveUp(){
		ykord-=accelerator;
	}
	/**
	 * Flyttar plattformen åt höger
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveDown(){
		ykord+=accelerator;
	}
	/**
	 * Flyttar plattformen nedåt
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveRight(){
		xkord+=accelerator;
	}
	/**
	 * Flyttar plattformen åt vänster
	 * @todo här kan man lägga in logik för ev. jetmotorer och bensin
	 */
	public void moveLeft(){
		xkord-=accelerator;
	}
	@Override
	public Shape getShape() {
		return new Rectangle(xkord, ykord, 100, 10);
	}
	public void poll(){
//		speedY+=1;
		System.out.println(speedY);
		super.poll();
	}
}
