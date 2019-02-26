package robot.view.ctrl;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import robot.manifest.Data;
import robot.transmission.TCPIPConnection;

public class HomeFXController implements Initializable {
    
    public static int startStatus=0;
    public static boolean forward,backward,right,left;
    public static int MODE=Data.MANUAL;       
//<editor-fold defaultstate="collapsed" desc="comment">
    @FXML
    private ImageView autoProImg;
    @FXML
    private ImageView backImg;
    @FXML
    private Button startBtn;
    @FXML
    private Button autopBtn;
    @FXML
    private ImageView stopIndicator;
    @FXML
    private Label msg;
    @FXML
    private ProgressBar pbar;
    @FXML
    private ProgressIndicator pcircle;
    @FXML
    private ImageView forwardCtrl;
    @FXML
    private Label wt;
    @FXML
    private Label at;
    @FXML
    private Label st;
    @FXML
    private Label dt;
    @FXML
    private Pane manualPane;
//</editor-fold>
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pbar.setVisible(false);
        pcircle.setVisible(false);
        startBtn.setStyle("-fx-background-image: url('/robot/assets/login/start_1.png')");
    }  

    @FXML
    void autoPilotOnAction(ActionEvent event) {
        startStatus=TCPIPConnection.createNewConnection();
        if(startStatus==1 && MODE==Data.MANUAL){
            manualPane.setVisible(false);
            autoProImg.setVisible(true);
        }else if(startStatus==1){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Stop the Connection.");
            alert.setHeaderText("Disonnect");
            alert.setContentText("Are you want to stop the connection");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){

            } else {
                alert.close();
            }
            msg.setText("Client is already connected.");
            msg.setTextFill(Color.CYAN);
        }
    }

    @FXML
    void robotControlOnAction(ActionEvent event) {
        
    }

    @FXML
    void startConnection(ActionEvent event) {
        startStatus=TCPIPConnection.createNewConnection();
        if(startStatus==0){
            msg.setText("The connection is started successfully.");
            msg.setTextFill(Color.GREENYELLOW);
            pbar.setVisible(true);
            pcircle.setVisible(true);
            for(int i=0;i<101;i+=10){
                pbar.setProgress(i);
                pcircle.setProgress(i);
            }
            stopIndicator.setVisible(false);
            startBtn.setStyle("-fx-background-image: url('/robot/assets/login/stop.png')");
        }else if(startStatus==1){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Stop the Connection.");
            alert.setHeaderText("Disonnect");
            alert.setContentText("Are you want to stop the connection");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                TCPIPConnection.closeConnection();
                startBtn.setStyle("-fx-background-image: url('/robot/assets/login/start_1.png')");
            } else {
                alert.close();
            }
            msg.setText("Client is already connected.");
            msg.setTextFill(Color.CYAN);
        }
    }
    
    @FXML
    void onKeyPressed(KeyEvent event) {
        if(event.getCode()==KeyCode.W){wt.setTextFill(Color.GREEN); forward=true;}  
        if(event.getCode()==KeyCode.S){st.setTextFill(Color.GREEN); backward=true;}
        if(event.getCode()==KeyCode.D){dt.setTextFill(Color.GREEN); right=true;} 
        if(event.getCode()==KeyCode.A){at.setTextFill(Color.GREEN); left=true;} 
        robotControl();
    }
    
    @FXML
    void onKeyReleased(KeyEvent event) {
        if(event.getCode()==KeyCode.W){wt.setTextFill(Color.WHITE); forward=false;}
        if(event.getCode()==KeyCode.S){st.setTextFill(Color.WHITE); backward=false;} 
        if(event.getCode()==KeyCode.D){dt.setTextFill(Color.WHITE); right=false;} 
        if(event.getCode()==KeyCode.A){at.setTextFill(Color.WHITE); left=false;}
        robotControl();
    }
    
    void robotControl(){
        if(forward==true && backward==false){
            if(right==true){    
                TCPIPConnection.sendCommand(Data.FORWARD);
            }else if(left==true){
                TCPIPConnection.sendCommand(Data.FORWARD_LEFT);
            }else{
                TCPIPConnection.sendCommand(Data.FORWARD_RIGHT);
            }
        }else if(forward==false && backward==true){
            if(right==true){
                TCPIPConnection.sendCommand(Data.BACKWARD);
            }else if(left==true){
                TCPIPConnection.sendCommand(Data.BACKWARD_LEFT);
            }else{
                TCPIPConnection.sendCommand(Data.BACKWARD_RIGHT);
            }
        }
    }
}
