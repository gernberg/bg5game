import java.util.Iterator;
import java.util.LinkedList;

/**
 * Denna klass borde hålla koll på allting som finns på banan just nu
 */
public class Coordinator {

	
	private LinkedList<Entity> entitys = new LinkedList<Entity>();
	
	/**
	 * Array med fallande hinder.
	 */
	private Obstacle[] obstacles;
	
	Window w;
	UserController uc;
	Platform platta;
	Ball boll;
	Boolean stroboMode = false;
	public Coordinator(Window w, UserController uc){
		this.w = w;
		this.uc = uc;
		platta = new Platform(200,400);
		boll = new Ball(250, 200);
		addToList(platta);
		addToList(boll);
		uc.setPlatform(platta);
		uc.setCoordinator(this);
		
		// Väggar utanför skärmen, längs kanterna, för studs...
		Wall leftWall =  new Wall (-20 +10, -3000);
		Wall rightWall = new Wall (Window.WINDOW_WIDTH -10, -3000);
		addToList(leftWall);
		addToList(rightWall);
		
		// skapar fallande objekt, lägger till dom
		obstacles = createObstacles(6);
		for (int i = 0; i < obstacles.length; i++) {
			addToList(obstacles[i]);
		}
	}

	public void addToList(Entity e){
		entitys.add(e);
	}
	public LinkedList<Entity> getEntitys(){
		return entitys;
	}
	
	/**
	 * Skapar massa fallande hinder med slumpade begynnelsepositioner.
	 * @param num Antal samtidiga fallande hinder.
	 * @return arrrrayyy
	 */
	private Obstacle[] createObstacles(int num) {
		Obstacle[] array = new Obstacle[num];
		for (int i = 0; i < array.length; i++) {
			int x = (int) Math.round(Math.random() * Window.WINDOW_WIDTH);
			int y = (int) Math.round(Math.random() * -1 * Window.WINDOW_HEIGHT);
			array[i] = new Obstacle(x, y);
			System.out.println(x);
			System.out.println(y);
		}
		
		return array;
	}
	
	public void update(){
		for (Iterator iterator = entitys.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			
			entity.poll();
		}
		
		w.draw(entitys, stroboMode);
		
	}

	public void switchStroboMode() {
		if (stroboMode == false){
			stroboMode = true;
		}else stroboMode = false;
		
	}
}
