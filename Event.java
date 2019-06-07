import java.util.Calendar;
import java.util.Date;

public class Event {
	private int ID;
	private int dayNumber;
	private int monthNumber;
	private int yearNumber;
	private String description;
	private String name;
	
	

	

	public Event(int iD, int dayNumber, int monthNumber, int yearNumber, String description, String name) {
		super();
		this.ID = iD;
		this.dayNumber = dayNumber;
		this.monthNumber = monthNumber;
		this.yearNumber = yearNumber;
		this.description = description;
		this.name = name;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public void setMonthNumber(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	public void setYearNumber(int yearNumber) {
		this.yearNumber = yearNumber;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getName() {
		return this.name;
	}
	
	
	@Override
	public String toString() {
		return "Event [ID=" + ID + ", dayNumber=" + dayNumber + ", monthNumber=" + monthNumber + ", yearNumber="
				+ yearNumber + ", description=" + description + ", name=" + name + "]\n";
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
