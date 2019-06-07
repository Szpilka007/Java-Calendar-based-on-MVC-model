import java.util.Calendar;
import java.util.Date;
import java.util.Vector;



public class EventContainer {

	private Vector<Event> eventsList;
	private XMLManager xmlManager;
	private SQLManager sqlManager;
	private OutlookManager outlookManager;
	
	public EventContainer() {
		this.eventsList = new Vector<Event>();
		this.xmlManager = new XMLManager();
		this.sqlManager = new SQLManager();
		this.outlookManager = new OutlookManager();
	}
	
	public Event getEvent(int eventID) {
		for (Event e: eventsList) 
			if (e.getID() == eventID)
				return e;
		
		return null;
		
	}
	
	public Vector<Event> getEventsList() {
		return eventsList;
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
	
	public void addEvent(int id, int dayNumber, int monthNumber, int yearNumber, String description, String name) {
		
		Event event = new Event(id, dayNumber, monthNumber, yearNumber, description, name);
		
		if (getEvent(event.getID()) != null) {
			System.out.println("Istnieje wydarzenie o takim ID");
			return;
		}
		eventsList.add(event);
		addEventToSQL(id);
		System.out.println("Zmodyfikowano wydarzenie");
	}
	
	public void addEvent(Event event) {
		if (getEvent(event.getID()) != null) {
			System.out.println("Istnieje wydarzenie o takim ID");
			return;
		}
		eventsList.add(event);
		addEventToSQL(event.getID());
		System.out.println("Dodano wydarzenie");			
	}
	
	public void addEvent(int dayNumber, int monthNumber, int yearNumber, String description, String name) {
		int eventID = getMinimumEventID();
		Event event = new Event(eventID ,dayNumber, monthNumber, yearNumber, description, name);
		eventsList.add(event);
		addEventToSQL(eventID);
		System.out.println("Dodano wydarzenie");
	}
	
	public boolean deleteEvent(int eventID) {
		if (getEvent(eventID) instanceof Event) {
			deleteEventFromSQL(eventID);
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
	
	public void modifyEvent(int eventID, int newDayNumber, int newMonthNumber, int newYearNumber,String newDescription, String newName) {
		if (getEvent(eventID) != null) {
			getEvent(eventID).setDayNumber(newDayNumber);
			getEvent(eventID).setMonthNumber(newMonthNumber);
			getEvent(eventID).setYearNumber(newYearNumber);
			getEvent(eventID).setDescription(newDescription);
			getEvent(eventID).setName(newName);
			deleteEventFromSQL(eventID);
			addEventToSQL(eventID);
		}
			

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
				deleteEventFromSQL(eventsList.get(i).getID());
				deleteEvent(eventsList.get(i).getID());
				i--; //je¿eli nie zatrzymamy iteracji nastêpuje pominiêcie elementów
			}	
	}
	
	public void writeToXMLFile(String xmlFilePath) {
		xmlManager.writeToXMLFile(getEventsList(), xmlFilePath);	
	}
	
	

	public void loadFromXMLFile(String xmlFilePath) {
		Vector<Event> loadedEvents = xmlManager.loadFromXMLFile(getEventsList(), xmlFilePath);	
		for (Event e: loadedEvents)
			addEvent(e);
		
		java.util.Collections.sort(eventsList, new EventIDComparator());
			
	}
	
	public void addEventToSQL(int eventID) {
		sqlManager.createEvent(getEvent(eventID));
	}
	
	public void deleteEventFromSQL(int eventID) {
		sqlManager.deleteEvent(getEvent(eventID));
	}
	
	public void loadEventsFromSQL() {
		for (Event e: sqlManager.getAllEvents())
			eventsList.add(e);
	}
	
	public void exportToCSV(String outlookPath) {
		outlookManager.exportToCSV(getEventsList(), outlookPath);
	}
	
}
