package robot.transmission;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import robot.manifest.Data;

public class TCPIPConnection {
    
    //socket for client.
    private static Socket socket=null;
    private static OutputStream outStream=null;
    private static InputStream inputStream=null;
    
    //create new connection with socket as a client.
    public static int createNewConnection(){
        int status=0;
        //create the client socket.
        if(!isSocketAvailable()){
            try{
                socket=new Socket(Data.HOST,Data.PORT);
                outStream=socket.getOutputStream();
                inputStream=socket.getInputStream();
            }catch(UnknownHostException e1){
                System.out.println("Cannot find the host.");
                System.exit(1);
            }catch(IOException e2){
                System.out.println("Cannot connect to the host");
                System.exit(2);
            }
            System.out.println("The connection is created successfully.");
        }else{
            System.out.println("the client is already connected.");
            status=1;   //for inform as already connected.
        }
        readMSG();
        return status;
    }
    
    //to send a command as a byte stream.
    public static int sendCommand(int command){
        
        //int status.
        int status=0;
        
        if(!isSocketAvailable()){
            createNewConnection();
        }

        //if connected the stream is write.
        if(socket.isConnected()){
            try{
                byte[] cmdByte=new byte[2];
                cmdByte[0]=(byte)command;
		byte[] temp;
		temp = "!".getBytes("US-ASCII");
		cmdByte[1]=temp[0];
                outStream.write(cmdByte);
                //outStream.write(stream);
            }catch(Exception e){
                status=1;
            }
        }
        return status;
    }
    
    //close the connections.
    public static int closeConnection(){
        int status=0;
        if(socket.isConnected()){
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Socket cannot close...");
                status =1;
            }
        }
        return status;
    }
    
    public static boolean isSocketAvailable(){
        boolean flag=false;
        //validation of the connections.
        if(!(socket==null)){
            if(socket.isConnected())
                flag=true;
        }
        return flag;
    }
    
    public static void readMSG(){
        int read=0;
        try {
            read=inputStream.read();
        } catch (IOException ex) {
            System.out.println("Input stream cannot be read.");
        }
        System.out.println("read value : "+read+"");
    }
}
