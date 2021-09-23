package seng202.team3.controller;

import org.junit.jupiter.api.Test;
import seng202.team3.model.PoliceData;

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
    public void checkReadCSV() {
        String file = ("src/test/java/seng202/team3/controller/ReaderTestFile.txt");
        ArrayList<PoliceData> ReadingCSV = ReadCSV.readDataLineByLine(file);

        ArrayList<String> expectedResultList = new ArrayList<>(Arrays.asList("JE266628", "JE266536"));

        ArrayList<String> result = new ArrayList<>();
        assert ReadingCSV != null;
        for (PoliceData crime : ReadingCSV) {
            result.add(crime.getCaseNumber());
        }

        assertEquals(result, expectedResultList);
    }

    /**
     * Reads CSV file with different amount of columns
     */
    @Test
    public void checkDatabaseCSV() {
        WriteCSV.writeDataLineByLine("src/test/java/seng202/team3/controller/WriterTestFIle.txt",
                "src/test/java/seng202/team3/controller/ReaderTestFile.txt");

        String file = ("src/test/java/seng202/team3/controller/WriterTestFIle.txt");
        ArrayList<PoliceData> ReadingCSV = ReadCSV.readDataLineByLine(file);

        ArrayList<String> expectedResultList = new ArrayList<>(Arrays.asList("JE266628", "JE266536"));

        ArrayList<String> result = new ArrayList<>();
        assert ReadingCSV != null;
        for (PoliceData crime : ReadingCSV) {
            result.add(crime.getCaseNumber());
        }

        assertEquals(result, expectedResultList);
    }
}
