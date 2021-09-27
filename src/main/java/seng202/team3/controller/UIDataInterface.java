package seng202.team3.controller;

import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import seng202.team3.controller.ReadCSV;
import seng202.team3.model.UserData;
import seng202.team3.view.DataPaneConstructor;

public class UIDataInterface {

    private static String dataLocation = "data.csv";

    private static Boolean compareCrimes = false;

    private static CrimeData comparator = null;

    public static void initCrimeData () {
        ArrayList<CrimeData> allData;
        allData = ReadCSV.readDataLineByLine(dataLocation);
        DataManager.constructCrimeData(allData);
    }

    public static ArrayList<CrimeData> getActiveData () {
        return DataManager.getData();
    }

    public static void addUserData (String userData) throws ParseException {
        userData = CrimeData.getLatestID() + "," + userData;
        ArrayList<String> spltUserData = new ArrayList<>(Arrays.asList(userData.split(",")));
        UserData uData = new UserData (CrimeData.getLatestID(), spltUserData);
        CrimeData.incrementLatestID();
        DataManager.addCrimeData(uData);
        Importer.addUserData(uData);
    }

    public static void setCompareCrimes (Boolean newValue) {
        compareCrimes = newValue;
    }

    public static Boolean isComparingCrimes () {
        return compareCrimes;
    }

    public static void setComparator (CrimeData data) {
        comparator = data;
    }

    public static CrimeData getComparator () {
        return comparator;
    }
}
