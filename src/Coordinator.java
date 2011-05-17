import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;

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
		} else
			stroboMode = false;

	}

	public void checkCollisions() {
		int obstalesCenterX, obstalesCenterY, plattaCenterX, plattaCenterY, bollCenterX, bollCenterY;
		checkCollisionsWhithWall(platta);
		checkCollisionsWhithWall(boll);
		// h�r letar vi reda p� plattans och bollens mitt.
		plattaCenterX = platta.getX() + 25;
		plattaCenterY = platta.getY() + 25;
		bollCenterX = boll.getX() + 10;
		bollCenterY = boll.getY() + 10;

		for (Entity e : obstacles) {
			if (platta.getShape().intersects((Rectangle2D) e.getShape())) {

				e.setColor(Color.green);
			}
		}

		// h�r kollar vi om de �verlappar.
		if ((Math.pow(plattaCenterX - bollCenterX, 2) + Math.pow(plattaCenterY
				- bollCenterY, 2)) < Math.pow(35, 2)) {
			collide(boll, platta);
		}

	}

	private void checkCollisionsWhithWall(Entity platta2) {
		if (platta2.getShape().intersects((Rectangle2D) leftWall.getShape())){
			platta2.speedX = -platta2.speedX;
		}else if (platta2.getShape().intersects((Rectangle2D) rightWall.getShape())){
			platta2.speedX = -platta2.speedX;
		}
		
	}

	private void collide(Ball b, Platform p) {
		// switchStroboMode();
		double bspeedX = b.speedX;
		double bspeedY = b.speedY;
		double pspeedX = p.speedX;
		double pspeedY = p.speedY;
		calculateNewSpeed(b, p, bspeedX, bspeedY, pspeedX, pspeedY);
		calculateNewSpeed(p, b, pspeedX, pspeedY, bspeedX, bspeedY);

	}

	private void calculateNewSpeed(Entity p, Entity b, double bspeedX,
			double bspeedY, double pspeedX, double pspeedY) {
		double totalWeight = b.getWeight() + p.getWeight();
		b.speedX = ((b.getWeight() - p.getWeight()) / totalWeight) * pspeedX;
		b.speedX += ((2 * p.getWeight()) / totalWeight) * bspeedX;
		b.speedY = ((b.getWeight() - p.getWeight()) / totalWeight) * pspeedY;
		b.speedY += ((2 * p.getWeight()) / totalWeight) * bspeedY;

	}

}
