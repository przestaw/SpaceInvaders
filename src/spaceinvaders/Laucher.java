package spaceinvaders;

import spaceinvaders.model.SpaceGame;
import spaceinvaders.view.SpaceInvaders;

public class Laucher {
    private static SpaceGame model;
    private static SpaceInvaders view;

    public static void main(String args[])
    {
        view  = new SpaceInvaders(2);
        model = view.getMyGame();
        view.begin(args); //fire the window
    }
}
