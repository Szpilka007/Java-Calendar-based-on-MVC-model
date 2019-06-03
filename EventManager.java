import java.util.Vector;

public class EventManager {

	private EventContainer eventContainer;
	
	public EventManager() {
		this.eventContainer = new EventContainer();
	}
	
	/*public boolean chechEventDate(int dayNumber, int monthNumber, int yearNumber) {
		switch (monthNumber) {
			case 1:
				if (dayNumber >= 1 && dayNumber <= 31)
					return true;
						
				else return false;
					
			case 2:
				if (dayNumber >= 1 && dayNumber <= 29)
					return true;
					
				else return false;
					
			case 3:
				if (dayNumber >= 1 && dayNumber <= 31)
					return true;
				
				else return false;
					
			case 4:
				if (dayNumber >= 1 && dayNumber <= 30)
					return true;
				else
					return false;
					
			case 5:
				if (dayNumber >= 1 && dayNumber <= 31)
					return true;
				
				else return false;
					
			case 6:
				if (dayNumber >= 1 && dayNumber <= 30)
					return true;
				
				else
					return false;		
				
			case 7:
				if (dayNumber >= 1 && dayNumber <= 31)
					return true;
				
				else return false;
					
			case 8:
				if (dayNumber >= 1 && dayNumber <= 31)
					return true;
					
				else
					return false;	
					
			case 9:
				if (dayNumber >= 1 && dayNumber <= 30)
					return true;
				else
					return false;	
					
				case 10:
					if (dayNumber >= 1 && dayNumber <= 31)
						return true;
					else
						return false;	
					
				case 11:
					if (dayNumber >= 1 && dayNumber <= 30)
						return true;
					else
						return false;
					
				case 12:
					if (dayNumber >= 1 && dayNumber <= 31)
						return true;
					else
						return false;	
					
			}
				
		}
		return true;
	}*/
	
	public void addEvent(int id, int dayNumber, int monthNumber, int yearNumber, String description) {
		Event event = new Event(id, dayNumber, monthNumber, yearNumber, description);
		eventContainer.addEvent(event);
	}
	
	public void showEvents() {
		eventContainer.showEvents();
	}
	
	/*
	}
	
	public void showEventInYear(int yearNumber) {
			for (int i = 0; i < 12; i++)
				years.elementAt(yearNumber-2019).showEventsInMonth(i+1);		
	}*/

}
