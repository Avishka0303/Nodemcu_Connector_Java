package robot.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import robot.manifest.Data;

public class Main extends Application {
    
    public static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        //initialize the stage.
        primaryStage=stage;
        Parent root = FXMLLoader.load(getClass().getResource(Data.LOGIN_FXML));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
