import java.util.Comparator;

public class EventIDComparator implements Comparator<Event> {

	public EventIDComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Event arg0, Event arg1) {
		return (arg0.getID() - arg1.getID());
	}

}
