package seng202.team3.controller;

import org.junit.jupiter.api.Test;
import seng202.team3.model.UserData;

import java.text.ParseException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test class for the CompareDataController class.
 */
public class CompareDataControllerTest {

    /**
     * Checks if the getDistanceZero method works as intended.
     * @throws ParseException if the input is invalid.
     */
    @Test
    public void checkGetDistanceZero() throws ParseException {
        ArrayList<String> uData = new ArrayList<>();
        uData.add("1");uData.add("11/26/2020 07:50:00 AM"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data1 = new UserData("1", uData);

        uData = new ArrayList<>();
        uData.add("2");uData.add("11/26/2020 07:50:00 AM"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data2 = new UserData("1", uData);

        CompareDataController controller = new CompareDataController(data1, data2);
        String dist = controller.getDistance();
        assertEquals("0.0 Km Apart", dist);
    }

    /**
     * Checks if the getDistance method works as intended.
     * @throws ParseException if the input is invalid.
     */
    @Test
    public void checkGetDistance() throws ParseException {
        ArrayList<String> uData = new ArrayList<>();
        uData.add("1");uData.add("11/26/2020 07:50:00 AM"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.943725081");
        UserData data1 = new UserData("1", uData);

        uData = new ArrayList<>();
        uData.add("2");uData.add("11/26/2020 07:50:00 AM"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data2 = new UserData("1", uData);

        CompareDataController controller = new CompareDataController(data1, data2);
        String dist = controller.getDistance();
        assertEquals("18.0 Km Apart", dist);
    }

    /**
     * Checks if the method getDistanceNull works as intended.
     * @throws ParseException if the input is invalid.
     */
    @Test
    public void checkGetDistanceNull() throws ParseException {
        ArrayList<String> uData = new ArrayList<>();
        uData.add("1");uData.add("11/26/2020 07:50:00 AM"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add(""); uData.add("");
        UserData data1 = new UserData("1", uData);

        uData = new ArrayList<>();
        uData.add("2");uData.add("11/26/2020 07:50:00 AM"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data2 = new UserData("1", uData);

        CompareDataController controller = new CompareDataController(data1, data2);
        String dist = controller.getDistance();
        assertEquals("Distance not available", dist);
    }

    /**
     * Checks if the method TimeDifferenceZero works as intended.
     * @throws ParseException if the data is invalid.
     */
    @Test
    public void checkTimeDifferenceZero() throws ParseException {
        ArrayList<String> uData = new ArrayList<>();
        uData.add("1");uData.add("2020-11-10"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data1 = new UserData("1", uData);

        uData = new ArrayList<>();
        uData.add("2");uData.add("2020-11-10"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data2 = new UserData("1", uData);

        CompareDataController controller = new CompareDataController(data1, data2);
        String time = controller.getTimeDifference();
        assertEquals("0 Days Apart", time);
    }

    /**
     * Checks if the method timeDifference works as intended.
     * @throws ParseException if the data is invalid.
     */
    @Test
    public void checkTimeDifference() throws ParseException {
        ArrayList<String> uData = new ArrayList<>();
        uData.add("1");uData.add("2020-10-9"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data1 = new UserData("1", uData);

        uData = new ArrayList<>();
        uData.add("2");uData.add("2020-11-10"); uData.add("49 MAYS ROAD"); uData.add("THEFT");  uData.add("41.812610526"); uData.add("-87.723765071");
        UserData data2 = new UserData("1", uData);

        CompareDataController controller = new CompareDataController(data1, data2);
        String time = controller.getTimeDifference();
        assertEquals("243 Days Apart", time);
    }

}
