import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Image;


public class Wall extends Entity{
	
	//private final Image wallPicturte = getImage(getCodeBase(), "images/wall.png");
	
	public Wall(int x, int y) {
		super(x, y);
		super.setColor(Color.PINK);
	}
	
	@Override
	public Shape getShape() {
		return new Rectangle(getX(), getY(), 20, 6000);
	}
	
//	public Image getPicture(){
//		return wallPicturte;
//	}

	@Override
	public void poll() {
		// Ingen förflyttning
	}
	
	

}
