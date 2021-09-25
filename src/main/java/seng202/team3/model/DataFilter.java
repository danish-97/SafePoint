package seng202.team3.model;

import seng202.team3.controller.FilterController;
import seng202.team3.controller.UIDataInterface;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

// TODO HIGH_RISK_AREA, LOW_RISK_AREA

public class DataFilter {

    FilterController filterController = new FilterController(); // TODO to be initialised in a different class.
    UIDataInterface uiDataInterface = new UIDataInterface(); // TODO to be initialised in a different class.
    private ArrayList<CrimeStat> activeFilters = filterController.getActiveFilters();
    private DataManager dataManager;

    // If regionDataActive is true, call sortCrimeData, make the array passed into filteredData that sorted array.
    // HIGH_RISK_AREAS sort ONLY the PoliceData and sort based on number of crimes per ward.
    // The crimes that belong to the ward that has the most crimes will be returned at the start of the array list.
    // LOW_RISK_AREAS do the same but in reverse

    /**
     * Returns a list of crime data after filtering has been processed with ALL the active filters.
     * @param data List of crime data to be filtered.
     * @return an ArrayList<CrimeData> containing only filtered data
     */
    public ArrayList<CrimeData> filterData(ArrayList<CrimeData> data) {
        ArrayList<CrimeData> filteredData = (ArrayList<CrimeData>) data.clone();
        for (CrimeStat filter : activeFilters) {
            filteredData.removeAll(filterCrimeData(filter, filteredData));
        }

        if (filterController.getRegionDataActive()) {
            filteredData = countFrequency(filteredData, true);
            if (activeFilters.contains(CrimeStat.LOW_FREQUENCY)) {
                Collections.reverse(filteredData);
            }
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
                    if (!Objects.equals(crime.getLocation(), uiDataInterface.getRegionActive())) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case CRIME_TYPE:
                    if (!Objects.equals(crime.getCrimeType(), uiDataInterface.getCurrCrimeType())) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case DATE:
                    try {
                        String crimeDateStr = crime.getDate().substring(0, 9);
                        Date crimeDate = new SimpleDateFormat("MM/dd/yyyy").parse(crimeDateStr);
                        if (crimeDate != uiDataInterface.getCurrDate()) {
                            singleFilterArray.add(crime);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case ARREST_MADE:
                    if (!(crime instanceof PoliceData) && !(Objects.equals(((PoliceData) crime).isArrestMade(), "YES"))) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case POLICE_DATA:
                    if (!(crime instanceof PoliceData)) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case USER_DATA:
                    if (!(crime instanceof UserData)) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case DATE_RANGE:
                    try {
                        String crimeDateStr = crime.getDate().substring(0, 9);
                        Date crimeDate = new SimpleDateFormat("MM/dd/yyyy").parse(crimeDateStr);
                        if (!(isWithinRange(uiDataInterface.getStartDate(), uiDataInterface.getEndDate(), crimeDate))) {
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

    /**
     * Setter for ActiveFilters parameter.
     * @param activeFilters
     */
    public void setActiveFilters(ArrayList<CrimeStat> activeFilters) {
        this.activeFilters = activeFilters;
    }

    /**
     * Method which returns a sorted arraylist of crimes based on high frequency or low frequency.
     * @param crimeDataArrayList The crimes required to sort.
     * @param isLowFrequency Whether we want to sort in ascending or descending order.
     * @return A sorted arraylist of crimeData.
     */
    public ArrayList<CrimeData> countFrequency(ArrayList<CrimeData> crimeDataArrayList, boolean isLowFrequency) {

        HashMap<String, ArrayList<CrimeData>> countList = new HashMap<>();
        for (int i = 0; i < crimeDataArrayList.size(); i++) {

            if (countList.containsKey(crimeDataArrayList.get(i).getCrimeType())) {
                ArrayList<CrimeData> currentArray = countList.get(crimeDataArrayList.get(i).getCrimeType());
                currentArray.add(crimeDataArrayList.get(i));
                countList.put(crimeDataArrayList.get(i).getCrimeType(), currentArray);
            } else {
                ArrayList<CrimeData> hashMapArray = new ArrayList<CrimeData>();
                hashMapArray.add(crimeDataArrayList.get(i));
                countList.put(crimeDataArrayList.get(i).getCrimeType(), hashMapArray);
            }
        }

        List<Map.Entry<String, ArrayList<CrimeData>>> list =
                new ArrayList<>(countList.entrySet());
        Collections.sort(list, new EntryComparator()); // Sort based on our new overridden comparator, checking ArrayList lengths.

        ArrayList<CrimeData> finalData = new ArrayList<CrimeData>();
        for (Map.Entry<String, ArrayList<CrimeData>> entry : list) {
            finalData.addAll(entry.getValue());
        }
        if (isLowFrequency) {
            Collections.reverse(finalData);
        }

        return finalData;
    }

    /**
     * Overrides comparator for countList's entries - allows us to compare the lengths of the values of this hashmap.
     * Sorts in descending order.
     */
    private static class EntryComparator
            implements Comparator<Map.Entry<String, ArrayList<CrimeData>>>
    {
        public int compare(Map.Entry<String, ArrayList<CrimeData>> left,
                           Map.Entry<String, ArrayList<CrimeData>> right) {
            // Right then left to get a descending order
            return Integer.compare(right.getValue().size(), left.getValue().size());
        }
    }





    }



