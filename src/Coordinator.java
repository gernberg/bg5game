import java.util.LinkedList;

/**
 * Denna klass borde hålla koll på allting som finns på banan just nu
 */
public class Coordinator {

	
	private LinkedList<Entity> entitys = new LinkedList<Entity>();
	

	public void addToList(Entity e){
		entitys.add(e);
	}
	public LinkedList<Entity> getEntitys(){
		return entitys;
	}
}
