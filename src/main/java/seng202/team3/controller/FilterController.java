package seng202.team3.controller;

import seng202.team3.model.CrimeStat;

import java.util.ArrayList;
import java.util.Date;

public class FilterController {

    private static String activeLocation;
    private static String activeCrimeType;
    private static Boolean policeDataActive = true;
    private static Boolean userDataActive = true;
    private static Boolean arrestMade = false;
    private static Boolean regionDataActive = false;
    private static String regionFilteringKey;
    private static Boolean dateFilteringActive = false;
    private static Date startDate;
    private static Date endDate;

    public static void setActiveLocation(String newLocation) {
        activeLocation =  newLocation;
    }

    public static void setActiveCrimeType(String newCrimeType) {
        activeCrimeType = newCrimeType;
    }

    public static void setPoliceDataActive (Boolean newData) {policeDataActive = newData;}

    public static void setUserDataActive (Boolean newData) {
        userDataActive = newData;
    }

    public static void setArrestMade (Boolean newData) {
        arrestMade = newData;
    }

    public static void setRegionDataActive (Boolean newData) {regionDataActive = newData;}

    public static void setRegionFilteringKey (String newKey) {regionFilteringKey = newKey;}

    public static void setDateFiltering (Boolean newData) {dateFilteringActive = newData;}

    public static void setStartDate (Date newDate) {startDate = newDate;}

    public static void setEndDate (Date newDate) {endDate = newDate;}

    public static ArrayList<CrimeStat> getActiveFilters() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<>();
        if (activeLocation != null && !activeLocation.equals("")) {
            activeFilters.add(CrimeStat.LOCATION);
        }
        if (activeCrimeType != null && !activeCrimeType.equals("ALL")) {
            activeFilters.add(CrimeStat.CRIME_TYPE);
        }
        if (policeDataActive) {
            activeFilters.add(CrimeStat.POLICE_DATA);
        }
        if (userDataActive) {
            activeFilters.add(CrimeStat.USER_DATA);
        }
        if (arrestMade) {
            activeFilters.add(CrimeStat.ARREST_MADE);
        }
        if (dateFilteringActive) {
            activeFilters.add(CrimeStat.DATE_RANGE);
        }
        return activeFilters;
    }

    public static String getActiveLocation() {return activeLocation;}

    public static String getActiveCrimeType() {return activeCrimeType;}

    public static Date getStartDate() {return startDate;}

    public static Date getEndDate() {return endDate;}

    public static Boolean getRegionDataActive() {return regionDataActive;}

}
