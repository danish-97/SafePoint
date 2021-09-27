package seng202.team3.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDataTest {

    /**
     * Checks if the getDateReported method works as intended.
     * @throws ParseException checks if the format is correct when string is parsed.
     */
    @Test
    public void checkDate() throws ParseException {
        String date = "11/26/2020 07:50:00 AM";
        ArrayList<String> uData = new ArrayList<>();
        uData.add("1");uData.add("11/26/2020 07:50:00 AM"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data = new UserData("1", uData);
        assertEquals(date, data.getDate());
    }
}
