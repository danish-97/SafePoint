package seng202.team3.controller;

import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeData;
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
        String file = ("src/main/java/seng202/team3/Database/ReaderTestFile.txt");
        ArrayList<CrimeData> ReadingCSV = ReadCSV.readDataLineByLine(file);

        ArrayList<String> expectedResultList = new ArrayList<>(Arrays.asList("JE266628", "JE266536"));

        ArrayList<String> result = new ArrayList<>();
        assert ReadingCSV != null;
        for (CrimeData crime : ReadingCSV) {
            result.add(((PoliceData) crime).getCaseNumber());
        }

        assertEquals(result, expectedResultList);
    }

    /**
     * Reads CSV file with different amount of columns
     */
    @Test
    public void checkDatabaseCSV() {
        ArrayList<CrimeData> inputCrimes;
        inputCrimes = ReadCSV.readDataLineByLine("src/main/java/seng202/team3/Database/ReaderTestFile.txt");
        ArrayList<String[]> stringCrimes = new ArrayList<>();
        for (CrimeData crime: inputCrimes) {
            String[] stringCrime = Importer.policeToString(((PoliceData) crime));
            stringCrimes.add(stringCrime);
        }
        WriteCSV.writeDataLineByLine("src/main/java/seng202/team3/Database/WriterTestFIle.txt", stringCrimes);

        String file = ("src/main/java/seng202/team3/Database/WriterTestFIle.txt");
        ArrayList<CrimeData> ReadingCSV = ReadCSV.readDataLineByLine(file);

        ArrayList<String> expectedResultList = new ArrayList<>(Arrays.asList("JE266628", "JE266536"));

        ArrayList<String> result = new ArrayList<>();
        assert ReadingCSV != null;
        for (CrimeData crime : ReadingCSV) {
            result.add(((PoliceData) crime).getCaseNumber());
        }

        assertEquals(result, expectedResultList);
    }
}
