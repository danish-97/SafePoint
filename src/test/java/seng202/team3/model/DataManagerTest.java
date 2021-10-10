package seng202.team3.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DataManagerTest {


    @Test
    public void addActiveCrime() throws ParseException {
        //Creates string of crime information
        String[] expected = {"0", "06/15/2021 09:30:00 AM", "080XX S DREXEL AVE","THEFT", "41.74848637", "-87.60267506"};

        //Creates userCrime and adds to activeCrimeData
        ArrayList<String> crime = new ArrayList<>(Arrays.asList(expected));
        UserData userCrime = new UserData(CrimeData.getLatestID(), crime);
        DataManager.addCrimeData(userCrime);
        CrimeData.incrementLatestID();

        //Gets information from activeCrimeData
        CrimeData gotCrime = DataManager.getData().get(0);
        String[] got = {"0", gotCrime.getDate(), gotCrime.getAddress(), gotCrime.getCrimeType(),
                gotCrime.getLatitude(), gotCrime.getLongitude()};

        assertArrayEquals(expected, got);
    }
}
