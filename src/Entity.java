import java.awt.Shape;

public abstract class Entity {

	private int xkord, ykord;
	private double speed;
	private Shape shape;

	public Entity(int x, int y) {
		xkord = x;
		ykord = y;
	}

	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRotationCenterX() {
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


}
