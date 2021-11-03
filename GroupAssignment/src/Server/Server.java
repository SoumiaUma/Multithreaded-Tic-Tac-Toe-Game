package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server extends Application {
    //A private socket variable initialized to null for player 1
    private Socket socket1 = null;
    //A private socket variable initialized to null for player 2
    private Socket socket2 = null;
    //A private server socket variable initialized to null
    private ServerSocket serverSocket = null;

    /**
     * A void function to create a new thread for the server
     * @param primaryStage  A stage variable used for the JavaFx application
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        new Thread(() -> {
            threadShop();
        }).start();

    }

    /**
     * A main function to launch the JavaFX application
     * @param args  A string array
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * A void function to create a thread and server socket to connect with the clients
     */
    public void threadShop(){
        try {
            //Initializing the server socket
            ServerSocket serverSocket = new ServerSocket(888);

            //infinite loop like example in tutorial
            Server a = new Server();
            Store storage = new Store();
            storage.strGameState = "eeeeeeeee";
            while (true) {
                // Listen for a connection request, add new connection
                Socket socket = serverSocket.accept();
                //Adding player 1 to the server by creating a thread
                ServerHandler handler = new ServerHandler(socket,storage);
                Thread handlerThread = new Thread(handler);
                handlerThread.start();
                //Adding player 2 to the server by creating a thread
                ServerHandler2 handler2 = new ServerHandler2(socket,storage);
                Thread handlerThread2 = new Thread(handler2);
                handlerThread2.start();
            }
        } catch (IOException e) {
        }
        try {
            //Closing the server socket
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
