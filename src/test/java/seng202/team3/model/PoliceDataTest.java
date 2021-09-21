package seng202.team3.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PoliceDataTest {

    /**
     * Checks if the getCaseNumber method gets the correct field from the String.
     */
    @Test
    public void caseNumberCheck() throws ParseException {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("JE163990", pData.getCaseNumber());
    }

    /**
     * Checks if the isArrestMade method gets the correct field from the String.
     */
    @Test

    public void arrestMadeCheck() throws ParseException {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals("NO", pData.isArrestMade());
    }

    /**
     * Checks if the getxCord method gets the correct field from the String.
     */
    @Test

    public void xCordCheck() throws ParseException {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals(1183633, pData.getXCoord());
    }

    /**
     * Checks if the getyCord method gets the correct field from the String.
     */

    @Test

    public void yCordCheck() throws ParseException {
        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        PoliceData pData = new PoliceData("1", data);
        assertEquals(1851786, pData.getYCoord());
    }



}
