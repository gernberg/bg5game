import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

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
		speedY = 100;
	}

	@Override
	public Shape getShape() {
		return new Rectangle(xkord, ykord, 40, 40);
	}
	
	/**
	 * Flyttar till ett slumpat x och ett slumpat y, som är lite ovanför skärmen.
	 */
	public void resetPosition() {
		xkord = (int) (Math.random()*Window.WINDOW_WIDTH);
		ykord = -10 * (int) (Math.random()*10);
	}

	/**
	 * Resettar y-positionen om utanför skärmen och uppdaterar positionen.
	 */
	@Override
	public void poll() {
		if (ykord > Window.WINDOW_HEIGHT) {
			resetPosition();
		}
		super.poll();
	}
	
	

}
