import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class loginTest extends Application {
	
	//Can Reuse this code for any application
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("chatLogin.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.setTitle("Phoenix Chat");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}
	//Can Reuse this code for any application
}