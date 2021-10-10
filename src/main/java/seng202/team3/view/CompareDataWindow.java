package seng202.team3.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng202.team3.controller.CompareDataController;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;
import java.text.ParseException;


/**
 * Window handler for comparing crimes. This will handle the opening of a window for two different crimes,
 * and the necessary elements to go in the window
 * @author mattgarrett
 */
public class CompareDataWindow {

    private Pane pane1;
    private Pane pane2;
    private Pane pane3;
    private CrimeData data1;
    private CrimeData data2;

    public CompareDataWindow (CrimeData data1, CrimeData data2) throws ParseException {
        this.data1 = data1;
        this.data2 = data2;
        //get the string for distance and time difference
        CompareDataController controller = new CompareDataController(data1, data2);
        String distanceStr = controller.getDistance ();
        String dateDiffStr = controller.getTimeDifference ();
        getPanes ();
        constructCompareComponents(distanceStr, dateDiffStr);
        openWindow ();
    }

    /**
     * Constructs the JavaFX components for the difference in the crimes, e.g. the elements in this window
     * not already constructed by the dataView window classes.
     * @param distanceStr string showing distance between crimes
     * @param dateDiffStr string showing time difference between crimes
     */
    private void constructCompareComponents(String distanceStr, String dateDiffStr) {
        Pane comparePane = new Pane();
        comparePane.setStyle("-fx-pref-width:100; -fx-pref-height:200");
        Label dist = new Label(distanceStr);
        dist.setStyle("-fx-translate-x:230; -fx-translate-y:154");
        Label time = new Label(dateDiffStr);
        time.setStyle("-fx-translate-x:230; -fx-translate-y:99;");
        comparePane.getChildren().addAll(dist, time);
        pane3 = comparePane;
    }


    /**
     * Constructs the two main panes for the two crime data objects to be compared.
     */
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

    /**
     * Handles the opening of the window and formatting of the JavaFX components
     */
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
