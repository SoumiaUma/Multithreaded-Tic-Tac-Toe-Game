package Server;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Date;

public class ServerHandler2 implements Runnable{

    //A private Socket variable used for creating sockets for the server
    private Socket socket = null;
    //Setting the BufferedReader and PrintStream reading and writing from the server and client
    private BufferedReader requestInput = null;
    private PrintStream responseOutput = null;
    //A private Store variable that stores the game state
    private Store myServer;

    //A public constructor to initialize the socket and
    public ServerHandler2(Socket socket, Store myServer) throws IOException{
        this.socket = socket;
        requestInput = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        responseOutput = new PrintStream(socket.getOutputStream(),true);
        this.myServer = myServer;
    }

    //Implementing the void run() function from the Runnable interface
    public void run() {
        System.out.println("Connected Player 2");
        String strPrev = "eeeeeeeee";
        while (true) {
            try {
                if (strPrev != myServer.strGameState) {
                    System.out.println("update detected!");
                    //Writing to the client the game state of the game
                    responseOutput = new PrintStream(socket.getOutputStream(), true);
                    responseOutput.println(myServer.strGameState);
                    //Setting the previous game state to the current one
                    strPrev = myServer.strGameState;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}