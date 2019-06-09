import java.util.Calendar;
import java.util.Date;
import java.util.Vector;



public class EventContainer {

	private Vector<Event> eventsList;
	private XMLManager xmlManager;
	private SQLManager sqlManager;
	private OutlookManager outlookManager;
	
	/**
	 * Creates new EventContainer
	 */
	public EventContainer() {
		this.eventsList = new Vector<Event>();
		this.xmlManager = new XMLManager();
		this.sqlManager = new SQLManager();
		this.outlookManager = new OutlookManager();
	}
	
	/**
	 * Get event based on the ID
	 * @param eventID int ID of the event
	 * @return Event or null if not found
	 */
	public Event getEvent(int eventID) {
		for (Event e: eventsList) 
			if (e.getID() == eventID)
				return e;
		
		return null;
		
	}
	
	/**
	 * Returns Vector of events
	 * @return Vector of Events
	 */
	public Vector<Event> getEventsList() {
		return eventsList;
	}
	
	/**
	 * Get the minimum ID of the events + 1
	 * @return int eventID
	 */
	public int getMinimumEventID() {
		if (eventsList.size() == 0)
			return 0;
		
		else {
			java.util.Collections.sort(eventsList, new EventIDComparator());
			return eventsList.lastElement().getID()+1;
		}
	}
	
	/**
	 * Gets event on day
	 * @param dayNumber int Day of the event
	 * @param monthNumber int Month of the event
	 * @param yearNumber int Year of the event
	 * @return Event
	 */
	public Vector<Event> getEventsOnDate(int dayNumber, int monthNumber, int yearNumber) {
		Vector<Event> eventsOnDate = new Vector<Event>();
		for (Event e:eventsList)
			if (e.getDayNumber() == dayNumber && e.getMonthNumber() == monthNumber && e.getYearNumber() == yearNumber)
				eventsOnDate.add(e);
		
		return eventsOnDate;
	}
	
	/**
	 * Add new Event
	 * @param id int ID of the event
	 * @param dayNumber int Day of the event
	 * @param monthNumber int Month of the event
	 * @param yearNumber int Year of the event
	 * @param description String description of the event
	 * @param name String name of the event
	 */
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
	
	/**
	 * Adds event
	 * @param event Event to add
	 */
	public void addEvent(Event event) {
		if (getEvent(event.getID()) != null) {
			System.out.println("Istnieje wydarzenie o takim ID");
			return;
		}
		eventsList.add(event);
		addEventToSQL(event.getID());
		System.out.println("Dodano wydarzenie");			
	}
	
	/**
	 * Adds event
	 * @param dayNumber int Day of the event
	 * @param monthNumber int Month of the event
	 * @param yearNumber int Year of the event
	 * @param description String description of the event
	 * @param name String name of the event
	 */
	public void addEvent(int dayNumber, int monthNumber, int yearNumber, String description, String name) {
		int eventID = getMinimumEventID();
		Event event = new Event(eventID ,dayNumber, monthNumber, yearNumber, description, name);
		eventsList.add(event);
		addEventToSQL(eventID);
		System.out.println("Dodano wydarzenie");
	}
	
	/**
	 * Deletes event based on the ID
	 * @param eventID int ID of searched event
	 * @return true if deleted, false if not
	 */
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

	/**
	 * Delete event based on name and date
	 * @param name String name of the event
	 * @param day int Day of the event
	 * @param month int Month of the event
	 * @param year int Year of the event
	 * @return true if deleted, false if not
	 */
	public boolean deleteEv(String name, int day, int month, int year){
		for(Event e:eventsList)	{
			if(name == e.getName() && day == e.getDayNumber() && month == e.getMonthNumber() && year == e.getYearNumber())	{
				
				int id = e.getID();
				eventsList.remove(getEvent(id));
				deleteEventFromSQL(id);				
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get event based on the name and date
	 * @param name String name of the event
	 * @param day int Day of the event
	 * @param month int Month of the event
	 * @param year int Year of the event
	 * @return Event or null
	 */
	public Event getEvent(String name, int day, int month, int year){
		for(Event e:eventsList)
		{
			if(name == e.getName() && day == e.getDayNumber() && month == e.getMonthNumber() && year == e.getYearNumber())
			{
				return e;
			}
		}
		return null;
	}

	/**
	 * Shows events through toString method
	 */
	public void showEvents() {
		for (Event e:eventsList)
			System.out.println(e.toString());
	}
	
	/**
	 * Modify event
	 * @param eventID int ID of the event
	 * @param newDayNumber int New Day of the event
	 * @param newMonthNumber int New Month of the event
	 * @param newYearNumber int New Year of the event
	 * @param newDescription String New Description of the event
	 * @param newName String New Name of the event
	 */
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
	
	/**
	 * Remove events before given date
	 * @param dayNumber int Day of the Date
	 * @param monthNumber int Month of the Date
	 * @param yearNumber int Year of the Date
	 */
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
				i--;
			}	
	}
	
	/**
	 * Write events to .xml file
	 * @param xmlFilePath String path of the file
	 */
	public void writeToXMLFile(String xmlFilePath) {
		xmlManager.writeToXMLFile(getEventsList(), xmlFilePath);	
	}
	
	
	/**
 	* Load Events from the .xml file
 	* @param xmlFilePath String path of the file
 	*/
	public void loadFromXMLFile(String xmlFilePath) {
		Vector<Event> loadedEvents = xmlManager.loadFromXMLFile(getEventsList(), xmlFilePath);	
		for (Event e: loadedEvents)
			addEvent(e);
		
		java.util.Collections.sort(eventsList, new EventIDComparator());
			
	}
	
	/**
	 * Adds event to SQL database
	 * @param eventID int ID of the event
	 */
	public void addEventToSQL(int eventID) {
		sqlManager.createEvent(getEvent(eventID));
	}
	
	/**
	 * Delete event from SQL database
	 * @param eventID int ID of the event
	 */
	public void deleteEventFromSQL(int eventID) {
		sqlManager.deleteEvent(getEvent(eventID));
	}
	
	/**
	 * Loads events from SQL database
	 */
	public void loadEventsFromSQL() {
		for (Event e: sqlManager.getAllEvents())
			eventsList.add(e);
	}
	
	/**
	 * Exports events to .csv file
	 * @param outlookPath String path of the file
	 */
	public void exportToCSV(String outlookPath) {
		outlookManager.exportToCSV(getEventsList(), outlookPath);
	}
	
	/**
	 * Get events that name contains "filtr"
	 * @param filtr String filtr that is searched through names
	 * @return Vector of Events containing "filtr"
	 */
	public Vector<Event> getFilterEventsWithString(String filtr) {
		Vector<Event> filteredEvents = new Vector<Event>();
		for (Event e: eventsList)
			if (e.getName().contains(filtr))
				filteredEvents.add(e);
		
		return filteredEvents;
	}
	
	/**
	 * Return Vector of Events from the next week
	 * @return Vector of Events
	 */
	public Vector<Event> getEventsInNextWeek() {
		Vector<Event> nextWeekEvents = new Vector<Event>();
		
		for (Event e:eventsList) {
			Calendar calendar = Calendar.getInstance();
			Calendar eventDate = Calendar.getInstance();
			eventDate.set(e.getYearNumber(), e.getMonthNumber()-1, e.getDayNumber());
			if (eventDate.after(calendar)) {
				calendar.add(Calendar.DATE, 7);
				if (eventDate.before(calendar))
						nextWeekEvents.add(e);
			}
					
		}		
		
		return nextWeekEvents;
	}
	
}
