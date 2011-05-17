import java.util.Iterator;
import java.util.LinkedList;

/**
 * Denna klass borde hålla koll på allting som finns på banan just nu
 */
public class Coordinator {

	
	private LinkedList<Entity> entitys = new LinkedList<Entity>();
	
	Window w;
	UserController uc;
	Platform platta;
	Ball boll;
	public Coordinator(Window w, UserController uc){
		this.w = w;
		this.uc = uc;
		platta = new Platform(200,400);
		boll = new Ball(250, 200);
		addToList(platta);
		addToList(boll);
		uc.setPlatform(platta);
	}

	public void addToList(Entity e){
		entitys.add(e);
	}
	public LinkedList<Entity> getEntitys(){
		return entitys;
	}
	
	public void update(){
		for (Iterator iterator = entitys.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			
			entity.poll();
		}
		w.draw(entitys);
		
	}
}
