import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.Set;
/**
 * Fönstret där allting visas
 * @author gustav
 */
public class Window extends JFrame {

    /**
	 * 
	 */
	private static int WINDOW_WIDTH = 600;
    private static int WINDOW_HEIGHT = 600;
    private static int WORLD_WIDTH = 1280;
    private static int WORLD_HEIGHT = 1280;
    public int offsetY, offsetX;
    double i = 0;
    Color backgroundColor = Color.GRAY;
    BufferedImage buffer;
    Graphics2D b, bg2;
    Panel panel;

    /**
     * Ritar ut all grafik
     * @param objects De objekt som skall synas på skärmen
     */
    public void draw(Set<Entity> backgroundObjects, Set<Entity> foregroundObjects, long points, String text) {
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
        
        // Detta ritar ut allting på riktigt :-)
        drawScreen();
    }


    /**
     * Ritar ut ett objekt, anropar drawImage
     * @param o
     */
    public void drawObject(Entity o, Graphics2D b) {
        drawImage(o.getImage(), o.getIntX(), o.getIntY(), o.getAngle(), o.getRotationCenterX(), o.getRotationCenterY(), b);
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
