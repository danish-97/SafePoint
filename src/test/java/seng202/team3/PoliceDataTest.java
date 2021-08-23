package seng202.team3;

import org.junit.jupiter.api.Test;
import seng202.team3.controller.PoliceData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoliceDataTest {
    @Test

    public void caseNumberCheck() {
        PoliceData data = new PoliceData("JE163990");
        data.setCaseNumber("JE163990");
        assertEquals("JE163990", data.getCaseNumber());
    }

    @Test

    public void arrestMadeCheck() {
        PoliceData data = new PoliceData("JE163990");
        data.setArrestMade('Y');
        assertEquals('Y', data.isArrestMade());
    }

    @Test

    public void xCordCheck() {
        PoliceData data = new PoliceData("JE163990");
        data.setxCord(56);
        assertEquals(Integer.valueOf(56), data.getxCord());
    }

    @Test

    public void yCordCheck() {
        PoliceData data = new PoliceData("JE163990");
        data.setyCord(56);
        assertEquals(Integer.valueOf(56), data.getyCord());
    }



}
