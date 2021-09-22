package seng202.team3.model;

import java.util.ArrayList;

public class DataManager {

    private String[] acitveDataLocation;
    private static final ArrayList<CrimeData> allCrimeData = new ArrayList<>();
    private static final ArrayList<CrimeData> activeCrimeData = new ArrayList<>();

    public static void addCrimeData(CrimeData object) {
        allCrimeData.add(object);
    }

    public void addActiveCrimeData(CrimeData crime) {
        activeCrimeData.add(crime);
    }

    public static ArrayList<CrimeData> getAllData() {return allCrimeData; }

    /**
     * Returns activeCrimeData which is a list of CrimeData objects
     * @return activeCrimeData
     */
    public ArrayList<CrimeData> getData() {
        return activeCrimeData;
    }
    

    /**
     * Loops through allCrimeData and returns matching CrimeData object
     * @param ID is the string using which we get the crimeData object we need
     * @return the CrimeData object
     * @throws Exception if crimeData object is not found
     */
   public static CrimeData getDataByID(String ID) throws Exception {
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