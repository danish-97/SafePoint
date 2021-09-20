package seng202.team3;


import org.junit.jupiter.api.Test;
import seng202.team3.controller.CrimeData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrimeDataTest {
    @Test
    public void checkAddress() {
        String data = "042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        assertEquals("042XX W MADISON ST", crime.getAddress());
    }

    @Test
    public void checkDate() {
        String data = "042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        assertEquals("06/15/2021 07:50:00 AM", crime.getDate());
    }

    @Test
    public void checkLocation() {
        String data = "042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        assertEquals("(41.880660786, -87.731186405)", crime.getLocation());
    }

    @Test
    public void checkCrimeType() {
        String data = "042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT";
        CrimeData crime = new CrimeData(data);
        System.out.println(crime.getCrimeType());
        assertEquals("ASSAULT", crime.getCrimeType());
    }
}
