import java.util.Vector;

public class EventManager {

	private Vector<Year> years; 
	
	public EventManager() {
		this.years = new Vector<Year>();
		for (int i = 2019; i < 2020; i++) {
			Year year = new Year(i);
			years.add(year);
		}
			
	}
	
	public void addEvent(int id, int dayNumber, int monthNumber, int yearNumber, String description) {
		years.elementAt(yearNumber-2019).addEventToMonth(new Event(id, dayNumber, monthNumber, yearNumber, description));
	}
	
	public void showEventInYear(int yearNumber) {
			for (int i = 0; i < 12; i++)
				years.elementAt(yearNumber-2019).showEventsInMonth(i+1);		
	}

}
