package spaceinvaders.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import spaceinvaders.model.SpaceGame;

/**
 * Controller Class for Game screen
 */
public class ControllerGame extends AbstractController {
	
	/**
	 * Variables connected with FXML defined in *.FXML file
	 */
    @FXML
    private Button resetBTN;
    @FXML
    private Label scoreLBL;
    @FXML
    private Button returnBTN;
    @FXML
    private Canvas gameCanvas;

    private SpaceGame myGame;
    private AnimationTimer timer;
    private long last_update;

    @FXML
    private void keyOnActionHandler(KeyEvent key)
    {
        switch (key.getCode())
        {
            case A :
                if(myGame.isPlay()) {
                    myGame.getPlayer().moveLeft();
                }
                application.redrawGame(gameCanvas);
                break;
            case D :
                if(myGame.isPlay()) {
                    myGame.getPlayer().moveRight();
                }
                application.redrawGame(gameCanvas);
                break;
            case W :
                if(myGame.isPlay()) {
                    myGame.playerShoot();
                }
                break;
            case Q:
                myGame.pause();
                break;
            default:
                break;
        }
    }
	/**
	 * Method run automatically after construction. Defines Action using lambdas.
	 */
    @FXML
    private void initialize()
    {
        resetBTN.setOnAction(e -> { myGame.restart(); });

        returnBTN.setOnAction(e -> { application.runMenu(); });
        last_update = 0;
        timer = new AnimationTimer(){
            public void handle(long now){
                if(now - last_update >= 1000000)
                {
                    if(myGame.isPlay()) {
                        myGame.update();
                    }
                    application.redrawGame(gameCanvas);
                    last_update = now;
                }
            }
        };
    }

    public void setMyGame(SpaceGame game)
    {
        myGame = game;
    }

    public Canvas getGameCanvas()
    {
        return gameCanvas;
    }

    public void start()
    {
        timer.start();
    }
}
