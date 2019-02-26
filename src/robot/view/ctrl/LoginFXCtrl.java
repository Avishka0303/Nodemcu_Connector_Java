package robot.view.ctrl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import robot.main.Main;
import robot.manifest.Data;

public class LoginFXCtrl implements Initializable {
    
    @FXML
    private Button loignBtn;

    @FXML
    private TextField user_name;

    @FXML
    private PasswordField pass_word;

    @FXML
    void loginOnAction(ActionEvent event) {
        String username=user_name.getText();
        String pass=pass_word.getText();
        //if(username.equals("ghost") && pass.equals("avis")){
            try {
                Parent root=FXMLLoader.load(getClass().getResource(Data.HOME_FXML));
                Main.primaryStage.setScene(new Scene(root));
            } catch (IOException ex) {
                System.out.println("Cannot load HOME page.");
            }
        //}
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
    
    
}
