package spaceinvaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * Controller Class for Game screen
 */
public class ControllerGame extends AbstractController{
	
	/**
	 * Variables connected with FXML defined in *.FXML file
	 */
    @FXML
    private Button resetBTN;
    @FXML
    private Label scoreLBL;
    @FXML
    private Button returnBTN;
	/**
	 * Method run automatically after construction. Defines Action using lambdas.
	 */
    @FXML
    private void initialize()
    {
        resetBTN.setOnAction(e -> { /*... */});

        returnBTN.setOnAction(e -> { application.runMenu(); });

    }


    //TODO - controller of game logic
}
