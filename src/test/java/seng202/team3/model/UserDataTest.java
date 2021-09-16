package seng202.team3.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDataTest {

    /**
     * Checks if the getDateReported method works as intended.
     */
    @Test
    public void checkDate(){
        String date = "06/15/2021 07:50:00 AM";
        UserData userDate = new UserData("JE163990, 042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT");
        assertEquals(date, userDate.getDateReported());
    }
}
