package seng202.team3.model;

/**
 * Represents a certain crime
 * @author Danish Jahangir
 */
public class CrimeData {

    private String id; /*Unique ID for each CrimeData*/
    private static int latestID; /*Is the latest id for crime Objects**/
    private String address; /*Address that the crime happened at (or closest to)*/
    private String date; /*Date that the crime happened on*/
    private String latitude; /*Latitude representation of the location of the crime*/
    private String longitude; /*Longitude representation of the location of the crime*/
    private String location; /*Tuple containing both latitude and longitude*/
    private String crimeType; /*Type of crime that this object represents*/

    /**
     * Constructor method for the class CrimeData
     * @param id is the string that is passed to the method
     */
    public CrimeData(String id){
        this.id = id;
    }

    /**
     * Gets the latestID of the data as a static method.
     * @return the latestId of type String
     */
    public static String getLatestID() {
        return Integer.toString(latestID);
    }

    /**
     * Increments the latestID.
     */
    public static void incrementLatestID() {
        latestID++;
    }

    /**
     * Gets the ID of the crime
     * @return the id of type String.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the address from the address field of the data.
     * @return address of the type String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address to the new address
     * @param address is the address we set it to.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the date from the date field of the data
     * @return the date of type String
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date to the new date
     * @param date is the date we set it to.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the latitude to the new latitude
     * @param latitude is the latitude we set it to.
     */
    public void setLatitude (String latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the latitude from the latitude field of the data
     * @return the latitude of type String
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the longitude to the new longitude
     * @param longitude is the longitude we set it to
     */
    public void setLongitude (String longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the longitude from the longitude field of the data
     * @return the longitude of the type String
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the location to the new location
     * @param location is the location we set it to.
     */
    public void setLocation (String location) {
        this.location = location;
    }

    /**
     * Gets the location by making a tuple from the latitude and longitude
     * @return the location of type String
     */
    public String getLocation() {
        //location is a tuple of (latitude, longitude)
        location = getLatitude()+ ", " + getLongitude();
        return location;
    }

    /**
     * Gets the crimeType from its field of the data
     * @return the crimeType of type String.
     */
    public String getCrimeType() {
        return crimeType;
    }

    /**
     * Sets the crimeType to the new value type
     * @param type is the value we set it to.
     */
    public void setCrimeType(String type) {
        this.crimeType = type;
    }

    /**
     * Sets the latest ID of the data
     * @param latest is the id we set it to.
     */
    public static void setLatestID (int latest) {
        latestID = latest;
    }


}
