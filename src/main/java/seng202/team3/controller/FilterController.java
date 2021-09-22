package seng202.team3.controller;

import seng202.team3.model.CrimeStat;

import java.util.ArrayList;

public class FilterController {

    private static String activeLocation;
    private static String activeCrimeType;
    private static Boolean policeDataActive = true;
    private static Boolean userDataActive = true;
    private static Boolean arrestMade = true;

    public static void setActiveLocation(String newLocation) {
        activeLocation =  newLocation;
    }

    public static void setActiveCrimeType(String newCrimeType) {
        activeCrimeType = newCrimeType;
    }

    public static void setPoliceDataActive (Boolean newData) {
        policeDataActive = newData;
    }

    public static void setUserDataActive (Boolean newData) {
        userDataActive = newData;
    }

    public static void setArrestMade (Boolean newData) {
        arrestMade = newData;
    }

    public static ArrayList<CrimeStat> getActiveFilters() {
        return null;
    }

}
