import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;import java.util.Date;
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
	public void addEvent(int id, int dayNumber, int monthNumber, int yearNumber, String description) {
		if (checkEventDate(dayNumber, monthNumber, yearNumber)) {
			Event event = new Event(id, dayNumber, monthNumber, yearNumber, description);
			eventContainer.addEvent(event);
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
	
	/*
	}
	
	public void showEventInYear(int yearNumber) {
			for (int i = 0; i < 12; i++)
				years.elementAt(yearNumber-2019).showEventsInMonth(i+1);		
	}*/

}
