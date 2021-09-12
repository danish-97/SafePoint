package seng202.team3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataFilter;
import seng202.team3.model.CrimeStat;
import seng202.team3.model.UIDataInterface;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.text.ParseException;
import java.util.ArrayList;

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
        CrimeData crimeData = new CrimeData("1 RANDOM STREET, 27/08/2021, 41.74848637, -87.60267506, THEFT");
        CrimeData crimeData1 = new CrimeData("2 RANDOM STREET, 28/08/2021, 41.74848637, -87.60267506, BATTERY");
        CrimeData crimeData2 = new CrimeData("3 RANDOM STREET, 29/08/2021, 41.74848637, -87.60267506, ASSAULT");
        CrimeData crimeData3 = new CrimeData("4 RANDOM STREET, 30/08/2021, 41.74848637, -87.60267506, HOMICIDE");
        crimeDataArrayList.add(crimeData);
        crimeDataArrayList.add(crimeData1);
        crimeDataArrayList.add(crimeData2);
        crimeDataArrayList.add(crimeData3);
        uiDataInterface = new UIDataInterface();

    }

    /**
     * Tests if a DataFilter will filter results based on types
     */
    @Test
    public void testValidTypeFilterOneResult() {
        System.out.println(crimeDataArrayList);
        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
        CrimeStat typeFilter = CrimeStat.CRIME_TYPE;
        activeFilters.add(typeFilter);
        dataFilter.setActiveFilters(activeFilters);
        uiDataInterface.setCurrCrimeType("THEFT");
        System.out.println(crimeDataArrayList);
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