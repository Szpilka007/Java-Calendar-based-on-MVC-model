import java.util.Vector;

public class Day {
    private int dayNumber;
    private int dayOfWeek;
    private Vector<Event> eventsList;
    
	public Day(int dayNumber, int dayOfWeek) {
		super();
		this.eventsList = new Vector<Event>();
		this.dayNumber = dayNumber;
		this.dayOfWeek = dayOfWeek;
	}

	public int getDayNumber() {
		return dayNumber;
	}
	
	public Event getEvent(int eventID) {
		for (Event e : eventsList)
			if (e.getDayNumber() == eventID)
				return e;
		
		return null;
	}
	
	public void showEvents() {
		if (eventsList.isEmpty() == false) {
			System.out.println("\nDay number " + String.valueOf(getDayNumber()) + " Day of Week: " + String.valueOf(getDayOfWeek()));
			for (Event e : eventsList)
				System.out.println(e.toString());
		}	

	}
	
	public void addEvent (Event event) {
		eventsList.add(event);		
	}
	
	public int getDayOfWeek() {
		return dayOfWeek;
	}
    
    
    
}
