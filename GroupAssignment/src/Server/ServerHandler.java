package Server;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Date;

public class ServerHandler implements Runnable{

    //A private Socket variable used for creating sockets for the server
    private Socket socket = null;
    //Setting the BufferedReader and PrintStream reading and writing from the server and client
    private BufferedReader requestInput = null;
    private PrintStream responseOutput = null;
    //
    private Store myServer;

    //A public constructor to initialize the socket and
    public ServerHandler(Socket socket, Store myServer) throws IOException{
        this.socket = socket;
        requestInput = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        responseOutput = new PrintStream(socket.getOutputStream(),true);
        this.myServer = myServer;
    }

    //Implementing the void run() function from the Runnable interface
    public void run(){
        //Prinitng connected to show when the client is connected
        System.out.println("Connected Player 1");
        String strLine;
        while(true){
            try {
                if((strLine = requestInput.readLine())!= null){
                    myServer.strGameState = strLine;
                    //Displaying that the game is updated for each player's run
                    System.out.println("strGame updated!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}