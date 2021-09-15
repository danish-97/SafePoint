package seng202.team3.model;


import java.util.ArrayList;

public class DataManager {

    private String[] acitveDataLocation;
    private ArrayList<CrimeData> allCrimeData = new ArrayList<>();
    private ArrayList<CrimeData> activeCrimeDataactiveCrimeData = new ArrayList<>();

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