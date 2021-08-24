package seng202.team3;

import org.junit.jupiter.api.Test;
import seng202.team3.model.UserData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDataTest {
    @Test
    public void checkDate(){
        String date = "06/15/2021 07:50:00 AM";
        UserData userDate = new UserData("042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT");
        assertEquals(date, userDate.getDateReported());
    }
}
