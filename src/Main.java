/**
 * Main startar upp allting, och instansierar det viktiga funktionerna.
 */
public class Main {
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}
	/**
	* Ett sätt som jag tycker är vettigt för att hantera
	* kontroller / uppritning och dylikt.
	*/
	private void run(){
		// Window (w) är själva fönstret som vi ritar i 
		Window w = new Window();
		
		// Vi instansierar vår kontroll som hanterar vad användaren gör för att påverka spelet.
		UserController uc = new UserController();
		
		// Vi vill gärna knyta någon typ av kontroller till det fönstret vi arbetar med
		// för att fånga händelser (mus/tangent)
		w.addUserController(uc);

		// Huvudloopen som körs oändligt länge (eller sålänge true är sant)
		while(true){
			try {
				// Vi vill gärna att loopen inte går helt okontrollerat utan med en liten vila.
				Thread.sleep(25);
			} catch (InterruptedException e) { /* Nej, det är inte så intressant */}
			
			// Den enda raden som gör något viktigt i denna loop
			w.update();
		}
	}
	

}
