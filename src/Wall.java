import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;


public class Wall extends Entity {

	public Wall(int x, int y) {
		super(x, y);
		super.setColor(Color.PINK);
	}

	@Override
	public Shape getShape() {
		return new Rectangle(xkord, ykord, 20, 6000);
	}

	@Override
	public void poll() {
		// Ingen förflyttning
	}
	
	

}
