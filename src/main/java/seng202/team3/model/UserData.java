package seng202.team3.model;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserData extends CrimeData {
    /**
     * Constructor method for the class UserData
     * @param data is the String received
     */
    public UserData(String id, ArrayList<String> data) throws ParseException {
        super(id);
        formatUserData(data);
    }

    public void formatUserData (ArrayList<String> data) {
        setDate(data.get(0));
        setAddress(data.get(1));
        setCrimeType(data.get(2));
        setLatitude(data.get(3));
        setLongitude(data.get(4));
        setLocation(data.get(3) + ", " + data.get(4));

    }
}
