package seng202.team3.controller;

import io.cucumber.java.bs.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeStat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the class FilterController.
 */
public class FilterControllerTest {

    /**
     * Sets the filters to null for all filters before the test.
     */
    @BeforeEach
    public void resetFilters () {
        FilterController.setActiveLocation(null);
        FilterController.setActiveCrimeType(null);
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(true);
        FilterController.setArrestMade(false);
        FilterController.setRegionDataActive(false);
        FilterController.setRegionFilteringKey(null);
        FilterController.setDateFiltering(false);
        FilterController.setStartDate(null);
        FilterController.setEndDate(null);
    }

    /**
     * Checks if the activeFilters method works as intended.
     */
    @Test
    public void testActiveFilters () {
        FilterController.setActiveLocation(new Double[]{43.76854, -87.1353});
        FilterController.setActiveCrimeType("ASSAULT");
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(false);

        ArrayList<CrimeStat> expected = new ArrayList<>();
        expected.add(CrimeStat.LOCATION);
        expected.add(CrimeStat.CRIME_TYPE);
        expected.add(CrimeStat.POLICE_DATA);
        assertEquals(expected, FilterController.getActiveFilters());
    }

    /**
     * Checks if the activeFilters have default value as null.
     */
    @Test
    public void testActiveFiltersNull () {
        ArrayList<CrimeStat> expected = new ArrayList<>();
        expected.add(CrimeStat.POLICE_DATA);
        expected.add(CrimeStat.USER_DATA);
        assertEquals(expected, FilterController.getActiveFilters());
    }

    /**
     * Checks if the regionFiltering method works as intended.
     */
    @Test
    public void testRegionFiltering () {
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("HIGH RISK AREAS");
        FilterController.setUserDataActive(true);

        ArrayList<CrimeStat> expected = new ArrayList<>();
        expected.add(CrimeStat.POLICE_DATA);
        expected.add(CrimeStat.HIGH_RISK_AREA);
        assertEquals(expected, FilterController.getActiveFilters());
    }

}
