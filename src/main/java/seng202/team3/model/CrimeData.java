package seng202.team3.model;

/**
 * Represents a certain crime
 * @author Danish Jahangir
 */
public class CrimeData {

    private final String id;/**Unique ID for each CrimeData*/
    private static int latestID; /**Is the latest id for crime Objects**/
    private String address; /**Address that the crime happened at (or closest to)*/
    private String date; /**Date that the crime happened on*/
    private String latitude; /**Latitude representation of the location of the crime*/
    private String longitude; /**Longitude representation of the location of the crime*/
    private String location; /**Tuple containing both latitude and longitude*/
    private String crimeType; /**Type of crime that this object represents*/

    /**
     * Constructor method for the class CrimeData
     */
    public CrimeData(String id){
        this.id = id;
    }

    public static String getLatestID() {
        return Integer.toString(latestID);
    }

    public static void incrementLatestID() {
        latestID++;
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

