package seng202.team3.controller;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for CSV writer
 * @author roryh
 */
public class CSVWriterTest {

    /**
     * Clears the contents of WriterTestFIle.txt
     * @throws FileNotFoundException if file is not found
     */
    @BeforeEach
    public void deleteFile() throws IOException {
        new FileWriter("src/main/java/seng202/team3/Database/WriterTestFIle.txt", false);

    }

    /**
     * Reads ReaderTestFile.txt and writes to WriterTestFIle.txt then compares the contents of the files
     */
    @Test
    public void checkWriteCSV() {
        ArrayList<CrimeData> inputCrimes;
        inputCrimes = ReadCSV.readDataLineByLine("src/main/java/seng202/team3/Database/ReaderTestFile.txt");
        ArrayList<String[]> stringCrimes = new ArrayList<>();
        for (CrimeData crime: inputCrimes) {
            String[] stringCrime = Importer.policeToString(((PoliceData) crime));
            stringCrimes.add(stringCrime);
        }
        WriteCSV.writeDataLineByLine("src/main/java/seng202/team3/Database/WriterTestFIle.txt", stringCrimes);

        String file = ("src/main/java/seng202/team3/Database/ReaderTestFile.txt");
        ArrayList<CrimeData> readFromInitial = ReadCSV.readDataLineByLine(file);

        String file2 = ("src/main/java/seng202/team3/Database/WriterTestFIle.txt");
        ArrayList<CrimeData> readFromFinal = ReadCSV.readDataLineByLine(file2);

        ArrayList<String> expectedResult = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        assert readFromInitial != null;
        for (CrimeData crime : readFromInitial) {
            expectedResult.add(((PoliceData) crime).getCaseNumber());
        }
        assert readFromFinal != null;
        for (CrimeData crime : readFromFinal) {
            result.add(((PoliceData) crime).getCaseNumber());
        }

        assertEquals(expectedResult, result);

    }

}
