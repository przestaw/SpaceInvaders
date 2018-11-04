package spaceinvaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerGame extends AbstractController{

    @FXML
    private Button resetBTN;
    @FXML
    private Label scoreLBL;
    @FXML
    private Button returnBTN;

    @FXML
    private void initialize()
    {
        resetBTN.setOnAction(e -> { /*... */});

        returnBTN.setOnAction(e -> { application.runMenu(); });

    }


    //TODO - controller of game logic
}
