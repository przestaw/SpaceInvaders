package spaceinvaders;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spaceinvaders.model.Model;
import spaceinvaders.view.AbstractController;
import spaceinvaders.view.QuitAlert;

import java.util.Optional;

/**
 * The spaceinvaders is a simple game created using OpenJDK 8 and OpenJFX.
 * This is main class.
 *
 * @author przestaw
 * @version 0.0.1-alpha
 * @since 2018-11-2
 */

public class SpaceInvaders extends Application {

    private static Model model;

    private Scene menu;
    private Scene game;
    private Scene steering;

    private Stage myStage;

    public static void main(String[] args) {

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
                new ViewLoader<>("view/view.Menu.fxml");

        BorderPane menuBorder = menuLoader.getLayout();
        menu = new Scene(menuBorder);
        menuLoader.getFxmlController().setStage(myStage);
        menuLoader.getFxmlController().setApplication(this);
    }

    private void loadGame()
    {
        //Load game
        ViewLoader<BorderPane, AbstractController> gameLoader =
                new ViewLoader<>("view/view.Game.fxml");

        BorderPane gameBorder = gameLoader.getLayout();
        game = new Scene(gameBorder);
        gameLoader.getFxmlController().setStage(myStage);
        gameLoader.getFxmlController().setApplication(this);
    }

    private void loadSteernig()
    {
        //Load menu
        ViewLoader<BorderPane, AbstractController> Loader =
                new ViewLoader<>("view/view.Steering.fxml");

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
