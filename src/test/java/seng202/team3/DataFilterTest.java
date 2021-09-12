package seng202.team3;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataFilter;
import seng202.team3.model.CrimeStat;


import java.util.ArrayList;

/**
 * Unit test for DataFilterTest.
 */
public class DataFilterTest
        extends TestCase {

    private DataFilter dataFilter;
    ArrayList<CrimeData> crimeDataArrayList = new ArrayList<>();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataFilterTest(String testName) {
        super(testName);
        dataFilter = new DataFilter();
        ArrayList<CrimeData> crimeDataArrayList = new ArrayList<CrimeData>();
        CrimeData crimeData = new CrimeData("1 RANDOM STREET, 27/08/2021, APARTMENT, THEFT");
        CrimeData crimeData1 = new CrimeData("2 RANDOM STREET, 28/08/2021, SIDEWALK, BATTERY");
        CrimeData crimeData2 = new CrimeData("3 RANDOM STREET, 29/08/2021, HOTEL, ASSAULT");
        CrimeData crimeData3 = new CrimeData("4 RANDOM STREET, 30/08/2021, STREET, HOMICIDE");
        crimeDataArrayList.add(crimeData);
        crimeDataArrayList.add(crimeData1);
        crimeDataArrayList.add(crimeData2);
        crimeDataArrayList.add(crimeData3);
        //  UIDataInterface UIdataInterface = new UIDataInterface();


    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(DataFilterTest.class);
    }
    // TODO clear ActiveFilters before testing

    /**
     * Tests if a DataFilter will filter results based on types
     */
    public void testValidTypeFilterOneResult() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
        CrimeStat typeFilter = CrimeStat.CRIME_TYPE;
        activeFilters.add(typeFilter);
        dataFilter.setActiveFilters(activeFilters);
        //    UIdataInterface.setCurrCrimeType("THEFT");
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(1, filteredData.size());
        assertEquals("1 RANDOM STREET", filteredData.get(0));
    }

    /**
     * Tests if a DataFilter will filter results based on types with two resulting crimeData objects
     */
    public void testValidTypeFilterTwoResults() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
        CrimeStat typeFilter = CrimeStat.CRIME_TYPE;
        activeFilters.add(typeFilter);
        dataFilter.setActiveFilters(activeFilters);
        //   UIdataInterface.setCurrCrimeType("THEFT");
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(2, filteredData.size());
    }

}