
import javax.swing.*;
public class main {
	
	public static void main(String[] args) {
		EventManager eventManager = new EventManager();
		JFrame.setDefaultLookAndFeelDecorated(true);
		eventManager.addEvent(22, 6, 2019, "Pierwsze wydarzenie");
		GUI gui = new GUI();
		
		eventManager.addEvent(31, 6, 2019, "Pierwsze wydarzenie");
		eventManager.addEvent(9, 5, 2019, "Pierwsze wydarzenie");
		eventManager.addEvent(10, 5, 2019, "Pierwsze wydarzenie");
		eventManager.addEvent(9, 5, 2019, "Pierwsze wydarzenie");
		

		eventManager.getEventsOnDate(9, 5, 2019);
		System.out.println(eventManager.getEventsOnDate(9, 5, 2019).size());
		
		eventManager.showEvents();
		
		eventManager.modifyEvent(4, 12, 6, 2019, "Zmodyfikowane wydarzenie");
		System.out.println();
		eventManager.showEvents();
		
		System.out.println();
		eventManager.removeTooOldEvents(9, 5, 2019);
		System.out.println();
		eventManager.showEvents();
		
	}

}
