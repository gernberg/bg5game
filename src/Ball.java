import java.awt.Color;
import java.awt.geom.Ellipse2D;
/**
 * Denna klass håller koll på bollen lite, 
 */
public class Ball extends Entity {

	public Ball(int x, int y) {
		super(x, y);
		
		// Vi bestämmer vilken form bollen ska ha, en ellipse känns rätt logiskt.
		super.setShape(new Ellipse2D.Double(x, y, 10, 10));
		
		// Röd boll!
		super.setColor(Color.RED);
	}
}
