import java.awt.Color;
import java.awt.Shape;
/**
 * Denna klass är grundklassen för alla typer av objekt som syns på skärmen.
 * För att skapa ett nytt objekt som visas på skärmen ärver man alltså denna klass.
 */
public abstract class Entity {

	private int xkord, ykord;
	private double speed;
	private Shape shape;
	private Color color;
	
	public Entity(int x, int y) {
		xkord = x;
		ykord = y;
	}

	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return xkord;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return ykord;
	}

	public double getSpeed() {
		return speed;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}

}
