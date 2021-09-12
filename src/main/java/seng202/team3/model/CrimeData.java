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

    private void formatStringToData(String data) throws ParseException {
        List<String> dataSplit = Arrays.asList(data.split(", "));
        address = dataSplit.get(0);
        String dateString = dataSplit.get(1);
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        latitude = dataSplit.get(2);
        longitude = dataSplit.get(3);
        crimeType = dataSplit.get(4);
    }


}

