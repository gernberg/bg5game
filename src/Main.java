
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

	private void run(){
		// Ett sätt som jag tycker är vettigt för att hantera
		// kontroller / uppritning och dylikt.
		Window w = new Window();
		UserController uc = new UserController();
		w.addUserController(uc);
		while(true){
			try {
				// Vi vill gärna att loopen inte går helt okontrollerat utan med en liten
				// vila.
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// Det här bryr vi oss inte om
			}
			w.draw(null, null);
		}
	}
	

}
