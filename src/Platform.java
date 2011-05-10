
import java.awt.Rectangle;

public class Platform extends Entity {

	public Platform(int x, int y) {
		super(x, y);
		super.setForm(new Rectangle.Double(x, y, 10, 10));
	}

	@Override
	public ImageObject getImage() {
		// TODO Auto-generated method stub
		return null;
	}
}
