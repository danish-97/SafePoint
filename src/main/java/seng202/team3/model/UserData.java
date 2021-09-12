package seng202.team3.model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class UserData extends CrimeData {
    private String dateReported;

    public UserData(String data) throws ParseException {
        super(data);
        List<String> date = Arrays.asList(data.split(", "));
        dateReported = date.get(1);
    }

    public String getDateReported() {
        return dateReported;
    }
}
