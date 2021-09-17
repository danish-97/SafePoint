package seng202.team3.controller;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;


public class ReadCSV {

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
                    for (String cell : nextRecord) {
                        crime.add(cell);
                    }
                    listOfCrimes.add(crime);
                    crime = new ArrayList<String>();
                } else {
                    counter++;
                }
            }

            return listOfCrimes;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

