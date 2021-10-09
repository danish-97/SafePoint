package seng202.team3.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

public class CompareDataWindow {

    private Pane pane1;
    private Pane pane2;
    private CrimeData data1;
    private CrimeData data2;

    public CompareDataWindow (CrimeData data1, CrimeData data2) {
        this.data1 = data1;
        this.data2 = data2;
        getPanes ();
        // TODO show differences
        openWindow ();
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

        mainPane.getChildren().addAll(pane1, pane2);

        Stage stage = new Stage();
        stage.setTitle("Compare Crimes");
        stage.setScene(new Scene(mainPane, 600, 335));
        stage.setResizable(false);
        stage.show();
    }

}
