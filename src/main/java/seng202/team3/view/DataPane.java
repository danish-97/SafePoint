package seng202.team3.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

/**
 * DataPane constructs a JavaFX pane that displays the data from a CrimeData object in a graphical representation.
 * @author Matthew Garrett
 */
public class DataPane extends Pane{

    CrimeViewButton viewButton;

    /**
     * Constructs a new DataPane object
     * @param data CrimeData object to be displayed on the DataPane
     */
    public DataPane(CrimeData data) {
        super();
        this.setStyle("-fx-border-color: black; -fx-pref-height: 80; -fx-pref-width: 175; -fx-border-radius: 5");
        constructComponents (data);
    }

    public void constructComponents (CrimeData data) {
        Label crimeLabel = new Label(data.getCrimeType());
        crimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-translate-x: 5;");

        Label location = new Label(data.getAddress());
        location.setStyle("-fx-translate-y: 15; -fx-translate-x: 5;");

        Label date = new Label(data.getDate());
        date.setStyle("-fx-translate-x: 5; -fx-translate-y: 41;");

        viewButton = new CrimeViewButton(data);

        this.getChildren().addAll(crimeLabel, location, date, viewButton);
    }

}
