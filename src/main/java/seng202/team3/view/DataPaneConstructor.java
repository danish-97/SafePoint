package seng202.team3.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import seng202.team3.controller.UIDataInterface;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import java.util.ArrayList;
import java.util.Collections;

import java.lang.Math;

/**
 * Constructs CrimeData objects to DataPane objects to be displayed
 * @author mattgarrett
 */
public class DataPaneConstructor {

    private static ArrayList<CrimeData> activeData;

    /**
     * sets the activeData variable to the activeData in UIDataInterface
     */
    public static void reloadData () {
        activeData = UIDataInterface.getActiveData();
    }

    /**
     * Culls the CrimeData being displayed as DataPanes in a ScrollPane.
     * This will allow better performance as only 8-13 panes will be displayed at once instead of all the
     * data that is active.
     *
     * @param height the height of the scroll bar in the crimeDataPane ScrollPane. between 0-1
     * @return all the visible CrimeData objects as CrimePanes and filler panes at the top and bottom
     */
    public static VBox cullPanes (double height) {
        VBox root;
        int numData = activeData.size();
        if (numData == 0) { // if there is no data to display, we don't want the scroll pane to contain anything
            return new VBox();
        }
        int middle = (int) Math.round(height * (numData - 1)); //the focus point of the scroll pane
        ArrayList<CrimeData> topData = DataPaneConstructor.getTopData(middle); //gets the crime data above the focus point
        ArrayList<CrimeData> bottomData = DataPaneConstructor.getBottomData(middle); // gets the crime data below the focus point

        root = DataPaneConstructor.constructPanes (topData, bottomData, middle);
        return root;
    }


    /**
     * Gets the CrimeData that would be visible above the index of activeData at middle
     *
     * @param middle The middle index of the focus point for the ScrollPane
     * @return all the CrimeData (if any) that occurs above the middle index of activeData
     */
    public static ArrayList<CrimeData> getTopData (int middle) {
        ArrayList<CrimeData> visibleData = new ArrayList<>();
        int i = 0;
        while (middle - i > Math.max(0, middle - 5)) { //gets the 5 crime data objects above the middle one with protection for overflow
            i++;
            visibleData.add (activeData.get(middle - i));
        }
        Collections.reverse(visibleData); //gets data in correct order
        return visibleData;
    }

    /**
     * Gets the CrimeData that would be visible below the index of activeData at middle
     *
     * @param middle The middle index of the focus point for the ScrollPane
     * @return all the CrimeData (if any) that occurs below the middle index of activeData
     */
    public static ArrayList<CrimeData> getBottomData (int middle) {
        ArrayList<CrimeData> visibleData = new ArrayList<>();
        int i = middle + 1;
        while (i < Math.min(activeData.size(), middle + 6)) { // gets the 6 crime data objects below the middle one with protection for overflow
            visibleData.add(activeData.get(i));
            i++;
        }
        return visibleData;
    }


    /**
     * Constructs the culled data into panes, as well as the filler top and bottom panes to allow a smoother
     * experience for the user. This will construct all the DataPanes in topData, middle, and bottomData, and fill
     * the rest of the ScrollPane with 2 empty panes that are not visible to the end user
     * @param topData the data that occurs above the middle index of activeData
     * @param bottomData the data that occurs below the middle index of activeData
     * @param middle the index of activeData that the user is focusing on with the scroll bar
     * @return a VBox containing all the constructed and culled panes
     */
    public static VBox constructPanes (ArrayList<CrimeData> topData, ArrayList<CrimeData> bottomData, int middle) {
        VBox root = new VBox();
        ArrayList<Pane> panes = new ArrayList<>();
        int topPaneHeight = Math.max (0, middle - topData.size()); //gets how many crime panes should be above this
        //gets how many crime panes should be below this
        int bottomPaneHeight = Math.min (activeData.size(), activeData.size() - (middle + bottomData.size() + 1));

        //constructing top pane
        Pane topPane = new Pane ();
        topPane.setPrefWidth(175);
        topPane.setPrefHeight(topPaneHeight * 80);
        panes.add(topPane);

        //adding all the culled data to the view
        for (CrimeData data : topData) {
            panes.add (DataPaneConstructor.constructPane(data));
        }

        panes.add (DataPaneConstructor.constructPane(activeData.get(middle)));

        for (CrimeData data : bottomData) {
            panes.add (DataPaneConstructor.constructPane(data));
        }
        //constructing bottom pane
        Pane bottomPane = new Pane ();
        bottomPane.setPrefWidth(175);
        bottomPane.setPrefHeight(bottomPaneHeight * 80);
        panes.add(bottomPane);

        //adding all constructed pane to the main root object
        root.getChildren().addAll(panes);

        return root;
    }

    /**
     * Constructs a DataPane defined by CrimeData data. This could be either PoliceData or UserData
     * @param data data to be turned into a DataPane
     * @return the DataPane constructed by data
     */
    public static DataPane constructPane (CrimeData data) {
        if (data instanceof PoliceData) {
            return new PoliceDataPane(data);
        }
        return new UserDataPane(data);

    }
}
