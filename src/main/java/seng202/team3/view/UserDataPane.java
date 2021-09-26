package seng202.team3.view;

import javafx.scene.control.Label;
import seng202.team3.model.CrimeData;

public class UserDataPane extends DataPane {

    public UserDataPane(CrimeData data) {
        super(data);
        constructUserComponents();
    }

    public void constructUserComponents() {
        Label arrestMade = new Label("Arrest Made: NO");
        arrestMade.setStyle("-fx-translate-x: 5; -fx-translate-y: 28;");

        this.getChildren().addAll(arrestMade);
    }
}
