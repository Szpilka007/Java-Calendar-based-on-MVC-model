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
	
	public void deleteEvent(int eventID) {
		if (getEvent(eventID) instanceof Event)
			eventsList.remove(getEvent(eventID));
		else
			System.err.println("Nie ma takiego wydarzenia do usuniêcia");
	}

	public void showEvents() {
		for (Event e:eventsList)
			System.out.println(e.toString());
	}
}
