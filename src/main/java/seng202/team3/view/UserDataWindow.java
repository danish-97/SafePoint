package seng202.team3.view;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import seng202.team3.model.CrimeData;
import seng202.team3.model.UserData;

public class UserDataWindow extends DataViewWindow{

    private Pane displayPane;

    public UserDataWindow (UserData data) {
        Parent root = constructWindow(data);
        displayWindow(root);
    }

    public UserDataWindow (UserData data, Boolean isMain) {
        if (isMain) {
            Parent root = constructWindow(data);
            displayWindow(root);
        } else {
            displayPane = constructWindow(data);
        }
    }

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
