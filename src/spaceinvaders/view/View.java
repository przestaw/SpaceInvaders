package spaceinvaders.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View for spaceinvaders
 * size variable to determine size of the window [this will be scalable with constant proportions]
 *
 */

public class View extends Application {

    private Scene appScene;

    private String title;

    public View() {
        this("Space Invaders");
    }

    public View(String title) {
        this.title = title;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("spaceinvaders.view.fxml"));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, 500, 750));
        primaryStage.show();
    }

    @Override
    public void init()
    {
        Parameters param = getParameters();
    }

    @Override
    public void stop()
    {
        //TODO - exit confrimation
    }
}
