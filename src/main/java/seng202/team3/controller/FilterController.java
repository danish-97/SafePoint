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
    private static Boolean highFreqActive = false;
    private static Boolean lowFreqActive = false;
    private static Boolean highRiskAreas = false;
    private static Boolean lowRiskAreas = false;

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

    public static void setDateFilteringActive(Boolean dateFilteringActive) {
        FilterController.dateFilteringActive = dateFilteringActive;
    }

    public static void setHighFreqActive(Boolean highFreqActive) {
        FilterController.highFreqActive = highFreqActive;
    }

    public static void setLowFreqActive(Boolean lowFreqActive) {
        FilterController.lowFreqActive = lowFreqActive;
    }

    public static void setHighRiskAreas(Boolean highRiskAreas) {
        FilterController.highRiskAreas = highRiskAreas;
    }

    public static void setLowRiskAreas(Boolean lowRiskAreas) {
        FilterController.lowRiskAreas = lowRiskAreas;
    }

    public static void setAllFilters(String location, String crimeType, Boolean policeActive, Boolean userActive,
                                     Boolean arrest, Boolean regionData, String regionKey, Boolean dateFiltering,
                                     Date start, Date end, Boolean highFreq, Boolean lowFreq,
                                     Boolean highRisk, Boolean lowRisk) {

        String activeLocation = location;
        String activeCrimeType = crimeType;
        Boolean policeDataActive = policeActive;
        Boolean userDataActive = userActive;
        Boolean arrestMade = arrest;
        Boolean regionDataActive = regionData;
        String regionFilteringKey = regionKey;
        Boolean dateFilteringActive = dateFiltering;
        Date startDate = start;
        Date endDate = end;
        Boolean highFreqActive = highFreq;
        Boolean lowFreqActive = lowFreq;
        Boolean highRiskAreas = highRisk;
        Boolean lowRiskAreas = lowRisk;

    }

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
        if (regionDataActive && highFreqActive) {
            activeFilters.add(CrimeStat.HIGH_FREQUENCY);
        }
        if (regionDataActive && lowFreqActive) {
            activeFilters.add(CrimeStat.LOW_FREQUENCY);
        }
        if (regionDataActive && highRiskAreas) {
            activeFilters.add(CrimeStat.HIGH_RISK_AREA);
            activeFilters.remove(CrimeStat.USER_DATA);
        }
        if (regionDataActive && lowRiskAreas) {
            activeFilters.add(CrimeStat.LOW_RISK_AREA);
            activeFilters.remove(CrimeStat.USER_DATA);
        }
        return activeFilters;
    }

    public static String getActiveLocation() {return activeLocation;}

    public static String getActiveCrimeType() {return activeCrimeType;}

    public static Date getStartDate() {return startDate;}

    public static Date getEndDate() {return endDate;}

    public static Boolean getRegionDataActive() {return regionDataActive;}

}
