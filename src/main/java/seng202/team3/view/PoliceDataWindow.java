package seng202.team3.view;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;

/**
 * Handles in depth displaying of PoliceData objects as a separate window.
 * @author mattgarrett
 */
public class PoliceDataWindow extends DataViewWindow{

    private Pane displayPane;

    /**
     * Creates a simple DataViewWindow with base attributes added by parent class DataViewWindow, and extra attributes
     * unique to PoliceData added.
     * @param data Data to be displayed
     */
    public PoliceDataWindow (PoliceData data) {
        Parent root = constructWindow(data);
        displayWindow(root);
    }

    /**
     * Creates a simple DataViewWindow with base attributes added by parent class DataViewWindow, and extra attributes
     * unique to PoliceData added. If !isMain then no window will be opened but the pane will still be constructed
     * @param data Data to be displayed
     * @param isMain choose whether a window should be opened on creation
     */
    public PoliceDataWindow (PoliceData data, Boolean isMain) {
        if (isMain) {
            Parent root = constructWindow(data);
            displayWindow(root);
        } else {
            displayPane = constructWindow(data);
        }
    }

    /**
     * Formats PoliceData into physical attributes on a pane.
     * @param data The data to be displayed on the window
     * @return Pane displaying all formatted attributes of PoliceData
     */
    @Override
    public Pane constructWindow (CrimeData data) {
        Pane pane = super.constructWindow(data);
        PoliceData pData = (PoliceData) data;
        Label caseNumber = new Label (pData.getCaseNumber());
        caseNumber.setStyle ("-fx-translate-x: 5; -fx-translate-y: 10; -fx-font-weight: bold; -fx-font-size: 15;");

        Label crimeDescription = new Label (pData.getSecondDescription());
        crimeDescription.setStyle("-fx-font-weight: bold; -fx-translate-x: 5; -fx-translate-y: 47; -fx-font-size: 10;");

        Label locationDescription = new Label (pData.getLocationDescription());
        locationDescription.setStyle("-fx-translate-x: 5; -fx-translate-y: 87;");

        Label xCoord = new Label ("x: " + pData.getXCoord());
        xCoord.setStyle ("-fx-translate-x: 5; -fx-translate-y: 130;");

        Label yCoord = new Label ("y: " + pData.getYCoord());
        yCoord.setStyle ("-fx-translate-x: 5; -fx-translate-y: 142;");

        Label arrest = new Label ("Arrest Made: " + pData.isArrestMade());
        arrest.setStyle ("-fx-translate-x: 5; -fx-translate-y: 185;");

        Label domestic = new Label ("Domestic: " + pData.getDomestic());
        domestic.setStyle ("-fx-translate-x: 5; -fx-translate-y: 197;");

        pane.getChildren().addAll(caseNumber, xCoord, yCoord, arrest, domestic, crimeDescription, locationDescription);
        return pane;
    }

    public Pane getPane() {
        return displayPane;
    }

}
