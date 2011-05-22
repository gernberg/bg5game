import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Denna klass håller koll på bollen lite, 
 */
public class Ball extends Entity {
	private BufferedImage itemImage;
	
	public Ball(int x, int y) {
		super(x, y);
		setWeight(10);
		// Röd boll!
		super.setColor(Color.RED);
	}
	
	public void HejGustav(){
		System.out.println("FUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU!!!!");
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
	
	public Image getPicture(){
		try {
    		itemImage = ImageIO.read(new File("images/Studsboll_guld.png"));
    		} catch (IOException ex) {
    			//Lol
    		}
		return itemImage;
	}
	
	public Ball clone(){
		return this.clone();
	}
}
