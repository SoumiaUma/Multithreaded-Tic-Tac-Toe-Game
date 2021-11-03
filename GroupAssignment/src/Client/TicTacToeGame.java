package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class TicTacToeGame extends Application {

    //Creating private variable to store the Print Stream and DataInputStream for the server and client communication
    private PrintStream out;
    private DataInputStream in;
    //Creating a Socket variable used for creating sockets and threads.
    public Socket socket;

    //Setting the server address and server port.
    public  static String SERVER_ADDRESS = "localhost";
    public  static int SERVER_PORT = 888;

    //A private string variable to store the active player 'x' or 'o' that is playing
    private String chrActive;
    //A public string 2d array to store the state of the player for a move (the position the player is at n the board)
    public String[][] chrState;
    //A canvas 2d array to store Canvas objects used to create the tic-tac-toe board.
    public Canvas[][] canvArr;
    //A Stage variable used for returning back to the main screen
    public Stage myStage;

    /**
     * A void function that creates the objects and scene for the JavaFx application.
     * @param stage  A Stage object used for the JavaFx application player vs player.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Initializing the stage object to use to return back to the main screen
        myStage = stage;

        //Initializing the canvas objects to represent the squares on the board.
        Canvas C00 = new Canvas(100, 100);
        Canvas C01 = new Canvas(100, 100);
        Canvas C02 = new Canvas(100, 100);
        Canvas C10 = new Canvas(100, 100);
        Canvas C11 = new Canvas(100, 100);
        Canvas C12 = new Canvas(100, 100);
        Canvas C20 = new Canvas(100, 100);
        Canvas C21 = new Canvas(100, 100);
        Canvas C22 = new Canvas(100, 100);

        //Setting the active player to 'x' (so human player goes first).
        chrActive = "x";
        //An array of characters that represent the state of the board (so what squares are filled)
        chrState = new String[][]{{"e", "e", "e"}, {"e", "e", "e"}, {"e", "e", "e"}};

        //A grid pane to place the canvas squares that make up the board.
        GridPane pane = new GridPane();
        pane.add(C00, 0, 0);
        pane.add(C01, 1, 0);
        pane.add(C02, 2, 0);
        pane.add(C10, 0, 1);
        pane.add(C11, 1, 1);
        pane.add(C12, 2, 1);
        pane.add(C20, 0, 2);
        pane.add(C21, 1, 2);
        pane.add(C22, 2, 2);

        //Initializing the socket and input and output streams
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        out = new PrintStream(socket.getOutputStream(),true);
        in = new DataInputStream((socket.getInputStream()));


        /**
         * To set the actions for each square that is clicked.
         */
         C00.setOnMouseClicked(event -> {
             if(chrState[0][0] == "e") {
                 chrState[0][0] = chrActive;
                 System.out.println("zulu");
                 try {
                     makeMoveGraphic(C00);
                     makeMoveBackEnd();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 checkForWin();
             }
         });

        C01.setOnMouseClicked(event -> {
            if(chrState[0][1] == "e") {
                chrState[0][1] = chrActive;
                try {
                    makeMoveGraphic(C01);
                    makeMoveBackEnd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkForWin();
            }
        });

        C02.setOnMouseClicked(event -> {
            if(chrState[0][2] == "e") {
                chrState[0][2] = chrActive;
                try {
                    makeMoveGraphic(C02);
                    makeMoveBackEnd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkForWin();
            }
        });

        C10.setOnMouseClicked(event -> {
            if(chrState[1][0] == "e") {
                chrState[1][0] = chrActive;
                try {
                    makeMoveGraphic(C10);
                    makeMoveBackEnd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkForWin();
            }
        });

        C11.setOnMouseClicked(event -> {
            if(chrState[1][1] == "e") {
                chrState[1][1] = chrActive;
                try {
                    makeMoveGraphic(C11);
                    makeMoveBackEnd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkForWin();
            }
        });

        C12.setOnMouseClicked(event -> {
            if(chrState[1][2] == "e") {
                chrState[1][2] = chrActive;
                try {
                    makeMoveGraphic(C12);
                    makeMoveBackEnd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkForWin();
            }
        });

        C20.setOnMouseClicked(event -> {
            if(chrState[2][0] == "e") {
                chrState[2][0] = chrActive;
                try {
                    makeMoveGraphic(C20);
                    makeMoveBackEnd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkForWin();
            }
        });

        C21.setOnMouseClicked(event -> {
            if(chrState[2][1] == "e") {
                chrState[2][1] = chrActive;
                try {
                    makeMoveGraphic(C21);
                    makeMoveBackEnd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkForWin();
            }
        });

        C22.setOnMouseClicked(event -> {
            if(chrState[2][2] == "e") {
                chrState[2][2] = chrActive;
                try {
                    makeMoveGraphic(C22);
                    makeMoveBackEnd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkForWin();
            }

        });

        //Initializing an array of Canvas objects to print the board
        canvArr = new Canvas[][]{{C00, C01, C02}, {C10, C11, C12}, {C20, C21, C22}};

        //A for loop to draw the canvas grid
        for(int i = 0;i < 3;i++){
            for(int j = 0;j<3;j++) {
                initCanvasBox(canvArr[i][j].getGraphicsContext2D());
            }
        }

        stage.setTitle("Player vs Player");
        stage.setScene(new Scene(pane, 300, 300));
        stage.show();

        //A new thread is created to connect with the server
        new Thread(() -> {
            ThreadShop();
        }).start();

    }

    /**
     * A void function to draw the board on the JavaFX screen
     * @param gc  The graphic 2d context used to draw the borders of the board
     */
    public void initCanvasBox(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        gc.strokeLine(0,0,0,100);
        gc.strokeLine(0,100,100,100);
        gc.strokeLine(100,100,100,0);
        gc.strokeLine(100,0,0,0);
        return;
    }

    /**
     * A boolean function that draws the shape X on the board for player X
     * @param gc  The graphic 2d context used to draw an O on the board
     * @return
     */
    public boolean setX(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        gc.strokeLine(20,20,80,80);
        gc.strokeLine(80,20,20,80);
        return true;
    }

    /**
     * A boolean function that draws the shape O on the board for player O
     * @param gc  The graphic 2d context used to draw an O on the board
     * @return
     */
    public boolean setO(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        gc.strokeOval(20,20,60,60);

        //gc.strokeLine(20,20,80,20);
        //gc.strokeLine(80,20,80,80);
        //gc.strokeLine(80,80,20,80);
        //gc.strokeLine(20,80,20,20);
        return true;
    }

    /**
     * A void function to call the shapes to draw on the board
     * based on the active character that is playing.
     * The active character playing is switched for next player.
     * @param canvas   The canvas on which the shapes are drawn
     * @throws IOException
     */
    private void makeMoveGraphic(Canvas canvas) throws IOException {
            if(chrActive == "x"){
                chrActive = "o";
                setX(canvas.getGraphicsContext2D());
            }else{
                chrActive = "x";
                setO(canvas.getGraphicsContext2D());
            }
    }

    /**
     * A string function that returns the player that won.
     * A for loop is used to check for rows, columns and diaganol wins
     * @return  The player that won the game
     */
    private String checkForWin(){
        //rows
        for(int i = 0;i < 3;i++){
            if(chrState[i][0] == "x" && chrState[i][1] == "x" && chrState[i][2] == "x"){
                alertPlayer("x");
                return "x";
            }else if(chrState[i][0] == "o" && chrState[i][1] == "o" && chrState[i][2] == "o"){
                alertPlayer("o");
                return "o";
            }
        }

        //cols
        for(int i = 0;i < 3;i++){
            if(chrState[0][i] == "x" && chrState[1][i] == "x" && chrState[2][i] == "x"){
                alertPlayer("x");
                return "x";
            }else if(chrState[0][i] == "o" && chrState[1][i] == "o" && chrState[2][i] == "o"){
                alertPlayer("o");
                return "o";
            }
        }

        //diagonals
        //SE
        if(chrState[0][0] == "x" && chrState[1][1] == "x" && chrState[2][2] == "x"){
            alertPlayer("x");
            return "x";
        }else if(chrState[0][0] == "o" && chrState[1][1] == "o" && chrState[2][2] == "o"){
            alertPlayer("o");
            return "o";
        }

        //SW
        if(chrState[0][2] == "x" && chrState[1][1] == "x" && chrState[2][0] == "x"){
            alertPlayer("x");
            return "x";
        }else if(chrState[0][2] == "o" && chrState[1][1] == "o" && chrState[2][0] == "o"){
            alertPlayer("o");
            return "o";
        }
        return "e";
    }

    /**
     * A void function that gives an alert box once the game is over.
     * @param chrWin  A character variable that stores the winner player
     */
    private void alertPlayer(String chrWin){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Player " + chrWin + " has won! Thanks for playing!"
                , ButtonType.OK);
        alert.show();

        if (alert.getResult() == ButtonType.OK) {
            //reset();
        }
    }


    /**
     * A void function that sends the new game state to the server.
     * @throws IOException
     */
    public void makeMoveBackEnd() throws IOException {
        out = new PrintStream(socket.getOutputStream());
        out.println(chrState[0][0] + chrState[0][1] + chrState[0][2] + chrState[1][0] + chrState[1][1] +
                chrState[1][2] + chrState[2][0] + chrState[2][1] + chrState[2][2]);
    }

    /**
     * A void function that updates the board based on the
     * string variable that stores the state of the game.
     * @param strNewState  A string to store the state of the game
     * @throws InterruptedException
     */
    public void update(String strNewState) throws InterruptedException {
        if(strNewState == "eeeeeeeee"){
            //System.exit(0);
        }

        if(strNewState.charAt(0) == 'o'){
            chrState[0][0] = "o";
            setO(canvArr[0][0].getGraphicsContext2D());
        }else if(strNewState.charAt(0) == 'x'){
            chrState[0][0] = "x";
            setX(canvArr[0][0].getGraphicsContext2D());
        }

        if(strNewState.charAt(1) == 'o'){
            chrState[0][1] = "o";
            setO(canvArr[0][1].getGraphicsContext2D());
        }else if(strNewState.charAt(1) == 'x'){
            chrState[0][1] = "x";
            setX(canvArr[0][1].getGraphicsContext2D());
        }

        if(strNewState.charAt(2) == 'o'){
            chrState[0][2] = "o";
            setO(canvArr[0][2].getGraphicsContext2D());
        }else if(strNewState.charAt(2) == 'x'){
            chrState[0][2] = "x";
            setX(canvArr[0][2].getGraphicsContext2D());
        }

        if(strNewState.charAt(3) == 'o'){
            chrState[1][0] = "o";
            setO(canvArr[1][0].getGraphicsContext2D());
        }else if(strNewState.charAt(3) == 'x'){
            chrState[1][0] = "x";
            setX(canvArr[1][0].getGraphicsContext2D());
        }

        if(strNewState.charAt(4) == 'o'){
            chrState[1][1] = "o";
            setO(canvArr[1][1].getGraphicsContext2D());
        }else if(strNewState.charAt(4) == 'x'){
            chrState[1][1] = "x";
            setX(canvArr[1][1].getGraphicsContext2D());
        }

        if(strNewState.charAt(5) == 'o'){
            chrState[1][2] = "o";
            setO(canvArr[1][2].getGraphicsContext2D());
        }else if(strNewState.charAt(5) == 'x'){
            chrState[1][2] = "x";
            setX(canvArr[1][2].getGraphicsContext2D());
        }

        if(strNewState.charAt(6) == 'o'){
            chrState[2][0] = "o";
            setO(canvArr[2][0].getGraphicsContext2D());
        }else if(strNewState.charAt(6) == 'x'){
            chrState[2][0] = "x";
            setX(canvArr[2][0].getGraphicsContext2D());
        }

        if(strNewState.charAt(7) == 'o'){
            chrState[2][1] = "o";
            setO(canvArr[2][1].getGraphicsContext2D());
        }else if(strNewState.charAt(7) == 'x'){
            chrState[2][1] = "x";
            setX(canvArr[2][1].getGraphicsContext2D());
        }

        if(strNewState.charAt(8) == 'o'){
            chrState[2][2] = "o";
            setO(canvArr[2][2].getGraphicsContext2D());
        }else if(strNewState.charAt(8) == 'x'){
            chrState[2][2] = "x";
            setX(canvArr[2][2].getGraphicsContext2D());
        }

        checkForWin();
    }

    /**
     * Makes threads for the client to connect with the server.
     * Made once this class is called.
     */
    public void ThreadShop(){

        TicTacToeGame a = this;
        try {
            ClientHandler handler = new ClientHandler(socket,a);
            Thread handlerThread = new Thread(handler);
            handlerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A function to return back to the main screen by creating an instance of the Main
     * class and calling the start function from the Main class.
     */
    public void retToMain(){
        Main mainScreen = new Main();
        try {
            mainScreen.start(myStage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
