package seng202.team3.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PoliceDataTest {

    /**
     * Checks if the getCaseNumber method gets the correct field from the String.
     */
    @Test
    public void caseNumberCheck() {
        PoliceData data = new PoliceData(CrimeData.getLatestID(), (ArrayList<String>) Arrays.asList("JE163990", "042XX W MADISON ST", "06/15/2021 07:50:00 AM", "(41.880660786, -87.731186405)", "ASSAULT"));
        CrimeData.incrementLatestID();
        data.setCaseNumber("JE163990");
        assertEquals("JE163990", data.getCaseNumber());
    }

    /**
     * Checks if the isArrestMade method gets the correct field from the String.
     */
    @Test

    public void arrestMadeCheck() {
        PoliceData data = new PoliceData(CrimeData.getLatestID(), (ArrayList<String>) Arrays.asList("JE163990", "042XX W MADISON ST", "06/15/2021 07:50:00 AM", "(41.880660786, -87.731186405)", "ASSAULT"));
        CrimeData.incrementLatestID();
        data.setArrestMade("Y");
        assertEquals("Y", data.isArrestMade());
    }

    /**
     * Checks if the getXCoord method gets the correct field from the String.
     */
    @Test

    public void xCordCheck() {
        PoliceData data = new PoliceData(CrimeData.getLatestID(), (ArrayList<String>) Arrays.asList("JE163990", "042XX W MADISON ST", "06/15/2021 07:50:00 AM", "(41.880660786, -87.731186405)", "ASSAULT"));
        CrimeData.incrementLatestID();
        data.setXCoord(Integer.toString(56));
        assertEquals(56, data.getXCoord());
    }

    /**
     * Checks if the getYCoord method gets the correct field from the String.
     */

    @Test

    public void yCordCheck() {
        PoliceData data = new PoliceData(CrimeData.getLatestID(), (ArrayList<String>) Arrays.asList("JE163990", "042XX W MADISON ST", "06/15/2021 07:50:00 AM", "(41.880660786, -87.731186405)", "ASSAULT"));
        CrimeData.incrementLatestID();
        data.setYCoord(Integer.toString(56));
        assertEquals(56, data.getYCoord());
    }



}
