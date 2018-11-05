package spaceinvaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

/**
 * Controller Class for Main Menu screen
 */
public class ControllerMenu extends AbstractController{
	
	/**
	 * Variables connected with FXML defined in *.FXML file
	 */
    @FXML
    private Button startBTN;
    @FXML
    private Button quitBTN;
    @FXML
    private Button steeringBTN;
	
	/**
	 * Method run automatically after construction. Defines Action using lambdas.
	 */
    @FXML
    private void initialize()
    {
        quitBTN.setOnAction(e -> {
                    primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
                }
        );

        startBTN.setOnAction(e -> { application.runGame(); });

        steeringBTN.setOnAction(e -> { application.runSteering(); });
        //Runed automaticly after construction
    }
}
