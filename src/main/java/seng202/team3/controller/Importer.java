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

                return new String[]{
                        userCrime.getId(), userCrime.getDate(), userCrime.getAddress(),
                        userCrime.getCrimeType(), "N","N", "N", "N", "N", "N", "N", "N", userCrime.getLatitude(),
                        userCrime.getLongitude(), "U"
                };
        }

        /**
         * Takes in a UserData object and writes it to the database file
         * @param data userData object
         */
        public static void addUserData(UserData data) {
                ArrayList<String[]> stringCrimes = new ArrayList<>();
                stringCrimes.add(userToString(data));
                WriteCSV.writeDataLineByLine("data.csv", stringCrimes);
        }
        /**
         * Takes in a UserData object and writes it to the database file
         * (takes in an outputPath for test file
         * @param data userData object
         * @param outputPath outputs to a test file
         */
        public static void addUserData(UserData data, String outputPath) {
                ArrayList<String[]> stringCrimes = new ArrayList<>();
                stringCrimes.add(userToString(data));
                WriteCSV.writeDataLineByLine(outputPath, stringCrimes);
        }

        /**
         * Converts PoliceData object into a string array
         * @param crime PoliceData object
         * @return String array of the PoliceData object
         */
        public static String[] policeToString(PoliceData crime) {
                return new String[]{crime.getCaseNumber(), crime.getDate(), crime.getAddress(),
                        crime.getCrimeType(), crime.getSecondDescription(), crime.getLocationDescription(), (Objects.equals(crime.isArrestMade(), "YES") ? "Y" : "N"),
                        (Objects.equals(crime.getDomestic(), "YES") ? "Y" : "N"), crime.getBeat(), crime.getWard(), Integer.toString(crime.getXCoord()),
                        Integer.toString(crime.getYCoord()), crime.getLatitude(), crime.getLongitude(), "P"
                };
        }

        /**
         * Takes in an input path and writes Crimes into the database file
         * @param inputPath Input path of police crimes
         */
        public static void addPoliceData(String inputPath) {
                //Reads Crimes from input Path
                ArrayList<CrimeData> crimes = ReadCSV.readDataLineByLine(inputPath);

                //Converts Crimes into strings in correct format
                ArrayList<String[]> stringCrimes = new ArrayList<>();
                for (CrimeData crime : crimes) {
                        stringCrimes.add(policeToString(((PoliceData) crime)));
                }
                //Calls WriteCSV with input crimes
                WriteCSV.writeDataLineByLine("data.csv", stringCrimes);
        }
}
