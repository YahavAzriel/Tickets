package ticketer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLTest {
public static void main(String[] args) throws SQLException
{
	Connection c;
	String url = "jdbc:mysql://localhost:3306/javabase?useLegacyDatetimeCode=false&serverTimezone=UTC";
	String username = "java";
	String password = "password";

	System.out.println("Connecting database...");

	try (Connection connection = DriverManager.getConnection(url, username, password)) {
	    System.out.println("Database connected!");
		String query = " insert into tickets (num, uniqueID, price, eventNam, seatNum,rowNum,stat,firstName,lastName)"
		        + " values (?, ?, ?, ?, ?,?,?,?,?)";
		 PreparedStatement preparedStmt = connection.prepareStatement(query);
	     preparedStmt.setInt (1, 1);
	     preparedStmt.setInt (2, 1);
	     preparedStmt.setInt   (3, 10);
	     preparedStmt.setString(4, "Omer Adam");
	     preparedStmt.setInt(5, 0);
	     preparedStmt.setInt(6, 0);
	     preparedStmt.setString(7, "Available");
	     preparedStmt.setString(8, "yahav");
	     preparedStmt.setString(9, "azriel");
	     preparedStmt.execute();
	} catch (SQLException e) {
	    throw new IllegalStateException("Cannot connect the database!", e);
	}

}
}
