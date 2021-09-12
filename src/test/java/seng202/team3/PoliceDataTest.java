package seng202.team3;

import org.junit.jupiter.api.Test;
import seng202.team3.model.PoliceData;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoliceDataTest {
    @Test
    public void caseNumberCheck() throws ParseException {
        PoliceData data = new PoliceData("042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT");
        data.setCaseNumber("JE163990");
        assertEquals("JE163990", data.getCaseNumber());
    }

    @Test

    public void arrestMadeCheck() throws ParseException {
        PoliceData data = new PoliceData("042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT");
        data.setArrestMade('Y');
        assertEquals('Y', data.isArrestMade());
    }

    @Test

    public void xCordCheck() throws ParseException {
        PoliceData data = new PoliceData("042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT");
        data.setxCord(56);
        assertEquals(Integer.valueOf(56), data.getxCord());
    }

    @Test

    public void yCordCheck() throws ParseException {
        PoliceData data = new PoliceData("042XX W MADISON ST, 06/15/2021 07:50:00 AM, (41.880660786, -87.731186405), ASSAULT");
        data.setyCord(56);
        assertEquals(Integer.valueOf(56), data.getyCord());
    }



}
