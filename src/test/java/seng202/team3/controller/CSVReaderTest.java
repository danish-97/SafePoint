package seng202.team3.controller;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVReaderTest {

    @Test
    public void checkReadCSV() throws FileNotFoundException {
        ReadCSV csv = new ReadCSV();

        String file = ("src/test/java/seng202/team3/controller/seng202_2021_crimes_one_year_prior_to_present_5k.csv");
        csv.readDataLineByLine(file);
       // assertEquals(csv.readDataLineByLine(file));
    }

}
