import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class roomController {
	
	private String SentText;
	private String RecievedText;
	Client client;

	@FXML
	private Button exitRoomButton;
	
	@FXML
	private Button SendButton;
	
	@FXML
	private Button GetMessages;
	
    @FXML
    private TextField MessageBoxTextField;
	
	@FXML
	private Label displayNameLabel;
	
    @FXML
    private Label SentMessage7;

    @FXML
    private Label SentMessage6;

    @FXML
    private Label SentMessage5;

    @FXML
    private Label SentMessage4;

    @FXML
    private Label SentMessage3;

    @FXML
    private Label SentMessage2;

    @FXML
    private Label SentMessage1;

    @FXML
    private Label RecievedMessage7;

    @FXML
    private Label RecievedMessage6;

    @FXML
    private Label RecievedMessage5;

    @FXML
    private Label RecievedMessage4;

    @FXML
    private Label RecievedMessage3;

    @FXML
    private Label RecievedMessage2;

    @FXML
    private Label RecievedMessage1;
	
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
	    public void initialize() {
			client = new Client("localhost", 7654, "Matt");
			//client.listen();
	    }
		
		@FXML
		void SendButtonPressed(ActionEvent event) {
			
			if (MessageBoxTextField.getText() == "")
			{
				//Display error message to enter text
			}
			else
			{
				
				// Validates for Sent message box labels being empty then sets text values
				SentText = MessageBoxTextField.getText();
				client.send("Matt: " + SentText + "\\e");
				if (SentMessage1.getText() == "" && RecievedMessage1.getText() == "") 
				{
					SentMessage1.setText(SentText);
				}
				else if (SentMessage2.getText() == "" && RecievedMessage2.getText() == "") 
				{
					SentMessage2.setText(SentText);
				}
				else if (SentMessage3.getText() == "" && RecievedMessage3.getText() == "") 
				{
					SentMessage3.setText(SentText);
				}
				else if (SentMessage4.getText() == "" && RecievedMessage4.getText() == "") 
				{
					SentMessage4.setText(SentText);
				}
				else if (SentMessage5.getText() == "" && RecievedMessage5.getText() == "") 
				{
					SentMessage5.setText(SentText);
				}
				else if (SentMessage6.getText() == "" && RecievedMessage6.getText() == "") 
				{
					SentMessage6.setText(SentText);
				}
				else if (SentMessage7.getText() == "" && RecievedMessage7.getText() == "") 
				{
					SentMessage7.setText(SentText);
				}
				else
				{
					SentMessage7.setText(SentMessage6.getText());
					SentMessage6.setText(SentMessage5.getText());
					SentMessage5.setText(SentMessage4.getText());
					SentMessage4.setText(SentMessage3.getText());
					SentMessage3.setText(SentMessage2.getText());
					SentMessage2.setText(SentMessage1.getText());
					SentMessage1.setText(SentText);
				}			
			}
			
		}
		
		@FXML
		void MessageButtonPressed ()
		{
			// Validates for Recieved message box labels being empty then sets text value
			RecievedText = client.GetMessage();
			if (RecievedText == "" || RecievedText == null)
			{
				
			}			
			else if (SentMessage1.getText() == "" && RecievedMessage1.getText() == "") 
			{
				RecievedMessage1.setText(RecievedText);
			}
			else if (SentMessage2.getText() == "" && RecievedMessage2.getText() == "") 
			{
				RecievedMessage2.setText(RecievedText);
			}
			else if (SentMessage3.getText() == "" && RecievedMessage3.getText() == "") 
			{
				RecievedMessage3.setText(RecievedText);
			}
			else if (SentMessage4.getText() == "" && RecievedMessage4.getText() == "") 
			{
				RecievedMessage4.setText(RecievedText);
			}
			else if (SentMessage5.getText() == "" && RecievedMessage5.getText() == "") 
			{
				RecievedMessage5.setText(RecievedText);
			}
			else if (SentMessage6.getText() == "" && RecievedMessage6.getText() == "") 
			{
				RecievedMessage6.setText(RecievedText);
			}
			else if (SentMessage7.getText() == "" && RecievedMessage7.getText() == "") 
			{
				RecievedMessage7.setText(RecievedText);
			}
			else
			{
				RecievedMessage7.setText(RecievedMessage6.getText());
				RecievedMessage6.setText(RecievedMessage5.getText());
				RecievedMessage5.setText(RecievedMessage4.getText());
				RecievedMessage4.setText(RecievedMessage3.getText());
				RecievedMessage3.setText(RecievedMessage2.getText());
				RecievedMessage2.setText(RecievedMessage1.getText());
				RecievedMessage1.setText(RecievedText);
			}
		}
}
