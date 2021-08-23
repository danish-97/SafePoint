package seng202.team3.controller;


import java.util.Arrays;
import java.util.List;

public class CrimeData extends PoliceData {
    private String address;
    private String date;
    private String location;
    private String crimeType;

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public CrimeData(String data) {
        formatStringToData(data);
    }

    private void formatStringToData(String data) {
        List<String> dataSplit = Arrays.asList(data.split(", "));
        address = dataSplit.get(0);
        date = dataSplit.get(1);
        location = dataSplit.get(2);
        crimeType = dataSplit.get(3);
    }


}

