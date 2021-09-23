package seng202.team3.controller;

import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles all Imports and adds them to the database file
 * @author roryh
 */
public class Importer {

        /**
         * Converts UserCrime object into a String array
         * @param userCrime user inputted crime
         * @return userCrime converted into a string
         */
        public static String[] userToString(UserData userCrime) {

                String[] crimeString = {
                        userCrime.getId(), userCrime.getDate(), userCrime.getAddress(),
                        userCrime.getCrimeType(), "N", "N", "N", "N", "N", userCrime.getLatitude(),
                        userCrime.getLongitude(), userCrime.getLocation()
                };
                return crimeString;
        }

        /**
         * Takes in a UserData object and writes it to the database file
         * @param data userData object
         */
        public static void addUserData(UserData data) {
                //TODO somehow separate userData from PoliceData
                ArrayList<String[]> stringCrimes = new ArrayList<>();
                stringCrimes.add(userToString(data));
                WriteCSV.writeDataLineByLine("src/main/java/seng202/team3/Database/Database.txt", stringCrimes);
        }

        /**
         * Converts PoliceData object into a string array
         * @param crime PoliceData object
         * @return String array of the PoliceData object
         */
        public static String[] policeToString(PoliceData crime) {
                String[] crimeString = {crime.getCaseNumber(), crime.getDate(), crime.getAddress(),
                        crime.getCrimeType(), (Objects.equals(crime.isArrestMade(), "YES") ? "Y" : "N"),
                        crime.getDomestic(), crime.getBeat(), crime.getWard(), Integer.toString(crime.getXCoord()),
                        Integer.toString(crime.getYCoord()), crime.getLatitude(), crime.getLongitude()
                };
                return crimeString;
        }

        /**
         * Takes in an input path and writes Crimes into the database file
         * @param inputPath Input path of police crimes
         */
        public static void addPoliceData(String inputPath) {
                ArrayList<CrimeData> crimes = ReadCSV.readDataLineByLine(inputPath);
                ArrayList<String[]> stringCrimes = new ArrayList<>();
                for (CrimeData crime : crimes) {
                        stringCrimes.add(policeToString(((PoliceData) crime)));
                }
                WriteCSV.writeDataLineByLine("src/main/java/seng202/team3/Database/Database.txt", stringCrimes);
        }
}
