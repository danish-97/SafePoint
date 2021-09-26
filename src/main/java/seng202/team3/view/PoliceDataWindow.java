package seng202.team3.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;

import java.io.IOException;
import java.util.Objects;

public class PoliceDataWindow extends DataViewWindow{

    private Pane displayPane;

    public PoliceDataWindow (PoliceData data) {
        Parent root = constructWindow(data);
        displayWindow(root);
    }

    public PoliceDataWindow (PoliceData data, Boolean isMain) {
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
