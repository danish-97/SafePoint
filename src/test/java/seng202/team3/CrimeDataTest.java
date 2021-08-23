package seng202.team3;

import org.junit.Test;
import seng202.team3.controller.CrimeData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrimeDataTest {
    @Test
    public void checkAddress() {
        String data = "042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT\n";
        CrimeData crime = new CrimeData(data);
        assertEquals("042XX W MADISON ST", crime.getAddress());
    }
    
}
