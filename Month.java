
public class Month {
    private int numberOfDays;
    private int monthNumber;
    private Day[] days = new Day[31];
	public Month(int numberOfDays, int monthNumber) {
		super();
		this.numberOfDays = numberOfDays;
		this.monthNumber = monthNumber;
		for (int i = 0; i < numberOfDays; i++)
			days[i] = new Day(i+1);
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
