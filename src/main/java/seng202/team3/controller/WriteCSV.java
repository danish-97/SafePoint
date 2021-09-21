package seng202.team3.controller;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Writes data found in an input file ot an output file
 * @author roryh and Danish
 */
public class WriteCSV {

    /**
     * Reads data from a csv input file and writes it to an output file
     * @param outputPath Location of output file
     * @param inputPath Location of input file
     */
    public static void writeDataLineByLine(String outputPath, String inputPath)
    {
        ArrayList<ArrayList<String>> inputCrimes;
        inputCrimes = ReadCSV.readDataLineByLine(inputPath);

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


            assert inputCrimes != null;
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
