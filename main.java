import javax.swing.*;
public class main {
	static String[] months = {"styczen", "luty", "marzec", "kwiecien", "maj",
					   "czerwiec", "lipiec", "sierpien", "wrzesien",
					   "pazdziernik", "listopad", "grudzien"};	
	
	public static void main(String[] args) {
		EventManager eManager = new EventManager();
		JFrame.setDefaultLookAndFeelDecorated(true);
		eManager.addEvent(56, 22, 6, 2019, "Pierwsze wydarzenie");
		GUI gui = new GUI();
		
		eManager.addEvent(0, 31, 6, 2019, "Pierwsze wydarzenie");
		eManager.addEvent(1, 9, 5, 2019, "Pierwsze wydarzenie");
		eManager.addEvent(2, 10, 5, 2019, "Pierwsze wydarzenie");
		eManager.addEvent(3, 9, 5, 2019, "Pierwsze wydarzenie");
		
		//eManager.getEventsOnDate(9, 5, 2019);
		
		eManager.showEvents();
		
		eManager.modifyEvent(4, 12, 6, 2019, "Zmodyfikowane wydarzenie");
		System.out.println();
		eManager.showEvents();
		
	}

}
