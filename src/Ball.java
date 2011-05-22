import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
/**
 * Denna klass håller koll på bollen lite, 
 */
public class Ball extends Entity {
	public Ball(int x, int y) {
		super(x, y);
		setWeight(10);
		// Röd boll!
		super.setColor(Color.RED);
	}
	/**
	 * Vi vill att bollen skall falla konstant
	 */
	public void poll(){
		speedY += 9.82;
		// Om bollen tappas
		if(getY()>Window.WINDOW_HEIGHT){
			// Ändra poängen
			Score.ballDropped();
			setY(0);
			setX(200);
			speedY = 0;
			speedX = 0;
		}
		super.poll();
	}
	
	public Shape getShape(){
		return new Ellipse2D.Double(getX(), getY(), 20, 20);
	}
	public Ball clone(){
		return this.clone();
	}
}
