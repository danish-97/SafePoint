package seng202.team3.controller;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteCSV {

    public static void writeDataLineByLine(String outputPath, String inputPath)
    {
        ReadCSV inputFile = null;
        ArrayList<ArrayList<String>> inputCrimes = new ArrayList<>();
        inputCrimes = inputFile.readDataLineByLine(inputPath);

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(outputPath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object writer as parameter
            CSVWriter writer = new CSVWriter(outputFile);

            // adding header to csv
            String[] header = { "Case#", "DATE OF OCCURRENCE", "BLOCK", "IUCR", "PRIMARY DESCRIPTION",
                    "SECONDARY DESCRIPTION", "LOCATION DESCRIPTION", "ARREST", "DOMESTIC", "BEAT", "WARD", "FBI CD","X COORDINATE",
                    "Y COORDINATE", "LATITUDE", "LONGITUDE", "LOCATION"};
            writer.writeNext(header);


            for (ArrayList<String> crime : inputCrimes) {
                String[] str = new String[crime.size()];
                for (int i = 0; i < crime.size(); i++) {
                    str[i] = crime.get(i);
                }
                writer.writeNext(str);
            }



            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
