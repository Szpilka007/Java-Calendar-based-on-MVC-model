
public class main {

	public static void main(String[] args) {
		month styczen = new month(31);
		Event ev = new Event(1,1,"styczen",1256,"Pierwsze wydarzenie");
		styczen.addEventToDay(ev);
		styczen.showEventInMonth();


	}

}
