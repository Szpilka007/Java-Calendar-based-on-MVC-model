import java.util.Calendar;
import java.util.Date;
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
	
	public int getMinimumEventID() {
		if (eventsList.size() == 0)
			return 0;
		
		else {
			java.util.Collections.sort(eventsList, new EventIDComparator());
			return eventsList.lastElement().getID()+1;
		}
	}
	
	public Vector<Event> getEventsOnDate(int dayNumber, int monthNumber, int yearNumber) {
		Vector<Event> eventsOnDate = new Vector<Event>();
		for (Event e:eventsList)
			if (e.getDayNumber() == dayNumber && e.getMonthNumber() == monthNumber && e.getYearNumber() == yearNumber)
				eventsOnDate.add(e);
		
		return eventsOnDate;
	}
	
	public void addEvent(int id, int dayNumber, int monthNumber, int yearNumber, String description) {
		
		Event event = new Event(id, dayNumber, monthNumber, yearNumber, description);
		
		if (getEvent(event.getID()) != null) {
			System.out.println("Istnieje wydarzenie o takim ID");
			return;
		}
		eventsList.add(event);
		System.out.println("Zmodyfikowano wydarzenie");
	}
	
	public void addEvent(int dayNumber, int monthNumber, int yearNumber, String description) {
		int eventID = getMinimumEventID();
		Event event = new Event(eventID ,dayNumber, monthNumber, yearNumber, description);
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
			addEvent(eventID, newDayNumber, newMonthNumber, newYearNumber, newDescription);
		java.util.Collections.sort(eventsList, new EventIDComparator());
	}
	
	public void removeTooOldEvents(int dayNumber, int monthNumber, int yearNumber) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DATE, dayNumber);
		calendar.set(Calendar.MONTH, monthNumber);
		calendar.set(Calendar.YEAR, yearNumber);
		Date currentDate = calendar.getTime();
		
		for (int i = 0; i < eventsList.size(); i++)
			if (currentDate.after(eventsList.get(i).toDate())) {
				System.out.println("Usunieto stare wydarzenie");
				deleteEvent(eventsList.get(i).getID());
				i--; //je¿eli nie zatrzymamy iteracji nastêpuje pominiêcie elementów
			}	
	}
	
	
}
