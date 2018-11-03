package spaceinvaders.view;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ViewLoader<T,U> {
    private T fxmlLayout;
    private U fxmlController;

    public ViewLoader(String fxml)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource(fxml));

            fxmlLayout = fxmlLoader.load();
            fxmlController = fxmlLoader.getController();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public T getLayout(){ return fxmlLayout; }

    public U getFxmlController() { return fxmlController; }
}
