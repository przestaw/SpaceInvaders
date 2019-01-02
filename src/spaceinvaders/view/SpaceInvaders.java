package spaceinvaders.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import spaceinvaders.controller.AbstractController;
import spaceinvaders.controller.ControllerGame;
import spaceinvaders.model.*;

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

    public SpaceInvaders()
    {
        this.myGame = new SpaceGame(800,800);
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
    public void runGame()
    {
        myStage.setScene(game);
    }
    /**
     * Changes screen to menu
     */
    public void runMenu()
    {
        myStage.setScene(menu);
    }
    /**
     * Changes screen to steering
     */
    public void runSteering()
    {
        myStage.setScene(steering);
    }
    /**
     * Redraws whole game context on given Canvas
     * @param canvas - where to draw game
     */
    public void redrawGame(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#000020"));
        gc.fillRect(0,0,myGame.getSizeX(),myGame.getSizeY());
    //Redraw all bullets
        for (Bullet bullet: myGame.getBullets()) {
            redrawBullet(gc, bullet);
        }
    //Redraw all rocks
        for (Rock rock: myGame.getRocks()) {
            redrawRock(gc, rock);
        }
    //Redraw all enemies
        for (Enemy enemy: myGame.getEnemies()) {
            redrawEnemy(gc, enemy);
        }
    //Redraw player
        redrawPlayer(gc, myGame.getPlayer());
    }
    /**
     * Redraws given enemy on given context
     * @param gc - where to draw enemy
     * @param enemy - enemy to draw
     */
    private void redrawEnemy(GraphicsContext gc, Enemy enemy) {
        switch (enemy.getEnemyType()){
            case angry:
                gc.setStroke(Color.GREEN);
                gc.setFill(Color.LIME);
                break;
            case bad:
                gc.setStroke(Color.DARKGOLDENROD);
                gc.setFill(Color.YELLOW);
                break;
            case evil:
                gc.setStroke(Color.ORANGERED);
                gc.setFill(Color.ORANGE);
                break;
        }
        gc.fillRect(enemy.getPosX()-enemy.getSizeX()/2.0, enemy.getPosY()-enemy.getSizeY()/2.0, enemy.getSizeX(), enemy.getSizeY());
        gc.setLineWidth(myGame.getSizeX()/200.0);
        gc.strokeRoundRect(enemy.getPosX()-enemy.getSizeX()/2.0, enemy.getPosY()-enemy.getSizeY()/2.0, enemy.getSizeX(), enemy.getSizeY(), 10, 10);
    }
    /**
     * Redraws given player on given context
     * @param gc - where to draw
     * @param player - player to draw
     */
    private void redrawPlayer(GraphicsContext gc, Player player) {
        gc.setFill(Color.GHOSTWHITE);

        gc.fillRect(player.getPosX()-player.getSizeX()/2.0, player.getPosY()+player.getSizeY()/2.0, player.getSizeX(), player.getSizeY());
        gc.fillRect(player.getPosX()-player.getSizeX()/160.0-player.getSizeX()/6.0, player.getPosY()+player.getSizeY()/80.0, player.getSizeX()/3.0, player.getSizeY());
    }
    /**
     * Redraws given bullet on given context
     * @param gc - where to draw
     * @param bullet - bullet to draw
     */
    private void redrawBullet(GraphicsContext gc, Bullet bullet) {
        gc.setFill(Color.DARKRED);
        if(bullet.isAlive())
        {gc.setFill(Color.PINK);}
        gc.fillRoundRect(bullet.getPosX()-bullet.getSizeX()/2.0, bullet.getPosY()+bullet.getSizeY()/2.0, bullet.getSizeX(), bullet.getSizeY(), 15, 15);
    }
    /**
     * Redraws given rock on given context
     * @param gc - where to draw
     * @param rock - rock to draw
     */
    private void redrawRock(GraphicsContext gc, Rock rock){
        if(rock.isDamaged()) {
            gc.setFill(Color.ORANGE);
        }else{
            gc.setFill(Color.RED);
        }
        gc.fillRect(rock.getPosX()-rock.getSizeX()/2.0, rock.getPosY()+rock.getSizeY()/2.0, rock.getSizeX(), rock.getSizeY());
    }
}