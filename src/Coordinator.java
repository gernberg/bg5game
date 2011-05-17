import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Denna klass borde hålla koll på allting som finns på banan just nu
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

		// h�r letar vi reda p� plattans och bollens mitt.
		plattaCenterX = platta.getX() + 25;
		plattaCenterY = platta.getY() + 25;
		bollCenterX = boll.getX() + 10;
		bollCenterY = boll.getY() + 10;

		// h�r kollar vi om de �verlappar.
		if ((Math.pow(plattaCenterX - bollCenterX, 2) + Math.pow(plattaCenterY
				- bollCenterY, 2)) < Math.pow(35, 2)) {
			collide(boll, platta);
		}

	}

	private void collide(Ball b, Platform p) {
		Ball bi = b;
		Platform pi = p;
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

		b.speedX += 2*(Math.abs(k1)+Math.abs(k2))*cosa;
		b.speedY += -2*(Math.abs(k1)+Math.abs(k2))*sina;
		p.speedX -= b.speedX/5;
		p.speedY -= b.speedY/5;
//		b.speedX = - Math.cos(a)*((Math.abs(b.speedX) + Math.abs(p.speedX)) * Math.sin(a)
//					+ (Math.abs(b.speedY) + Math.abs(p.speedY)) * Math.cos(a));
//		b.speedY = Math.sin(a)*((Math.abs(b.speedY) + Math.abs(p.speedY)) * Math.cos(a)
//					+ (Math.abs(b.speedX) + Math.abs(p.speedX)) * Math.sin(a));
	}	

}
