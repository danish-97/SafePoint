package test.java.seng202.team3;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import seng202.team3.controller.DataFilter;
import seng202.team3.model.CrimeStat;


import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class DataFilterTest
        extends TestCase {

    private DataFilter dataFilter;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataFilterTest(String testName) {
        super(testName);
        dataFilter = new DataFilter();
        ArrayList<CrimeData> crimeDataArrayList = new ArrayList<CrimeData>();
        CrimeData crimeData = new CrimeData("JE123990", "27/08/2021", "1 RANDOM STREET", "THEFT" );
        CrimeData crimeData1 = new CrimeData("JE394839", "28/08/2021", "2 ANOTHER DRIVE", "ROBBERY" );
        CrimeData crimeData2 = new CrimeData("JE239802", "29/08/2021", "3 TEST ROAD", "BURGLARY" );
        CrimeData crimeData3 = new CrimeData("JE029495", "30/08/2021", "4 LAST DRIVE", "THEFT" );
        crimeDataArrayList.add(crimeData);
        crimeDataArrayList.add(crimeData1);
        crimeDataArrayList.add(crimeData2);
        crimeDataArrayList.add(crimeData3);
        UIDataInterface UIdataInterface = new UIDataInterface();


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
        UIdataInterface.setCurrCrimeType("THEFT");
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(1, filteredData.size());
        assertEquals("JE123990", filteredData.get(0).id);
    }

    /**
     * Tests if a DataFilter will filter results based on types with two resulting crimeData
     */
    public void testValidTypeFilterTwoResults() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
        CrimeStat typeFilter = CrimeStat.CRIME_TYPE;
        activeFilters.add(typeFilter);
        dataFilter.setActiveFilters(activeFilters);
        UIdataInterface.setCurrCrimeType("THEFT");
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(2, filteredData.size());
    }


}