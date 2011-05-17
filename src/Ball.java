import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
/**
 * Denna klass håller koll på bollen lite, 
 */
public class Ball extends Entity {

	public Ball(int x, int y) {
		super(x, y);
<<<<<<< HEAD
		super.setShape(new Ellipse2D.Double(x, y, 10, 10));
=======
		weight = 10;
		// Röd boll!
		super.setColor(Color.RED);
>>>>>>> c1d5e1f02806d2a02f60557914ef506dc8ecd5ec
	}
	/**
	 * Vi vill att bollen skall falla konstant
	 */
	public void poll(){
		speedY += 9.82;
		super.poll();
	}
	
	public Shape getShape(){
		return new Ellipse2D.Double(xkord, ykord, 20, 20);
	}
}
