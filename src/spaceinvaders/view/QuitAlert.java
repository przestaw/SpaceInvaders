package spaceinvaders.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class QuitAlert extends Alert {

    public QuitAlert()
    {
        super(Alert.AlertType.CONFIRMATION, null, ButtonType.CANCEL, ButtonType.OK);
        this.setTitle("Space Invaders");
        this.setHeaderText("Are you sure you want to quit?");
    }

    public Optional<ButtonType> popUp()
    {
        Optional<ButtonType> result = this.showAndWait();
        return result;
    }
}
