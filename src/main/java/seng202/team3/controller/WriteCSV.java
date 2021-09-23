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
     * @param inputPath Location of input file
     */
    public static void writeDataLineByLine(String outputPath, String inputPath)
    {
        ArrayList<PoliceData> inputCrimes;
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
            String[] header = { "Case#", "DATE OF OCCURRENCE", "BLOCK", "PRIMARY DESCRIPTION",
                     "ARREST", "DOMESTIC", "BEAT", "WARD","X COORDINATE",
                    "Y COORDINATE", "LATITUDE", "LONGITUDE"};
            writer.writeNext(header);

            assert inputCrimes != null;
            for (PoliceData crime : inputCrimes) {
                String[] crimeString = {crime.getCaseNumber(), crime.getDate(), crime.getAddress(),
                        crime.getCrimeType(), (Objects.equals(crime.isArrestMade(), "YES") ? "Y" : "N"),
                        crime.getDomestic(), crime.getBeat(), crime.getWard(), Integer.toString(crime.getXCoord()),
                        Integer.toString(crime.getYCoord()), crime.getLatitude(), crime.getLongitude()
                };
                writer.writeNext(crimeString);
            }



            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
