import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
public class SQLManager {
	private static final String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=calendar;integratedSecurity=true;";
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	private void connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void disconnect() {
		if (con != null) {
			try {
				con.close();
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void createEvent(Event event) {
		connect();
		
		String querry = "insert into events values(?,?,?,?,?);";
		int id = 0;
		
		try {
			String sql = "select top 1 eventID from events order by eventID desc";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next())
				id = rs.getInt(1);
			
			id++;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (PreparedStatement stmt = con.prepareStatement(querry)){
			stmt.setLong(1, id);
			stmt.setInt(2, event.getDayNumber());
			stmt.setInt(3, event.getMonthNumber());
			stmt.setInt(4, event.getYearNumber());
			stmt.setString(5, event.getDescription());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		disconnect();
	}
	
	public void deleteEvent(Event event) {
		connect();
		
		String querry = "delete from events where dayNumber = ? and monthNumber = ? and yearNumber = ? and descriptionString = ?;";
		
		try (PreparedStatement stmt = con.prepareStatement(querry)){
			stmt.setInt(1, event.getDayNumber());
			stmt.setInt(2, event.getMonthNumber());
			stmt.setInt(3, event.getYearNumber());
			stmt.setString(4, event.getDescription());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		
	}
	
	public void deleteAllEvents() {
		connect();
		
		String querry = "delete from events;";
		
		try (PreparedStatement stmt = con.prepareStatement(querry)){
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
	}
	
	public String allEventsToString() {
		connect();
		String querry = "select * from events;";
		String allEventsToString = "";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(querry);
			
			while (rs.next())
				allEventsToString += (String.valueOf(rs.getInt(1)) + " " 
				+  String.valueOf(rs.getInt(2)) + " " 
				+  String.valueOf(rs.getInt(3)) + " " 
				+  String.valueOf(rs.getInt(4)) + " " 
				+  rs.getString(5) + "\n");
					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		return allEventsToString;
	}
	
	public Vector<Event> getAllEvents() {
		Vector<Event> events = new Vector<Event>();
		
		connect(); 
		String querry = "Select * from events;";
		
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(querry);
			
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
				events.add(event);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		
		return events;
	}
	
	

}
