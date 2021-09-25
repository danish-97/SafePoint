package seng202.team3.controller;

import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;
import java.util.ArrayList;
import seng202.team3.controller.ReadCSV;
import seng202.team3.view.DataPaneConstructor;

public class UIDataInterface {

    private static String dataLocation = "data.csv";

    public static void initCrimeData () {
        ArrayList<CrimeData> allData;
        allData = ReadCSV.readDataLineByLine(dataLocation);
        DataManager.constructCrimeData(allData);
    }

    public static ArrayList<CrimeData> getActiveData () {
        return DataManager.getData();
    }

    public static void addUserData (String userData) {
        WriteCSV exporter = new WriteCSV();

    }
}
