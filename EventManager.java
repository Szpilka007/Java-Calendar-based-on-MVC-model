import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class EventManager {

	private EventContainer eventContainer;
	final static String dateFormat = "yyyy-MM-dd";
	
	public EventManager() {
		this.eventContainer = new EventContainer();
	}
	
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
	public void addEvent(int dayNumber, int monthNumber, int yearNumber, String description, String name) {
		if (checkEventDate(dayNumber, monthNumber, yearNumber)) {
			eventContainer.addEvent(dayNumber, monthNumber, yearNumber, description, name);
		}
		else
			System.err.println("Wprowadzona data wykracza poza zakres");
		
	}
	
	public Vector<Event> getEventsOnDate(int dayNumber, int monthNumber, int yearNumber) {
		return eventContainer.getEventsOnDate(dayNumber, monthNumber, yearNumber);
	}

	public Event getEvent(String name,int dayNumber, int monthNumber, int yearNumber){
		return eventContainer.getEvent(name,dayNumber,monthNumber,yearNumber);
	}
	
	public void showEvents() {
		eventContainer.showEvents();
	}
	
	public void modifyEvent(int eventID, int newDayNumber, int newMonthNumber, int newYearNumber, String newDescription, String newName) {
		eventContainer.modifyEvent(eventID, newDayNumber, newMonthNumber, newYearNumber, newDescription, newName);
	}
	
	public void deleteEvent(int eventID) {
		eventContainer.deleteEvent(eventID);
	}

	public void deleteEve(String name, int Day, int month, int year) { 
		if(eventContainer.deleteEv(name,Day,month,year))
			System.out.println("Usuniêto wydarzenie");
		
		else
			System.out.println("Nie ma takiego wydarzenia");
	}
	
	public void removeTooOldEvents(int dayNumber, int numberOfMonths, int yearNumber) {
		eventContainer.removeTooOldEvents(dayNumber, numberOfMonths, yearNumber);
	}
	
	public void writeToXMLFile(String xmlFilePath) {
		eventContainer.writeToXMLFile(xmlFilePath);
	}
	
	public void loadFromXMLFile(String xmlFilePath) {
		eventContainer.loadFromXMLFile(xmlFilePath);
	}
	
	public void addEventToSQL(int eventID) {
		eventContainer.addEventToSQL(eventID);
	}
	
	public void deleteEventFromSQL(int eventID) {
		eventContainer.deleteEventFromSQL(eventID);
	}
	
	
	public void loadEventsFromSQL() {
		eventContainer.loadEventsFromSQL();
	}
	
	public void exportToCSV(String outlookPath) {
		eventContainer.exportToCSV(outlookPath);
	}
	
	public Vector<Event> getFilterEventsWithString(String filtr) {
		return eventContainer.getFilterEventsWithString(filtr);
	}
		

}
