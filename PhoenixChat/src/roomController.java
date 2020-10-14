import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class roomController {

	@FXML
	private Button exitRoomButton;
	
	@FXML
	private Label displayNameLabel;
	
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
}
