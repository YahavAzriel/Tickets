package ticketer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	String id;
	TextField t = new TextField();
	Event e;
	Pane root;
	Stage stage;
	Controller c;
	Event event;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage=stage;
		try { //loading fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("layout.fxml"));
			root = loader.load();
			c = loader.getController(); //saving controller class
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Pane ticketScan = c.getTicketCheckPane();
		Button activateScan = c.getActivateScanButton();
		activateScan.setOnAction(new Scan(ticketScan));
		Button createEvent = c.getCreateEvent();
		createEvent.setOnAction(new CreateEvent(c.getCreateEventPane()));
		Scene s = new Scene(root);
		stage.setScene(s);
		stage.show();
	}
	private ArrayList<Node> getShownNodes() //returns a list of all nodes that are currently shown to user
	{
		ArrayList<Node> shown = new ArrayList<>();
		for (Node n : root.getChildren())
			if (n.isVisible())
				shown.add(n);
		return shown;

	}

	class Scan implements EventHandler<ActionEvent> //handler for switching panes to ticket scanner
	{
		Pane ticketScan;
		public Scan(Pane ticketScan) {
			this.ticketScan=ticketScan;
		}

		@Override
		public void handle(ActionEvent event) {
			List<Node> shown = getShownNodes();
			for (Node n : shown) //hide all nodes that are currently shown
				n.setVisible(false);
			ticketScan.setVisible(true); //show pane that needed
		Button insertButton = c.getInsertButton();
		insertButton.setOnAction(new Check());
		Button exitScan = c.getExitScanButton();
		exitScan.setOnAction(e->{
			for (Node n : shown)
				n.setVisible(true);
			ticketScan.setVisible(false); //return all panes
		});
		}

		}


	class Check implements EventHandler<ActionEvent> //handler for check button, uses the ticket and change its status
	// NEED TO CHANGE AND SEARCH IN DB!!!! for yahav
	{

		@Override
		public void handle(ActionEvent arg0) {
			id=c.getTicketId().getText();
			if (e.use(id)) //change here
			{
				Label t = c.getValidText();
				t.setText("Ticket is valid!");
			}
			else {
				Label t = c.getValidText();
				t.setText("Ticket is invalid or used!");
			}

		}

	}
/*
 * creates an event and adds all tickets to the db with available status
 */
	class CreateEvent implements EventHandler<ActionEvent>
	{
		int i=0;
		Pane toShow;
		public CreateEvent(Pane toShow)
		{
			this.toShow=toShow;
		}
		@Override
		public void handle(ActionEvent arg0) {
			List<Node> shown = getShownNodes();
			for (Node n : shown)
				n.setVisible(false);
			toShow.setVisible(true);
			Button createButton = c.getCreateButton();
			createButton.setOnAction(ev->{
				event=new Event(c.getEventName().getText(),Integer.parseInt(c.getTicketNumber().getText()),Double.parseDouble(c.getTicketPrice().getText()));
				Label l = c.getCreateEventStatus();
				Connection con;
				String url = "jdbc:mysql://localhost:3306/javabase?useLegacyDatetimeCode=false&serverTimezone=UTC";
				String username = "java";
				String password = "password";
				l.setText("Connecting database...");
				try (Connection connection = DriverManager.getConnection(url, username, password)) {
				    l.setText("Database connected!");
				    for (Ticket t : event.getTickets())
					{
						String query = " insert into tickets (num, uniqueID, eventName, price,ticketStatus)"
						        + " values (?, ?, ?, ?, ?)";
						PreparedStatement state = connection.prepareStatement(query);
						state.setInt (1, i);
						state.setString(2, t.getID());
						state.setString(3, t.getEventName());
						state.setString  (4, Double.toString(t.getPrice()));
						state.setString(5, t.getStatusString());
						state.execute();
					}

			} catch (SQLException e) {
				l.setText("Database failed to connect");
			}
				l.setText("Event Created");
			});
			Button exitCreate = c.getExitCreate();
			exitCreate.setOnAction(listener->{
				for (Node n : shown)
					n.setVisible(true);
				toShow.setVisible(false);
			});


	}


}
}
