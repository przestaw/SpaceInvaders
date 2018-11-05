package spaceinvaders;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Class used to load Interface from FXML files
 */

public class ViewLoader<T,U> {
    private T fxmlLayout;
    private U fxmlController;

	/**
	 * Class constructor
	 * @param path to FXML file
	 */
    public ViewLoader(String fxml)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SpaceInvaders.class.getResource(fxml));

            fxmlLayout = fxmlLoader.load();
            fxmlController = fxmlLoader.getController();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	/**
	 * Method used to obtain Layout
	 * @return Layout of type T 
	 */
    public T getLayout(){ return fxmlLayout; }

	/**
	 * Method used to obtain Controller
	 * @return Controller of type U 
	 */
    public U getFxmlController() { return fxmlController; }
}
