package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Handles the creation of the main GUI for SafePoint
 */
public class SafePoint extends Application{
    /**
     * Starts the program.
     * @param stage main stage created by JavaFX
     * @throws IOException when FXML file is not found or invalid
     */
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource("seng202.team3.view/main-view.fxml"))));
        stage.setTitle("SafePoint");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}