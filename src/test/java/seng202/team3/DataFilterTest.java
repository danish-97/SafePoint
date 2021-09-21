package seng202.team3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.team3.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for DataFilterTest.
 */
public class DataFilterTest {

    private DataFilter dataFilter;
    private UIDataInterface uiDataInterface;
    ArrayList<CrimeData> crimeDataArrayList = new ArrayList<>();


    /**
     * Create the test case
     *
     * @param testName
     */

    public DataFilterTest(String testName) throws ParseException {

        dataFilter = new DataFilter();



    }

    @BeforeEach
    public void initDataFilterTestInfo() throws ParseException {

        ArrayList<CrimeData> crimeDataArrayList = new ArrayList<CrimeData>();
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        crimeDataArrayList.add(new PoliceData("1", data));
        strSplit = "JE163990,11/23/2020 03:05:00 PM,073XX S SOUTH SHORE DR,0820,THEFT,$500 AND UNDER,APARTMENT,N,N,334,7,06,,,,,".split(",");
        data = new ArrayList<>(Arrays.asList(strSplit));
        crimeDataArrayList.add(new PoliceData("2", data));
        strSplit = "JD364009,09/11/2020 04:20:00 PM,056XX S WESTERN AVE,0560,ASSAULT,SIMPLE,AUTO / BOAT / RV DEALERSHIP,N,N,824,15,08A,1161332,1867195,41.79126146,-87.683967547,(41.79126146, -87.683967547)".split(",");
        data = new ArrayList<>(Arrays.asList(strSplit));
        crimeDataArrayList.add(new PoliceData("3", data));
        ArrayList<String> uData = new ArrayList<>();
        uData.add("THEFT"); uData.add("49 MAYS ROAD"); uData.add("41.812610526"); uData.add("-87.723765071");
        crimeDataArrayList.add(new UserData("4", uData));
        uiDataInterface = new UIDataInterface();

    }

    /**
     * Tests if a DataFilter will filter results based on types
     */
    @Test
    public void testValidTypeFilterOneResult() {
        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
        CrimeStat typeFilter = CrimeStat.CRIME_TYPE;
        activeFilters.add(typeFilter);
        dataFilter.setActiveFilters(activeFilters);
        uiDataInterface.setCurrCrimeType("THEFT");
        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
        assertEquals(1, filteredData.size());
        assertEquals("1 RANDOM STREET", filteredData.get(0));
    }

    /**
     * Tests if a DataFilter will filter results based on types with two resulting crimeData objects
     */
    @Test
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