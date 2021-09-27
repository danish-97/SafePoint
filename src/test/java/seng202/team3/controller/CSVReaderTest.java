package seng202.team3.controller;

import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Tests for CSV reader
 * @author roryh
 */
public class CSVReaderTest {

    /**
     * Reads ReaderTestFile.txt and compares result to correct output
     */
    /*
    @Test
    public void checkReadPoliceData() {
        //Create expected String[]'s
        ArrayList<String[]> expectedResult = new ArrayList<>();
        expectedResult.add(new String[]{"JE266536","06/15/2021 07:50:00 AM","042XX W MADISON ST",
                "ASSAULT","NO","NO","1115","28","1148227","1899678","41.88066079","-87.73118641"});
        expectedResult.add(new String[]{"JE266628", "06/15/2021 09:30:00 AM", "080XX S DREXEL AVE",
                "THEFT", "NO", "NO", "631", "8", "1183633", "1851786", "41.74848637", "-87.60267506"});


        //Call to ReadCSV using ReaderTestFile.txt path
        //Adds CrimeData objects to ReadingCSV
        String file = ("src/main/java/seng202/team3/Database/ReaderTestFile.txt");
        ArrayList<CrimeData> ReadingCSV = ReadCSV.readDataLineByLine(file);

        //Loops through ReadingCSV
        int index = 0;
        assert ReadingCSV != null;
        for (CrimeData crime: ReadingCSV) {

            //Compares each crime with matching expected result
            String[] got = {((PoliceData) crime).getCaseNumber(), crime.getDate(), crime.getAddress(),
                    crime.getCrimeType(), ((PoliceData) crime).isArrestMade(), ((PoliceData) crime).getDomestic(),
                    ((PoliceData) crime).getBeat(), ((PoliceData) crime).getWard(), String.valueOf(((PoliceData) crime).getXCoord()),
                    String.valueOf(((PoliceData) crime).getYCoord()), crime.getLatitude(), crime.getLongitude()
            };
            assertArrayEquals(expectedResult.get(index), got);

            index++;
        }
    }

     */
}
