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
	
	
	
}
