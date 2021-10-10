package seng202.team3.view;


import javafx.event.ActionEvent;
import com.opencsv.exceptions.CsvValidationException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;
import seng202.team3.controller.UIDataInterface;

import org.json.JSONObject;
import seng202.team3.controller.ReadCSV;
import seng202.team3.controller.UIDataInterface;

import javafx.event.ActionEvent;
import seng202.team3.model.CrimeData;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * FXML Control file for FXML attributes in report-crime-view
 * @author mattgarrett, Danish Jahangir
 */
public class ReportCrimeController implements Initializable {

    //Initialises each attribute from FXML file into a variable
    @FXML private ChoiceBox crimeTypeSelector;
    @FXML private TextField addressField;
    @FXML private TextField latitude;
    @FXML private TextField longitude;
    @FXML private DatePicker date;

    private String currentID;

    private Boolean isEditing = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        constructCrimeChoiceBox(crimeTypeSelector);
        ReportCrimeWindow.setActiveController(this);
    }

    /**
     * Handles the report crime button function
     * @param e button pressed
     * @throws ParseException when input is unexpected
     */
    @FXML
    public void reportCrime (ActionEvent e) throws ParseException, CsvValidationException, IOException, InterruptedException {
        if (validateInputs ()) {
            //valid input given
            getLatLong(addressField.getText());
            if (!isEditing) {
                //adds userdata and opens confirmation window
                UIDataInterface.addUserData(formatInputs());
                openConfirmationWindow(Integer.parseInt(CrimeData.getLatestID()) - 1);
            } else {
                //edits existing userdata and opens confirmation window
                ReadCSV.replaceData ("data.csv", currentID, formatInputs());
                openConfirmationWindow();
            }
            ReportCrimeWindow.closeStage();
        } else if (isEditing) {
            //remove this data from the database
            ReadCSV.removeLineByID("data.csv", currentID);
            openConfirmationWindow();
            ReportCrimeWindow.closeStage();
            isEditing = false;
        }
    }

    /**
     * Verifies data input into the window is valid
     * @return if the user inputs correct data
     */
    public Boolean validateInputs () {
        if (crimeTypeSelector.getValue() == null) return false;
        if (addressField.getText() == null) return false;
        if (Objects.equals(addressField.getText(), "")) return false;
        return date.getValue() != null;
    }

    /**
     * Formats the inputs from the window into one comma separated string to be input into the database and
     * created into a UserData object
     * @return Formatted string to be turned into a UserData object
     */
    public String formatInputs () throws IOException, InterruptedException {
        String formattedString;
        formattedString = date.getValue().toString() + ",";
        formattedString = formattedString + addressField.getText() + ",";
        formattedString = formattedString + crimeTypeSelector.getValue() + ",";
        Double[] latLong = getLatLong(addressField.getText());
        //if the lat long exists, that is the input for address is valid
        if (latLong[0] != 0.0) {
            formattedString = formattedString + latLong[0] + ",";
            formattedString = formattedString + latLong[1];
        } else {
            //null data given, fill string with nothing so that map doesn't get messed up
            formattedString = formattedString + ",";
            formattedString = formattedString + "N";
        }
        return formattedString;
    }

    /**
     * Adds all crime types to a ChoiceBox for selection
     * @param crimeTypeSelector The ChoiceBox that the fields should be added into
     */
    public static void constructCrimeChoiceBox(ChoiceBox crimeTypeSelector) {
        String[] crimeTypes = {"ALL", "ARSON", "ASSAULT", "BATTERY", "BURGLARY", "CONCEALED CARRY LICENCE", "CRIMINAL DAMAGE",
                "CRIMINAL SEXUAL ASSAULT", "CRIMINAL TRESPASS", "DECEPTIVE PRACTICE", "HOMICIDE", "INTERFERENCE WITH PUBLIC OFFICER",
                "INTIMIDATION", "KIDNAPPING", "LIQUOR LAW VIOLATION", "MOTOR VEHICLE THEFT", "NARCOTICS", "OFFENSE INVOLVING CHILDREN",
                "OTHER NARCOTIC VIOLATION", "OTHER OFFENSE", "PROSTITUTION", "PUBLIC PEACE VIOLATION", "ROBBERY", "SEX OFFENSE",
                "STALKING", "THEFT", "WEAPONS VIOLATION"};
        for (String crimeType : crimeTypes) {
            crimeTypeSelector.getItems().add(crimeType);
        }
    }

    /**
     * Gets the latitude and longitude from an input address using the class GeocoderApi.
     * @throws IOException if the input data is invalid.
     * @throws InterruptedException if the thread is interrupted.
     */
    public static Double[] getLatLong(String address) throws IOException, InterruptedException {
        Double lat = null;
        Double lon = null;
        GeocoderApi geocoderApi = new GeocoderApi();
        //format input string into string API can use
        String res = geocoderApi.doRequest(address.replaceAll(" ", "-"));
        JSONObject obj = new JSONObject(res);
        JSONArray data = obj.getJSONArray("results");
        //loops through to get the latest lat long value for redundancies' sake
        for (int i = 0; i < data.length(); i++) {
            JSONObject result = data.getJSONObject(i);
            lat = result.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            lon = result.getJSONObject("geometry").getJSONObject("location").getDouble("lng");

        }

        if (lat != null) {
            return new Double[]{lat, lon};
        }

        //if data is invalid return general lat long coords
        return new Double[]{0.0, 0.0};

    }

    public void openConfirmationWindow (int confirmationID) {
        new ConfirmationWindow (confirmationID);
    }

    public void openConfirmationWindow () {new ConfirmationWindow();}

    public void setAttributes (CrimeData data) {
        isEditing = true;
        currentID = data.getId();
        setCrimeTypeSelector(data.getCrimeType());
        setAddressField(data.getAddress());
        setLongitude(data.getLongitude());
        setLatitude(data.getLatitude());
        setDate(data.getDate());
    }

    public void setCrimeTypeSelector (String curr) {
        crimeTypeSelector.setValue(curr);
    }

    public void setAddressField (String address) {
        addressField.setText(address);
    }

    public void setLatitude (String lat) {
        latitude.setText(lat);
    }

    public void setLongitude (String lon) {
        longitude.setText(lon);
    }

    public void setDate (String currDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date.setValue(LocalDate.parse(currDate, formatter));
    }
}
