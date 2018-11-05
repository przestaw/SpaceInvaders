package spaceinvaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * Controller Class for Steering screen
 */
public class ControllerSteering extends AbstractController {
	
	/**
	 * Variables connected with FXML defined in *.FXML file
	 */
    @FXML
    private Button returnBTN;
    @FXML
    private Button startBTN;
	/**
	 * Method run automatically after construction. Defines Action using lambdas.
	 */
    @FXML
    private void initialize()
    {
        returnBTN.setOnAction(e -> { application.runMenu(); });
        startBTN.setOnAction(e -> { application.runGame(); });
        //Runed automaticly after construction
    }
}
