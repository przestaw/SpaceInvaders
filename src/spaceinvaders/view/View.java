package spaceinvaders.view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * View for spaceinvaders
 * size variable to determine size of the window [this will be scalable with constant proportions]
 *
 */

public class View extends Application {


    private String title;

    public View(){
        this("Space Invaders");
    }

    public View(String title){
        this.title = title;

    }

    @Override
    public void start(Stage primaryStage) throws Exception  {
        primaryStage.setTitle(title);

        ViewLoader<BorderPane,ControllerMenu> viewLoader =
                new ViewLoader<>("spaceinvaders.Menu.fxml");

        BorderPane border = viewLoader.getLayout();
        Scene mainMenu = new Scene(border);

        primaryStage.setScene(mainMenu);

        primaryStage.setOnCloseRequest(e -> closeRequest(e));

        viewLoader.getFxmlController().setStage(primaryStage);

        primaryStage.show();
    }



    private void closeRequest(Event ev)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?", ButtonType.NO, ButtonType.YES);
        Optional<ButtonType> result = alert.showAndWait();

        if(result.orElse(ButtonType.NO) != ButtonType.YES){
            ev.consume();
        }

    }
}
