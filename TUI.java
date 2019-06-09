import java.util.Scanner;

import javax.swing.JOptionPane;

public class TUI {
	private EventManager eventManager;
	private Scanner scanner;
	
	/**
	 * Add event through TUI
	 */
	private void addEvent() {
		Scanner input = new Scanner(System.in);
		System.out.println("ADD EVENT: ");
		System.out.println("Insert name");
		System.out.println();
		String name = input.nextLine();
		System.out.println("Insert description");
		System.out.println();
		String description = input.nextLine();
		System.out.println("Insert day");
		System.out.println();
		int day = input.nextInt();
		System.out.println("Insert month");
		System.out.println();
		int month = input.nextInt();
		System.out.println("Insert year");
		System.out.println();
		int year = input.nextInt();
		eventManager.addEvent(day, month, year, description, name);
	}
	
	/**
	 * Delete event through TUI
	 */
	private void deleteEvent() {
		Scanner input = new Scanner(System.in);
		System.out.println("DELETE EVENT: ");
		System.out.println("Insert event ID");
		System.out.println();
		int id = input.nextInt();
		eventManager.deleteEvent(id);
	}
	
	/**
	 * Delete too old events through TUI
	 */
	private void deleteTooOldEvents() {
		Scanner input = new Scanner(System.in);
		System.out.println("DELETE TOO OLD EVENTS: ");		
		System.out.println("Insert day");
		System.out.println();
		int day = input.nextInt();
		System.out.println("Insert month");
		System.out.println();
		int month = input.nextInt();
		System.out.println("Insert year");
		System.out.println();
		int year = input.nextInt();
		eventManager.removeTooOldEvents(day, month, year);
	}
	
	/**
	 * Modify event through TUI
	 */
	private void modifyEvent() {
		Scanner input = new Scanner(System.in);
		System.out.println("MODIFY EVENT: ");	
		System.out.println("Insert event ID");
		System.out.println();
		int id = input.nextInt();
		System.out.println("Insert new name");
		System.out.println();
		String name = input.nextLine();
		System.out.println("Insert new description");
		System.out.println();
		String description = input.nextLine();
		System.out.println("Insert new day");
		System.out.println();
		int day = input.nextInt();
		System.out.println("Insert new month");
		System.out.println();
		int month = input.nextInt();
		System.out.println("Insert new year");
		System.out.println();
		int year = input.nextInt();
		eventManager.modifyEvent(id, day, month, year, description, name);
	}
	
	/**
	 * Save to .xml file through TUI
	 */
	private void saveToXML() {
		Scanner input = new Scanner(System.in);
		System.out.println("SAVE TO XML FILE: ");	
		System.out.println("Insert new file path (end with .xml)");
		System.out.println();
		String path = input.next();
		eventManager.writeToXMLFile(path);
	}
	
	/**
	 * Load from .xml file through TUI
	 */
	private void loadFromXML() {
		Scanner input = new Scanner(System.in);
		System.out.println("LOAD FROM XML FILE: ");	
		System.out.println("Insert file path (end with .xml)");
		System.out.println();
		String path = input.next();
		eventManager.loadFromXMLFile(path);
	}
	
	/**
	 * Export events to .csv file through TUI
	 */
	private void exportToCSV() {
		Scanner input = new Scanner(System.in);
		System.out.println("EXPORT TO .CSV FILE: ");	
		System.out.println("Insert file path (end with .csv)");
		System.out.println();
		String path = input.next();
		eventManager.exportToCSV(path);
	}
	
	/**
	 * Filter events through TUI
	 */
	private void filteredEvents() {
		Scanner input = new Scanner(System.in);
		System.out.println("SHOW FILTERED EVENTS: ");	
		System.out.println("Insert string that might be contained in a name of event");
		System.out.println();
		String name = input.next();
		for (Event e:eventManager.getFilterEventsWithString(name))
			System.out.println(e.toString());
		
		System.out.println("ENTER ANYTHING TO CONTINUE");
		String press = input.next();
	}
	
	/**
	 * Get events in next week through TUI
	 */
	private void eventsInNextWeek() {
		Scanner input = new Scanner(System.in);
		System.out.println("EVENTS IN NEXT WEEK");
		for (Event e: eventManager.getEventsInNextWeek())
			System.out.println(e.toString());
		
		System.out.println("ENTER ANYTHING TO CONTINUE");
		String press = input.next();
		
	}
	
	/**
	 * TUI constructor - made by Jacob Guzek
	 */
	TUI() {
		eventManager = new EventManager();
		scanner = new Scanner(System.in);
			
		eventManager.loadEventsFromSQL();
		eventsInNextWeek();
		for(;;) {
			System.out.println("OPTIONS: \n"
							 + "1  | Show events\n"
							 + "2  | Add event\n"
							 + "3  | Delete event\n"
							 + "4  | Delete too old events\n"
							 + "5  | Modify event\n"
							 + "6  | Save to .xml File\n"
							 + "7  | Load from .xml File\n"
							 + "8  | Save to .csv\n"
							 + "9  | About program\n"
							 + "10 | Show events filtered\n"
							 + "11 | Events in next week\n"
							 + "12 | Exit\n");
			int option = scanner.nextInt();
			
			if(option == 1)
				eventManager.showEvents();
			
			else if(option == 2)
				addEvent();
			
			else if(option == 3)
				deleteEvent();
			
			else if(option == 4)
				deleteTooOldEvents();
			
			else if(option == 5)
				modifyEvent();
			
			else if(option == 6)
				saveToXML();
			
			else if(option == 7)
				loadFromXML();
				
			else if(option == 8)
				exportToCSV();
			
			else if(option == 9)
				System.out.println("Program made by Jakub Guzek and Adam Krzanowski");
			
			else if (option == 10)
				filteredEvents();
			
			else if (option == 11)
				eventsInNextWeek();
			
			else if(option == 12)
				System.exit(0);		
			
			else
				System.err.println("Wrong option");
			
			
		}
		
		
	}
}
