package seng202.team3.model;

import seng202.team3.controller.FilterController;
import seng202.team3.controller.UIDataInterface;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

// TODO HIGH_FREQ, LOW_FREQ sorting

public class DataFilter {

    /**
     * Returns a list of crime data after filtering has been processed with ALL the active filters.
     * @param data List of crime data to be filtered.
     * @return an ArrayList<CrimeData> containing only filtered data
     */
    public ArrayList<CrimeData> filterData(ArrayList<CrimeData> data) {
        ArrayList<CrimeStat> activeFilters = FilterController.getActiveFilters();

        ArrayList<CrimeData> filteredData = new ArrayList<CrimeData>();
        for (CrimeStat filter : activeFilters) {
            filteredData.addAll(filterCrimeData(filter, data));
        }
        return filteredData;

    }

    /**
     * Checks whether a date is within range of the startDate and endDate given.
     * @param startDate selected by the user and processed in UIDataInterface
     * @param endDate selected by the user and processed in UIDataInterface
     * @param testDate date of a given CrimeData object.
     * @return true if within range, false if not.
     */
    public boolean isWithinRange(Date startDate, Date endDate, Date testDate) {
        return !(testDate.before(startDate) || testDate.after(endDate));
    }

    /**
     * Filters a list of CrimeData according to a single specific given filter.
     * @param filter criteria used to filter data.
     * @param data list of CrimeData objects to be filtered.
     * @return an Arraylist<CrimeData> with filtered crimes.
     */
    public ArrayList<CrimeData> filterCrimeData(CrimeStat filter, ArrayList<CrimeData> data) {
        ArrayList<CrimeData> singleFilterArray = new ArrayList<CrimeData>();
        for (CrimeData crime : data) {
            switch (filter) {
                case LOCATION:
                    if (Objects.equals(crime.getLocation(), FilterController.getActiveLocation())) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case CRIME_TYPE:
                    if (Objects.equals(crime.getCrimeType(), FilterController.getActiveCrimeType())) {
                        singleFilterArray.add(crime);
                    }
                    break;
                    /*
                case DATE:
                    try {
                        String crimeDateStr = crime.getDate().substring(0, 9);
                        Date crimeDate = new SimpleDateFormat("MM/dd/yyyy").parse(crimeDateStr);
                        if (crimeDate == uiDataInterface.getCurrDate()) {
                            singleFilterArray.add(crime);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                    */
                case ARREST_MADE:
                    if (crime instanceof PoliceData && (Objects.equals(((PoliceData) crime).isArrestMade(), "YES"))) {
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
                    try {
                        String crimeDateStr = crime.getDate().substring(0, 9);
                        Date crimeDate = new SimpleDateFormat("MM/dd/yyyy").parse(crimeDateStr);
                        if (isWithinRange(FilterController.getStartDate(), FilterController.getEndDate(), crimeDate)) {
                            singleFilterArray.add(crime);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
        return singleFilterArray;
    }

//    public ArrayList<CrimeData> sortByFrequency() {
//
//    }
}
