package seng202.team3.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.team3.model.PoliceData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
    public void deleteFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/test/java/seng202/team3/controller/WriterTestFIle.txt");
        writer.print("");
        writer.close();
    }

    /**
     * Reads ReaderTestFile.txt and writes to WriterTestFIle.txt then compares the contents of the files
     */
    @Test
    public void checkWriteCSV() {

        WriteCSV.writeDataLineByLine("src/test/java/seng202/team3/controller/WriterTestFIle.txt",
                "src/test/java/seng202/team3/controller/ReaderTestFile.txt");

        String file = ("src/test/java/seng202/team3/controller/ReaderTestFile.txt");
        ArrayList<PoliceData> readFromInitial = ReadCSV.readDataLineByLine(file);

        String file2 = ("src/test/java/seng202/team3/controller/WriterTestFIle.txt");
        ArrayList<PoliceData> readFromFinal = ReadCSV.readDataLineByLine(file2);

        ArrayList<String> expectedResult = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        assert readFromInitial != null;
        for (PoliceData crime : readFromInitial) {
            expectedResult.add(crime.getCaseNumber());
        }
        assert readFromFinal != null;
        for (PoliceData crime : readFromFinal) {
            result.add(crime.getCaseNumber());
        }

        assertEquals(expectedResult, result);

    }

}
