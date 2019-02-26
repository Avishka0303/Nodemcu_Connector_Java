package robot.manifest;

public interface Data {
    public static final String LOGIN_FXML="/robot/view/fxml/LoginFX.fxml";
    public static final String HOME_FXML="/robot/view/fxml/HomeFX.fxml";
    
    
    //-----------------------------------------------------------------------
    public static final int PORT = 2000;
    public static final String HOST="192.168.4.1";
    
    //------------------------command list-----------------------------------
    public static final int FORWARD=97;
    public static final int BACKWARD=98;
    public static final int FORWARD_LEFT=99;
    public static final int FORWARD_RIGHT=100;
    public static final int BACKWARD_LEFT=101;
    public static final int BACKWARD_RIGHT=102;
    
    //-------------------------Mode------------------------------------------
    public static final int MANUAL=0;
    public static final int AUTO=1;
}
