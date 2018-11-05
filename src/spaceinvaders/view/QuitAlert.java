package spaceinvaders.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Custonomized Alert box showed before program exit
 */

public class QuitAlert extends Alert {
	/**
	 * Class constructor
	 */
    public QuitAlert()
    {
        super(Alert.AlertType.CONFIRMATION, null, ButtonType.CANCEL, ButtonType.OK);
        this.setTitle("Space Invaders");
        this.setHeaderText("Are you sure you want to quit?");
    }
	/**
	 * Method used to show Alert box and obtain clicked button
	 * @return type button clicked*/
    public Optional<ButtonType> popUp()
    {
        Optional<ButtonType> result = this.showAndWait();
        return result;
    }
}
