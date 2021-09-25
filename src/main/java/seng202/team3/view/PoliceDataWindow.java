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

public class PoliceDataWindow {
    // TODO refactor to make a more generic WindowHandler class and this be a subclass

    public PoliceDataWindow (PoliceData data) {
        Parent root = constructWindow (data);
        Stage stage = new Stage();
        stage.setTitle("Report Crime");
        stage.setScene(new Scene(root, 250, 335));
        System.out.println("2");
        stage.show();
    }

    public Pane constructWindow (PoliceData data) {
        Pane pane = new Pane();
        pane.setStyle("-fx-pref-height: 220; -fx-pref-width: 200;");

        Label caseNumber = new Label (data.getCaseNumber());
        caseNumber.setStyle ("-fx-translate-x: 5; -fx-translate-y: 10; -fx-font-weight: bold; -fx-font-size: 15;");

        Line l1 = new Line(5.0, 31.0, 185.0, 31.0);

        Label crimeType = new Label(data.getCrimeType());
        crimeType.setStyle("-fx-translate-x: 5; -fx-translate-y: 33; -fx-font-weight: bold; -fx-font-size: 13;");

        // TODO add description to PoliceData

        Line l2 = new Line(5.0, 70.0, 185.0, 70.0);

        Label address = new Label (data.getAddress());
        address.setStyle ("-fx-translate-x: 5; -fx-translate-y: 75;");

        // TODO add second description for address

        Label date = new Label (data.getDate());
        date.setStyle ("-fx-translate-x: 5; -fx-translate-y: 99;");

        Line l3 = new Line(5.0, 123.0, 185.0, 123.0);

        Label xCoord = new Label ("x: " + data.getXCoord());
        xCoord.setStyle ("-fx-translate-x: 5; -fx-translate-y: 130;");

        Label yCoord = new Label ("y: " + data.getYCoord());
        yCoord.setStyle ("-fx-translate-x: 5; -fx-translate-y: 142;");

        Label location = new Label (data.getLocation());
        location.setStyle ("-fx-translate-x: 5; -fx-translate-y: 154;");

        Line l4 = new Line(5.0, 178.5, 185.0, 178.5);

        Label arrest = new Label ("Arrest Made: " + data.isArrestMade());
        arrest.setStyle ("-fx-translate-x: 5; -fx-translate-y: 185;");

        Label domestic = new Label ("Domestic: " + data.getDomestic());
        domestic.setStyle ("-fx-translate-x: 5; -fx-translate-y: 197;");

        pane.getChildren().addAll(caseNumber, l1, crimeType, l2, address, date, l3, xCoord, yCoord, location, l4, arrest, domestic);
        return pane;
    }

}
