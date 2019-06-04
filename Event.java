import java.util.Calendar;
import java.util.Date;

public class Event {
	private int ID;
	private int dayNumber;
	private int monthNumber;
	private int yearNumber;
	private String description;
	
	public Event(int iD, int dayNumber, int monthNumber, int yearNumber, String description) {
		super();
		this.ID = iD;
		this.dayNumber = dayNumber;
		this.monthNumber = monthNumber;
		this.yearNumber = yearNumber;
		this.description = description+"\n";
	}

	public int getID() {
		return ID;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public int getYearNumber() {
		return yearNumber;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		return "Event [getID()=" + getID() + ", getDayNumber()=" + getDayNumber() + ", getMonthNumber()="
				+ getMonthNumber() + ", getYearNumber()=" + getYearNumber() + ", getDescription()=" + getDescription()
				+ "]";
	}
	
	public Date toDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, this.dayNumber);
		calendar.set(Calendar.MONTH, this.monthNumber-1);
		calendar.set(Calendar.YEAR, this.yearNumber);
		
		Date eventDate = calendar.getTime();
	
		return eventDate;
	}
	
	
}
