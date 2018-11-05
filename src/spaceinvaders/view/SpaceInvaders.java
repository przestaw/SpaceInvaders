package spaceinvaders.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spaceinvaders.controller.AbstractController;

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

    public SpaceInvaders()
    {
        path = "FXML/W800/";
    }

    public SpaceInvaders(int size)
    {
        switch (size) {
            case 1:
                path = "FXML/W400/";
                break;
            case 3:
                path = "FXML/W1200/";
                break;
            default:
                path = "FXML/W800/";
                break;
        }
    }

    public void begin(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {
        primaryStage.setTitle("Space Invaders");
        primaryStage.setOnCloseRequest(e -> closeRequest(e));
        myStage = primaryStage;

        loadGame();
        loadMenu();
        loadSteernig();

        primaryStage.setScene(menu);
        primaryStage.show();
    }

    private void closeRequest(Event ev)
    {
        Optional<ButtonType> result = new QuitAlert().popUp();

        if(result.orElse(ButtonType.CANCEL) != ButtonType.OK){
            ev.consume();
        }
    }

    private void loadMenu()
    {
        //Load menu
        ViewLoader<BorderPane, AbstractController> menuLoader =
                new ViewLoader<>(path+"view.Menu.fxml");

        BorderPane menuBorder = menuLoader.getLayout();
        menu = new Scene(menuBorder);
        menuLoader.getFxmlController().setStage(myStage);
        menuLoader.getFxmlController().setApplication(this);
    }

    private void loadGame()
    {
        //Load game
        ViewLoader<BorderPane, AbstractController> gameLoader =
                new ViewLoader<>(path+"view.Game.fxml");

        BorderPane gameBorder = gameLoader.getLayout();
        game = new Scene(gameBorder);
        gameLoader.getFxmlController().setStage(myStage);
        gameLoader.getFxmlController().setApplication(this);
    }

    private void loadSteernig()
    {
        //Load menu
        ViewLoader<BorderPane, AbstractController> Loader =
                new ViewLoader<>(path+"view.Steering.fxml");

        BorderPane Border = Loader.getLayout();
        steering = new Scene(Border);
        Loader.getFxmlController().setStage(myStage);
        Loader.getFxmlController().setApplication(this);
    }
    public void runGame()
    {
        myStage.setScene(game);
    }

    public void runMenu()
    {
        myStage.setScene(menu);
    }

    public void runSteering()
    {
        myStage.setScene(steering);
    }
}