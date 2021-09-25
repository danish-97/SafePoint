package seng202.team3.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import seng202.team3.model.CrimeData;

public class DataViewWindow {

    public Pane constructWindow (CrimeData data) {
        Pane pane = new Pane ();
        pane.setStyle("-fx-pref-height: 220; -fx-pref-width: 200;");

        Line l1 = new Line(5.0, 31.0, 185.0, 31.0);

        Label crimeType = new Label(data.getCrimeType());
        crimeType.setStyle("-fx-translate-x: 5; -fx-translate-y: 33; -fx-font-weight: bold; -fx-font-size: 13;");

        Line l2 = new Line(5.0, 70.0, 185.0, 70.0);

        Label address = new Label (data.getAddress());
        address.setStyle ("-fx-translate-x: 5; -fx-translate-y: 75;");

        Label date = new Label (data.getDate());
        date.setStyle ("-fx-translate-x: 5; -fx-translate-y: 99;");

        Line l3 = new Line(5.0, 123.0, 185.0, 123.0);

        Label location = new Label (data.getLocation());
        location.setStyle ("-fx-translate-x: 5; -fx-translate-y: 154;");

        Line l4 = new Line(5.0, 178.5, 185.0, 178.5);

        pane.getChildren().addAll(l1, crimeType, l2, address, date, l3, location, l4);
        return pane;
    }

    public void displayWindow (Parent root) {
        Stage stage = new Stage();
        stage.setTitle("View Crime");
        stage.setScene(new Scene(root, 250, 335));
        stage.setResizable(false);
        stage.show();
    }

}
