<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.Set;
//import lithefarligt.entities.UserController;
//import lithefarligt.entities.Entity;

/**
 * Fönstret där allting visas
 * @author gustav
 */
public class Window extends JFrame {

    private static int WINDOW_WIDTH = 600;
    private static int WINDOW_HEIGHT = 600;
    private static int WORLD_WIDTH = 1280;
    private static int WORLD_HEIGHT = 1280;
    public int offsetY, offsetX;
    double i = 0;
    Color backgroundColor = Color.BLACK;
    BufferedImage buffer;
    Graphics2D b, bg2;
    Panel panel;

    /**
     * Ritar ut all grafik
     * @param objects De objekt som skall synas på skärmen
     */
    public void draw() {
        b = buffer.createGraphics();

        // Gör så att allt blir härligt smooth
//        b.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//        b.setRenderingHint(RenderingHints.KEY_RENDERING,
//                RenderingHints.VALUE_RENDER_QUALITY);

        // Fyll bakgrunden i hela spelet med bakgrundsfärgen
        b.setColor(backgroundColor);
        b.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

//        for (Entity foregroundObject : foregroundObjects) {
//            drawObject(foregroundObject, b);
//        }
//        for (Entity backgroundObject : backgroundObjects) {
//            drawObject(backgroundObject, b);
//        }
//        drawScore(points);
//        drawTextMessage(text);
        drawScreen();
    }



    private int getRelativeX(int x) {
        return x - offsetX + getWINDOW_WIDTH() / 2;
    }

    private int getRelativeY(int y) {
        return y - offsetY + getWINDOW_HEIGHT() / 2;
    }

    /**
     * Ritar en bild, med rotation
     * @param image Bilden som skall ritas ut
     * @param x x-position
     * @param y y-position
     * @param rotation Rotering (mätt i radianer)
     * @param rotationCenterX x-position för rotation, relativt bildens x position
     * @param rotationCenterY y-position för rotation, relativt bildens y position
     * @param b Graphics2D, buffern som skapas
     */
    public void drawImage(ImageObject image, int x, int y, double rotation, int rotationCenterX, int rotationCenterY, Graphics2D b) {
        AffineTransform tfm = new AffineTransform();
        tfm.rotate(rotation, x + rotationCenterX - offsetX + getWINDOW_WIDTH() / 2, y + rotationCenterY - offsetY + getWINDOW_HEIGHT() / 2);
        b.setTransform(tfm);
        b.drawImage(image.getImage(), x - offsetX + getWINDOW_WIDTH() / 2, y - offsetY + getWINDOW_HEIGHT() / 2, this);
        tfm.rotate(0, 0, 0);
    }

    /**
     * Ritar en bild, helt utan rotation.
     * @param image
     * @param x
     * @param y
     */
    public void drawImage(ImageObject image, Graphics2D b, int x, int y) {
        drawImage(image, x, y, 0, 0, 0, b);
    }

    /**
     * Skapar alla viktiga saker
     */
    public Window() {
        panel = new Panel();
        buffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        add(panel);
        setTitle("GTA - LiTHe Farligt");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Kul att det inte är default
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        createBufferStrategy(2);
    }

    public void addUserInput(UserController UserController) {
        panel.addKeyListener(UserController);
    }

    public static int getWORLD_HEIGHT() {
        return WORLD_HEIGHT;
    }

    public static int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }

    public static int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

    public static int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }

    /**
     * Ritar ut ett meddelande på skärmen strax ovanför användaren.
     * @param textMessage
     */
    public void drawTextMessage(String textMessage) {
        if (textMessage == null) {
            return;
        }
        AffineTransform tfm = new AffineTransform();
        tfm.setToScale(3, 3);
        b.setTransform(tfm);
        b.setColor(Color.BLACK);
        b.drawChars(textMessage.toCharArray(), 0, textMessage.length(), 100 - textMessage.length() * 3, 100);
        tfm.setToScale(2.985, 2.985);
        b.setTransform(tfm);
        b.setColor(Color.WHITE);
        b.drawChars(textMessage.toCharArray(), 0, textMessage.length(), 100 - textMessage.length() * 3, 100);
        b.dispose();
    }

    private void drawScore(long points) {
        b.setTransform(new AffineTransform());
        b.setColor(Color.BLACK);
        b.fillRect(0, 0, getWINDOW_WIDTH(), 50);
        b.setColor(Color.WHITE);
        b.drawLine(0, 50, getWINDOW_WIDTH(), 50);
        b.setColor(Color.GREEN);
        b.drawChars(("Poäng: " + String.valueOf(points)).toCharArray(), 0, ("Poäng: " + String.valueOf(points)).length(), getWINDOW_WIDTH() / 2 - 20, 45);
    }
    /**
     * Ritar ut allting på skärmen
     */
    private void drawScreen() {
        b.dispose();
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
}
=======
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
/**
 * Fönstret där allting visas, 
 * Stora delar är stulet från slutprojekt i TDDC77, 
 * men eftersom att det inte innehåller någon fysik hoppas vi på att det är okej.  
 */
public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;
    public int offsetY, offsetX;
    double i = 0;
    Color backgroundColor = Color.BLACK;
    BufferedImage buffer;
    Graphics2D b, bg2;
    Panel panel;
    
    
   
   
    /**
     * Ritar ut all grafik
     * @param objects De objekt som skall synas på skärmen
     */
    public void draw(List entities, Boolean StorboMode) {
        b = buffer.createGraphics();

        // Gör så att allt blir härligt smooth
        b.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        b.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        // Fyll bakgrunden i hela spelet med bakgrundsfärgen
        b.setColor(backgroundColor);
        b.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

       
        //eniterator som h�mtar sammlingen fr�n cordinator och sedan ritar ut den ett och ett
        for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			
			drawObject(entity, b);
			
		}
        if (StorboMode){
        	drawStroboPop();
        }
        // Detta ritar ut allting på riktigt :-)
        drawScreen();
        
    }


    private void drawStroboPop() {
    	if(Math.random()<0.5){
    	drawObject(new Entity(0,0) {
			public Color getColor(){
				return new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
			}
			@Override
			public Shape getShape() {
				// TODO Auto-generated method stub
				return new Rectangle2D.Double(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
			}
		}, b);
    	}
	}


	/**
     * Ritar ut ett objekt, anropar drawImage
     * @param o
     */
    public void drawObject(Entity o, Graphics2D b) {
        b.setColor(o.getColor());
        b.fill(o.getShape());
    }

    /**
     * Ritar en form - Känns lite överflödig
     * @param shape Formen som skall ritas ut
     * @param b Graphics2D, buffern som skapas
     */
    public void drawShape(Shape shape, Graphics2D b) {
        b.fill(shape);
    }

    /**
     * Skapar alla viktiga saker
     */
    public Window() {
        panel = new Panel();
        buffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        add(panel);
        setTitle("BG5 - The Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Kul att det inte är så per default
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        
      
        setVisible(true);
        setResizable(true);
        createBufferStrategy(2);
    }

    public void addUserController(UserController userController) {
        panel.addKeyListener(userController);
        // Berätta vilket objekt som userController skall påverka
//        
    }

    /**
     * Ritar ut allting på skärmen
     */
    private void drawScreen() {
        b.dispose();
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
}
>>>>>>> c1d5e1f02806d2a02f60557914ef506dc8ecd5ec
