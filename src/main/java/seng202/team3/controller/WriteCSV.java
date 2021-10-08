package seng202.team3.controller;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Writes data found in an input file ot an output file
 * @author roryh, Danish, mattgarrett
 */
public class WriteCSV extends Importer {

    /**
     * Reads data from a csv input file and writes it to an output file
     * @param outputPath Location of output file
     * @param inputCrimes Input Crimes to be added
     */
    public static void writeDataLineByLine(String outputPath, ArrayList<String[]> inputCrimes)
    {
        try {

            File file = new File(outputPath);
            FileWriter outputFile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputFile);
            BufferedWriter bw = new BufferedWriter(outputFile);

            // Adding header to csv
            if (file.length() == 0 ) {
                String[] header = { "Case#", "DATE OF OCCURRENCE", "BLOCK", "PRIMARY DESCRIPTION", "SECONDARY DESCRIPTION",
                        "LOCATION DESCRIPTION", "ARREST", "DOMESTIC", "BEAT", "WARD","X COORDINATE",
                        "Y COORDINATE", "LATITUDE", "LONGITUDE", "OBJECT TYPE"};
                writer.writeNext(header);
            }

            // Writing crimes
            assert inputCrimes != null;
            for (String[] crime : inputCrimes) {
                bw.newLine();
                writer.writeNext(crime);
            }

            // Closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reconstructs the file from newRecords, in the format of one String[] (record) per line
     * @param newRecords the new records to be inserted into the database file
     * @param file target file
     * @throws IOException if file is invalid
     */
    public static void reconstructFile (ArrayList<String[]> newRecords, String file) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
        csvWriter.writeAll(newRecords);
        csvWriter.close();
        UIDataInterface.initCrimeData();
    }
}
