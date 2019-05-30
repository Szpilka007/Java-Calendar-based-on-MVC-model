import java.util.Vector;

public class Day {
    private int dayNumber;
    private Vector<Event> eventsList;
    
	public Day(int dayNumber) {
		super();
		this.eventsList = new Vector<Event>();
		this.dayNumber = dayNumber;
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
			System.out.println("\nDay number " + String.valueOf(getDayNumber()));
			for (Event e : eventsList)
				System.out.println(e.toString());
		}	

	}
	
	public void addEvent (Event event) {
		eventsList.add(event);		
	}
    
    
    
}
