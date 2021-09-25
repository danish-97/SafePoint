package seng202.team3.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

/**
 * DataPane constructs a JavaFX pane that displays the data from a CrimeData object in a graphical representation.
 * @author Matthew Garrett
 */
public class DataPane implements Renderable{

    CrimeData data;
    Pane pane;

    /**
     * Constructs a new DataPane object
     * @param data CrimeData object to be displayed on the DataPane
     */
    public DataPane(CrimeData data) {
        this.data = data;
    }


    /**
     * Creates the JavaFX pane from the CrimeData object data.
     * @throws ClassNotFoundException on variable data not being PoliceData or UserData
     */
    @Override
    public void createPane() throws ClassNotFoundException {
        if (data instanceof PoliceData) {
            createPoliceDataPane();
        } else if (data instanceof UserData) {
            createUserDataPane();
        } else {
            throw new ClassNotFoundException();
        }
    }

    /**
     * Constructs the JavaFX pane when data is an instance of PoliceData.
     * Also adds unique labels and button with styling.
     */
    public void createPoliceDataPane() {
        PoliceData pData = (PoliceData) data;
        //construct DataPane pane
        pane = new Pane();
        pane.setPrefHeight(80);
        pane.setPrefWidth(175);
        pane.setStyle("-fx-border-color: black;");

        //Crime type label
        Label crimeLabel = new Label(pData.getCrimeType());
        crimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-translate-x: 5;");
        //Location label
        Label location = new Label(pData.getAddress());
        location.setStyle("-fx-translate-y: 15; -fx-translate-x: 5;");
        //arrest made label
        Label arrestMade = new Label(pData.isArrestMade());
        arrestMade.setStyle("-fx-translate-x: 5; -fx-translate-y: 28;");
        //case number label
        Label caseNumber = new Label(pData.getCaseNumber());
        caseNumber.setStyle("-fx-translate-x: 5; -fx-translate-y: 41;");
        //view button
        Button viewButton = new Button("View");
        viewButton.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-font-size: 10; -fx-padding: 0; -fx-pref-width: 50; -fx-translate-x: 65; -fx-translate-y: 60;");

        //adding all javafx components constructed above to the main pain
        pane.getChildren().addAll(crimeLabel, location, arrestMade, caseNumber, viewButton);
    }

    /**
     * Constructs the JavaFX pane when data is an instance of UserData.
     * Also adds unique labels and button with styling.
     */
    public void createUserDataPane() {
        //TODO construct proper data pane for user submitted crimes
        //UserData uData = (UserData) data;
        pane = new Pane();
    }


    /**
     * @return Returns pane constructed by the createPane() method.
     * @throws NullPointerException On pane not being properly constructed
     */
    @Override
    public Pane getPane() throws NullPointerException{
        if (pane == null) {
            throw new NullPointerException();
        }
        return pane;
    }
}
