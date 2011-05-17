import java.awt.Color;
import java.awt.Shape;
/**
 * Denna klass är grundklassen för alla typer av objekt som syns på skärmen.
 * För att skapa ett nytt objekt som visas på skärmen ärver man alltså denna klass.
 */
public abstract class Entity {
	protected int xkord, ykord;
	protected double speedX = 0;
	protected double speedY = 0;
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

	public double getSpeedX() {
		return speedX;
	}
	public double getSpeedY() {
		return speedY;
	}


	public abstract Shape getShape();


	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	/**
	 * Denna metod skall kallas på en gång per "spelvarv"
	 */
	public void poll(){
		ykord += Math.round(speedY/50);
		xkord += Math.round(speedX/50);

	}

}
