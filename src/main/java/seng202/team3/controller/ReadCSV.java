package seng202.team3.controller;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to read Crime content of a file and return list of String crimes
 * @author roryh and Danish
 */

public class ReadCSV {
    /**
     * Reads content of a file and returns list of Crimes in file without a header
     * @param file Location of input file
     * @return A list of crimes in file
     */
    public static ArrayList<ArrayList<String>> readDataLineByLine(String file) {

        try {
            //Creates list of lists
            ArrayList<ArrayList<String>> listOfCrimes = new ArrayList<>();
            ArrayList<String> crime = new ArrayList<>();

            // Create an object of fileReader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;


            // we are going to read data line by line
            int counter = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (counter != 0) { //Removes header of file
                    Collections.addAll(crime, nextRecord);
                    listOfCrimes.add(crime);
                    crime = new ArrayList<>();
                } else {
                    counter++;
                }
            }
            System.out.println(listOfCrimes);
            return listOfCrimes;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

