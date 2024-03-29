import java.awt.*;
import javax.imageio.ImageIO;

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
		if (getIntY() > Window.WINDOW_HEIGHT)
			return false;
		return true;
	}
	
	public abstract Shape getShape();
	
	public abstract Image getPicture();

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
		// Beräkna fram vinkeln i kollisionen
		double dx = (e1.getCenterX() - e2.getCenterX());
		double dy = (e1.getCenterY() - e2.getCenterY());
		
		double alpha = 0;
		// Om man krockar så att x är 0 kommer vi få division med 0, vilket är lite osmidigt
		// däremot vet vi att för x=0 är a=0, så vi behöver inte räkna ut a.
		if(dx!=0){
			alpha = Math.atan(dy/dx);
		}
		
		// Sätt objekten där dom var precis innan krocken, detta gör vi för att förhindra att saker fastnar i varandra.
		// TODO: Tror dock ironiskt nog att det är denna kod som gör att man kan få bollen att lagga fast i väggen.
		e1.revertPosition();
		e2.revertPosition();

		// För läsbarhetens skull definierar vi cosa och sina 
		double cosa = Math.cos(alpha);
		double sina = Math.sin(alpha);

		// För läsbarhetens skull har vi också k1 och k2 definierat här
		// De är hastigheterna i k^-led
		double k1 = e1.getSpeedX()*cosa + e1.getSpeedY()*sina;
		double k2 = e2.getSpeedX()*cosa + e2.getSpeedY()*sina;
		
		// Eftersom att vi är "dumma" och använder hastigheter i x,y led så behöver vi 
		// ta ut komposanterna x resp y för k1 och k2
		double k1x = (k1)*cosa;
		double k2x = (k2)*cosa;
		double k1y = (k1)*sina;
		double k2y = (k2)*sina;
		
		// Definierar massor som m1 / m2 för läsbarhetens skull
		double m1 = e1.getWeight();
		double m2 = e2.getWeight();
		
		// Här händer all magi :-)
		// Vi använder formel för kollision i en riktning, två gånger dvs. för x / y
		e1.setSpeedX(((m1-m2)/(m1+m2))*k1x+(2*m2/(m1+m2))*k2x);
		e1.setSpeedY((((m1-m2)/(m1+m2))*k1y+(2*m2/(m1+m2))*k2y));
		e2.setSpeedX((((m2-m1)/(m1+m2))*k2x+(2*m1/(m1+m2))*k1x));
		e2.setSpeedY((((m2-m1)/(m1+m2))*k2y+(2*m1/(m1+m2))*k1y));
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
