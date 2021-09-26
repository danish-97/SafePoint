package seng202.team3.model;


import java.text.ParseException;
import java.util.ArrayList;


/**
 * Child class for the class CrimeData which takes the user inputted data
 * @author Danish Jahangir
 */

public class UserData extends CrimeData {
    /**
     * Constructor method for the class UserData
     * @param id is the String that identifies a particular Crime
     * @param data is an ArrayList containing strings of all Crimes
     * @throws ParseException checks if the format is correct when string is parsed.
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
