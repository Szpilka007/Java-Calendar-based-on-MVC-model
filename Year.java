public class Year {
    private int yearNumber;
    private Month[] months = new Month[12];
	public Year(int yearNumber) {
		super();
		this.yearNumber = yearNumber;
		
		this.months[0] = new Month(31,1);

        if (yearNumber % 4 == 0)
            this.months[1] = new Month(28,2);

        else
            this.months[1] = new Month(29,2);

        this.months[2] = new Month(31,3);
        this.months[3] = new Month(30,4);
        this.months[4] = new Month(31,5);
        this.months[5] = new Month(30,6);
        this.months[6] = new Month(31,7);
        this.months[7] = new Month(31,8);
        this.months[8] = new Month(30,9);
        this.months[9] = new Month(31,10);
        this.months[10] = new Month(30,11);
        this.months[11] = new Month(31,12);
	}
	public int getYearNumber() {
		return yearNumber;
	}
	public Month getMonth(int monthNumber) {
		return months[monthNumber-1];
	}
	
	public void addEventToMonth (Event event) {
		months[event.getMonthNumber()-1].addEventToDay(event);
	}
	
	public Event getEvent(int eventID) {
		for (Month m : months)
			if (m.getEvent(eventID) != null)
				return m.getEvent(eventID);
		
		return null;
	}
	
	public void showEventsInMonth(int monthNumber) {
		months[monthNumber-1].showEventsInMonth();
	}
	

}
