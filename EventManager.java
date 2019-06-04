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
	public void addEvent(int dayNumber, int monthNumber, int yearNumber, String description) {
		if (checkEventDate(dayNumber, monthNumber, yearNumber)) {
			eventContainer.addEvent(dayNumber, monthNumber, yearNumber, description);
		}
		else
			System.err.println("Wprowadzona data wykracza poza zakres");
		
	}
	
	public Vector<Event> getEventsOnDate(int dayNumber, int monthNumber, int yearNumber) {
		return eventContainer.getEventsOnDate(dayNumber, monthNumber, yearNumber);
	}
	
	public void showEvents() {
		eventContainer.showEvents();
	}
	
	public void modifyEvent(int eventID, int newDayNumber, int newMonthNumber, int newYearNumber, String newDescription) {
		eventContainer.modifyEvent(eventID, newDayNumber, newMonthNumber, newYearNumber, newDescription);
	}
	
	public void deleteEvent(int eventID) {
		eventContainer.deleteEvent(eventID);
	}
	
	public void removeTooOldEvents(int numberOfMonths) {
		eventContainer.removeTooOldEvents(numberOfMonths);
	}
	
	
	

}
