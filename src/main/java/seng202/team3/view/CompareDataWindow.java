package seng202.team3.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CompareDataWindow {
    //TODO remake this class
    private Pane pane1;
    private Pane pane2;
    private Pane pane3;
    private CrimeData data1;
    private CrimeData data2;

    public CompareDataWindow (CrimeData data1, CrimeData data2) throws ParseException {
        this.data1 = data1;
        this.data2 = data2;
        getPanes ();
        constructCompareComponents();
        openWindow ();
    }

    private void constructCompareComponents() throws ParseException {
        String distanceStr = "Distance not available";
        if (!Objects.equals(data1.getLocation(), ", ") && !Objects.equals(data2.getLocation(), ", ")) {
            distanceStr = Double.toString(distance(Double.parseDouble(data1.getLatitude()), Double.parseDouble(data1.getLongitude()),
                    Double.parseDouble(data2.getLatitude()), Double.parseDouble(data2.getLongitude())));
            distanceStr += " Km Apart";
        }

        Date date1;
        Date date2;
        if (data1 instanceof PoliceData) {
            date1 = new SimpleDateFormat("MM/dd/yyyy").parse(data1.getDate().substring(0, 9));
        } else {
            date1 = new SimpleDateFormat("yyyy-dd-MM").parse(data1.getDate().substring(0, 9));
        }

        if (data2 instanceof PoliceData) {
            date2 = new SimpleDateFormat("MM/dd/yyyy").parse(data2.getDate().substring(0, 9));
        } else {
            date2 = new SimpleDateFormat("yyyy-dd-MM").parse(data2.getDate().substring(0, 9));
        }

        long diffInMill = Math.abs(date2.getTime() - date1.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMill, TimeUnit.MILLISECONDS);

        Pane comparePane = new Pane();
        comparePane.setStyle("-fx-pref-width:100; -fx-pref-height:200");
        Label dist = new Label(distanceStr);
        dist.setStyle("-fx-translate-x:230; -fx-translate-y:154");
        Label time = new Label(diff + " Days Apart");
        time.setStyle("-fx-translate-x:230; -fx-translate-y:99;");
        comparePane.getChildren().addAll(dist, time);
        pane3 = comparePane;
    }


    //TODO this should all be in a separate controller
    private double distance(Double latitude, Double longitude, Double latitude1, Double longitude1) {
        double theta = longitude - longitude1;
        double dist = Math.sin(deg2rad(latitude)) * Math.sin(deg2rad(latitude1)) + Math.cos(deg2rad(latitude)) * Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return Math.round(dist * 100) / 100;
    }

    private double deg2rad(double deg) {return (deg * Math.PI / 180.0);}

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public void getPanes () {
        if (data1 instanceof UserData) {
            pane1 = new UserDataWindow((UserData) data1, false).getPane();
        } else if (data1 instanceof PoliceData) {
            pane1 = new PoliceDataWindow((PoliceData) data1 , false).getPane();
        }
        if (data2 instanceof UserData) {
            pane2 = new UserDataWindow((UserData) data2, false).getPane();
        } else if (data2 instanceof PoliceData) {
            pane2 = new PoliceDataWindow((PoliceData) data2 , false).getPane();
        }
    }

    public void openWindow () {
        Pane mainPane = new Pane();
        mainPane.setStyle("-fx-pref-width: 600; -fx-pref-height: 335");

        pane2.setStyle("-fx-translate-x: 350");

        mainPane.getChildren().addAll(pane1, pane2, pane3);

        Stage stage = new Stage();
        stage.setTitle("Compare Crimes");
        stage.setScene(new Scene(mainPane, 600, 335));
        stage.setResizable(false);
        stage.show();
    }

}
