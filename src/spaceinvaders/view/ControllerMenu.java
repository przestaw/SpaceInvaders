package spaceinvaders.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerMenu {


    @FXML
    private Button start;
    @FXML
    private Button quit;
    @FXML
    private Button options;

    private Stage primaryStage;
    private ViewLoader<BorderPane,Object> viewLoader;

    public void setStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }


    @FXML
    private void initialize()
    {
        quit.setOnAction(e -> {
                    primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
                }
        );
        //Runed automaticly after construction
    }
}
