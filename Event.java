public class Event {
    private String id;
    private String description;
    public Event(String id,String description){
        this.id = id;
        this.description = description;
    }
    public String getId(){
        return this.id;
    }
    public String getDescription(){
        return this.description;
    }
    public String showEvent(){
        return this.id+" "+this.description;
    }
}
