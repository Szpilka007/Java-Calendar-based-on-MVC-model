

public class month {
    private int numberOfDays;
    private int monthNumber;
    private day[] days = new day[31];

    public month(int days,int monthNumber) {
        this.numberOfDays = days;
        this.monthNumber = monthNumber;
        for(int i=0 ; i<numberOfDays; i++)
            this.days[i] = new day(i);
    }
    public int getDays(){ return this.numberOfDays; }
    public void addEventToDay(Event ev){
        days[ev.getDayNumber()].addEventToDay(ev);
    }
    public void showEventInMonth(){
        for(day d: days) {
            d.ShowEvents();
        }
    }
    public int getMonthNumber(){
        return this.monthNumber;
    }

}
