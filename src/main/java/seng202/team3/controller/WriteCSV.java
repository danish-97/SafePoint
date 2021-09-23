package seng202.team3.controller;

import com.opencsv.CSVWriter;
import seng202.team3.model.PoliceData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Writes data found in an input file ot an output file
 * @author roryh and Danish
 */
public class WriteCSV extends Importer {

    /**
     * Reads data from a csv input file and writes it to an output file
     * @param outputPath Location of output file
     * @param inputCrimes Input Crimes to be added
     */
    public static void writeDataLineByLine(String outputPath, ArrayList<String[]> inputCrimes)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(outputPath);

        try {


            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object writer as parameter
            CSVWriter writer = new CSVWriter(outputFile);

            // adding header to csv
            if (file.length() == 0 ) {
                String[] header = { "Case#", "DATE OF OCCURRENCE", "BLOCK", "PRIMARY DESCRIPTION",
                        "ARREST", "DOMESTIC", "BEAT", "WARD","X COORDINATE",
                        "Y COORDINATE", "LATITUDE", "LONGITUDE"};
                writer.writeNext(header);
            }

            assert inputCrimes != null;
            for (String[] crime : inputCrimes) {
                writer.writeNext(crime);
            }



            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
