import java.util.Vector;

public class day {
    private int number;
    Vector<Event> daysEvents = new Vector<Event>();
    public day(int number) {
        this.number = number;
    }
    public int getNumber() {
        return this.number;
    }
    public void addEventToDay(Event ev){
        daysEvents.add(ev);
    }
    public void ShowEvents(){
        for(int i=0; i<daysEvents.size(); i++)
            daysEvents.get(i).showEvent();
    }

}
