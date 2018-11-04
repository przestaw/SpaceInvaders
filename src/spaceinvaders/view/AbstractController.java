package spaceinvaders.view;

import javafx.stage.Stage;
import spaceinvaders.SpaceInvaders;

public abstract class AbstractController {
    protected Stage primaryStage;

    protected SpaceInvaders application;
    /* Deprecated
    protected Scene myScene;
    */

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setApplication(SpaceInvaders application) {
        this.application = application;
    }

    public SpaceInvaders getApplication() {
        return application;
    }

    /* Deprecated
    public void setMyScene(Scene myScene) {
        this.myScene = myScene;
    }

    public Scene getMyScene() {
        return myScene;
    }
    */
}
