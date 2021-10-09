package seng202.team3.model;

import seng202.team3.controller.FilterController;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;


/**
 * Main filtering class. Filters specific attributes (such as PoliceData or UserData records, whether an arrest was made,
 * etc.) and also sorts depending on whether region search is active (according to low/high frequency, high/low risk areas)
 * @author Priscilla Ishida-Foale
 */

public class DataFilter {

    /**
     * Returns a list of crime data after filtering has been processed with ALL the active filters.
     * @param data List of crime data to be filtered.
     * @return an ArrayList<CrimeData> containing only filtered data
     */
    public ArrayList<CrimeData> filterData(ArrayList<CrimeData> data) {
        ArrayList<CrimeStat> activeFilters = FilterController.getActiveFilters();

        if (activeFilters.size() == 0) {
            return data;
        }
        ArrayList<CrimeData> filteredData = (ArrayList<CrimeData>) data.clone();
        for (CrimeStat filter : activeFilters) {
            filteredData.removeAll(filterCrimeData(filter, filteredData, activeFilters));
        }

        if (FilterController.getRegionDataActive()) {
            if (activeFilters.contains(CrimeStat.LOW_FREQUENCY) || activeFilters.contains(CrimeStat.HIGH_FREQUENCY)) {
            filteredData = sortByFrequency(filteredData, true);
            if (activeFilters.contains(CrimeStat.HIGH_FREQUENCY)) {
                Collections.reverse(filteredData);
            } } else {
                filteredData = sortByRisk(filteredData);
                if (activeFilters.contains(CrimeStat.LOW_RISK_AREA)) {
                    Collections.reverse(filteredData);
                }
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
    public ArrayList<CrimeData> filterCrimeData(CrimeStat filter, ArrayList<CrimeData> data, ArrayList<CrimeStat> activeFilters) {
        ArrayList<CrimeData> singleFilterArray = new ArrayList<>();

        for (CrimeData crime : data) {
            switch (filter) {
                case LOCATION:
                    if (!Objects.equals(crime.getLocation(), FilterController.getActiveLocation())) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case CRIME_TYPE:
                    if (!(Objects.equals(crime.getCrimeType(), FilterController.getActiveCrimeType()))) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case ARREST_MADE:
                    if (!((crime instanceof PoliceData) && (Objects.equals(((PoliceData)crime).isArrestMade(), "YES")))) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case POLICE_DATA:
                    if (!(crime instanceof PoliceData) && !(activeFilters.contains(CrimeStat.USER_DATA))) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case USER_DATA:
                    if (!(crime instanceof UserData) && !(activeFilters.contains(CrimeStat.POLICE_DATA))) {
                        singleFilterArray.add(crime);
                    }
                    break;
                case DATE_RANGE:
                    try {
                        String crimeDateStr = crime.getDate().substring(0, 10);
                        Date crimeDate = new SimpleDateFormat("MM/dd/yyyy").parse(crimeDateStr);
                        if (!(isWithinRange(FilterController.getStartDate(), FilterController.getEndDate(), crimeDate))) {
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
     * Overrides the sorting criteria by comparing the lengths of ArrayLists in the given HashMap.
     * @param countList a Hashmap<String, ArrayList<CrimeData>> which maps a given attribute (crime_type or ward) to an
     *                  ArrayList<CrimeData> associated with this.
     * @return a final sorted ArrayList, in ascending order.
     */
    public ArrayList<CrimeData> sortOverrider(HashMap<String, ArrayList<CrimeData>> countList) {

        List<Map.Entry<String, ArrayList<CrimeData>>> list =
                new ArrayList<>(countList.entrySet());
        Collections.sort(list, new EntryComparator()); // Sort based on our new overridden comparator, checking ArrayList lengths.
        ArrayList<CrimeData> finalData = new ArrayList<CrimeData>();
        for (Map.Entry<String, ArrayList<CrimeData>> entry : list) {
            finalData.addAll(entry.getValue());
        }

        return finalData;

    }

    /**
     * Sorts the CrimeData objects array according to whether they belong to the ward with the highest number of crimes.
     * @param filteredData An ArrayList<CrimeData> which has already been filtered/checked against other filters.
     * @return an ArrayList<CrimeData> in ascending order, according to which ward has the highest number of crimes.
     */
    public ArrayList<CrimeData> sortByRisk(ArrayList<CrimeData> filteredData) {
        HashMap<String, ArrayList<CrimeData>> countList = new HashMap<>();
        for (int i = 0; i < filteredData.size(); i++) {

            if (countList.containsKey(((PoliceData)filteredData.get(i)).getWard())) {
                ArrayList<CrimeData> currentArray = countList.get(((PoliceData) filteredData.get(i)).getWard());
                currentArray.add(filteredData.get(i));
                countList.put(((PoliceData) filteredData.get(i)).getWard(), currentArray);
            } else {
                ArrayList<CrimeData> hashMapArray = new ArrayList<CrimeData>();
                hashMapArray.add(filteredData.get(i));
                countList.put(((PoliceData) filteredData.get(i)).getWard(), hashMapArray);
            }
        }


        ArrayList<CrimeData> finalData = sortOverrider(countList);
        return finalData;


    }


    /**
     * Method which returns a sorted arraylist of crimes based on high frequency or low frequency.
     * @param crimeDataArrayList The crimes required to sort.
     * @param isLowFrequency Whether we want to sort in ascending or descending order.
     * @return A sorted arraylist of crimeData.
     */
    public ArrayList<CrimeData> sortByFrequency(ArrayList<CrimeData> crimeDataArrayList, boolean isLowFrequency) {

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

        ArrayList<CrimeData> finalData = sortOverrider(countList);

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



