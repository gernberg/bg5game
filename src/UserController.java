import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UserController implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//test kod som funkar om den vill :-)
		
		
		if(arg0.getKeyChar() == 'g'){
			System.out.println("G är bästa bokstaven!");
		}else{
			System.out.println("Du tryckte ner: " + arg0.getKeyChar());
		}

	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
