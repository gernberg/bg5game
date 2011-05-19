import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

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

	public void update() {
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();

			entity.poll();
		}
		checkCollisions();
		w.draw(entities, stroboMode);

	}

	public void switchStroboMode() {
		if (stroboMode == false) {
			stroboMode = true;
			Main.GAME_SPEED = 10;
		} else{
			stroboMode = false;
			Main.GAME_SPEED = 25;
		}
	}

	public void checkCollisions() {

		checkCollisionsWhithWall(platta);
		checkCollisionsWhithWall(boll);

		for (Entity e : obstacles) {
			// Detta är varken snyggt eller korrekt - men det ser helt okej ut.
			if (boll.getShape().intersects((Rectangle2D) e.getShape())) {
				boll.revertPosition(e);
				boll.speedX = -boll.speedX;
				boll.speedY = -boll.speedY;				
			}
			if (platta.getShape().intersects((Rectangle2D) e.getShape())) {

				platta.revertPosition(e);
				platta.speedX = -platta.speedX;
				platta.speedY = -platta.speedY;				

			}
		}

		// h�r kollar vi om de �verlappar.
		if ((Math.pow(platta.getCenterX() - boll.getCenterX(), 2) + Math.pow(platta.getCenterY()
				- boll.getCenterY(), 2)) < Math.pow(60, 2)) {
			collide(boll, platta);
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

	private void collide(Ball b, Platform p) {
		double x = (b.getCenterX() - p.getCenterX());
		double y = (b.getCenterY() - p.getCenterY());
		double a = 0;
		if(x!=0){
			a = Math.atan(y/x);
		}
		if(x<0){
			a = Math.PI - a;
		}
		if(x>0){
			a = -a;
		}
		b.revertPosition();
		System.out.println("Alpha: " + a + "gr:" +(a/(2.0*Math.PI))*360 +  " XY:" + x + "|" + y);
		double cosa = Math.cos(a);
		double sina = Math.sin(a);

		double k1 = b.getSpeedX()*cosa + b.getSpeedY()*sina;
		double k2 = p.getSpeedX()*cosa + p.getSpeedY()*sina;

		b.speedX = (Math.abs(k1)+Math.abs(k2))*cosa;
		b.speedY = -(Math.abs(k1)+Math.abs(k2))*sina;
		p.speedX = -b.speedX/5;
		p.speedY = -b.speedY/5;
	}	

}
