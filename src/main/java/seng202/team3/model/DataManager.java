package seng202.team3.model;

import java.util.ArrayList;

/**
 * Handles and stores data
 * @author roryh
 */
public class DataManager {

    private String[] acitveDataLocation;
    private ArrayList<CrimeData> allCrimeData = new ArrayList<>();
    private ArrayList<CrimeData> activeCrimeData = new ArrayList<>();

    public void addCrimeData(CrimeData object) {
        allCrimeData.add(object);
    }

   public ArrayList<CrimeData> getData() {
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