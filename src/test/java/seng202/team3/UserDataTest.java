package seng202.team3;

import junit.framework.Test;
import seng202.team3.model.UserData;
import static junit.framework.Assert.assertEquals;

public class UserDataTest {

    public void checkDate(){
        String date = "06/15/2021 07:50:00 AM";
        UserData userDate = new UserData("042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT");
        assertEquals(date, userDate.getDateReported());
    }
}
