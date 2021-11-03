package Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class Main extends Application {

    //A Canvas object used for making the main screen
    private Canvas canvas;
    //Setting the width and height of the JavaFx application
    private double screenWidth = 320;
    private double screenHeight = 320;

    /**
     * A void function that creates the objects and scene for the JavaFx application.
     * @param primaryStage  A Stage object used for the JavaFx application.
     * @throws FileNotFoundException
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        //A Scene object for the Home screen
        Scene mainScene;
        Group root = new Group();

        /**
         * To read in the title image using File Input Stream for the application.
         * The image is set
         */
        FileInputStream input = new FileInputStream("resources/images/GameLogo.png");
        javafx.scene.image.Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(200);

        //Create Canvas object and add it into the scene
        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        /**
         * Using FileInputStream to read in an image
         * and create an icon for the window.
         */
        FileInputStream inputLogo = new FileInputStream("resources/images/iconImage.png");
        javafx.scene.image.Image icon = new Image(inputLogo);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Ultimate Tic-Tac-Toe Game");

        //Creating a grid pane to add the buttons and the image
        GridPane grid = new GridPane();
        //Setting the grid to align its objects in the center
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //Creating the menu buttons
        javafx.scene.control.Button btAppPvP = new javafx.scene.control.Button("Player vs Player");
        btAppPvP.setPrefWidth(200);
        javafx.scene.control.Button btAppPvC = new javafx.scene.control.Button("Player vs Computer");
        btAppPvC.setPrefWidth(200);
        javafx.scene.control.Button btAppHowToGuide = new javafx.scene.control.Button("How to guide");
        btAppHowToGuide.setPrefWidth(200);
        javafx.scene.control.Button btAppExit = new javafx.scene.control.Button("Exit");
        btAppExit.setPrefWidth(200);


        //Add the menu buttons and image to the grid
        grid.add(imageView, 0,0);
        grid.add(btAppPvP, 0,1);
        grid.add(btAppPvC, 0,2);
        grid.add(btAppHowToGuide, 0,3);
        grid.add(btAppExit, 0,4);
        //Setting background colour
        grid.setStyle("-fx-background-color: #FFCCCC;");

        //Main App Scene
        mainScene = new Scene(grid, screenWidth, screenHeight);

        //Setting the Event handlers for each buttons

        //Setting action for the player vs =playe button
        btAppPvP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Calling the TicTacToeGame class to start the player vs player game
                TicTacToeGame mainScene = new TicTacToeGame();
                try {
                    mainScene.start(primaryStage);
                    //mainScene.start(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //Closing the main menu once the new window is opened for player vs player
                //primaryStage.close();
            }
        });

        //Setting action for the player vs computer button
        btAppPvC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Asking for player name
                TextInputDialog dialog = new TextInputDialog("Player Name");
                dialog.setTitle("Player Name");
                dialog.setHeaderText("Enter Player Name");
                dialog.setContentText("Please Enter Player 1 Name");

                //get the response value.
                Optional<String> result = dialog.showAndWait();
                TicTacToeAI mainScene = new TicTacToeAI(result.get(),primaryStage);
                //Calling the TicTacToeAI class to start the player vs computer game
                try {
                    mainScene.start(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //Closing the main menu once the new window is opened for player vs player
                //primaryStage.close();

            }
        });

        //Setting the button action for the how to guide button
        btAppHowToGuide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Setting an alert box to show a link to a wikipedia article of the rules for tic tac toe.
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "the Wikipedia article can be found at : https://en.wikipedia.org/wiki/Tic-tac-toe"
                        , ButtonType.OK);
                alert.show();
            }
        });

        //Setting the action for the exit button
        btAppExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Closing the stage when called to close the window
                primaryStage.close();
            }
        });

        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    /**
     * A main function to run the java application
     * @param args  A string array that holds the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


}


