
import java.awt.Color;
import java.awt.Rectangle;

public class Platform extends Entity {

	public Platform(int x, int y) {
		super(x, y);
		super.setShape(new Rectangle.Double(x, y, 100, 10));
		super.setColor(Color.CYAN);
	}

}
