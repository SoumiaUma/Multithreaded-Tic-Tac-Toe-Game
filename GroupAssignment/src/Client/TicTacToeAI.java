package Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;


public class TicTacToeAI extends Application {
    //A private character variable to store the state of the player
    private char chrActive;
    //A private 2D character array to store the state of the position that human player and AI player has chosen
    private char[][] chrState;
    //Creating private array of Canvas object to store the array of the board
    private Canvas[][] canvArr;
    //Creating private canvas objects to represent the squares on the board.
    public Canvas C00;
    public Canvas C01;
    public Canvas C02;
    public Canvas C10;
    public Canvas C11;
    public Canvas C12;
    public Canvas C20;
    public Canvas C21;
    public Canvas C22;

    //A string variable to store the name of the player
    private String name;
    private Stage stage;

    //A constructor to initialize the player name and stage
    public TicTacToeAI(String name, Stage stage){
        this.name = name;
        this.stage = stage;
    }

    /**
     * A void function that creates the objects and scene for the JavaFx application.
     * @param stage   A Stage object used for the JavaFx application player vs AI
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        //Initializing the canvas objects to represent the squares on the board.
        C00 = new Canvas(100,100);
        C01 = new Canvas(100,100);
        C02 = new Canvas(100,100);
        C10 = new Canvas(100,100);
        C11 = new Canvas(100,100);
        C12 = new Canvas(100,100);
        C20 = new Canvas(100,100);
        C21 = new Canvas(100,100);
        C22 = new Canvas(100,100);

        //Setting the active player to 'x' (so human player goes first).
        chrActive = 'x';
        //An array of characters that represent the state of the board (so what squares are filled)
        chrState = new char[][]{{'e', 'e', 'e'}, {'e', 'e', 'e'}, {'e', 'e', 'e'}};

        //A grid pane to place the canvas squares that make up the board.
        GridPane pane = new GridPane();
        pane.add(C00,0,0);
        pane.add(C01,1,0);
        pane.add(C02,2,0);
        pane.add(C10,0,1);
        pane.add(C11,1,1);
        pane.add(C12,2,1);
        pane.add(C20,0,2);
        pane.add(C21,1,2);
        pane.add(C22,2,2);

        /**
         * To set the actions for each square that is clicked.
         */
        C00.setOnMouseClicked(event -> {
            if(chrState[0][0] == 'e') {
                makeMoveGraphic(C00);
                chrState[0][0] = chrActive;
                checkForWin();
                //check if won -> stop game
                //if not won -> makeBestMove
                //bestMove(int 1) --> returns best spot
                if(boardFull()==false){
                    makeBestMove();
                }
            }
        });

        C01.setOnMouseClicked(event -> {
            if(chrState[0][1] == 'e') {
                makeMoveGraphic(C01);
                chrState[0][1] = chrActive;
                checkForWin();
                if(boardFull()==false){
                    makeBestMove();
                }
            }
        });

        C02.setOnMouseClicked(event -> {
            if(chrState[0][2] == 'e') {
                makeMoveGraphic(C02);
                chrState[0][2] = chrActive;
                checkForWin();
                if(boardFull()==false){
                    makeBestMove();
                }
            }
        });

        C10.setOnMouseClicked(event -> {
            if(chrState[1][0] == 'e') {
                makeMoveGraphic(C10);
                chrState[1][0] = chrActive;
                checkForWin();
                if(boardFull()==false){
                    makeBestMove();
                }
            }
        });

        C11.setOnMouseClicked(event -> {
            if(chrState[1][1] == 'e') {
                makeMoveGraphic(C11);
                chrState[1][1] = chrActive;
                checkForWin();
                if(boardFull()==false){
                    makeBestMove();
                }
            }
        });

        C12.setOnMouseClicked(event -> {
            if(chrState[1][2] == 'e') {
                makeMoveGraphic(C12);
                chrState[1][2] = chrActive;
                checkForWin();
                if(boardFull()==false){
                    makeBestMove();
                }
            }
        });

        C20.setOnMouseClicked(event -> {
            if(chrState[2][0] == 'e') {
                makeMoveGraphic(C20);
                chrState[2][0] = chrActive;
                checkForWin();
                if(boardFull()==false){
                    makeBestMove();
                }
            }
        });

