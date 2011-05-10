import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.Set;
/**
 * Fönstret där allting visas, 
 * Stora delar är stulet från slutprojekt i TDDC77, 
 * men eftersom att det inte innehåller någon fysik hoppas vi att det är okej.  
 */
public class Window extends JFrame {

    /**
	 * 
	 */
	private static int WINDOW_WIDTH = 600;
    private static int WINDOW_HEIGHT = 600;
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
        b.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        b.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        // Fyll bakgrunden i hela spelet med bakgrundsfärgen
        b.setColor(backgroundColor);
        b.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // TODO: Här borde det ritas ut objekt
        drawObject(new Platform(100,100), b);
        // Detta ritar ut allting på riktigt :-)
        drawScreen();
    }


    /**
     * Ritar ut ett objekt, anropar drawImage
     * @param o
     */
    public void drawObject(Entity o, Graphics2D b) {
        drawShape(o.getShape(), b);
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

    private int getWINDOW_HEIGHT() {
		// TODO Auto-generated method stub
		return 0;
	}


	private int getWINDOW_WIDTH() {
		// TODO Auto-generated method stub
		return 0;
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
        setTitle("BG5 - The Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Kul att det inte är så per default
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        
        
        setVisible(true);
        setResizable(false);
        createBufferStrategy(2);
    }

    public void addUserController(UserController userController) {
        panel.addKeyListener(userController);
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
