package seng202.team3.controller;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVReaderTest {

    @Test
    public void checkReadCSV() throws FileNotFoundException {
        ReadCSV csv = new ReadCSV();
        FileReader file = new FileReader("src/test/java/seng202/team3/controller/file.txt");
       // assertEquals(csv.readDataLineByLine(file));
    }

}
