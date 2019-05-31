import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Month {
    private int numberOfDays;
    private int monthNumber;
    private Day[] days = new Day[31];
    
    private int whatDayOfWeek(int dayNumber, int monthNumber, int yearNumber) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, yearNumber);
    	calendar.set(Calendar.MONTH, monthNumber-1);
    	calendar.set(Calendar.DAY_OF_MONTH, dayNumber);
    	Date date = calendar.getTime();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("u");
    	return Integer.parseInt(dateFormat.format(date));    	
    }
    
	public Month(int numberOfDays, int monthNumber, int yearNumber) {
		super();
		this.numberOfDays = numberOfDays;
		this.monthNumber = monthNumber;
		for (int i = 0; i < numberOfDays; i++) {
			days[i] = new Day(i+1, whatDayOfWeek(i+1, monthNumber, yearNumber));
		}
			
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public int getMonthNumber() {
		return monthNumber;
	}
	
	public Day getDay(int dayNumber) {
		return days[dayNumber-1];		
	}

	public void addEventToDay(Event event) {
		days[event.getDayNumber()-1].addEvent(event);
	}
	
	public Event getEvent(int eventID) {
		for (Day d : days)
			if (d.getEvent(eventID) != null)
				return d.getEvent(eventID);
		
		return null;
	}
	
	public void showEventsInMonth() {
		System.out.println("Month: " + String.valueOf(monthNumber));
		for (int i = 0; i < numberOfDays; i++)
			days[i].showEvents();
	}    
    
}
