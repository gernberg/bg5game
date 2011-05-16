import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Den nödvändigaste klassen - nästan. Håller koll på vad användaren gör vid
 * datorn och om det skall påverka spelet.
 */
public class UserController implements KeyListener, MouseListener {
	Platform platform;
	/**
	 * Denna kallas på när en knapp trycks ner i fönstret.
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		// Vi undersöker vilken KeyCode som trycktes ner
		switch (ke.getKeyCode()) {
			case KeyEvent.VK_UP:
				platform.moveUp();
				break;
			case KeyEvent.VK_DOWN:
				platform.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				platform.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				platform.moveRight();
				break;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Den här kommer vi inte använda med all sannolikhet, den är rätt dum
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setPlatform(Platform platform) {
		// TODO Auto-generated method stub
		this.platform = platform;
	}

}
