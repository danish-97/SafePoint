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
        setCrimeType(data.get(0));
        setAddress(data.get(1));
        setLatitude(data.get(2));
        setLongitude(data.get(3));
        setLocation(data.get(2) + ", " + data.get(3));
        setDate(data.get(4));
    }
}
