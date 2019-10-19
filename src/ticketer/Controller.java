package ticketer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {

	@FXML
	private Button buttonCheckId;
	@FXML
	private Label textValid;
	@FXML
	private TextField fieldTicketId;
	@FXML
	private Pane ticketCheckPane;
	@FXML
	private Button activateScanButton;
	@FXML
	private Button exitScanButton;
	@FXML
	private Button createEvent;
	@FXML
	private TextField eventName;
	@FXML
	private TextField ticketNumber;
	@FXML
	private TextField ticketPrice;
	@FXML
	private Pane createEventPane;
	@FXML
	private Label createEventStatus;
	@FXML
	private Button createButton;
	@FXML
	private Button exitCreate;

	public Button getExitCreate()
	{
		return exitCreate;
	}

	public Button getCreateButton()
	{
		return createButton;
	}
	public Button getInsertButton()
	{
		return buttonCheckId;
	}
	public Button getCreateEvent()
	{
		return createEvent;
	}
	public Button getExitScanButton()
	{
		return exitScanButton;
	}

	public Label getValidText()
	{
		return textValid;
	}
	public Label getCreateEventStatus()
	{
		return createEventStatus;
	}
	public TextField getTicketId()
	{
		return fieldTicketId;
	}
	public TextField getEventName()
	{
		return eventName;
	}
	public TextField getTicketNumber()
	{
		return ticketNumber;
	}
	public TextField getTicketPrice()
	{
		return ticketPrice;
	}
	public Pane getTicketCheckPane()
	{
		return ticketCheckPane;
	}
	public Pane getCreateEventPane()
	{
		return createEventPane;
	}

	public Button getActivateScanButton()
	{
		return activateScanButton;
	}
}
