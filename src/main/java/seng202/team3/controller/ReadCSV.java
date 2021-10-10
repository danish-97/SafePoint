package seng202.team3.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class to read Crime content of a file and return list of String crimes
 * @author roryh, Danish and mattgarrett
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
            columnNotWanted = List.of(16); //Columns not needed for PoliceData

        } else { //If reading from database.txt

            //If Crime is going to be a UserData object
            if (Objects.equals(crimeType, "U")) {
                columnNotWanted = Arrays.asList(4, 5, 6, 7, 8, 9, 10, 11, 14);
            } else {
                columnNotWanted = List.of(3, 12); // Removes Crime Type
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
            UserData userDataObject = new UserData(row[0], modifiedCrime); //Creates PoliceData object
            CrimeData.setLatestID(Integer.parseInt(row[0]) + 1);

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
            int headerSize = 0;

            //Get header length to determine if reading from database.txt
            String[] header = csvReader.readNext();
            if (header != null) {
                headerSize = header.length;
            }
            // Read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {

                //Create CrimeData object and add it to return list
                if (nextRecord.length > 1) {
                    CrimeData crimeDataObject = readerHelper(nextRecord, headerSize); //Calls reader helper, sends in line of CSV file
                    listOfCrimes.add(crimeDataObject); //Adds Crime to final list
                }
            }
            //Returns crime
            return listOfCrimes;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: File not found");
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error: IO Exception");
        } catch (CsvValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Invalid CSV");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Parse exception");
        }


    }

    /**
     * Given a UserData ID, removes this line from the database file.
     * @param file the target file to remove the line from
     * @param ID ID of the UserData to be removed
     * @throws IOException if file is invalid
     * @throws CsvValidationException if CSV format is invalid
     */
    public static void removeLineByID (String file, String ID) throws IOException, CsvValidationException {
        //Initialize variables
        FileReader filereader = new FileReader(file);
        CSVReader csvReader = new CSVReader(filereader);
        String [] nextRecord;
        ArrayList<String[]> allRecords = new ArrayList<>();

        //Removes first line from CSV file, which is the header
        allRecords.add(csvReader.readNext());

        /* Loops through all existing lines in the file and adds them to allRecords except the
        line that has the required ID to remove */
        while ((nextRecord = csvReader.readNext()) != null) {
            if (!isRecord (nextRecord, ID)) {
                allRecords.add(nextRecord);
            }
        }

        //Reconstructs the CSV file with the new records
        WriteCSV.reconstructFile (allRecords, file);
    }

    /**
     * Replaces the line of file that has ID id with the new data
     * @param file target file to replace the data into
     * @param id ID of the UserData object to be replaced
     * @param newData formatted String from ReportCrimeController representing the new data
     * @throws CsvValidationException if CSV format is incorrect
     * @throws IOException if given invalid path or file
     */
    public static void replaceData (String file, String id, String newData) throws CsvValidationException, IOException {
        //Initialize
        FileReader filereader = new FileReader(file);
        CSVReader csvReader = new CSVReader(filereader);
        String [] nextRecord;
        ArrayList<String[]> allRecords = new ArrayList<>();

        //removes header line from CSV
        allRecords.add(csvReader.readNext());

        /* Loops through the CSV until the line that we want is found. For each line it is added
        either unmodified if it is not the target line, or modified if it is, to allRecords */
        while ((nextRecord = csvReader.readNext()) != null) {
            if (!isRecord (nextRecord, id)) {
                allRecords.add(nextRecord);
            } else {
                //formatting of newData into String[], with empty positions accounted for
                String[] newStringArr = newData.split(",");
                ArrayList<String> newDataArr = new ArrayList<>(Arrays.asList(newStringArr));
                newDataArr.add(0, id);
                for (int i = 0; i < 8; i++) {
                    //adding null elements unique to PoliceData
                    newDataArr.add(4, "N");
                }
                //If there is no Longitude, replace it with ""
                if (Objects.equals(newDataArr.get(newDataArr.size() - 1), "N")) {
                    newDataArr.set(newDataArr.size()-1, "");
                }
                //Specify type of UserData
                newDataArr.add("U");
                //Convert to a String[] from ArrayList<String>
                String[] stringArr = new String[newDataArr.size()];
                for (int i = 0; i < newDataArr.size(); i++) {
                    stringArr[i] = newDataArr.get(i);
                }
                allRecords.add(stringArr);
            }
        }
        //Reconstructs File
        WriteCSV.reconstructFile (allRecords, file);
    }

    /**
     * Tests if the record is the one we are looking for
     * @param currRecord record we want to compare
     * @param ID id that we are looking for
     * @return true if the currRecord.ID = ID, false otherwise
     */
    public static Boolean isRecord (String[] currRecord, String ID) {
        return (Objects.equals(currRecord[0], ID));
    }

}
