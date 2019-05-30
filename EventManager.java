import java.util.Vector;

public class EventManager {

	private Vector<Year> years; 
	
	public EventManager() {
		this.years = new Vector<Year>();
		for (int i = 1970; i < 2051; i++) {
			Year year = new Year(i);
			years.add(year);
		}
			
	}
	
	public void addEvent(int id, int dayNumber, int monthNumber, int yearNumber, String description) {
		years.elementAt(yearNumber-1970).addEventToMonth(new Event(id, dayNumber, monthNumber, yearNumber, description));
	}
	
	public void showEventInYear(int yearNumber) {
			for (int i = 0; i < 12; i++)
				years.elementAt(yearNumber-1970).showEventsInMonth(i+1);		
	}

}
