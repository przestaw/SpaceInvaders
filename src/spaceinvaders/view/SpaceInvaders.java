package spaceinvaders.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spaceinvaders.controller.AbstractController;
import spaceinvaders.controller.ControllerGame;
import spaceinvaders.model.SpaceGame;

import java.util.Optional;

/**
 * The spaceinvaders is a simple game created using OpenJDK 8 and OpenJFX.
 * This is main class.
 *
 * @author przestaw
 * @version 0.0.2-alpha
 * @since 2018-11-2
 */

public class SpaceInvaders extends Application {
    private static String path;

    private Scene menu;
    private Scene game;
    private Scene steering;

    private Stage myStage;

    private SpaceGame myGame;
    private DrawGame myDraw;

    public SpaceInvaders()
    {
        this.myGame = new SpaceGame(800,800);
        this.myDraw = new DrawGame(myGame);
        path = "FXML/W800/";
    }

    public SpaceGame getMyGame(){
        return myGame;
    }

    public SpaceInvaders(int size) {
        switch (size) {
            //TODO://case 1:
                //this.myGame = new SpaceGame(600,600);
                //path = "FXML/W400/";
                //break;
            //TODO://case 3:
                //this.myGame = new SpaceGame(1000,1000);
                //path = "FXML/W1200/";
                //break;
            default:
                this.myGame = new SpaceGame(800,800);
                this.myDraw = new DrawGame(myGame);
                path = "FXML/W800/";
                break;
        }
    }
    ///////// Used to launch the app ////////////
    public void begin(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {
        primaryStage.setTitle("Space Invaders");
        primaryStage.setOnCloseRequest(e -> closeRequest(e));
        myStage = primaryStage;

        loadSteernig();
        loadMenu();
        loadGame();

        primaryStage.setScene(menu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    /**
     * Event handler for closing window popup
     * @param ev - event of window closing
     */
    private void closeRequest(Event ev)
    {
        Optional<ButtonType> result = new QuitAlert().popUp();

        if(result.orElse(ButtonType.CANCEL) != ButtonType.OK){
            ev.consume();
        }
    }
    /**
     * Loads menu.View and menu.Controller
     */
    private void loadMenu()
    {
        //Load menu
        ViewLoader<AnchorPane, AbstractController> menuLoader =
                new ViewLoader<>(path+"view.Menu.fxml");

        AnchorPane menuBorder = menuLoader.getLayout();
        menu = new Scene(menuBorder);
        menuLoader.getFxmlController().setStage(myStage);
        menuLoader.getFxmlController().setApplication(this);
    }
    /**
     * Loads game.View and game.Controller
     */
    private void loadGame() {
        //Load game
        ViewLoader<BorderPane, ControllerGame> gameLoader =
                new ViewLoader<>(path+"view.Game.fxml");

        BorderPane gameBorder = gameLoader.getLayout();
        game = new Scene(gameBorder);
        gameLoader.getFxmlController().setMyGame(myGame);
        gameLoader.getFxmlController().setStage(myStage);
        gameLoader.getFxmlController().setApplication(this);
        gameLoader.getFxmlController().start();
    }
    /**
     * Loads steering.View and steering.Controller
     */
    private Scene loadSteernig() {
        //Load menu
        ViewLoader<BorderPane, AbstractController> Loader =
                new ViewLoader<>(path+"view.Steering.fxml");

        BorderPane Border = Loader.getLayout();
        Loader.getFxmlController().setStage(myStage);
        Loader.getFxmlController().setApplication(this);
        steering = new Scene(Border);
        return steering;
    }
    /**
     * Changes screen to game
     */
    public void runGame() {
        myStage.setScene(game);
    }
    /**
     * Changes screen to menu
     */
    public void runMenu() {
        myStage.setScene(menu);
    }
    /**
     * Changes screen to steering
     */
    public void runSteering() {
        myStage.setScene(steering);
    }
    /**
     * Calls drawer to redraw whole game context on given Canvas
     * @param canvas - where to draw game
     */
    public void callRedrawGame(Canvas canvas){
        myDraw.redrawGame(canvas);
    }
}