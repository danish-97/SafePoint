package seng202.team3;

import seng202.team3.model.CrimeData;

import static junit.framework.Assert.assertEquals;

public class CrimeDataTest {

    public void checkAddress() {
        String data = "042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT\n";
        CrimeData crime = new CrimeData(data);
        assertEquals("042XX W MADISON ST", crime.getAddress());
    }
    
}
