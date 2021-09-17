package seng202.team3.controller;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVReaderTest {
    /**
     * Reads file.txt and compares result to correct output
     */

    @Test
    public void checkReadCSV() {

        String file = ("src/test/java/seng202/team3/controller/file.txt");
        ArrayList<ArrayList<String>> test = ReadCSV.readDataLineByLine(file);

        ArrayList<ArrayList<String>> expectedResultList = new ArrayList<>();

        ArrayList<String> crime1 = new ArrayList<>(
            Arrays.asList("JE163990","11/23/2020 03:05:00 PM",
                    "073XX S SOUTH SHORE DR","820","THEFT",
                    "$500 AND UNDER","APARTMENT","N","N","334"
                    ,"7","6","","","","",""));

        ArrayList<String> crime2 = new ArrayList<>(
                Arrays.asList("JE266628","06/15/2021 09:30:00 AM",
                        "080XX S DREXEL AVE","820","THEFT",
                        "$500 AND UNDER","STREET","N","N","631","8",
                        "6","1183633","1851786","41.74848637",
                        "-87.60267506","(41.748486365, -87.602675062)"));

        expectedResultList.add(crime1);
        expectedResultList.add(crime2);

        assertEquals(test, expectedResultList);
    }

}
