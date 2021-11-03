package Client;
import javafx.application.Platform;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Date;

public class ClientHandler implements Runnable{
    //A private Socket variable used for creating sockets for the server
    private Socket socket = null;
    //Setting the BufferedReader and PrintStream reading and writing from the server and client
    private BufferedReader requestInput = null;
    private DataOutputStream responseOutput = null;
    //A private variable to store the TicTacToe game
    private TicTacToeGame myGame;

    //A public constructor to initialize the private variables
    public ClientHandler(Socket socket,TicTacToeGame myGame) throws IOException{
        this.socket = socket;
        requestInput = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        responseOutput = new DataOutputStream(socket.getOutputStream());
        this.myGame = myGame;
    }

    //Implementing the void run() function from the Runnable interface
    public void run(){
        String strLine;
        while(true){
            try {
                if((strLine = requestInput.readLine())!= null){
                    //If the board is empty, print that the reset board is required.
                    if(strLine == "eeeeeeeee"){
                        System.out.println("Reset Instruction Received.");
                        //Closing the connection with the client once the board is empty (since new game starts now)
                        System.exit(0);
                    }
                    System.out.println("Update Received: " + strLine);
                    myGame.update(strLine);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}