package seng202.team3.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Child class for CrimeData class which takes the existing data and formats it
 * @author Danish Jahangir
 */

public class PoliceData extends CrimeData {
    private String caseNumber;
    private String arrestMade;
    private String domestic;
    private String beat;
    private String ward;
    private int xCoord;
    private int yCoord;
    private String secondDescription;
    private String locationDescription;

    /**
     * Constructor method for the class PoliceData
     * @param id is the String that identifies a particular Crime
     * @param data is an ArrayList containing strings of all Crimes
     */
    public PoliceData(String id ,ArrayList<String> data) {
        super(id);
        formatPoliceData(data);
    }

    public String getCaseNumber() {
        return String.format("%8s", caseNumber).replace(' ', '0');
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String isArrestMade() {
        return arrestMade;
    }

    public void setArrestMade(String arrestMade) {
        if (Objects.equals(arrestMade, "Y")) {
            this.arrestMade = "YES";
        } else {
            this.arrestMade = "NO";
        }
    }

    public int getXCoord() {
        return xCoord;
    }

    public void setXCoord(String xCoord) {
        try {
            this.xCoord = Integer.parseInt(xCoord);
        } catch (Exception e){
            this.xCoord = 0;
        }
    }

    public int getYCoord() {
        return yCoord;
    }

    public void setYCoord(String yCoord) {
        try {
            this.yCoord = Integer.parseInt(yCoord);
        } catch (Exception e){
            this.yCoord = 0;
        }
    }

    public String getDomestic() {return domestic;}

    public void setDomestic(String domestic) {
        if (Objects.equals(domestic, "Y")) {
            this.domestic = "YES";
        } else {
            this.domestic = "NO";
        }
    }

    public String getWard() {return ward;}

    public void setWard(String ward) {this.ward = ward;}

    public String getBeat() {return beat;}

    public void setBeat(String beat) {this.beat = beat;}

    public void setSecondDescription (String sDescription) {
        secondDescription = sDescription;
    }

    public String getSecondDescription () {
        return secondDescription;
    }

    public void setLocationDescription (String lDescription) {
        locationDescription = lDescription;
    }

    public String getLocationDescription () {
        return locationDescription;
    }


    /**
     * Function that splits the given string to get the required fields
     * @param data is the ArrayList of String which is to be formatted
     */
    private void formatPoliceData(ArrayList<String> data) {
        setCaseNumber(data.get(0));
        setDate(data.get(1));
        setAddress(data.get(2));
        setCrimeType(data.get(3)); //Originally 4
        setSecondDescription(data.get(4));
        setLocationDescription(data.get(5));
        setArrestMade(data.get(6)); //7
        setDomestic(data.get(7)); //8
        setBeat(data.get(8));//9
        setWard(data.get(9));//10
        setXCoord(data.get(10));//12
        setYCoord(data.get(11));//13
        setLatitude(data.get(12));//14
        setLongitude(data.get(13));//15
        setLocation(data.get(12) + ", " + data.get(13));//14 + 15
    }

}
