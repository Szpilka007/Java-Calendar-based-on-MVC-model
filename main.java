
import javax.swing.*;
public class main {
	
	public static void main(String[] args) {
		EventManager eventManager = new EventManager();
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		GUI gui = new GUI();;
		//eventManager.loadEventsFromSQL();
		//eventManager.showEvents();
		
		eventManager.addEvent(1, 6, 2019, "witam serdecznie", "tak");
		eventManager.addEvent(1, 6, 2019, "witam serdecznie", "tak2");
		System.out.println(eventManager.getEvent("tak", 1, 6, 2019).toString());
		eventManager.showEvents();
		eventManager.deleteEve("tak", 1, 6, 2019);
		
		eventManager.showEvents();
		


		
		
		
	}

}
