package seng202.team3.controller;

import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Handles the computation for the differences in the crimes being compared.
 * This includes methods for finding the distance apart and time apart.
 * @author mattgarrett
 */
public class CompareDataController {

    private CrimeData data1;
    private CrimeData data2;

    public CompareDataController (CrimeData data1, CrimeData data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    /**
     * Gets the string representation of the distance between crimes, or a string saying there isn't a distance available
     * @return String of the distance in KM with formatting
     */
    public String getDistance () {
        String distanceStr = "Distance not available"; //protection if one object has no lat long
        //tests if either lat long value is null
        if (!Objects.equals(data1.getLocation(), ", ") && !Objects.equals(data2.getLocation(), ", ")) {
            //data objects to the distance
            distanceStr = Double.toString(distance(Double.parseDouble(data1.getLatitude()), Double.parseDouble(data1.getLongitude()),
                    Double.parseDouble(data2.getLatitude()), Double.parseDouble(data2.getLongitude())));
            distanceStr += " Km Apart";
        }
        return distanceStr;
    }

    /**
     * Gets the distance between a set of two points in KM
     * @param latitude latitude of the first point
     * @param longitude longitude of the first point
     * @param latitude1 latitude of the second point
     * @param longitude1 longitude of the second point
     * @return the distance in km as a double, rounded to 2dp
     */
    private double distance(Double latitude, Double longitude, Double latitude1, Double longitude1) {
        double theta = longitude - longitude1;
        double dist = Math.sin(deg2rad(latitude)) * Math.sin(deg2rad(latitude1)) + Math.cos(deg2rad(latitude)) * Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return Math.round(dist * 100) / 100;
    }

    private double deg2rad(double deg) {return (deg * Math.PI / 180.0);}

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    /**
     * Gets the number of days between the two crime data objects
     * @return String representation of the number of days between the crimes
     * @throws ParseException
     */
    public String getTimeDifference () throws ParseException {
        Date date1;
        Date date2;
        if (data1 instanceof PoliceData) {
            //formats date for policedata object
            date1 = new SimpleDateFormat("MM/dd/yyyy").parse(data1.getDate().substring(0, 9));
        } else {
            //formats date for userdata object
            date1 = new SimpleDateFormat("yyyy-dd-MM").parse(data1.getDate().substring(0, 9));
        }

        if (data2 instanceof PoliceData) {
            //format date for policedata object
            date2 = new SimpleDateFormat("MM/dd/yyyy").parse(data2.getDate().substring(0, 9));
        } else {
            //format date for userdata object
            date2 = new SimpleDateFormat("yyyy-dd-MM").parse(data2.getDate().substring(0, 9));
        }

        //gets difference and converts to days
        long diffInMill = Math.abs(date2.getTime() - date1.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMill, TimeUnit.MILLISECONDS);
        return diff + " Days Apart";
    }

}
