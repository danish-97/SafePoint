package seng202.team3.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

public class DataPane implements Renderable{

    CrimeData data;
    Pane pane;

    public DataPane(CrimeData data) {
        this.data = data;
    }

    @Override
    public void createPane() {
        if (data instanceof PoliceData) {
            createPoliceDataPane();
        } else if (data instanceof UserData) {
            createUserDataPane();
        } else {
            pane = new Pane();
        }
    }

    public void createPoliceDataPane() {
        PoliceData pData = (PoliceData) data;
        pane = new Pane();
        pane.setPrefHeight(80);
        pane.setPrefWidth(175);
        pane.setStyle("-fx-border-color: black;");

        //Crime type label
        Label crimeLabel = new Label(pData.getCrimeType());
        crimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-translate-x: 5;");

        Label location = new Label(pData.getAddress());
        location.setStyle("-fx-translate-y: 15; -fx-translate-x: 5;");

        Label arrestMade = new Label(pData.isArrestMade());
        arrestMade.setStyle("-fx-translate-x: 5; -fx-translate-y: 28;");

        Label caseNumber = new Label(pData.getCaseNumber());
        caseNumber.setStyle("-fx-translate-x: 5; -fx-translate-y: 41;");

        Button viewButton = new Button("View");
        viewButton.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-font-size: 10; -fx-padding: 0; -fx-pref-width: 50; -fx-translate-x: 65; -fx-translate-y: 60;");

        pane.getChildren().addAll(crimeLabel, location, arrestMade, caseNumber, viewButton);
    }

    public void createUserDataPane() {
        UserData uData = (UserData) data;
        pane = new Pane();
    }

    @Override
    public Pane getPane() {
        return pane;
    }
}
