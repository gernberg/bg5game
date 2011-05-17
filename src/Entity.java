import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Denna klass är grundklassen för alla typer av objekt som syns på skärmen.
 * För att skapa ett nytt objekt som visas på skärmen ärver man alltså
 * denna klass.
 */
public abstract class Entity {
	private int xkord, ykord, oldx, oldy;
	public double speedX = 0;
	public double speedY = 0;
	private Color color;
	protected int weight = 10;

	public Entity(int x, int y) {
		setX(x);
		setY(y);
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

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Denna metod skall kallas på en gång per "spelvarv"
	 */
	public void poll() {
		setY(getY() + Math.round(speedY / 50));
		setX(getX() + Math.round(speedX / 50));
	}

	private void setX(long l) {
		setX(Math.round(l));
	}

	private void setY(long l) {
		setY(Math.round(l));
	}

	public int getWeight() {
		return weight;
	}
	public int getCenterX(){
		return (int) (getX() + getShape().getBounds().getWidth()/2);
	}
	public int getCenterY(){
		return (int) (getY() + getShape().getBounds().getWidth()/2);
	}
	public void setX(int x){
		oldx = xkord;
		xkord = x;
	}
	public void setY(int y){
		oldy = ykord;
		ykord = y;
	}

	public void revertPosition() {
		xkord = oldx;
		ykord = oldy;
	}
	/**
	 * Flyttar tillbaka och kontrollerar att man inte krockar längre.
	 */
	public void revertPosition(Rectangle2D rectangle) {
		double tmpoldx = oldx;
		double tmpoldy = oldy;
		revertPosition();
		if(getShape().intersects(rectangle)){
			// Om dom fortfarande krockar - skrik till terminalen!
			System.out.println("SKRIIIK!");
		}
	}
}
