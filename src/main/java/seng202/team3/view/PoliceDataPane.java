package seng202.team3.view;

import javafx.scene.control.Label;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;

public class PoliceDataPane extends DataPane{

    public PoliceDataPane (CrimeData data) {
        super(data);
        constructPoliceComponents ((PoliceData) data);
    }

    public void constructPoliceComponents (PoliceData data) {
        Label arrestMade = new Label("Arrest Made: " + data.isArrestMade());
        arrestMade.setStyle("-fx-translate-x: 5; -fx-translate-y: 28;");

        this.getChildren().addAll(arrestMade);
    }

}
