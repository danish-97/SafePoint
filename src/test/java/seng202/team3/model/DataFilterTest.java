package seng202.team3.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.team3.controller.FilterController;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for DataFilter class.
 * @author Priscilla Ishida-Foale
 */
public class DataFilterTest {

    private DataFilter dataFilter = new DataFilter();
    ArrayList<CrimeData> crimeDataArrayList = new ArrayList<>();
    private FilterController FilterController;

    /**
     * Initializes the info for the data filter
     * @throws ParseException if the input format is invalid.
     */
    @BeforeEach
    public void initDataFilterTestInfo() throws ParseException {

        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,n,n,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        crimeDataArrayList.add(new PoliceData("1", data));
        strSplit = "JE266959,06/15/2021 01:30:00 PM,018XX N DAMEN AVE,BATTERY,n,n,Y,N,1434,34,1162738,1912139,41.91456299,-87.67755343,(41.914562993, -87.677553434)".split(",");
        data = new ArrayList<>(Arrays.asList(strSplit));
        crimeDataArrayList.add(new PoliceData("2", data));
        strSplit = "JD364009,09/11/2020 04:20:00 PM,056XX S WESTERN AVE,ASSAULT,n,n,N,N,824,8,1161332,1867195,41.79126146,-87.683967547,(41.79126146, -87.683967547)".split(",");
        data = new ArrayList<>(Arrays.asList(strSplit));
        crimeDataArrayList.add(new PoliceData("3", data));
        ArrayList<String> uData = new ArrayList<>();
        uData.add("ID123");
        uData.add("11/26/2020"); uData.add("49 MAYS ROAD"); uData.add("THEFT"); uData.add("41.812610526"); uData.add("-87.723765071");
        crimeDataArrayList.add(new UserData("4", uData));
        FilterController = new FilterController();

    }

    /**
     * Tests if a DataFilter will filter results based on types
     */
    @Test
    public void testValidTypeFilterOneResult() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
        CrimeStat typeFilter = CrimeStat.CRIME_TYPE;
        activeFilters.add(typeFilter);
       FilterController.setActiveCrimeType("THEFT");
       FilterController.setArrestMade(false);
       FilterController.setUserDataActive(true);
       FilterController.setPoliceDataActive(true);
       FilterController.setDateFiltering(false);
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(2, filteredData.size());
        assertEquals("1", filteredData.get(0).getId());
    }

    /**
     * Tests if a DataFilter will filter results based on arrests made
     */
    @Test
    public void testArrestMadeFilterOneResult() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
        CrimeStat typeFilter = CrimeStat.ARREST_MADE;
        activeFilters.add(typeFilter);
        FilterController.setArrestMade(true);
        FilterController.setActiveCrimeType(null);
        FilterController.setDateFiltering(false);
        FilterController.setRegionDataActive(false);
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(1, filteredData.size());
    }

    /**
     * Tests if only PoliceData will be returned to the ArrayList
     */
    @Test
    public void testPoliceDataFilter() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<>();
        CrimeStat typeFilter = CrimeStat.POLICE_DATA;
        activeFilters.add(typeFilter);
        FilterController.setArrestMade(false);
        FilterController.setActiveCrimeType(null);
        FilterController.setUserDataActive(false);
        FilterController.setDateFiltering(false);
        FilterController.setRegionDataActive(false);
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(3, filteredData.size());
    }

    /**
     * Tests if an arraylist with crimes that occurred within the given date range is returned
     */
    @Test
    public void testDateRangeFilter() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<>();
        CrimeStat typeFilter = CrimeStat.DATE_RANGE;
        activeFilters.add(typeFilter);
        FilterController.setArrestMade(false);
        FilterController.setActiveCrimeType(null);
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(true);
        FilterController.setDateFiltering(true);
        FilterController.setRegionDataActive(false);
        Date date = new Date(121, 5, 10); // Months are 0-based, therefore June = month 5
        FilterController.setStartDate(date);
        Date date2 = new Date(121, 5, 30); // Years are calculated based on actualYear - 1900
        FilterController.setEndDate(date2);
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(2, filteredData.size());
    }

    /**
     * Tests if the arraylist has been sorted from low frequency crimes to high
     */
    @Test
    public void testLowFrequencyFilter() {
        FilterController.setArrestMade(false);
        FilterController.setActiveCrimeType(null);
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(true);
        FilterController.setDateFiltering(false);
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("LOW FREQUENCY");

        System.out.println(crimeDataArrayList);
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        System.out.println(filteredData);
        assertEquals("THEFT", filteredData.get(2).getCrimeType());

    }

    /**
     * Tests if the arraylist has been sorted from high frequency crimes to low
     */
    @Test
    public void testHighFrequencyFilter() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<>();
        CrimeStat typeFilter = CrimeStat.HIGH_FREQUENCY;
        activeFilters.add(typeFilter);
        FilterController.setArrestMade(false);
        FilterController.setActiveCrimeType(null);
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(true);
        FilterController.setDateFiltering(false);
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("HIGH FREQUENCY");
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        System.out.println(filteredData);
        assertEquals("THEFT", filteredData.get(0).getCrimeType());

    }

    /**
     * Tests if only crimes which occurred on the specified day will be returned
     */
    @Test
    public void testSpecificDateFilter() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<>();
        CrimeStat typeFilter = CrimeStat.DATE_RANGE;
        activeFilters.add(typeFilter);
        FilterController.setArrestMade(false);
        FilterController.setActiveCrimeType(null);
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(true);
        FilterController.setDateFiltering(true);
        FilterController.setRegionDataActive(false);

        Date date = new Date(121, 5, 15); // Months are 0-based, therefore June = month 5
        FilterController.setStartDate(date);
        Date date2 = new Date(121, 5, 15); // Years are calculated based on actualYear - 1900
        FilterController.setEndDate(date2);
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(2, filteredData.size());

    }

    /**
     * Tests if this returns an ArrayList of crimes from high risk areas to low risk areas
     */
    @Test
    public void testHighRiskAreasSorting() {
        FilterController.setArrestMade(false);
        FilterController.setActiveCrimeType(null);
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(false);
        FilterController.setDateFiltering(false);
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("HIGH RISK AREAS");
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals("8", ((PoliceData)filteredData.get(0)).getWard());

    }

    /**
     * Tests if this returns an ArrayList of crimes from low risk areas to high risk areas
     */
    @Test
    public void testLowRiskAreasSorting() {
        FilterController.setArrestMade(false);
        FilterController.setActiveCrimeType(null);
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(false);
        FilterController.setDateFiltering(false);
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("LOW RISK AREAS");
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        System.out.println(filteredData.get(2));
        System.out.println(crimeDataArrayList);
        System.out.println(filteredData);
        assertEquals("8", ((PoliceData)filteredData.get(2)).getWard());
    }




}