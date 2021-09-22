package seng202.team3.model;


import java.util.Arrays;
import java.util.List;

public class CrimeData {
    private String id;
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
        DataManager.addCrimeData(this);
    }

    public String getId() {return id;}

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
        id = dataSplit.get(0);
        address = dataSplit.get(1);
        date = dataSplit.get(2);
        latitude = dataSplit.get(3);
        longitude = dataSplit.get(4);
        crimeType = dataSplit.get(5);
    }


}

