package spaceinvaders;

import javafx.application.Application;
import spaceinvaders.controller.Controller;
import spaceinvaders.model.Model;
import spaceinvaders.view.View;

/**
 * The spaceinvaders is a simple game created using OpenJDK 8 and OpenJFX.
 * This is main class.
 *
 * @author przestaw
 * @version 0.0.1-alpha
 * @since 2018-11-2
 */

public class SpaceInvaders {

    /**
     * This is main method
     * @param args
     */

    private static Model model;
    private static View view;
    private static Controller control;

    public static void main(String[] args) {
        model = new Model();
        control = new Controller();
        view = new View();
        Application.launch(View.class, args); //launch View
    }
}
