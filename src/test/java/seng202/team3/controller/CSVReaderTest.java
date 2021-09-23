package seng202.team3.controller;

import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for CSV reader
 * @author roryh
 */
public class CSVReaderTest {
    /**
     * Reads ReaderTestFile.txt and compares result to correct output
     */

    @Test
    public void checkReadCSV() throws ParseException {

        String file = ("src/test/java/seng202/team3/controller/ReaderTestFile.txt");
        ArrayList<PoliceData> ReadingCSV = ReadCSV.readDataLineByLine(file);

        ArrayList<String> expectedResultList = (ArrayList<String>) Arrays.asList("JE163990", "JE266628");

        ArrayList<String> result = new ArrayList<>();
        for (PoliceData crime : ReadingCSV) {
            result.add(crime.getCaseNumber());
        }


        assertEquals(result, expectedResultList);
    }

}
