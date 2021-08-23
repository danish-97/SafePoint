package seng202.team3.controller;

import seng202.team3.model.CrimeData;
import seng202.team3.model.CrimeStat;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

import java.util.ArrayList;

// TODO DATA_RANGE section, using java.Date
// TODO HIGH_FREQ, LOW_FREQ sorting

public class DataFilter {

    private ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();


    public ArrayList<CrimeData> filterData(ArrayList<CrimeData> data) {
        ArrayList<CrimeData> filteredData = new ArrayList<CrimeData>();
        for (CrimeStat filter : activeFilters) {
            filteredData.addAll(filterCrimeData(filter, data));
        }
        return filteredData;

    }

    public ArrayList<CrimeData> filterCrimeData(CrimeStat filter, ArrayList<CrimeData> data) {
        ArrayList<CrimeData> singleFilterArray = new ArrayList<CrimeData>();
        for (CrimeData crime : data) {
            switch (filter) {
                case LOCATION:
                    if (crime.getLocation() == (UIDataInterface.regionActive)) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case CRIME_TYPE:
                    if (crime.getCrimeType() == UIDataInterface.currCrimeType) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case DATE:
                    if (crime.getDate() == UIDataInterface.currDate) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case ARREST_MADE:
                    if (crime instanceof PoliceData && (((PoliceData) crime).isArrestMade() == 'Y')) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case POLICE_DATA:
                    if (crime instanceof PoliceData) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case USER_DATA:
                    if (crime instanceof UserData) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case DATE_RANGE:
                    if (crime.getDate() == UIDataInterface.currDateRange) { // TODO might have to change date to be a Java.date value to work with range
                        singleFilterArray.add(crime);
                    }
                    break;
            }

        }
        return singleFilterArray;
    }

    public ArrayList<CrimeData> sortByFrequency() {

    }

    public void setActiveFilters(ArrayList<CrimeStat> activeFilters) {
        this.activeFilters = activeFilters;
    }
}
