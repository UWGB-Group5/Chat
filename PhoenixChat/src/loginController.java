import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {

	@FXML
	private TextField displayNameTextField;
	
	@FXML
	private Button enterRoomButton;
	
	//what happens when enter room button is pressed
	@FXML
	void enterRoomButtonPressed(ActionEvent event) {
	    	try {
	    		//Store the user's input into variable displayName
	    		String displayName = displayNameTextField.getText();
	    		
	    		//Transfer name to other controller
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("chatRoom.fxml"));
	    		Parent root = loader.load();
	    		
	    		//Change the scene
	    		roomController roomCtrl = loader.getController();
	    		roomCtrl.transferName(displayName);
	    		Scene scene2 = new Scene(root);
	    		scene2.getStylesheets().add("style.css");
	    		
	    		//Get the stage information
	    		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    		
	    		window.setScene(scene2);
	    		window.show();
	    		
	    	}
	    	
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	}
	
}
