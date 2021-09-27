package seng202.team3.view;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import seng202.team3.model.CrimeData;
import seng202.team3.model.UserData;

/**
 * Handles in depth displaying of UserData objects as a seperate window.
 */
public class UserDataWindow extends DataViewWindow{

    private Pane displayPane;

    /**
     * Creates a simple DataViewWindow with base attributes added by parent class DataViewWindow, and extra attributes
     * unique to UserData added.
     * @param data Data to be displayed
     */
    public UserDataWindow (UserData data) {
        Parent root = constructWindow(data);
        displayWindow(root);
    }


    /**
     * Creates a simple DataViewWindow with base attributes added by parent class DataViewWindow, and extra attributes
     * unique to UserData added. If !isMain then no window will be opened but the pane will still be constructed
     * @param data Data to be displayed
     * @param isMain choose whether a window should be opened on creation
     */
    public UserDataWindow (UserData data, Boolean isMain) {
        if (isMain) {
            Parent root = constructWindow(data);
            displayWindow(root);
        } else {
            displayPane = constructWindow(data);
        }
    }

    /**
     * Formats UserData into physical attributes on a pane.
     * @param data The data to be displayed on the window
     * @return Pane displaying all formatted attributes of UserData
     */
    @Override
    public Pane constructWindow (CrimeData data) {
        Pane pane = super.constructWindow(data);
        Label userLabel = new Label ("USER REPORTED");
        userLabel.setStyle ("-fx-translate-x: 5; -fx-translate-y: 10; -fx-font-weight: bold; -fx-font-size: 15;");

        Label arrest = new Label ("Arrest Made: NO");
        arrest.setStyle ("-fx-translate-x: 5; -fx-translate-y: 185;");

        pane.getChildren().addAll(userLabel, arrest);

        return pane;
    }

    public Pane getPane () {
        return displayPane;
    }
}
