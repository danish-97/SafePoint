//package seng202.team3.model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import seng202.team3.controller.FilterController;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
///**
// * Unit test for DataFilterTest.
// */
//public class DataFilterTest {
//
//    private DataFilter dataFilter = new DataFilter();
//    ArrayList<CrimeData> crimeDataArrayList = new ArrayList<>();
//    private FilterController filterController = new FilterController();
//
////    /**
////     * Create the test case
////     *
////     * @param testName
////     */
////
////    public DataFilterTest(String testName) throws ParseException {
////
////        dataFilter = new DataFilter();
////        filterController = new FilterController();
////
////
////    }
//
//    @BeforeEach
//    public void initDataFilterTestInfo() throws ParseException {
//
//        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
//        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
//        crimeDataArrayList.add(new PoliceData("1", data));
//        strSplit = "JE266959,06/15/2021 01:30:00 PM,018XX N DAMEN AVE,460,BATTERY,SIMPLE,PARK PROPERTY,N,N,1434,32,08B,1162738,1912139,41.91456299,-87.67755343,(41.914562993, -87.677553434)".split(",");
//        data = new ArrayList<>(Arrays.asList(strSplit));
//        crimeDataArrayList.add(new PoliceData("2", data));
//        strSplit = "JD364009,09/11/2020 04:20:00 PM,056XX S WESTERN AVE,0560,ASSAULT,SIMPLE,AUTO / BOAT / RV DEALERSHIP,N,N,824,15,08A,1161332,1867195,41.79126146,-87.683967547,(41.79126146, -87.683967547)".split(",");
//        data = new ArrayList<>(Arrays.asList(strSplit));
//        crimeDataArrayList.add(new PoliceData("3", data));
//        ArrayList<String> uData = new ArrayList<>();
//        uData.add("THEFT"); uData.add("49 MAYS ROAD"); uData.add("41.812610526"); uData.add("-87.723765071"); uData.add("11/26/2020");
//        crimeDataArrayList.add(new UserData("4", uData));
//
//    }
//
//    /**
//     * Tests if a DataFilter will filter results based on types
//     */
//    @Test
//    public void testValidTypeFilterOneResult() {
//        System.out.println(crimeDataArrayList);
//        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
//        CrimeStat typeFilter = CrimeStat.CRIME_TYPE;
//        activeFilters.add(typeFilter);
//       filterController.setActiveCrimeType("THEFT");
//        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
//        assertEquals(2, filteredData.size());
//        assertEquals("1", filteredData.get(0).getId());
//    }
//
//    /**
//     * Tests if a DataFilter will filter results based on types with two resulting crimeData objects
//     */
//    @Test
//    public void testValidTypeFilterTwoResults() {
//        ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();
//        CrimeStat typeFilter = CrimeStat.CRIME_TYPE;
//        activeFilters.add(typeFilter);
//        filterController.setActiveCrimeType("THEFT");
//        ArrayList<CrimeData> filteredData = dataFilter.filterData(crimeDataArrayList);
//        assertEquals(2, filteredData.size());
//    }
//
//}