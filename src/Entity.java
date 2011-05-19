import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Denna klass är grundklassen för alla typer av objekt som syns på skärmen.
 * För att skapa ett nytt objekt som visas på skärmen ärver man alltså
 * denna klass.
 */
public abstract class Entity{
	/**
	 * Privata för att slippa trubbel.
	 */
	private double xkord, ykord, oldx, oldy;
	private Color color;
	private int weight = 10;
	/**
	 * Dessa borde också vara privata... vem orkar?
	 */
	public double speedX = 0;
	public double speedY = 0;

	public Entity(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return xkord;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return ykord;
	}

	public double getSpeedX() {
		return speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public boolean isAlive() {
		if (getY() > Window.WINDOW_HEIGHT)
			return true;
		else 
			return false;
	}
	
	public abstract Shape getShape();

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Denna metod skall kallas på en gång per "spelvarv"
	 */
	public void poll() {
		setY(getY() + speedY / 50);
		setX(getX() + speedX / 50);
	}

//	private void setX(long l) {
//		setX(l);
//	}
//
//	private void setY(long l) {
//		setY(l);
//	}
	public int getWeight() {
		return weight;
	}
	public int getCenterX(){
		return (int) (getX() + getRadius());
	}
	public int getCenterY(){
		return (int) (getY() + getRadius());
	}
	public void setX(double x){
		oldx = xkord;
		xkord = x;
	}
	public void setY(double y){
		oldy = ykord;
		ykord = y;
	}
	/**
	 * Denna metod flyttar tillbaka objektet till dit man var "nyss"
	 */
	public void revertPosition() {
		xkord = oldx;
		ykord = oldy;
	}
	/**
	 * Flyttar tillbaka objektet dit man var nyss
	 * Kontrollerar även att man inte längre krockar med den Entity som orsaker krocken
	 * Använd denna istället för bara "revertPosition" 
	 */
	public void revertPosition(Entity e) {
		revertPosition();
		if(getShape().intersects((Rectangle2D) e.getShape())){
			// TODO Se till så att man inte krockar längre...
		}
	}
	/**
	 * 
	 * @return
	 */
	public double getRadius(){
		return getShape().getBounds().getWidth() / 2;
	}

	/**
	 * Kollision med de fasta objekten på banan
	 * @param e
	 */
	public boolean collisionTestAgainst(Entity e) {
		/**
		 * Kontrollerar om avståndet mellan objekten är 
		 */
		if ((Math.pow(e.getCenterX() - getCenterX(), 2) + Math.pow(e.getCenterY()
				- getCenterY(), 2)) < Math.pow(getRadius()+e.getRadius(), 2)) {		
			calculateCollisionChanges(this, e);
			return true;
		}
		return false;
		
	}

	private void calculateCollisionChanges(Entity e1, Entity e2) {
		double x = (e1.getCenterX() - e2.getCenterX());
		double y = (e1.getCenterY() - e2.getCenterY());
		double a = 0;
		if(x!=0){
			a = Math.atan(y/x);
		}
//		if(x<0){
//			a = Math.PI - a;
//		}
//		if(x>0){
//			a = -a;
//		}
		e1.revertPosition();
		e2.revertPosition();
		System.out.println("Alpha: " + a + "gr:" +(a/(2.0*Math.PI))*360 +  " XY:" + x + "|" + y);
		double cosa = Math.cos(a);
		double sina = Math.sin(a);

		double k1 = e1.getSpeedX()*cosa + e1.getSpeedY()*sina;
		double k2 = e2.getSpeedX()*cosa + e2.getSpeedY()*sina;
		double k1x = (k1)*cosa;
		double k2x = (k2)*cosa;
		double k1y = (k1)*sina;
		double k2y = (k2)*sina;
		System.out.println(e1 + ":" + k1x +":"+ k1y);
		System.out.println(e2 + ":" + k2x +":"+ k2y);
		double m1 = e1.getWeight();
		double m2 = e2.getWeight();

		e1.setSpeedX(((m1-m2)/(m1+m2))*k1x+(2*m2/(m1+m2))*k2x);
		e1.setSpeedY((((m1-m2)/(m1+m2))*k1y+(2*m2/(m1+m2))*k2y));
		e2.setSpeedX((((m2-m1)/(m1+m2))*k2x+(2*m1/(m1+m2))*k1x));
		e2.setSpeedY((((m2-m1)/(m1+m2))*k2y+(2*m1/(m1+m2))*k1y));
		
		
//		e1.setSpeedX(Math.abs(k1)*cosa + Math.abs(k2)*cosa);
//		e1.setSpeedY(-(Math.abs(k1)*sina+Math.abs(k2)*sina));
//		
//		e2.setSpeedX(-e1.speedX/e2.getWeight());
//		e2.setSpeedY(-e1.speedY/e2.getWeight());
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public int getIntX(){
		return (int) Math.round(getX());
	}
	public int getIntY(){
		return (int) Math.round(getY());
	}
	
}
