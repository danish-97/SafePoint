package seng202.team3.controller;

import com.opencsv.CSVReader;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to read Crime content of a file and return list of String crimes
 * @author roryh and Danish
 */

public class ReadCSV extends Importer {


    /**
     * Loops through each Line in the CSV file and constructs PoliceData object
     * @param line Each line in the csv file
     * @param columnAmount size of header (column amount)
     * @return policeDataObject
     */
    private static PoliceData readerHelper(String[] line, int columnAmount){

        ArrayList<String> modifiedCrime = new ArrayList(); //Modified line to send to PoliceData

        int columnCount = 0;
        List<Integer> columnNotWanted = new ArrayList<>();
        if (columnAmount == 17) {
            columnNotWanted = Arrays.asList(3, 5, 6, 11, 16, 17); //Columns not needed for PoliceData
        }

        for (String column : line) { //Loops through each crime
            boolean inList = (columnNotWanted).contains(columnCount); //If column is not necessary for PoliceData
            if (!inList) { //Gets columns needed
                modifiedCrime.add(column);
            }
            columnCount++;
        }

        PoliceData policeDataObject = new PoliceData(CrimeData.getLatestID(), modifiedCrime); //Creates PoliceData object
        CrimeData.incrementLatestID();
        
        return policeDataObject;
    }
    
    /**
     * Reads content of a file and returns list of Crimes in file without a header
     * @param file Location of input file
     * @return A list of crimes in file
     */
    public static ArrayList<PoliceData> readDataLineByLine(String file) {

        try {
            
            ArrayList<PoliceData> listOfCrimes =  new ArrayList<>();


            // Create an object of fileReader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            int headerSize = csvReader.readNext().length;
            // Read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                PoliceData policeDataObject = readerHelper(nextRecord, headerSize); //Calls reader helper, sends in line of CSV file
                listOfCrimes.add(policeDataObject); //Adds Crime to final list
            }
            return listOfCrimes;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

