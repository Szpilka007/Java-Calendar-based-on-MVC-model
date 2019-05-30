
public class main {
	String[] months = {"styczen", "luty", "marzec", "kwiecien", "maj",
					   "czerwiec", "lipiec", "sierpien", "wrzesien",
					   "pazdziernik", "listopad", "grudzien"};



	public static void main(String[] args) {
		month styczen = new month(31,1);
		Event ev = new Event(1,15,"styczen",1256,"Pierwsze wydarzenie");
		styczen.addEventToDay(ev);
		styczen.showEventInMonth();
	}

	public void orderAddEventToDay() {
		int eventId, eventDayNumber, eventYear;
		String month, description;

		System.out.print("Wprowadz id wydarzenia: ");
		eventId = Integer.getInteger(System.console().readLine());

		System.out.print("Wprowadz dzien wydarzenia: ");
		eventDayNumber = Integer.getInteger(System.console().readLine());

		System.out.print("Wprowadz rok wydarzenia: ");
		eventYear = Integer.getInteger(System.console().readLine());

		System.out.print("Wprowadz numer miesiaca wydarzenia: ");
		month = months[Integer.getInteger(System.console().readLine())-1];

		System.out.print("Wprowadz opis wydarzenia: ");
		description = System.console().readLine();


	}

}
