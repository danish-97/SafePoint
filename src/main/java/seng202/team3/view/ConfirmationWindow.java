package seng202.team3.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ConfirmationWindow {

    public ConfirmationWindow (int confirmationID) {
        String formattedConfID = Integer.toString(confirmationID * 7159);
        openWindow (formattedConfID);
    }

    public void openWindow (String confirmationStr) {
        Pane mainPane = new Pane();
        mainPane.setStyle("-fx-pref-width: 296; -fx-pref-height: 142");

        Label warningl1 = new Label("You have reported a crime.");
        warningl1.setStyle("-fx-font-size: 21; -fx-translate-x: 28; -fx-translate-y: 10;");

        Label warningl2 = new Label ("This will now be visible to all other users");
        warningl2.setStyle("-fx-translate-x: 35; -fx-translate-y: 35;");

        Label infol1 = new Label ("To remove or edit information about the crime,");
        infol1.setStyle("-fx-translate-x: 20; -fx-translate-y: 70;");

        Label infol2 = new Label ("keep track of the unique ID below.");
        infol2.setStyle("-fx-translate-x: 55; -fx-translate-y: 85;");

        Label crimeConfirmation = new Label(confirmationStr);
        crimeConfirmation.setStyle("-fx-alignment: center; -fx-translate-x: 10; -fx-translate-y: 115; -fx-pref-width: 275;");

        mainPane.getChildren().addAll(warningl1, warningl2, infol1, infol2, crimeConfirmation);

        Stage stage = new Stage();
        stage.setTitle("Confirmation");
        stage.setScene(new Scene(mainPane, 296, 142));
        stage.setResizable(false);
        stage.show();
    }

}
