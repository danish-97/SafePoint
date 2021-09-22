package seng202.team3;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for CrimeDataTest
 */

public class CrimeDataTest {

    /**
     * Tests if the getAddress method works as intended.
     */
    @Test
    public void checkAddress() {
        String data = "JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        assertEquals("042XX W MADISON ST", crime.getAddress());
    }

    /**
     * Tests if the getData method works as intended.
     */
    @Test
    public void checkDate() {
        String data = "JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        assertEquals("06/15/2021 07:50:00 AM", crime.getDate());
    }

    /**
     * Checks if the getLocation method works as intended.
     */
    @Test
    public void checkLocation() {
        String data = "JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        assertEquals("(41.880660786, -87.731186405)", crime.getLocation());
    }

    /**
     * Checks if the getCrimeType method works as intended.
     */
    @Test
    public void checkCrimeType() {
        String data = "JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        System.out.println(crime.getCrimeType());
        assertEquals("ASSAULT", crime.getCrimeType());
    }

    /**
     * Checks to make sure crimes are added to allCrimeData
     */
    @Test
    @Order(1)
    public void checkAllCrimeData() {
        String data = "JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        assertTrue(DataManager.getAllData().contains(crime));
    }
}
