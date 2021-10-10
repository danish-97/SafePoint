package seng202.team3.controller;

import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import seng202.team3.controller.ReadCSV;
import seng202.team3.model.UserData;
import seng202.team3.view.DataPaneConstructor;

/**
 * Class which handles the backend of the User Interface.
 */
public class UIDataInterface {

    private static String dataLocation = "data.csv";

    private static Boolean compareCrimes = false;

    private static CrimeData comparator = null;

    /**
     * Initializes the CrimeData and reads it from the CSV file.
     */
    public static void initCrimeData () {
        ArrayList<CrimeData> allData;
        allData = ReadCSV.readDataLineByLine(dataLocation);
        DataManager.constructCrimeData(allData);
    }

    /**
     * Gets the activeData of the crimes.
     * @return an ArrayList of active CrimeData.
     */
    public static ArrayList<CrimeData> getActiveData () {
        return DataManager.getData();
    }

    /**
     * Adds the user data input by the user to the CSV file.
     * @param userData is the data added by the user.
     * @throws ParseException if the input format is invalid.
     */
    public static void addUserData (String userData) throws ParseException {
        userData = CrimeData.getLatestID() + "," + userData;
        ArrayList<String> spltUserData = new ArrayList<>(Arrays.asList(userData.split(",")));
        UserData uData = new UserData (CrimeData.getLatestID(), spltUserData);
        CrimeData.incrementLatestID();
        DataManager.addCrimeData(uData);
        Importer.addUserData(uData);
    }

    /**
     * Sets compareCrimes to newValue as a static method
     * @param newValue is the value we set it to.
     */
    public static void setCompareCrimes (Boolean newValue) {
        compareCrimes = newValue;
    }

    /**
     * Checks if the isComparingCrimes is active
     * @return compareCrimes as the boolean value true or false
     */
    public static Boolean isComparingCrimes () {
        return compareCrimes;
    }

    /**
     * Sets the comparator to the value data as a static method.
     * @param data is the value we set it to.
     */
    public static void setComparator (CrimeData data) {
        comparator = data;
    }

    /**
     * Gets the comparator as a static value
     * @return the value of the comparator of type CrimeData.
     */
    public static CrimeData getComparator () {
        return comparator;
    }
}
