/**
 * Main startar upp allting, och instansierar det viktiga funktionerna.
 */
public class Main {
	private final int GAME_SPEED = 25;
	private Window w;
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}
	/**
	* Ett sätt som jag tycker är vettigt för att hantera
	* kontroller / uppritning och dylikt.
	*/
	private void run(){
		// Vi instansierar vår kontroll som hanterar vad användaren gör för att påverka spelet.
		UserController uc = new UserController();
		
		// Window (w) är själva fönstret som vi ritar i 
		 w = new Window();
		 Coordinator coordinator = new Coordinator(w, uc);
		
	
		
		// Vi vill gärna knyta någon typ av kontroller till det fönstret vi arbetar med
		// för att fånga händelser (mus/tangent)
		w.addUserController(uc);

		// Huvudloopen som körs oändligt länge (eller sålänge true är sant)
		while(true){
			try {
				// Vi vill gärna att loopen inte går helt okontrollerat utan med en liten vila.
				Thread.sleep(GAME_SPEED);
			} catch (InterruptedException e) { /* Nej, det är inte så intressant */}
			
			// Den enda raden som gör något viktigt i denna loop
			coordinator.update();
		}
		
	}
	
}
