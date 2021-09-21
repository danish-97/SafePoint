package seng202.team3.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDataTest {

    /**
     * Checks if the getDateReported method works as intended.
     */
    @Test
    public void checkDate() throws ParseException {
        String date = "11/26/2020 07:50:00 AM";
        ArrayList<String> uData = new ArrayList<>();
        uData.add("THEFT"); uData.add("49 MAYS ROAD"); uData.add("41.812610526"); uData.add("-87.723765071"); uData.add("11/26/2020 07:50:00 AM");
        UserData data = new UserData("1", uData);
        assertEquals(date, data.getDate());
    }
}
