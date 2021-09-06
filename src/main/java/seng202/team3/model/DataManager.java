package main.java.seng202.team3.model;

import seng202.team3.model.CrimeStat;

public class DataManager {

    private String[] acitveDataLocation;
    private ArrayList<CrimeData> allCrimeData = new ArrayList<CrimeData>();
    private ArrayList<CrimeData> activeCrimeDataactiveCrimeData = new ArrayList<CrimeData>();

   /* public ArrayList<CrimeData> getData() {
        //TODO IMPLEMENT getData()
        return NULL;
    }
    */
   public CrimeData getDataByID(String ID) throws Exception {
        //Loops through allCrimeData and returns Crime with matching ID
        CrimeData matchingCrime = null;
        for (CrimeData crime : allCrimeData) {
            if (crime.getCrimeId() == ID) {
                matchingCrime = crime;
            }
        }
        if (matchingCrime == null) {
            throw new Exception("Crime not found excpetion");

        } else return matchingCrime;
    }

}