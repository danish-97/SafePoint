package seng202.team3.controller;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;

public class CSVWriterTest {

    @Test
    public void checkWriteCSV() {
        WriteCSV write = new WriteCSV();
        write.writeDataLineByLine("src/test/java/seng202/team3/controller/file.txt",
                "src/test/java/seng202/team3/controller/seng202_2021_crimes_one_year_prior_to_present_5k.csv");
    }

}
