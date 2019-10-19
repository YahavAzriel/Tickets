package ticketer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ticket {

	private double price;
	private boolean isFree =false;
	private static Event event;
	private String uniqueID; //hashcode
	private Status status;
	private String firstName,lastName;
	private int row,seat;
	public Ticket(double price,Event event) //creates ticket and gives it unique id
	{
		this.price=price;
		this.event=event;
		if (price==0)
			isFree=true;
		status=Status.Available;
		if (event.decrease())
		{
		uniqueID=""+this.hashCode();
		}
	}

	public boolean use() //turn ticket to used from purchased
	{
		if (status==Status.Purchased)
		{
			status=Status.Used;
			return true;
		}
		return false;
	}

	public void changeStatus(Status status) //for manager, maybe delete?
	{
		this.status=status;
	}
/*Turns a ticket from available to purchase, assigns the details of the buyer on the DB
 * still needs to return uniqueid to maybe send mail or store somewhere
 * need to add purchase date
 */
	public static boolean Purchase(String firstName,String lastName,int row,int seat) //FINISH THIS ONE
	{
		String uniqueID;
		Date date= new Date();
				Connection c;
				String url = "jdbc:mysql://localhost:3306/javabase?useLegacyDatetimeCode=false&serverTimezone=UTC";
				String username = "java";
				String password = "password";

				System.out.println("Connecting database...");

				try (Connection connection = DriverManager.getConnection(url, username, password)) {
				    System.out.println("Database connected!");
					String query = "update tickets set firstName = '"+firstName+"',lastName='"+lastName+"', rowNum="+row+", seatNum="+seat+", ticketStatus='"+Status.Purchased.toString()+"' where ticketStatus='Available' limit 1";
					 PreparedStatement st = connection.prepareStatement(query);
				     st.execute();
				 //  ResultSet rs = st.executeQuery(); // check if returns uniqueID
				//   System.out.println(rs.toString());
				} catch (SQLException e) {
				    throw new IllegalStateException("Cannot connect the database!", e);
				}



		return true;

	}

	public static void main(String[] args) //test delete this
	{
		Purchase("adi","Hershhhhko",1,1);
	}

	public void sendMessage() //later change to email message
	{
		System.out.println("Your ticket is" + uniqueID);
	}

	public void cancelTicket()
	{
		if (status!=Status.Cancelled)
		status=Status.Cancelled;
		event.increase();
	}


	public String toString()
	{
		return ""+uniqueID;
	}

	public String getID()
	{
		return uniqueID;
	}

	public double getPrice()
	{
		return price;
	}
	public String getEventName()
	{
		return event.getName();
	}
	public String getStatusString()
	{
		return status.toString();
	}


	public enum Status
	{
		Available,Used,Cancelled,Purchased
	}


}
