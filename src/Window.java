import java.awt.Color;
import java.awt.Font;
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
    public static final int WINDOW_HEIGHT = 768;
    public int offsetY, offsetX;
    double i = 0;
	private int score = 0;
    Color backgroundColor = Color.BLACK;
    BufferedImage buffer;
    Graphics2D b, bg2;
    Panel panel;
    
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
        
        drawScore(score++);
        drawScreen();
        
    }

    /**
     * Ritar ut poängen
     * @param score poäng
     */
	public void drawScore(int score) {
		String s = Integer.toString(score);
		Font font = Font.getFont("Arial");
		b.setFont(font);
		b.setColor(Color.YELLOW);
		b.drawChars(s.toCharArray(), 0, s.length(), 30, 50);
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
