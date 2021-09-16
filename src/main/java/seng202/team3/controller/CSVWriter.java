package seng202.team3.controller;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class WriteCSV {

    public static void writeDataLineByLine(String filePath, String[] data)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object writer as parameter
            CSVWriter writer = new CSVWriter(outputFile);

            writer.writeNext(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
