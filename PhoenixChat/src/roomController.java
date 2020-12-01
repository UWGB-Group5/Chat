import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class roomController {
	
	private static String name;
	Client client;
	private ArrayList<Label> messages = new ArrayList<>();
	private int index = 0;
	private Date currentDate = new Date();

	@FXML
	private Button exitRoomButton;
	
	@FXML
	private Button SendButton;
	
    @FXML
    private TextField MessageBoxTextField;
	
	@FXML
	private Label displayNameLabel;
	
	@FXML
	private VBox chatBoxSend;
	
	@FXML
	private VBox receiveBoxSend;
	
	@FXML
	private HBox hboxContainer;
	
	@FXML
	private ScrollPane container;
	
	//Create and format date object
	LocalDateTime dateObj = LocalDateTime.now();
	DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	String formattedDate = dateObj.format(formatDateObj);
	
	//what happens when exit room button is pressed
	@FXML
	void exitRoomButtonPressed(ActionEvent event) throws IOException {
	
			//Change the scene
			Parent root = FXMLLoader.load(getClass().getResource("chatLogin.fxml"));
			Scene scene1 = new Scene(root);
			
			//Get the stage information
			Stage window2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window2.setScene(scene1);
    		window2.show();
	}
	
    @FXML
    //Enter now activates SendButton.
    public void initialize() throws SocketException {
    	Platform.setImplicitExit(false);
    	container.vvalueProperty().bind(chatBoxSend.heightProperty());
    	MessageBoxTextField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
            	SendButton.fire();
            }
        });
    }
	
	@FXML
	void SendButtonPressed(ActionEvent event) {
		
		if (!MessageBoxTextField.getText().trim().isEmpty())
		{
			messages.add(new Label("You: " + MessageBoxTextField.getText() + "\n"+ formattedDate)); //Add timestamp to bottom of message
            messages.get(index).setAlignment(Pos.BOTTOM_LEFT);
            messages.get(index).setId("chat");
            messages.get(index).setWrapText(true);;
            chatBoxSend.getChildren().add(messages.get(index));
            receiveBoxSend.getChildren().add(new Label(""));
//	            System.out.println(name + ":"+messages.get(index)+"\\e");
            client.send(name + ":"+MessageBoxTextField.getText()+"\\e");
            index++;
            MessageBoxTextField.setText("");
		}
		
	}
	
	
	public void transferName(String text) {
		name = text;
		client = new Client("localhost", 7654, name, receiveBoxSend, chatBoxSend);
		displayNameLabel.setText(name);
	}
}
