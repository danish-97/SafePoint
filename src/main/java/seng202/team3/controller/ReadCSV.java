package seng202.team3.controller;

import com.opencsv.CSVReader;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class to read Crime content of a file and return list of String crimes
 * @author roryh and Danish
 */

public class ReadCSV extends Importer {


    /**
     * Loops through each Line in the CSV file and constructs PoliceData object
     * @param row Each row in the csv file
     * @param columnAmount size of header (column amount)
     * @return policeDataObject
     */
    private static CrimeData readerHelper(String[] row, int columnAmount) throws ParseException {

        //Initialise variables
        ArrayList<String> modifiedCrime = new ArrayList<>(); //Modified row to send to PoliceData
        int columnCount = 0;
        List<Integer> columnNotWanted;
        String crimeType = row[row.length-1];

        //Checks column count to determine if reading from database.txt
        if (columnAmount == 17) { //If reading from an Excel document
            columnNotWanted = Arrays.asList(3, 5, 6, 11, 16, 17); //Columns not needed for PoliceData

        } else { //If reading from database.txt

            //If Crime is going to be a UserData object
            if (Objects.equals(crimeType, "U")) {
                columnNotWanted = Arrays.asList(0, 4, 5, 6, 7, 8, 9, 12);

            } else {
                columnNotWanted = List.of(12); // Removes Crime Type
            }
        }

        //Loops through each column in row
        for (String column : row) {

            //Checks if column is wanted for CrimeData object
            boolean inList = (columnNotWanted).contains(columnCount);

            //Adds columns wanted to modifiedCrime
            if (!inList) {
                modifiedCrime.add(column);
            }
            columnCount++;
        }

        //If CrimeData object is a UserData object
        if (Objects.equals(crimeType, "U")) {
            UserData userDataObject = new UserData(CrimeData.getLatestID(), modifiedCrime); //Creates PoliceData object
            CrimeData.incrementLatestID();

            return userDataObject;

        //If CrimeData object is a PoliceData object
        } else {
            PoliceData policeDataObject = new PoliceData(CrimeData.getLatestID(), modifiedCrime); //Creates PoliceData object
            CrimeData.incrementLatestID();

            return policeDataObject;
        }
    }
    
    /**
     * Reads content of a file and returns list of Crimes in file without a header
     * @param file Location of input file
     * @return A list of crimes in file
     */
    public static ArrayList<CrimeData> readDataLineByLine(String file) {

        try {
            //Initialising variables
            ArrayList<CrimeData> listOfCrimes = new ArrayList<>();
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            //Get header length to determine if reading from database.txt
            int headerSize = csvReader.readNext().length;

            // Read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {

                //Create CrimeData object and add it to return list
                CrimeData crimeDataObject = readerHelper(nextRecord, headerSize); //Calls reader helper, sends in line of CSV file
                listOfCrimes.add(crimeDataObject); //Adds Crime to final list
            }
            //Returns crime
            return listOfCrimes;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}

