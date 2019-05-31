import javax.swing.*;
public class main {
	static String[] months = {"styczen", "luty", "marzec", "kwiecien", "maj",
					   "czerwiec", "lipiec", "sierpien", "wrzesien",
					   "pazdziernik", "listopad", "grudzien"};	
	
	public static void main(String[] args) {
		EventManager eManager = new EventManager();
		
		eManager.addEvent(0, 8, 5, 2019, "Pierwsze wydarzenie");
		eManager.showEventInYear(2019);
		
	}

}
