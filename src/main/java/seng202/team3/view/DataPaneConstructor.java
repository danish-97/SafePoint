package seng202.team3.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import seng202.team3.controller.UIDataInterface;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import java.lang.Math;

/**
 * Constructs CrimeData objects to DataPane objects to be displayed
 */
public class DataPaneConstructor {

    /**
     * @return the pane containing all the CrimePanes
     */
    public static VBox loadActiveCrimes() {
        VBox root = new VBox();
        ArrayList<CrimeData> activeData = UIDataInterface.getActiveData();
        ArrayList<Pane> panes = new ArrayList<>();
        for (CrimeData data : activeData) {
            if (data instanceof PoliceData) {
                panes.add(new PoliceDataPane(data));
            } else if  (data instanceof UserData) {
                panes.add (new UserDataPane(data));
            }
        }
        root.getChildren().addAll(panes);
        return root;
    }

    private static ArrayList<CrimeData> activeData;

    public static VBox cullPanes (double height) {
        VBox root;
        activeData = UIDataInterface.getActiveData();
        int numData = activeData.size();
        int middle = (int) Math.round(height * (numData - 1));
        ArrayList<CrimeData> topData = DataPaneConstructor.getTopData(middle);
        ArrayList<CrimeData> bottomData = DataPaneConstructor.getBottomData(middle);
        int topPaneHeight = Math.max (0, middle - topData.size());
        int bottomPaneHeight = Math.min (activeData.size(), activeData.size() - (middle + bottomData.size() + 1));
        root = DataPaneConstructor.constructPanes (topData, bottomData, middle, topPaneHeight, bottomPaneHeight);
        return root;
    }

    public static ArrayList<CrimeData> getTopData (int middle) {
        ArrayList<CrimeData> visibleData = new ArrayList<>();
        int i = 0;
        while (middle - i > Math.max(0, middle - 5)) {
            i++;
            visibleData.add (activeData.get(middle - i));
        }
        Collections.reverse(visibleData);
        return visibleData;
    }

    public static ArrayList<CrimeData> getBottomData (int middle) {
        ArrayList<CrimeData> visibleData = new ArrayList<>();
        int i = middle + 1;
        while (i < Math.min(activeData.size(), middle + 5)) {
            visibleData.add(activeData.get(i));
            i++;
        }
        return visibleData;
    }

    public static VBox constructPanes (ArrayList<CrimeData> topData, ArrayList<CrimeData> bottomData, int middle, int topPaneHeight, int bottomPaneHeight) {
        VBox root = new VBox();
        ArrayList<Pane> panes = new ArrayList<>();
        Pane topPane = new Pane ();
        topPane.setPrefWidth(175);
        topPane.setPrefHeight(topPaneHeight);
        panes.add(topPane);
        for (CrimeData data : topData) {
            if (data instanceof PoliceData) {
                panes.add (new PoliceDataPane(data));
            } else {
                panes.add (new UserDataPane(data));
            }
        }
        if (activeData.get(middle) instanceof PoliceData) {
            panes.add (new PoliceDataPane(activeData.get(middle)));
        } else {
            panes.add (new UserDataPane(activeData.get(middle)));
        }
        for (CrimeData data : bottomData) {
            if (data instanceof PoliceData) {
                panes.add (new PoliceDataPane(data));
            } else {
                panes.add (new UserDataPane(data));
            }
        }
        Pane bottomPane = new Pane ();
        bottomPane.setPrefWidth(175);
        bottomPane.setPrefHeight(bottomPaneHeight);
        panes.add(bottomPane);

        root.getChildren().addAll(panes);

        return root;
    }

}
