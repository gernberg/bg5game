import java.awt.Color;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Denna klass borde hålla koll på allting som finns på banan just nu
 */
public class Coordinator {

	private LinkedList<Entity> entities = new LinkedList<Entity>();

	/**
	 * Array med fallande hinder.
	 */
	private Obstacle[] obstacles;

	Window w;
	UserController uc;
	Platform platta;
	Ball boll;
	Wall leftWall, rightWall;
	Boolean stroboMode = false;

	private boolean playing = true; // Spelar vi?

	public Coordinator(Window w, UserController uc) {
		this.w = w;
		this.uc = uc;
		platta = new Platform(200, 400);
		boll = new Ball(200, 200);
		addToList(platta);
		addToList(boll);
		uc.setPlatform(platta);
		uc.setCoordinator(this);

		// Väggar utanför skärmen, längs kanterna, för studs...
		leftWall = new Wall(-20 + 10, -3000);
		rightWall = new Wall(Window.WINDOW_WIDTH - 10, -3000);
		addToList(leftWall);
		addToList(rightWall);

		// skapar fallande objekt, lägger till dom
		obstacles = createObstacles(6);
		for (int i = 0; i < obstacles.length; i++) {
			addToList(obstacles[i]);
		}
	}

	public void addToList(Entity e) {
		entities.add(e);
	}

	public LinkedList<Entity> getEntitys() {
		return entities;
	}

	/**
	 * Skapar massa fallande hinder med slumpade begynnelsepositioner.
	 * 
	 * @param num
	 *            Antal samtidiga fallande hinder.
	 * @return arrrrayyy
	 */
	private Obstacle[] createObstacles(int num) {
		Obstacle[] array = new Obstacle[num];
		for (int i = 0; i < array.length; i++) {
			int x = (int) Math.round(Math.random() * Window.WINDOW_WIDTH);
			int y = (int) Math.round(Math.random() * -1 * Window.WINDOW_HEIGHT);
			array[i] = new Obstacle(x, y);

		}

		return array;
	}
	
	private void gameOver() {
		// game over, game over man.
		playGameOver();
		JOptionPane.showMessageDialog(new JFrame(), "You lose, loser...\nKein mehr strobopop für dich!", "ACHTUNG ACHTUNG!",
			    JOptionPane.WARNING_MESSAGE);
		playing = false;
	}

	public void update() {
		if(playing){
			for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
				Entity entity = (Entity) iterator.next();
	
				entity.poll();
			}
			checkCollisions();
			w.draw(entities, stroboMode);
			
			if (!platta.isAlive()) {
				gameOver();
			}
		}
	}

	public void switchStroboMode() {
		if (stroboMode == false) {
			stroboMode = true;
			Main.GAME_SPEED = 10;
		} else{
			stroboMode = false;
			Main.GAME_SPEED = 25;
		}
		stroboPlay(stroboMode);
	}

    public void stroboPlay(boolean a){
    	if(a){
    		music = new AePlayWave("sound/takeitjens.wav");
    		music.start();
    	}else{
    		music.stop();
    	}
    }
    AePlayWave music = new AePlayWave("sound/takeitjens.wav");
    public void playBounce(){
    	new AePlayWave("sound/studs1.wav").start();
    }
    public void playGameOver(){
    	new AePlayWave("sound/game-over.wav").start();
    }
//    Thread music = new Thread(){
//    	public void run(){
//    		AePlayWave music = new AePlayWave("sound/takeitjens.wav");
//    		while(true){
//    			music.start();
//    		}
//    	}
//    };
    

	public void checkCollisions() {
		boolean collisionDetected = false;;
		checkCollisionsWhithWall(platta);
		checkCollisionsWhithWall(boll);
		
		collisionDetected = collisionDetected || platta.collisionTestAgainst(boll);
		for (Entity e : obstacles) {
			collisionDetected = collisionDetected || platta.collisionTestAgainst(e);
			collisionDetected = collisionDetected || boll.collisionTestAgainst(e);
		}
		
		if(collisionDetected){
			playBounce();
		}

	}


	private void checkCollisionsWhithWall(Entity platta2) {
		if (platta2.getShape().intersects((Rectangle2D) leftWall.getShape())) {
			platta2.speedX = -platta2.speedX;
		} else if (platta2.getShape().intersects(
				(Rectangle2D) rightWall.getShape())) {
			platta2.speedX = -platta2.speedX;

		}

	}


}
