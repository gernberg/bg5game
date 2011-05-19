import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Image;


public class Wall extends Entity{

	
	public Wall(int x, int y) {
		super(x, y);
		super.setColor(Color.PINK);
	}
	//private final Image wallPicturte = getImage(getCodeBase(), "images/wall.png");
	@Override
	public Shape getShape() {
		return new Rectangle(getIntX(), getIntY(), 20, 6000);
	}
	
//	public Image getPicture(){
//		return wallPicturte;
//	}

	@Override
	public void poll() {
		// Ingen förflyttning
	}
	
	

}
