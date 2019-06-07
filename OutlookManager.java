import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class OutlookManager {
	
	public void exportToCSV(Vector<Event> eventsList, String outlookPath){
		File file = new File(outlookPath);
		
		try {
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			for (Event e:eventsList) {
				String obj = e.getName() + ";" + e.getDescription() + ";" + String.valueOf(e.getDayNumber())+"/"+String.valueOf(e.getMonthNumber())+"/"+String.valueOf(e.getYearNumber()) +";TRUE" +"\n";
				fileWriter.write(obj);
			}
			fileWriter.close();			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
