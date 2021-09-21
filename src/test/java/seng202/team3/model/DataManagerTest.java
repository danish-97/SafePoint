package seng202.team3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DataManagerTest {

    /**
     * Test to check if getDataByID return the correct crimeData object
     */
    @Test
    public void getDataByIDTest() {
        CrimeData crimeTest = null;
        DataManager data = new DataManager();
        CrimeData crimeData = new CrimeData("JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT" );
        data.addCrimeData(crimeData);
        try {
            crimeTest = data.getDataByID("JE163990");
        } catch (Exception e) {
            Assertions.fail("Test failed: " + e.getMessage());
        }
        assertEquals(crimeData, crimeTest);
    }

    /**
     * Test to check if the method getDataByID handles exceptions properly
     */
    @Test
    public void checkException() {
        DataManager data = new DataManager();
        CrimeData crimeData = new CrimeData("JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT" );
        data.addCrimeData(crimeData);
        try {
            data.getDataByID("JE163770");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Crime JE163770 not found exception");
        }

    }
}
