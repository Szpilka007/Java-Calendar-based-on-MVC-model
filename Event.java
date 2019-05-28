public class Event {
    private int id;
    private int dayNumber;
    private String moth;
    private int year;
    private String description;
    public Event(int id,int dayNumber,String month, int year,String description){
        this.id = id;
        this.dayNumber= dayNumber;
        this.moth = month;
        this.year = year;
        this.description = description;
    }
    public int getId(){
        return this.id;
    }
    public String getDescription(){ return this.description; }
    public int getDayNumber(){ return this.dayNumber;}
    public int getYear(){ return this.year;}
    public String getMoth(){ return this.moth;}
    public String showEvent(){
        return "Id: "+ this.id+" "+"Data: "+this.getDayNumber()+" "+this.getMoth()+" "+this.getYear()+" Description: "+this.description;
    }
}
