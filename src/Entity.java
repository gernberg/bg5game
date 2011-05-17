import java.awt.Color;
import java.awt.Shape;

/**
 * Denna klass är grundklassen för alla typer av objekt som syns på skärmen.
 * För att skapa ett nytt objekt som visas på skärmen ärver man alltså
 * denna klass.
 */
public abstract class Entity {
<<<<<<< HEAD

	private int xkord, ykord;
	private double speed;
	private Shape shape;
=======
	protected int xkord, ykord;
	public double speedX = 0;
	public double speedY = 0;
	private Color color;
	protected int weight = 10;
>>>>>>> c1d5e1f02806d2a02f60557914ef506dc8ecd5ec

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

	public double getSpeedX() {
		return speedX;
	}

<<<<<<< HEAD
	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape form) {
		this.shape = form;
=======
	public double getSpeedY() {
		return speedY;
	}

	public abstract Shape getShape();

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
>>>>>>> c1d5e1f02806d2a02f60557914ef506dc8ecd5ec
	}

	/**
	 * Denna metod skall kallas på en gång per "spelvarv"
	 */
	public void poll() {
		ykord += Math.round(speedY / 50);
		xkord += Math.round(speedX / 50);
	}

	public int getWeight() {
		return weight;
	}
}
