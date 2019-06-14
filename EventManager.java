import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class EventManager {

	private EventContainer eventContainer;
	final static String dateFormat = "yyyy-MM-dd";
	/**
	 * EventManager constructor
	 */
	public EventManager() {
		this.eventContainer = new EventContainer();
	}
	/**
	 * Checks if date is correct
	 * @param dayNumber int Day
	 * @param monthNumber int Month
	 * @param yearNumber int Year
	 * @return boolean true or false
	 */
	public boolean checkEventDate(int dayNumber, int monthNumber, int yearNumber) {
		
		try {
			DateFormat df = new SimpleDateFormat(dateFormat);
			String date = new String (String.valueOf(yearNumber)+"-"+String.valueOf(monthNumber)+"-"+String.valueOf(dayNumber));	
			df.setLenient(false);
			df.parse(date);
			return true;
		} 
		catch (ParseException e) {
			return false;
		}

		
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
		if (checkEventDate(dayNumber, monthNumber, yearNumber)) {
			eventContainer.addEvent(dayNumber, monthNumber, yearNumber, description, name);
		}
		else
			System.err.println("Wprowadzona data wykracza poza zakres");
		
	}
	
	/**
	 * Gets events on day
	 * @param dayNumber int Day of the event
	 * @param monthNumber int Month of the event
	 * @param yearNumber int Year of the event
	 * @return Vector of the Events
	 */
	public Vector<Event> getEventsOnDate(int dayNumber, int monthNumber, int yearNumber) {
		return eventContainer.getEventsOnDate(dayNumber, monthNumber, yearNumber);
	}

	/**
	 * Get event based on the name and date
	 * @param name String name of the event
	 * @param dayNumber int Day of the event
	 * @param monthNumber int Month of the event
	 * @param yearNumber int Year of the event
	 * @return Event or null
	 */
	public Event getEvent(String name,int dayNumber, int monthNumber, int yearNumber){
		return eventContainer.getEvent(name,dayNumber,monthNumber,yearNumber);
	}
	
	/**
	 * Get event based on the ID
	 * @param id int ID of the event
	 * @return Event or null if not found
	 */
	public Event getEvent(int id){
		return eventContainer.getEvent(id);
	}
	
	/**
	 * Shows all events
	 */
	public void showEvents() {
		eventContainer.showEvents();
	}

	public Vector<Event> getAllEvents(){ return eventContainer.getEventsList();};
	
	/**
	 * Modify event
	 * @param eventID int ID of the event
	 * @param newDayNumber int New Day of the event
	 * @param newMonthNumber int New Month of the event
	 * @param newYearNumber int New Year of the event
	 * @param newDescription String New Description of the event
	 * @param newName String New Name of the event
	 */
	public void modifyEvent(int eventID, int newDayNumber, int newMonthNumber, int newYearNumber, String newDescription, String newName) {
		eventContainer.modifyEvent(eventID, newDayNumber, newMonthNumber, newYearNumber, newDescription, newName);
	}
	
	
	/**
	 * Deletes event based on the ID
	 * @param eventID int ID of searched event
	 */
	public void deleteEvent(int eventID) {
		eventContainer.deleteEvent(eventID);
	}

	/**
	 * Delete event based on name and date
	 * @param name String name of the event
	 * @param day int Day of the event
	 * @param month int Month of the event
	 * @param year int Year of the event
	 */
	public void deleteEve(String name, int day, int month, int year) { 
		if(eventContainer.deleteEv(name,day,month,year))
			System.out.println("Usuniêto wydarzenie");
		
		else
			System.out.println("Nie ma takiego wydarzenia");
	}
	
	/**
	 * Remove events before given date
	 * @param dayNumber int Day of the Date
	 * @param monthNumber int Month of the Date
	 * @param yearNumber int Year of the Date
	 */
	public void removeTooOldEvents(int dayNumber, int monthNumber, int yearNumber) {
		eventContainer.removeTooOldEvents(dayNumber, monthNumber, yearNumber);
	}
	
	/**
	 * Write events to .xml file
	 * @param xmlFilePath String path of the file
	 */
	public void writeToXMLFile(String xmlFilePath) {
		eventContainer.writeToXMLFile(xmlFilePath);
	}
	
	/**
 	* Load Events from the .xml file
 	* @param xmlFilePath String path of the file
 	*/
	public void loadFromXMLFile(String xmlFilePath) {
		eventContainer.loadFromXMLFile(xmlFilePath);
	}
	
	/**
	 * Adds event to SQL database
	 * @param eventID int ID of the event
	 */
	public void addEventToSQL(int eventID) {
		eventContainer.addEventToSQL(eventID);
	}
	
	/**
	 * Delete event from SQL database
	 * @param eventID int ID of the event
	 */
	public void deleteEventFromSQL(int eventID) {
		eventContainer.deleteEventFromSQL(eventID);
	}
	
	/**
	 * Loads events from SQL database
	 */
	public void loadEventsFromSQL() {
		eventContainer.loadEventsFromSQL();
	}
	
	/**
	 * Exports events to .csv file
	 * @param outlookPath String path of the file
	 */
	public void exportToCSV(String outlookPath) {
		eventContainer.exportToCSV(outlookPath);
	}
	
	/**
	 * Get events that name contains "filtr"
	 * @param filtr String filtr that is searched through names
	 * @return Vector of Events containing "filtr"
	 */
	public Vector<Event> getFilterEventsWithString(String filtr) {
		return eventContainer.getFilterEventsWithString(filtr);
	}
	
	/**
	 * Return Vector of Events from the next week
	 * @return Vector of Events
	 */
	public Vector<Event> getEventsInNextWeek() {
		return eventContainer.getEventsInNextWeek();
	}
		

}
