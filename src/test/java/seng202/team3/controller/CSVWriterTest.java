package seng202.team3.controller;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;

public class CSVWriterTest {

    @Test
    public void checkWriteCSV() {
        WriteCSV write = new WriteCSV();
        String[] data = {"JD457234", "12/12/2020 15:16", "046XX W BELMONT AVE",  "486", "BATTERY", "DOMESTIC BATTERY SIMPLE", "STREET", "N" +
                "Y", "2521", "31", "08B", "1144859", "1920871", "41.9388808", "-87.74301797", "(41.938880798, -87.743017971)"};
        write.writeDataLineByLine("src/test/java/seng202/team3/controller/file.txt", data);
    }

}
