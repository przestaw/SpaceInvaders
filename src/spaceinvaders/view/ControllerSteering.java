package spaceinvaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerSteering extends AbstractController {

    @FXML
    private Button returnBTN;
    @FXML
    private Button startBTN;

    @FXML
    private void initialize()
    {
        returnBTN.setOnAction(e -> { application.runMenu(); });
        startBTN.setOnAction(e -> { application.runGame(); });
        //Runed automaticly after construction
    }
}
