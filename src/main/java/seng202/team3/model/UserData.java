package seng202.team3.model;

import java.util.Arrays;
import java.util.List;

public class UserData extends CrimeData {
    private String dateReported;

    /**
     * Constructor method for the class UserData
     * @param data is the String received
     */

    public UserData(String data) {
        super(data);
        List<String> date = Arrays.asList(data.split(", "));
        dateReported = date.get(2);
    }

    public String getDateReported() {
        return dateReported;
    }
}
