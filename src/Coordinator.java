import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

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
		Ball bi = b;
		Platform pi = p;
		b.revertPosition();
		int x = (b.getCenterX() - p.getCenterX());
		int y = (b.getCenterY() - p.getCenterY());
		double a = 0;
		if(x!=0){
			a = Math.atan(y/x) + 3*Math.PI/2;
		}
		System.out.println("Alpha: " + a + " XY:" + x + "|" + y);
		System.out.println((a/(2*Math.PI))*360);
		
		b.speedX = b.speedX + ((b.speedY - p.speedY)* Math.cos(a));
		b.speedY = b.speedY + ((b.speedY - p.speedY)* Math.sin(a));
//		b.speedX = - Math.cos(a)*((Math.abs(b.speedX) + Math.abs(p.speedX)) * Math.sin(a)
//					+ (Math.abs(b.speedY) + Math.abs(p.speedY)) * Math.cos(a));
//		b.speedY = Math.sin(a)*((Math.abs(b.speedY) + Math.abs(p.speedY)) * Math.cos(a)
//					+ (Math.abs(b.speedX) + Math.abs(p.speedX)) * Math.sin(a));
	}	

}
