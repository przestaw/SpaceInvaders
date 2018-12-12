package spaceinvaders.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import spaceinvaders.controller.AbstractController;
import spaceinvaders.controller.ControllerGame;
import spaceinvaders.model.Bullet;
import spaceinvaders.model.Enemy;
import spaceinvaders.model.Player;
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

    public SpaceInvaders() //I don't know why and where but it's used
    {
        this.myGame = new SpaceGame(800,550);
        path = "FXML/W800/";
    }

    public SpaceGame getMyGame(){
        return myGame;
    }

    public SpaceInvaders(int size)
    {
        this.myGame = new SpaceGame(800,550);

        switch (size) {
            case 1:
                this.myGame = new SpaceGame(400,275);
                path = "FXML/W400/";
                break;
            case 3:
                this.myGame = new SpaceGame(1200,825);
                path = "FXML/W1200/";
                break;
            default:
                this.myGame = new SpaceGame(800,550);
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
        ViewLoader<BorderPane, ControllerGame> gameLoader =
                new ViewLoader<>(path+"view.Game.fxml");

        BorderPane gameBorder = gameLoader.getLayout();
        game = new Scene(gameBorder);
        gameLoader.getFxmlController().setStage(myStage);
        gameLoader.getFxmlController().setApplication(this);
        gameLoader.getFxmlController().start();     //this could be only temporary TODO: remove
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

    public void redrawGame(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //redrawPlayer(gc, myGame.getPlayer()); //TODO -> error
        gc.setFill(Color.BLUE);
        gc.fillRect(100,100,100,100);
    }

    private void redrawEnemy(GraphicsContext gc, Enemy enemy)
    {
        gc.setStroke(Color.BLUE);
        gc.strokeRoundRect(enemy.getPosX(), enemy.getPosY(), enemy.getSizeX(), enemy.getSizeY(), 15, 15);
    }

    private void redrawPlayer(GraphicsContext gc, Player player)
    {
        double x,y,sizeX,sizeY;
        gc.setStroke(Color.GREENYELLOW);
        gc.fillRect(100, 100, 100,100);
        sizeX = player.getSizeX();
        sizeY = player.getSizeY();
        x = player.getPosX();
        y = player.getPosY();

        gc.strokePolyline(  new double[]{x, x, x-sizeX/2, x+sizeX/2},
                new double[]{y+sizeY, y, y, y },
                4);
    }

    private void redrawBullet(GraphicsContext gc, Bullet bullet)
    {
        gc.setFill(Color.RED);
        gc.fillRoundRect(bullet.getPosX(), bullet.getPosY(), bullet.getSizeX(), bullet.getSizeY(), 15, 15);
    }
}