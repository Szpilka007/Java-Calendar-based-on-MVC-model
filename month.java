import java.util.Vector;

public class month {
    private int numberOfDays;
    private day[] days = new day[31];

    public month(int days) {
        this.numberOfDays = days;
        for(int i=0 ; i<31; i++)
            this.days[i] = new day(i);
    }
    public int getDays(){ return this.numberOfDays; }
    public void addEventToDay(Event ev){
        days[ev.getDayNumber()].addEventToDay(ev);
    }
    public void showEventInMonth(){
        for(day d: days){
            d.ShowEvents();
        }
    }

}
