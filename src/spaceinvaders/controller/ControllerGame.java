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

    @FXML
    private void keyOnActionHandler(KeyEvent key)
    {
        switch (key.getCode())
        {
            case A :
                System.out.println("LEFT");
                break;
            case D :
                System.out.println("RIGHT");
                break;
            case W :
                System.out.println("FIRE");
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
        resetBTN.setOnAction(e -> { /*... */});

        returnBTN.setOnAction(e -> { application.runMenu(); });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //MyGame.update();
                application.redrawGame(gameCanvas);
            }
        };

       timer.start();



    }

    public void start()
    {
        timer.start();
    }
    //TODO - controller of game logic
}
