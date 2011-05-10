import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Ball extends Entity {

	public Ball(int x, int y) {
		super(x, y);
		super.setShape(new Ellipse2D.Double(x, y, 10, 10));
		super.setColor(Color.RED);
	}
}
