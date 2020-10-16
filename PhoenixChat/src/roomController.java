import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class roomController {
	
	private String SentText;
	private String RecievedText;
	private static String name;
	private static DatagramSocket socket;
	private InetAddress address;
	Client client;
	//private final VBox chatBox = new VBox(5);
	private ArrayList<Label> messages = new ArrayList<>();
	private ArrayList<Label> IncomingMessages = new ArrayList<>();
	//private ScrollPane container = new ScrollPane();
	private int index = 0;
	private int incIndex = 0;

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
	public VBox chatBoxReceive;
	
	@FXML
	private ScrollPane container;

	//Set label equal to user's display name from login (Figure out how to share info between scenes)
	
	
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
	    public void initialize() throws SocketException {
	        //container.setContent(chatBoxSend); 
	    	Platform.setImplicitExit(false);
	    }
		
		@FXML
		void SendButtonPressed(ActionEvent event) {
			
			if (MessageBoxTextField.getText().trim().isEmpty())
			{
				//Display error message to enter text
			}
			else
			{
				messages.add(new Label("You: " + MessageBoxTextField.getText()));
	            messages.get(index).setAlignment(Pos.BOTTOM_LEFT);
	            chatBoxSend.getChildren().add(messages.get(index));
//	            System.out.println(name + ":"+messages.get(index)+"\\e");
	            client.send(name + ":"+messages.get(index).getText()+"\\e");
	            index++;
	            MessageBoxTextField.setText("");
	                      
			}
			
		}
		
		public void transferName(String text) {
			name = text;
			client = new Client("localhost", 7654, name, chatBoxReceive);
			
		}
}
