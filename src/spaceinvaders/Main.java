package spaceinvaders;

import spaceinvaders.model.Model;
import spaceinvaders.view.SpaceInvaders;

public class Main {
    private static Model model;
    private static SpaceInvaders view;

    public static void main(String args[])
    {
        view  = new SpaceInvaders(2);

        view.begin(args); //fire the window
    }
}
