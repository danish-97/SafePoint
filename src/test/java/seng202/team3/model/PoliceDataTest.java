package seng202.team3.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for PoliceData test
 */

public class PoliceDataTest {

    /**
     * Checks if the getCaseNumber method gets the correct field from the ArrayList.
     */
    @Test
    public void caseNumberCheck() {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("JE266628", pData.getCaseNumber());
    }

    /**
     * Checks if the isArrestMade method gets the correct field from the ArrayList.
     */
    @Test

    public void arrestMadeCheck() {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("NO", pData.isArrestMade());
    }

    /**
     * Checks if the getxCord method gets the correct field from the ArrayList.
     */
    @Test

    public void xCordCheck() {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        pData.setXCoord("1183633");
        assertEquals(1183633, pData.getXCoord());
    }

    /**
     * Checks if the getyCord method gets the correct field from the ArrayList.
     */

    @Test

    public void yCordCheck() {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        pData.setYCoord("1851786");
        assertEquals(1851786, pData.getYCoord());
    }

    /**
     * Checks if the getDomestic method gets the correct field from the ArrayList.
     */

    @Test

    public void getDomesticCheck() {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("NO", pData.getDomestic());
    }

    /**
     * Checks if the getWard method gets the correct field from the ArrayList.
     */

    @Test

    public void getWardCheck() {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("8", pData.getWard());
    }

    /**
     * Checks if the getBeat method gets the correct field from the ArrayList.
     */

    @Test

    public void getBeatCheck() {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,THEFT,$500 AND UNDER,STREET,N,N,631,8,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("631", pData.getBeat());
    }




}
