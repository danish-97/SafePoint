package seng202.team3.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UserInputHandler {

    public UserInputHandler () {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("seng202.team3.view/report-crime-view.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Report Crime");
            stage.setScene(new Scene(root, 250, 335));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
