import java.util.Iterator;
import java.util.LinkedList;

/**
 * Denna klass borde hÃ¥lla koll pÃ¥ allting som finns pÃ¥ banan just nu
 */
public class Coordinator {

	private LinkedList<Entity> entities = new LinkedList<Entity>();

	Window w;
	UserController uc;
	Platform platta;
	Ball boll;
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
	}

	public void addToList(Entity e) {
		entities.add(e);
	}

	public LinkedList<Entity> getEntitys() {
		return entities;
	}

	public void update() {
		checkCollisions();
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();

			entity.poll();
		}
		w.draw(entities, stroboMode);

	}

	public void switchStroboMode() {
		if (stroboMode == false) {
			stroboMode = true;
		} else
			stroboMode = false;

	}

	public void checkCollisions() {
		int plattaCenterX, plattaCenterY, bollCenterX, bollCenterY;

		// här letar vi reda på plattans och bollens mitt.
		plattaCenterX = platta.getX() + 25;
		plattaCenterY = platta.getY() + 25;
		bollCenterX = boll.getX() + 10;
		bollCenterY = boll.getY() + 10;

		// här kollar vi om de överlappar.
		if ((Math.pow(plattaCenterX - bollCenterX, 2) + Math.pow(plattaCenterY
				- bollCenterY, 2)) < Math.pow(35, 2)) {
			collide(boll, platta);
		}

	}

	private void collide(Ball b, Platform p) {
//		switchStroboMode()
		double bspeedX = b.speedX;
		double bspeedY = b.speedY;
		double pspeedX = p.speedX;
		double pspeedY = p.speedY;
		calculateNewSpeed(b, p, bspeedX, bspeedY, pspeedX, pspeedY);
		calculateNewSpeed(p, b, pspeedX, pspeedY, bspeedX, bspeedY);

	}

	private void calculateNewSpeed(Entity p, Entity b, double bspeedX,
			double bspeedY, double pspeedX,	double pspeedY) {
		double totalWeight  = b.getWeight() + p.getWeight();
		b.speedX = ((b.getWeight()-p.getWeight())/totalWeight) * pspeedX;
		b.speedX += ((2*p.getWeight())/totalWeight) * bspeedX;
		b.speedY = ((b.getWeight()-p.getWeight())/totalWeight) * pspeedY;
		b.speedY += ((2*p.getWeight())/totalWeight) * bspeedY;
		
	}
	

}
