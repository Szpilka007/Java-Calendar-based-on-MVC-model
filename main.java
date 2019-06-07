
import javax.swing.*;
public class main {
	
	public static void main(String[] args) {
		EventManager eventManager = new EventManager();
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		//GUI gui = new GUI();;
		eventManager.loadEventsFromSQL();
		eventManager.showEvents();

		
		


		
		
		
	}

}
