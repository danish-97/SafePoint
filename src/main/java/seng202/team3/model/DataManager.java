package seng202.team3.model;

import java.util.ArrayList;

/**
 * Handles and stores data
 * @author roryh
 */
public class DataManager {

    private static ArrayList<CrimeData> allCrimeData = new ArrayList<>();
    private static ArrayList<CrimeData> activeCrimeData = new ArrayList<>();

    public void addCrimeData(CrimeData object) {
        allCrimeData.add(object);
    }

    public static void constructCrimeData (ArrayList<CrimeData> newCrimeData) {
        allCrimeData = newCrimeData;
    }

    public static ArrayList<CrimeData> getData() {
        DataFilter filter = new DataFilter();
        activeCrimeData = filter.filterData(allCrimeData);
        return activeCrimeData;
    }


    /**
     * Loops through allCrimeData and returns matching CrimeData object
     * @param ID is the string using which we get the crimeData object we need
     * @return the CrimeData object
     * @throws Exception if crimeData object is not found
     */
    public CrimeData getDataByID(String ID) throws Exception {
        CrimeData matchingCrime = null;
        for (CrimeData crime : allCrimeData) {
            if (crime.getId().equals(ID)) {
                matchingCrime = crime;
            }
        }
        if (matchingCrime == null) {
            throw new Exception("Crime " + ID + " not found exception");

        } else {
            return matchingCrime;
        }
    }
}
