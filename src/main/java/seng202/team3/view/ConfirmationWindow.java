package seng202.team3.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class holds the information for windows that open after a certain user action has happened.
 * This confirms information with the user that their input has been properly handled,
 * or gives necessary information to the user about what they have reported.
 * @author mattgarrett
 */
public class ConfirmationWindow {

    public ConfirmationWindow (int confirmationID) {
        //format ID to the hashed string representation
        String formattedConfID = Integer.toString(confirmationID * 7159);
        openReportedWindow (formattedConfID);
    }

    public ConfirmationWindow () {
        openEditedWindow ();
    }

    /**
     * Opens a window when a user reports a new crime with all valid inputs.
     * @param confirmationStr String hashed ID of the ID of the crime, used for when a user goes to edit their crime
     */
    public void openReportedWindow (String confirmationStr) {
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

        //add elements to main pane
        mainPane.getChildren().addAll(warningl1, warningl2, infol1, infol2, crimeConfirmation);

        showStage(mainPane, 296, 142);
    }

    /**
     * Opens a window when a user successfully edits or deletes a crime
     */
    public void openEditedWindow () {
        Pane pane = new Pane();
        pane.setStyle("-fx-pref-height: 100; -fx-pref-width: 200");

        Label confirmation = new Label ("Crime Record Updated");
        confirmation.setStyle("-fx-font-size: 17; -fx-translate-x: 17; -fx-translate-y: 20;");

        Label info1 = new Label("You may need to reload data to");
        info1.setStyle("-fx-font-size: 12; -fx-translate-x: 22; -fx-translate-y: 45;");

        Label info2 = new Label("see your changes");
        info2.setStyle("-fx-translate-x: 50; -fx-translate-y: 58;");

        //add elements to main pane
        pane.getChildren().addAll(confirmation, info1, info2);

        showStage(pane, 200, 100);

    }

    /**
     * Opens a JavaFX window and handles the initializer for it
     * @param root main root pane to be displayed
     * @param width width of the window
     * @param height height of the window
     */
    public void showStage (Pane root, int width, int height) {
        Stage stage = new Stage ();
        stage.setTitle("Confirmation");
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        stage.show();
    }

}
