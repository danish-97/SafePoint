package seng202.team3.controller;

import seng202.team3.model.CrimeStat;

import java.util.ArrayList;
import java.util.Date;

/**
 * Controls the filters and their application on the crime data.
 */
public class FilterController {

    private static Double[] activeLocation;
    private static String activeCrimeType;
    private static Boolean policeDataActive = true;
    private static Boolean userDataActive = true;
    private static Boolean arrestMade = false;
    private static Boolean regionDataActive = false;
    private static String regionFilteringKey;
    private static Boolean dateFilteringActive = false;
    private static Date startDate;
    private static Date endDate;

    /**
     * Sets the activeLocation to the newLocation as a static method
     * @param newLocation is the location we set it to.
     */
    public static void setActiveLocation(Double[] newLocation) {
        activeLocation =  newLocation;
    }

    /**
     * Sets the activeCrimeType to the newCrimeType as a static method
     * @param newCrimeType is the crimeType we set it to.
     */
    public static void setActiveCrimeType(String newCrimeType) {
        activeCrimeType = newCrimeType;
    }

    /**
     * Sets the policeDataActive to the newData as a static method
     * @param newData is the data we set it to.
     */
    public static void setPoliceDataActive (Boolean newData) {policeDataActive = newData;}

    /**
     * Sets the userDataActive to the newData as a static method
     * @param newData is the data we set it to.
     */
    public static void setUserDataActive (Boolean newData) {
        userDataActive = newData;
    }

    /**
     * Sets the arrestMade to the newData as a static method
     * @param newData is the data we set it to.
     */
    public static void setArrestMade (Boolean newData) {
        arrestMade = newData;
    }

    /**
     * Sets the regionDataActive to the newData as a static method
     * @param newData is the data we set it to.
     */
    public static void setRegionDataActive (Boolean newData) {regionDataActive = newData;}

    /**
     * Sets the regionFilteringKey to the newKey as a static method
     * @param newKey is the key we set it to.
     */
    public static void setRegionFilteringKey (String newKey) {regionFilteringKey = newKey;}

    /**
     * Sets the dateFiltering to the newData as a static method
     * @param newData is the data we set it to.
     */
    public static void setDateFiltering (Boolean newData) {dateFilteringActive = newData;}

    /**
     * Sets the startDate to the newDate as a static method
     * @param newDate is the date we set it to.
     */
    public static void setStartDate (Date newDate) {startDate = newDate;}

    /**
     * Sets the endDate to the newDate as a static method
     * @param newDate is the date we set it to.
     */
    public static void setEndDate (Date newDate) {endDate = newDate;}

    /**
     * Sets the dateFilteringActive to true or false as a static method
     * @param dateFilteringActive is the boolean we set it to.
     */
    public static void setDateFilteringActive(Boolean dateFilteringActive) {
        FilterController.dateFilteringActive = dateFilteringActive;
    }

    /**
     * Gets the active filters of the crimes and adds them to an ArrayList as a static method
     * @return an ArrayList of active crime filters.
     */
    public static ArrayList<CrimeStat> getActiveFilters() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<>();
        if (activeLocation != null) {
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
        if (regionDataActive && regionFilteringKey.equals("HIGH FREQUENCY")) {
            activeFilters.add(CrimeStat.HIGH_FREQUENCY);
        }
        if (regionDataActive && regionFilteringKey.equals("LOW FREQUENCY")) {
            activeFilters.add(CrimeStat.LOW_FREQUENCY);
        }
        if (regionDataActive && regionFilteringKey.equals("HIGH RISK AREAS")) {
            activeFilters.add(CrimeStat.HIGH_RISK_AREA);
            activeFilters.remove(CrimeStat.USER_DATA);
        }
        if (regionDataActive && regionFilteringKey.equals("LOW RISK AREAS")) {
            activeFilters.add(CrimeStat.LOW_RISK_AREA);
            activeFilters.remove(CrimeStat.USER_DATA);
        }
        return activeFilters;
    }

    /**
     * Gets the activeLocation of the crime as a static method
     * @return the activeLocation of type double[].
     */
    public static Double[] getActiveLocation() {return activeLocation;}

    /**
     * Gets the activeCrimeType of the crime as a static method
     * @return the activeCrimeType of type String.
     */
    public static String getActiveCrimeType() {return activeCrimeType;}

    /**
     * Gets the startDate of the crime as a static method
     * @return the startDate of type Date.
     */
    public static Date getStartDate() {return startDate;}

    /**
     * Gets the endDate of the crime as a static method
     * @return the endDate of type Date.
     */
    public static Date getEndDate() {return endDate;}

    /**
     * Gets the regionDataActive of the crime as a static method
     * @return the regionDataActive of type Boolean.
     */
    public static Boolean getRegionDataActive() {return regionDataActive;}

}
