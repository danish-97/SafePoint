package seng202.team3.model;


import java.util.Arrays;
import java.util.List;

public class CrimeData {
    private String address;
    private String date;
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

    public String getAddress() {
        return address;
    }

    public String getDate() {
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

    /**
     * Function that splits the given string to get the required fields
     * @param data is the String which is to be formatted
     */

    private void formatStringToData(String data) {
        List<String> dataSplit = Arrays.asList(data.split(", "));
        address = dataSplit.get(0);
        date = dataSplit.get(1);
        latitude = dataSplit.get(2);
        longitude = dataSplit.get(3);
        crimeType = dataSplit.get(4);
    }


}

