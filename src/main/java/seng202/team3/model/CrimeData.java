package seng202.team3.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CrimeData {
    private String id; // TODO id needs to have a value passed in at the beginning (temporary id)
    private String address;
    private Date date; // TODO change this to a date instead
    private String latitude;
    private String longitude;
    private String location;
    private String crimeType;

    /**
     * Constructor method for the class CrimeData
     * @param data is the string that is passed to the method
     */

    public CrimeData(String data) {
        formatStringToData(data);
    }

    public String getId() {return id;}

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLocation() {
        location = getLatitude()+ ", " + getLongitude();
        return location;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public CrimeData(String data) throws ParseException {
        formatStringToData(data);
    }
    /**
     * Function that splits the given string to get the required fields
     * @param data is the String which is to be formatted
     */

    private void formatStringToData(String data) throws ParseException {
        List<String> dataSplit = Arrays.asList(data.split(", "));
        id = dataSplit.get(0);
        address = dataSplit.get(1);
        latitude = dataSplit.get(3);
        longitude = dataSplit.get(4);
        crimeType = dataSplit.get(5);
        String dateString = dataSplit.get(2);
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);

    }


}

