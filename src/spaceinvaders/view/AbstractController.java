package spaceinvaders.view;

import javafx.stage.Stage;
import spaceinvaders.SpaceInvaders;

/**
 * Abstract Class to unify shared interface of controllers for FXML scenes
 */

public abstract class AbstractController {
    
	protected Stage primaryStage;

    protected SpaceInvaders application;
    /* Deprecated
    protected Scene myScene;
    */
	/**
	 * Setter for variable primaryStage
	 * @param primaryStage
	 */
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
	/**
	 * Getter for variable primaryStage
	 * @return primaryStage
	 */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	/**
	 * Setter for variable application
	 * @param application
	 */
    public void setApplication(SpaceInvaders application) {
        this.application = application;
    }
	/**
	 * Getter for variable application
	 * @return application
	 */
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
