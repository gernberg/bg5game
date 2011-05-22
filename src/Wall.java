import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Wall extends Entity{
	private BufferedImage itemImage;
	
	public Wall(int x, int y) {
		super(x, y);
		super.setColor(Color.PINK);
	}
	@Override
	public Shape getShape() {
		return new Rectangle(getIntX(), getIntY(), 20, Window.WINDOW_HEIGHT);
	}
	
	public Image getPicture(){
		try {
    		itemImage = ImageIO.read(new File("images/wall.png"));
    		} catch (IOException ex) {
    			//Lol
    		}
		return itemImage;
	}

	@Override
	public void poll() {
		// Ingen förflyttning
	}
	
	

}
