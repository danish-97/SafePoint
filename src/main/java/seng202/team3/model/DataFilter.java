package seng202.team3.model;

import org.apache.commons.lang3.ArrayUtils;
import seng202.team3.controller.FilterController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Main filtering class. Filters specific attributes (such as PoliceData or UserData records, whether an arrest was made,
 * etc.) and also sorts depending on whether region search is active (according to low/high frequency, high/low risk areas)
 * @author Priscilla Ishida-Foale
 */

public class DataFilter {

    /**
     * Returns a list of crime data after filtering has been processed with ALL the active filters.
     * @param data List of crime data to be filtered.
     * @return an ArrayList of CrimeData containing only filtered data
     */
    public ArrayList<CrimeData> filterData(ArrayList<CrimeData> data) {
        ArrayList<CrimeStat> activeFilters = FilterController.getActiveFilters();
        if (activeFilters.size() == 0) {
            return data;
        }

        if (FilterController.getRegionDataActive() &&
                (activeFilters.contains(CrimeStat.HIGH_RISK_AREA)||
                        activeFilters.contains(CrimeStat.LOW_RISK_AREA))) {
            activeFilters.remove(CrimeStat.USER_DATA); // Remove user crime data
            activeFilters.add(CrimeStat.POLICE_DATA); // Add only police data
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
     * @param activeFilters list of the active filters on the data.
     * @return an Arraylist of CrimeData with filtered crimes.
     */
    public ArrayList<CrimeData> filterCrimeData(CrimeStat filter, ArrayList<CrimeData> data, ArrayList<CrimeStat> activeFilters) {
        ArrayList<CrimeData> singleFilterArray = new ArrayList<>();

        for (CrimeData crime : data) {
            switch (filter) {
                case LOCATION:
                    if (!(Objects.equals(crime.getLocation(), ", "))) {
                        if (!crimeInRange(crime.getLocation(), FilterController.getActiveLocation())) {
                            singleFilterArray.add(crime);
                        }
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
     * @param countList a Hashmap which maps a given attribute (crime_type or ward) to an ArrayList of CrimeData
     *                 associated with this.
     * @return a final sorted ArrayList, in ascending order.
     */
    public ArrayList<CrimeData> sortOverrider(HashMap<String, ArrayList<CrimeData>> countList) {

        List<Map.Entry<String, ArrayList<CrimeData>>> list =
                new ArrayList<>(countList.entrySet());
        Collections.sort(list, new EntryComparator()); // Sort based on our new overridden comparator, checking ArrayList lengths.
        ArrayList<CrimeData> finalData = new ArrayList<>();
        for (Map.Entry<String, ArrayList<CrimeData>> entry : list) {
            finalData.addAll(entry.getValue());
        }

        return finalData;

    }

    /**
     * Sorts the CrimeData objects array according to whether they belong to the ward with the highest number of crimes.
     * @param filteredData An ArrayList of CrimeData which has already been filtered/checked against other filters.
     * @return an ArrayList of CrimeData in ascending order, according to which ward has the highest number of crimes.
     */
    public ArrayList<CrimeData> sortByRisk(ArrayList<CrimeData> filteredData) {
        HashMap<String, ArrayList<CrimeData>> countList = new HashMap<>();
        for (CrimeData filteredDatum : filteredData) {

            if (countList.containsKey(((PoliceData) filteredDatum).getWard())) {
                ArrayList<CrimeData> currentArray = countList.get(((PoliceData) filteredDatum).getWard());
                currentArray.add(filteredDatum);
                countList.put(((PoliceData) filteredDatum).getWard(), currentArray);
            } else {
                ArrayList<CrimeData> hashMapArray = new ArrayList<>();
                hashMapArray.add(filteredDatum);
                countList.put(((PoliceData) filteredDatum).getWard(), hashMapArray);
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
        for (CrimeData crimeData : crimeDataArrayList) {

            if (countList.containsKey(crimeData.getCrimeType())) {
                ArrayList<CrimeData> currentArray = countList.get(crimeData.getCrimeType());
                currentArray.add(crimeData);
                countList.put(crimeData.getCrimeType(), currentArray);
            } else {
                ArrayList<CrimeData> hashMapArray = new ArrayList<>();
                hashMapArray.add(crimeData);
                countList.put(crimeData.getCrimeType(), hashMapArray);
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

    /**
     * Calculates whether two address' are within 1 km radius of each other
     * @param crimeAddress Address of a CrimeDataObject
     * @param inputLatLong Address of user input
     * @return True if address' are within 1 km radius, false otherwise
     */
    public boolean crimeInRange(String crimeAddress, Double[] inputLatLong){
        //TODO call this method where its supposed to be called

        //Initialising variables
        String[] crimeLatLong = crimeAddress.split(",");

        double[] inputLatLong2 = ArrayUtils.toPrimitive(inputLatLong);
        if (Objects.equals(crimeLatLong[0], "") || Objects.equals(crimeLatLong[1], "")) {
            return false;
        }
        return (distance(Double.parseDouble(crimeLatLong[0]), Double.parseDouble(crimeLatLong[1]), inputLatLong2[0], inputLatLong2[1]));
    }


    /**
     * Calculates distance between two lat/long points
     * Adapted from: https://dzone.com/articles/distance-calculation-using-3
     * @param lat1 latitude of user inputted address
     * @param lon1 longitude of user inputted address
     * @param lat2 latitude of crime address
     * @param lon2 longitude of crime address
     * @return True if distance between two points is less than 1 km
     */
    private boolean distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist < 1.1);
    }

    /**
     * Converts decimal degrees to radians
     * Adapted from: https://dzone.com/articles/distance-calculation-using-3
     * @param deg Decimal degree value
     * @return Radian value
     */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * Converts radians to decimal degrees
     * Adapted from: https://dzone.com/articles/distance-calculation-using-3
     * @param rad Radian Value
     * @return Decimal degree value
     */
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}