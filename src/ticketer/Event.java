package ticketer;

import java.util.ArrayList;
import java.util.List;
//change to map <String,Ticket>
//maybe date
public class Event {

	private String name;
	private int numOfTickets;
	private int available;
	private double ticketPrice;
	private List<Ticket> tickets = new ArrayList<>();
	public Event(String name,int num,double ticketPrice) //creates an event
	{
		this.name=name;
		numOfTickets=num;
		available=num;
		for (int i = 0; i<num;i++) //creates tickets for the event
		{
			Ticket t = new Ticket(ticketPrice,this);
			System.out.println(t.getID()); //delete later, prints all uniqueID's
			tickets.add(t);
		}
	}

	public boolean use(String uniqueID) //turn ticket from purchased to used
	{
		boolean temp = false;
		for (Ticket t :tickets)
		{
			if (uniqueID.equals(t.getID()))
			{

				temp=t.use();
			}
		}
		return temp;
	}

	public boolean decrease() //decreases ticket number
	{
		if (available==0)
			return false;
		available--;
		return true;
	}


	public boolean increase() //increases ticket number
	{
		if (available==numOfTickets)
			return false;
		available++;
		return true;
	}
	public List<Ticket> getTickets() //returns tickets list
	{
		return tickets;
	}
	public String getName() //return event name
	{
		return name;
	}
	public boolean hasTickets() //returns if there are tickets left
	{
		return available>0;
	}

}
