package seng202.team3.model;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Represents a certain crime
 * @author Danish Jahangir
 */
public class CrimeData {

    private String id; /**Unique ID for each CrimeData*/
    private String address; /**Address that the crime happened at (or closest to)*/
    private String date; /**Date that the crime happened on*/
    private String latitude; /**Latitude representation of the location of the crime*/
    private String longitude; /**Longitude representation of the location of the crime*/
    private String location; /**Tuple containing both latitude and longitude*/
    private String crimeType; /**Type of crime that this object represents*/

    /**
     * Constructor method for the class CrimeData
     * @param data is the string that is passed to the method
     */
    public CrimeData(String id){
        this.id = id;
    }

    public String getId() {return id;}

    public String getAddress() {
        return address;
    }
    public String getDate() {
        return date;
    }

    public void setLatitude (String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude (String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLocation (String location) {
        this.location = location;
    }

    public String getLocation() {
        //location is a tuple of (latitude, longitude)
        location = getLatitude()+ ", " + getLongitude();
        return location;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCrimeType(String type) {
        this.crimeType = type;
    }


}

