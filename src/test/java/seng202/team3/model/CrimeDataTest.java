package seng202.team3.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for CrimeDataTest
 */

public class CrimeDataTest {

    /**
     * Tests if the getAddress method works as intended.
     * @throws ParseException checks if the format is correct when string is parsed.
     */
    @Test
    public void checkAddress() throws ParseException {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("080XX S DREXEL AVE", pData.getAddress());
    }

    /**
     * Tests if the getData method works as intended.
     * @throws ParseException checks if the format is correct when string is parsed.
     */
    @Test
    public void checkDate() throws ParseException {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("06/15/2021 09:30:00 AM", pData.getDate());
    }

    /**
     * Checks if the getLocation method works as intended.
     * @throws ParseException checks if the format is correct when string is parsed.
     */
    @Test
    public void checkLocation() throws ParseException {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("41.748486365, -87.602675062", pData.getLocation());
    }

    /**
     * Checks if the getCrimeType method works as intended.
     * @throws ParseException checks if the format is correct when string is parsed.
     */
    @Test
    public void checkCrimeType() throws ParseException {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("THEFT", pData.getCrimeType());
    }

}
