import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserPreferenceController {
	
	String currentName;
	String newName = " ";
	Color currentColor = Color.BLACK;
	Color newColor;
	Image currentImage;
	Image newImage;	
	
    @FXML
    private Label UserNameLabel;

    @FXML
    private ColorPicker ColorPicker;

    @FXML
    private Button ChangeImageButton;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private ImageView IconPreview;
    
    @FXML
    private TextField UsernameTextField;
	
	public void setColor(Color C) {
		currentColor = C;
	}
	
	public void setImage(Image I)
	{
		currentImage = I;
	}
	
	
    @FXML
    void ColorPickerChanged(ActionEvent event) {
    	newColor = ColorPicker.getValue();  	
    }
    
    @FXML
    void ChangeImageButtonPressed(ActionEvent event) throws Exception {
        // configure dialog allowing selection of a file 
        FileChooser fileChooser = new FileChooser();               
        fileChooser.setTitle("Select File");

        // display files in folder from which the app was launched
        fileChooser.setInitialDirectory(new File(".")); 

        // display the FileChooser
        File file = fileChooser.showOpenDialog(
           borderPane.getScene().getWindow());               

        // process selected Path or display a message
        if (file != null) {
           file.toPath();
           BufferedImage image = ImageIO.read(file);
           Image i = SwingFXUtils.toFXImage(image, null );  
           newImage = i;
           if (i != null)
           {
           IconPreview.setImage(i);
	           
           }
        }
        else {
        	
        }
    }

    @FXML
    void SaveSettingsButtonPressed(ActionEvent event) throws Exception {
    	
		//Transfer name to other controller
		FXMLLoader loader = new FXMLLoader(getClass().getResource("chatRoom.fxml"));
		Parent root = loader.load();
		
		//Change the scene
		roomController roomCtrl = loader.getController();
		roomCtrl.setLabelColor(changeColor());
		roomCtrl.setIcon(newImage);
		roomCtrl.transferName(SendName());
		Scene scene2 = new Scene(root);
		
		//Get the stage information
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene2);
		window.show();
    }
    
	public void RecieveName(String text) {
		currentName = text;
		UserNameLabel.setText(text);
	}
	
	public String SendName()
	{
		if (newName == currentName || UsernameTextField.getText() == "EnterNewName")
		{
			return currentName;
		}
		else
		{
			newName = UsernameTextField.getText();
			return newName;
		}
		
	}
    
	public Color changeColor()
	{	
		if (newColor == currentColor || newColor == null )
		{
			return currentColor;
		}
		else
		{
			return newColor;
		}
	}
	
	public Image changeImage()
	{
		if (newImage == currentImage || newImage == null )
		{
			return currentImage;
		}
		else
		{
			return newImage;
		}
	}
	
}
