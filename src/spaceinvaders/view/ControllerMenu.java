package spaceinvaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.WindowEvent;
import spaceinvaders.ViewLoader;

public class ControllerMenu extends AbstractController{


    @FXML
    private Button startBTN;
    @FXML
    private Button quitBTN;
    @FXML
    private Button optionsBTN;

    private ViewLoader<BorderPane,ControllerGame> viewLoader;

    @FXML
    private void initialize()
    {
        quitBTN.setOnAction(e -> {
                    primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
                }
        );

        startBTN.setOnAction(e -> { application.runGame(); });
        //Runed automaticly after construction
    }
}
