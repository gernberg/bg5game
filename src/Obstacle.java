import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Fallande hinder jao.
 * 
 * @todo Slumpa form? slumpa pos bättre?
 *
 */
public class Obstacle extends Entity {

	public Obstacle(int x, int y) {
		super(x, y);
		super.setColor(Color.YELLOW);
		setWeight(Integer.MAX_VALUE);
		speedY = 100;
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(getX(), getY(), 50, 50);
//		return new Rectangle(getX(), getY(), 40, 40);
	}
	
	/**
	 * Flyttar till ett slumpat x och ett slumpat y, som är lite ovanför skärmen.
	 */
	public void resetPosition() {
		setX((int) (Math.random()*Window.WINDOW_WIDTH));
		setY(-10 * (int) (Math.random()*10));
	}

	/**
	 * Resettar y-positionen om utanför skärmen och uppdaterar positionen.
	 */
	@Override
	public void poll() {
		if (getY() > Window.WINDOW_HEIGHT) {
			resetPosition();
		}
		super.poll();
	}

	public void revertPosition(){
		// Gör igenting, Obstacles ångrar aldrig vart dom varit!
	}
	public void setSpeedX(double speedX){
		// Gör igenting, Obstacles har aldrig någon hastighet!
	}
	public void setSpeedY(double speedY){
		// Gör igenting, Obstacles har aldrig någon hastighet!
	}
	

}
