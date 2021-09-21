package seng202.team3.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
     * Clears the contents of output.txt
     * @throws FileNotFoundException if file is not found
     */
    @BeforeEach
    public void deleteFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/test/java/seng202/team3/controller/output.txt");
        writer.print("");
        writer.close();
    }

    /**
     * Reads file.txt and writes to output.txt then compares the contents of the files
     */
    @Test
    public void checkWriteCSV() {
        WriteCSV.writeDataLineByLine("src/test/java/seng202/team3/controller/output.txt",
                "src/test/java/seng202/team3/controller/file.txt");

        String file = ("src/test/java/seng202/team3/controller/file.txt");
        ArrayList<ArrayList<String>> expectedResult = ReadCSV.readDataLineByLine(file);

        String file2 = ("src/test/java/seng202/team3/controller/output.txt");
        ArrayList<ArrayList<String>> result = ReadCSV.readDataLineByLine(file2);
        assertEquals(expectedResult, result);

    }

}
