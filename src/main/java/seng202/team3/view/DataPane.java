package seng202.team3.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import seng202.team3.model.CrimeData;

/**
 * DataPane is a JavaFX pane that displays the data from a CrimeData object in a graphical representation.
 * This is used on the CrimeData scroll pane on the main GUI
 * @author mattgarrett
 */
public class DataPane extends Pane{

    CrimeViewButton viewButton;

    /**
     * Constructs a new DataPane object
     * @param data CrimeData object to be displayed on the DataPane
     */
    public DataPane(CrimeData data) {
        super(); //initialize base pane
        this.setStyle("-fx-border-color: black; -fx-pref-height: 80; -fx-pref-width: 175; -fx-border-radius: 5");
        constructComponents (data);
    }


    /**
     * Adds attributes from CrimeData into this DataPane as labels with formatting
     * @param data Data to be displayed on the DataPane
     */
    public void constructComponents (CrimeData data) {
        Label crimeLabel = new Label(data.getCrimeType());
        crimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-translate-x: 5;");

        Label location = new Label(data.getAddress());
        location.setStyle("-fx-translate-y: 15; -fx-translate-x: 5;");

        Label date = new Label(data.getDate());
        date.setStyle("-fx-translate-x: 5; -fx-translate-y: 41;");

        //button for viewing crime in more detail
        viewButton = new CrimeViewButton(data);

        this.getChildren().addAll(crimeLabel, location, date, viewButton);
    }

}
