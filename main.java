import javax.swing.*;
public class main {
	static String[] months = {"styczen", "luty", "marzec", "kwiecien", "maj",
					   "czerwiec", "lipiec", "sierpien", "wrzesien",
					   "pazdziernik", "listopad", "grudzien"};	
	
	public static void main(String[] args) {
		EventManager eManager = new EventManager();
		
		eManager.addEvent(0, 1, 1, 1970, "Pierwsze wydarzenie");
		eManager.showEventInYear(1970);
		
	}

}
