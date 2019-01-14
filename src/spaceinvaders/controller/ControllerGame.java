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
    private Button returnBTN;
    @FXML
    private Canvas gameCanvas;
    @FXML
    private Label scoreLBL;
    @FXML
    private Label gameMessage;

    private SpaceGame myGame;
    private AnimationTimer timer;
    private long last_update;

    @FXML
    private void keyTypedActionHandler(KeyEvent key) {
        switch (key.getCode()) {
            case ENTER:
                myGame.unPause();
                break;
            case ESCAPE:
                myGame.pause();
                break;
            case R:
                myGame.restart();
                break;
            default:
                break;
        }
    }

    @FXML
    private void keyPressedActionHandler(KeyEvent key) {
        switch (key.getCode()) {
            case A:
                myGame.setLeftOn();
                break;
            case D:
                myGame.setRightOn();
                break;
            case W:
                myGame.setFireOn();
                break;
            case ESCAPE:
                myGame.pause();
                break;
            case R:
                myGame.restart();
                break;
            default:
                break;
        }
    }

    @FXML
    private void keyRelasedActionHandler(KeyEvent key) {
        switch (key.getCode()) {
            case A:
                myGame.setLeftOff();
                break;
            case D:
                myGame.setRightOff();
                break;
            case W:
                myGame.setFireOff();
                break;
            default:
                break;
        }
    }
	/**
	 * Method run automatically after construction. Defines Action using lambdas.
	 */
    @FXML
    private void initialize() {
        resetBTN.setOnAction(e -> { myGame.restart(); });
        returnBTN.setOnAction(e -> { application.runMenu(); });

        last_update = 0;
        timer = new AnimationTimer(){
            public void handle(long now){
                if(now - last_update >= 210) {
                    if(myGame.isPlay()) {
                        myGame.update();
                        gameMessage.setText("");
                    }else if(myGame.isWon()) {
                        gameMessage.setText("Congratulations\nYou saved the Earth!");
                    }else if(myGame.isGameover()){
                        gameMessage.setText("You have lost...\nInvaders has taken down Earth");
                    }else{
                        gameMessage.setText("PAUSE");
                    }
                    scoreLBL.setText("Score : "+myGame.getScore());
                    application.callRedrawGame(gameCanvas);
                    last_update = now;
                }
            }
        };
    }
    /**
     * Setter for the myGame SpaceGame reference
     * @param game - game to set
     */
    public void setMyGame(SpaceGame game) {
        myGame = game;
    }
    /**
     * Used to start game timer when everything will be ready
     */
    public void start() {
        timer.start();
    }
}