        C21.setOnMouseClicked(event -> {
            if(chrState[2][1] == 'e') {
                makeMoveGraphic(C21);
                chrState[2][1] = chrActive;
                checkForWin();
                if(boardFull()==false){
                    makeBestMove();
                }
            }
        });

        C22.setOnMouseClicked(event -> {
            if(chrState[2][2] == 'e') {
                makeMoveGraphic(C22);
                chrState[2][2] = chrActive;
                checkForWin();
                if(boardFull()==false){
                    makeBestMove();
                }
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

        //Setting the window name with the player's name entered with alert box from main screen.
        stage.setTitle("Player " + name + " vs Computer");
        stage.setScene(new Scene(pane, 300, 300));
        stage.show();

    }

    /**
     * A void function to draw the board on the JavaFX screen
     * @param gc  The graphic 2d context used to draw the borders of the board
     */
    private void initCanvasBox(GraphicsContext gc){
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
        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
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
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);

        gc.strokeOval(20,20,60,60);

        return true;
    }

    /**
     * A Boolean function to call the shapes to draw on the board
     * @param canvas   The canvas on which the shapes are drawn
     * @return
     */
    private Boolean makeMoveGraphic(Canvas canvas){
        if(chrActive == 'x'){
            chrActive = 'o';
            return setX(canvas.getGraphicsContext2D());
        }else{
            chrActive = 'x';
            return setO(canvas.getGraphicsContext2D());
        }
    }

    /**
     * A void function that
     * @param r  An integer to store the position of the character the AI is going to put
     * @param c  An integer to store the position of the character the AI is going to put
     */
    private void makeMoveAI(int r, int c){
        //Drawing the character on the board in the correct position
        makeMoveGraphic(canvArr[r][c]);
        //Setting the active player character to the human player
        chrState[r][c] = chrActive;
        //Checking for a win in the game
        checkForWin();
    }

    /**
     * A void function to make the best move by the AI
     */
    private void makeBestMove(){
        if(boardFull()==false){
            int r =1;
            int c =1;

            //occupy middle if empty
            if(chrState[r][c]=='e'){
                makeMoveAI(r,c);
                return;
            }
            //block
            for(int i = 0;i < 3;i++){
                //rows ##-
                if(chrState[i][0] == 'x' && chrState[i][1] == 'x' && chrState[i][2]=='e'){r=i;c=2;makeMoveAI(r,c);return;}

                //rows -##
                else if(chrState[i][1] == 'x' && chrState[i][2] == 'x' && chrState[i][0]=='e'){r=i;c=0;makeMoveAI(r,c);return;}

                //rows #-#
                else if(chrState[i][0] == 'x'  && chrState[i][2] == 'x' && chrState[i][2]=='e'){r=i;c=1;makeMoveAI(r,c);return;}

                //cols ##-
                else if(chrState[0][i] == 'x' && chrState[1][i] == 'x' && chrState[2][i]=='e'){r=2;c=i;makeMoveAI(r,c);return;}

                //cols -##
                else if(chrState[1][i] == 'x' && chrState[2][i] == 'x' && chrState[0][i]=='e'){r=0;c=i;makeMoveAI(r,c);return;}

                //cols #-#
                else if(chrState[0][i] == 'x' && chrState[2][i] == 'x' && chrState[1][i]=='e'){r=1;c=i;makeMoveAI(r,c);return;}

                //diagonal
                //SE
                if(chrState[0][0] == 'x' && chrState[1][1] == 'x' && chrState[2][2] == 'e'){r=2;c=2;makeMoveAI(r,c);return;}
                else if(chrState[0][0] == 'x' && chrState[1][1] == 'e' && chrState[2][2] == 'x'){r=1;c=1;makeMoveAI(r,c);return;}
                else if(chrState[0][0] == 'e' && chrState[1][1] == 'x' && chrState[2][2] == 'x'){r=0;c=0;makeMoveAI(r,c);return;}

                //SW
                if(chrState[0][2] == 'x' && chrState[1][1] == 'x' && chrState[2][0] == 'e'){r=2;c=0;makeMoveAI(r,c);return;}
                if(chrState[0][2] == 'x' && chrState[1][1] == 'e' && chrState[2][0] == 'x'){r=1;c=1;makeMoveAI(r,c);return;}
                if(chrState[0][2] == 'e' && chrState[1][1] == 'x' && chrState[2][0] == 'x'){r=0;c=2;makeMoveAI(r,c);return;}

            }
            //try scoring first
            for(int i = 0;i < 3;i++){
                //rows ##-
                if(chrState[i][0] == 'o' && chrState[i][1] == 'o' && chrState[i][2]=='e'){r=i;c=2;makeMoveAI(r,c);return;}

                //rows -##
                else if(chrState[i][1] == 'o' && chrState[i][2] == 'o' && chrState[i][0]=='e'){r=i;c=0;makeMoveAI(r,c);return;}

                //rows #-#
                else if(chrState[i][0] == 'o'  && chrState[i][2] == 'o' && chrState[i][1]=='e'){r=i;c=1;makeMoveAI(r,c);return;}

                //cols ##-
                else if(chrState[0][i] == 'o' && chrState[1][i] == 'o' && chrState[2][i]=='e'){r=2;c=i;makeMoveAI(r,c);return;}

                //cols -##
                else if(chrState[1][i] == 'o' && chrState[2][i] == 'o' && chrState[0][i]=='e'){r=0;c=i;makeMoveAI(r,c);return;}

                //cols #-#
                else if(chrState[0][i] == 'o' && chrState[2][i] == 'o' && chrState[1][i]=='e'){r=1;c=i;makeMoveAI(r,c);return;}

                //diagonal
                //SE
                if(chrState[0][0] == 'o' && chrState[1][1] == 'o' && chrState[2][2] == 'e'){r=2;c=2;makeMoveAI(r,c);return;}
                else if(chrState[0][0] == 'o' && chrState[1][1] == 'e' && chrState[2][2] == 'o'){r=1;c=1;makeMoveAI(r,c);return;}
                else if(chrState[0][0] == 'e' && chrState[1][1] == 'o' && chrState[2][2] == 'o'){r=0;c=0;makeMoveAI(r,c);return;}

                //SW
                if(chrState[0][2] == 'o' && chrState[1][1] == 'o' && chrState[2][0] == 'e'){r=2;c=0;makeMoveAI(r,c);return;}
                if(chrState[0][2] == 'o' && chrState[1][1] == 'e' && chrState[2][0] == 'o'){r=1;c=1;makeMoveAI(r,c);return;}
                if(chrState[0][2] == 'e' && chrState[1][1] == 'o' && chrState[2][0] == 'o'){r=0;c=2;makeMoveAI(r,c);return;}

            }

            //otherwise occupy corners
            if(chrState[0][0]=='e'){makeMoveAI(0,0);return;}
            else if(chrState[0][2]=='e'){makeMoveAI(0,2);return;}
            else if(chrState[2][0]=='e'){makeMoveAI(2,0);return;}
            else if(chrState[2][2]=='e'){makeMoveAI(2,2);return;}
        }
    }

    /**
     * A function that returns a character 'x','o' or 'e' for the player who won where 'e' is a tie.
     * @return A character of the player who won ('x','o' or 'e')
     */
    private char checkForWin(){
        //rows
        for(int i = 0;i < 3;i++){
            if(chrState[i][0] == 'x' && chrState[i][1] == 'x' && chrState[i][2] == 'x'){
                alertPlayer('x');
                return 'x';
            }else if(chrState[i][0] == 'o' && chrState[i][1] == 'o' && chrState[i][2] == 'o'){
                alertPlayer('o');
                return 'o';
            }
        }

        //cols
        for(int i = 0;i < 3;i++){
            if(chrState[0][i] == 'x' && chrState[1][i] == 'x' && chrState[2][i] == 'x'){
                alertPlayer('x');
                return 'x';
            }else if(chrState[0][i] == 'o' && chrState[1][i] == 'o' && chrState[2][i] == 'o'){
                alertPlayer('o');
                return 'o';
            }
        }

        //diagonals
        //SE
        if(chrState[0][0] == 'x' && chrState[1][1] == 'x' && chrState[2][2] == 'x'){
            alertPlayer('x');
            return 'x';
        }else if(chrState[0][0] == 'o' && chrState[1][1] == 'o' && chrState[2][2] == 'o'){
            alertPlayer('o');
            return 'o';
        }

        //SW
        if(chrState[0][2] == 'x' && chrState[1][1] == 'x' && chrState[2][0] == 'x'){
            alertPlayer('x');
            return 'x';
        }else if(chrState[0][2] == 'o' && chrState[1][1] == 'o' && chrState[2][0] == 'o'){
            alertPlayer('o');
            return 'o';
        }

        if(boardFull()){
            alertPlayer('e');
            return 'e';
        }

        return 'e';
    }

    /**
     * A function that returns a boolean for whether the game board is full or not
     * @return true or false for either the board is full or the board still has room respectively
     */
    private Boolean boardFull(){
        if(chrState[0][0] != 'e' && chrState[0][1] != 'e' && chrState[0][2] != 'e'
                && chrState[1][0] != 'e' && chrState[1][1] != 'e' && chrState[1][2] != 'e'
                && chrState[2][0] != 'e' && chrState[2][1] != 'e' && chrState[2][2] != 'e'){
            System.out.println("Board is full");
            reset();
            return true;
        }
        return false;
    }

    /**
     * A void function that displays alert box when the game is over,
     * so there is a win, loose or a tie.
     * @param chrWin   The character that stores the letter of the player that won
     */
    private void alertPlayer(char chrWin){
        //An Alert object used for the alert box once the game is over
        Alert alert = null;
        //Setting the condition for when the player won
        if(chrWin=='o'){
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Congratulations you have won! Try again?"
                    , ButtonType.YES, ButtonType.NO);
            alert.showAndWait(); //To display the alert box and wait for user to choose an option

            //Setting the conditions of the buttons 'yes' and 'no' from alert box
            if (alert.getResult() == ButtonType.YES) {
                //Resetting the board to start a new game if user chooses 'yes'
                reset();
            }else if(alert.getResult() == ButtonType.NO){
                //Creating an instance of the Main class to return back to the main menu.
                Main mainScene = new Main();

                try{
                    //Returning back to the main screen
                    mainScene.start(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        //Setting the condition for when the AI won
        else if(chrWin=='x'){
            alert = new Alert(Alert.AlertType.CONFIRMATION, "The AI has won! It is unbeatable! Try again?"
                    , ButtonType.YES, ButtonType.NO);
            alert.showAndWait(); //To display the alert box and wait for user to choose an option

            //Setting the conditions of the buttons 'yes' and 'no' from alert box
            if (alert.getResult() == ButtonType.YES) {
                //Resetting the board to start a new game if user chooses 'yes'
                reset();
            }else if(alert.getResult() == ButtonType.NO){
                //Creating an instance of the Main class to return back to the main menu.
                Main mainScene = new Main();
                try{
                    //Returning back to the main screen
                    mainScene.start(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        //Setting the condition for when the game resulted in a tie
        else if(chrWin=='e'){
            alert = new Alert(Alert.AlertType.CONFIRMATION, "You have tied with the AI! Try again?"
                    , ButtonType.YES, ButtonType.NO);
            alert.showAndWait(); //To display the alert box and wait for user to choose an option

            //Setting the conditions of the buttons 'yes' and 'no' from alert box
            if (alert.getResult() == ButtonType.YES) {
                reset();
            }else if(alert.getResult() == ButtonType.NO){
                //Creating an instance of the Main class to return back to the main menu.
                Main mainScene = new Main();

                try{
                    //Returning back to the main screen
                    mainScene.start(stage);
                    //mainScene.start(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * A void function which resets the board to empty squares if the player chooses to play again.
     */
    public void reset(){
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                GraphicsContext gc = canvArr[i][j].getGraphicsContext2D();
                gc.clearRect(0,0,100,100);
                initCanvasBox(gc);
                chrState[i][j] = 'e'; //Setting the board with the 'e' char for empty space
            }
        }
    }
}


