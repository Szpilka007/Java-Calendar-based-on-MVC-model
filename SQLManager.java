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
	
	/**
	 * Tries to connect to SQL server
	 */
	private void connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tries to disconnect from SQL server
	 */
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
	
	/**
	 * Adds Event to SQL database
	 * @param event Event to add
	 */
	public void createEvent(Event event) {
		connect();
		
		String querry = "insert into events values(?,?,?,?,?,?);";
		
		try (PreparedStatement stmt = con.prepareStatement(querry)){
			stmt.setLong(1, event.getID());
			stmt.setInt(2, event.getDayNumber());
			stmt.setInt(3, event.getMonthNumber());
			stmt.setInt(4, event.getYearNumber());
			stmt.setString(5, event.getDescription());
			stmt.setString(6, event.getName());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		disconnect();
	}
	
	/**
	 * Deletes event from SQL database
	 * @param event Event to delete
	 */
	public void deleteEvent(Event event) {
		connect();
		
		String querry = "delete from events where ID = ?;";
		
		try (PreparedStatement stmt = con.prepareStatement(querry)){
			stmt.setInt(1, event.getID());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		
	}
	
	/**
	 * Saves all Events from SQL server into vector
	 * @return Vector of Events
	 */
	public Vector<Event> getAllEvents() {
		Vector<Event> events = new Vector<Event>();
		
		connect(); 
		String querry = "Select * from events;";
		
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(querry);
			
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
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
