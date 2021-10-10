package seng202.team3.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for CSV writer
 * @author roryh
 */
public class ImporterTests {

    /**
     * Clears the contents of Database.txt
     * @throws FileNotFoundException if file is not found
     */
    @BeforeEach
    public void deleteFile() throws IOException {
        new FileWriter("src/main/java/seng202/team3/Database/Database.txt", false);

    }


    /**

    public void addFiveTho() {
        Importer.addPoliceData("src/main/java/seng202/team3/Database/seng202_2021_crimes_one_year_prior_to_present_5k.csv");
    }
    /**


    /**
     * Creates UserData object and writes it to Database.txt
     * Checks if Database.txt contains String[] of Crime
     * @throws ParseException if the input is invalid.
     */
    @Test
    public void importUserData() throws ParseException {
        //Creates UserData object
        ArrayList<String> crime = new ArrayList<>(Arrays.asList(CrimeData.getLatestID(),"06/15/2021 09:30:00 AM", "080XX S DREXEL AVE","THEFT", "41.74848637", "-87.60267506"));
        String latestId = CrimeData.getLatestID();
        UserData userCrime = new UserData(latestId, crime);


        //Adds UserData object to database
        String file = ("src/main/java/seng202/team3/Database/Database.txt");
        Importer.addUserData(userCrime, file);

        //Creates expected String[]
        String[] expected = {"06/15/2021 09:30:00 AM","080XX S DREXEL AVE", "THEFT","41.74848637", "-87.60267506"};
        CrimeData.incrementLatestID();
        //Reads CrimeData objects from database

        ArrayList<CrimeData> readFromDatabase = ReadCSV.readDataLineByLine(file);

        //Loops through crimes read from database
        assert readFromDatabase != null;
        for (CrimeData data : readFromDatabase) {

            //If CrimeData object ID == UserData Object ID
            if (Objects.equals((Integer.parseInt(data.getId())-1), (Integer.parseInt(latestId)))) {
                //Compare Objects to check equality
                String[] got = {data.getDate(), data.getAddress(), data.getCrimeType(),
                        data.getLatitude(), data.getLongitude()};
                assertArrayEquals(expected, got);
            }
        }
    }

    /**
     * Takes police crime data and writes it to Database.txt
     * Checks Database.txt contains correct String[]
     */
    @Test
    public void importPoliceData() {
        //Create expected String[]'s
        ArrayList<String[]> expectedResult = new ArrayList<>();
        expectedResult.add(new String[]{"JE266628", "06/15/2021 09:30:00 AM", "080XX S DREXEL AVE", "820", "NO", "NO", "N", "631", "8", "6", "1183633", "1851786"});
        expectedResult.add(new String[]{"JE266536", "06/15/2021 07:50:00 AM", "042XX W MADISON ST", "560", "NO", "NO", "N", "1115", "28", "0", "1148227", "1899678"});


        //Call to importer addPoliceData
        String file = ("src/main/java/seng202/team3/Database/ReaderTestFile.txt");
        Importer.addPoliceData(file);

        //Reads CrimeData objects from Database
        String databaseFile = ("src/main/java/seng202/team3/Database/ReaderTestFile.txt");
        ArrayList<CrimeData> crimes = ReadCSV.readDataLineByLine(databaseFile);

        //Loops through CrimeData objects in Database
        int index = 0;
        assert crimes != null;
        for (CrimeData crime: crimes) {

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

}
