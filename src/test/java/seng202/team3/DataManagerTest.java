package seng202.team3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seng202.team3.model.DataManager.*;


public class DataManagerTest {

    /**
     * Test to check if getDataByID return the correct crimeData object
     */
    @Test
    public void getDataByIDTest() {
        CrimeData crimeTest = null;
        CrimeData crimeData = new CrimeData("JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT" );
        addCrimeData(crimeData);
        try {
            crimeTest = getDataByID("JE163990");
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
        CrimeData crimeData = new CrimeData("JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT" );
        addCrimeData(crimeData);
        try {
            getDataByID("JE163770");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Crime JE163770 not found exception");
        }

    }

    /**
     * Test to check if getData() returns correct ArrayList
     */
    @Test

    public void getActiveData() {
        DataManager data = new DataManager();
        ArrayList<CrimeData> activeCrimeDataTest = new ArrayList<>();

        CrimeData crime1 = new CrimeData("JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT" );
        CrimeData crime2 = new CrimeData("JE163991, 042YY W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT" );
        activeCrimeDataTest.add(crime1);
        activeCrimeDataTest.add(crime2);

        data.addActiveCrimeData(crime1);
        data.addActiveCrimeData(crime2);

        ArrayList<CrimeData> activeCrimes = data.getData();
        assertEquals(activeCrimes, activeCrimeDataTest);

    }
}
