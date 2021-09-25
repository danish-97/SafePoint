package seng202.team3.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import seng202.team3.controller.UIDataInterface;
import seng202.team3.model.CrimeData;

import java.text.ParseException;
import java.util.ArrayList;

public class DataPaneConstructor {

    public static VBox loadActiveCrimes() throws ClassNotFoundException {
        VBox root = new VBox();
        ArrayList<CrimeData> allData = UIDataInterface.getActiveData();
        ArrayList<Pane> panes = new ArrayList<>();
        for (CrimeData data : allData) {
            DataPane pane = new DataPane(data);
            pane.createPane();
            panes.add(pane.getPane());
        }
        root.getChildren().addAll(panes);
        return root;
    }

}
