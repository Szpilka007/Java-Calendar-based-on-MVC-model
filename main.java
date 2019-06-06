
import javax.swing.*;
public class main {
	
	public static void main(String[] args) {
		EventManager eventManager = new EventManager();
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		GUI gui = new GUI();
		eventManager.addEvent(22, 6, 2019, "Pierwsze wydarzenie");
		eventManager.addEvent(3, 7, 2019, "Pierwsze wydarzenie");
		eventManager.addEvent(9, 5, 2019, "Pierwsze wydarzenie");
		eventManager.addEvent(10, 5, 2019, "Pierwsze wydarzenie");
		eventManager.addEvent(9, 5, 2019, "Pierwsze wydarzenie");
		

		eventManager.getEventsOnDate(9, 5, 2019);
		System.out.println(eventManager.getEventsOnDate(9, 5, 2019).size());
		//eventManager.writeToXMLFile("D:\\Projekty\\Java\\Projekt\\xmlfile.xml");
		eventManager.modifyEvent(4, 12, 6, 2019, "Zmodyfikowane wydarzenie");
		
		/*
		System.out.println();
		eventManager.showEvents();
		
		System.out.println();
		eventManager.removeTooOldEvents(10, 5, 2019);
		System.out.println();
		eventManager.showEvents();
		eventManager.removeTooOldEvents(31, 12, 2019);
		eventManager.showEvents();
		eventManager.loadFromXMLFile("D:\\Projekty\\Java\\Projekt\\xmlfile.xml");
		eventManager.showEvents();
		
		*/
		
		
		
		
		//eventManager.addEventToSQL(0);
		//eventManager.addEventToSQL(1);
		//eventManager.addEventToSQL(2);
		//eventManager.addEventToSQL(3);
		//eventManager.addEventToSQL(4);
		
		
		//eventManager.deleteEventFromSQL(1);
		
		//eventManager.deleteAllEventsFromSQL();
		
		//eventManager.modifyEventInSQL(0, 7, 12, 2019, "Modifykacja przez SQL");
		//eventManager.showEvents();
		
		//System.out.println(eventManager.allEventsFromSQLToString());
		
		//for(Event e: eventManager.getAllEventsFromSQL()) {
			//System.out.println(e.toString());
		//}
		
		
		
	}

}
