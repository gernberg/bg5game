import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
/**
 * Denna klass håller koll på bollen lite, 
 */
public class Ball extends Entity {

	public Ball(int x, int y) {
		super(x, y);
		
		// Röd boll!
		super.setColor(Color.RED);
	}
	/**
	 * Vi vill att bollen skall falla konstant
	 */
	public void poll(){
		speedY += 9.82;
		super.poll();
	}
	
	public Shape getShape(){
		return new Ellipse2D.Double(xkord, ykord, 10, 10);
	}
}
