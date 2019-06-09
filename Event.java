import java.util.Calendar;
import java.util.Date;

public class Event {
	private int ID;
	private int dayNumber;
	private int monthNumber;
	private int yearNumber;
	private String description;
	private String name;
	
	

	/**
	 * 
	 * @param ID int ID of the event
	 * @param dayNumber int, Day of the event
	 * @param monthNumber int Month of the event
	 * @param yearNumber int Year of the event
	 * @param description String Description of the event
	 * @param name String Name of the event
	 */

	public Event(int ID, int dayNumber, int monthNumber, int yearNumber, String description, String name) {
		super();
		this.ID = ID;
		this.dayNumber = dayNumber;
		this.monthNumber = monthNumber;
		this.yearNumber = yearNumber;
		this.description = description;
		this.name = name;
	}
	
	/**
	 * Used to set Day Number
	 * @param dayNumber Day to set the event
	 */
	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}
	
	/**
	 * Used to set Month Number
	 * @param monthNumber Month to set the event
	 */
	public void setMonthNumber(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	/**
	 * Used to set Year Number
	 * @param yearNumber Year to set the event
	 */
	public void setYearNumber(int yearNumber) {
		this.yearNumber = yearNumber;
	}

	/**
	 * Used to set Description
	 * @param description Description for the event
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Used to set Name
	 * @param name Name for the event
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return int ID - ID of the event
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return int dayNumber - Day of the event
	 */
	public int getDayNumber() {
		return dayNumber;
	}

	/**
	 * @return int monthNumber - Month of the event
	 */
	public int getMonthNumber() {
		return monthNumber;
	}

	/**
	 * @return int yearNumber - Year of the event
	 */
	public int getYearNumber() {
		return yearNumber;
	}

	/**
	 * @return String description - Description of the event
	 */
	public String getDescription() {
		return description;
	}
	
	/** 
	 * @return String name - Returns Name of the event
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Returns String containing all data from the class
	 */
	@Override
	public String toString() {
		return "Event [ID=" + ID + ", dayNumber=" + dayNumber + ", monthNumber=" + monthNumber + ", yearNumber="
				+ yearNumber + ", description=" + description + ", name=" + name + "]";
	}

	/**
	 * @return Date eventDate - Returns compiled date of the event
	 */
	public Date toDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, this.dayNumber);
		calendar.set(Calendar.MONTH, this.monthNumber-1);
		calendar.set(Calendar.YEAR, this.yearNumber);
		
		Date eventDate = calendar.getTime();
	
		return eventDate;
	}
	
	
}
