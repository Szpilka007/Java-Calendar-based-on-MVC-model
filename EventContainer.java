import java.util.Vector;

public class EventContainer {

	private Vector<Event> eventsList;
	
	public EventContainer() {
		this.eventsList = new Vector<Event>();
	}
	
	public Event getEvent(int eventID) {
		for (Event e: eventsList) 
			if (e.getID() == eventID)
				return e;
		
		return null;
		
	}
	
	public Vector<Event> getEventsOnDate(int dayNumber, int monthNumber, int yearNumber) {
		Vector<Event> eventsOnDate = new Vector<Event>();
		for (Event e:eventsList)
			if (e.getDayNumber() == dayNumber && e.getMonthNumber() == monthNumber && e.getYearNumber() == yearNumber)
				eventsOnDate.add(e);
		
		return eventsOnDate;
	}
	
	public void addEvent(Event event) {		
		if (getEvent(event.getID()) != null) {
			System.out.println("Istnieje wydarzenie o takim ID");
			return;
		}
		eventsList.add(event);
		System.out.println("Dodano wydarzenie");
	}
	
	public boolean deleteEvent(int eventID) {
		if (getEvent(eventID) instanceof Event) {
			eventsList.remove(getEvent(eventID));
			return true;
		}
			
		else {
			System.err.println("Nie ma takiego wydarzenia");
			return false;
		}
			
	}

	public void showEvents() {
		for (Event e:eventsList)
			System.out.println(e.toString());
	}
	
	public void modifyEvent(int eventID, int newDayNumber, int newMonthNumber, int newYearNumber,String newDescription) {
		if (deleteEvent(eventID))
			addEvent(new Event(eventID, newDayNumber, newMonthNumber, newYearNumber, newDescription));
	}
}
