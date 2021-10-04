package seng202.team3.view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import seng202.team3.controller.UIDataInterface;

import javafx.event.ActionEvent;

import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Control file for FXML attributes in report-crime-view
 */
public class ReportCrimeController implements Initializable {

    //Initialises each attribute from FXML file into a variable
    @FXML private ChoiceBox crimeTypeSelector;
    @FXML private TextField addressField;
    @FXML private TextField latitude;
    @FXML private TextField longitude;
    @FXML private DatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        constructCrimeChoiceBox(crimeTypeSelector);
    }

    /**
     * Handles the report crime button function
     * @param e button pressed
     * @throws ParseException when input is unexpected
     */
    @FXML
    public void reportCrime (ActionEvent e) throws ParseException {
        if (validateInputs ()) {
            UIDataInterface.addUserData(formatInputs());
            ReportCrimeWindow.closeStage();
        }
    }

    /**
     * Verifies data input into the window is valid
     * @return if the user inputs correct data
     */
    public Boolean validateInputs () {
        return (crimeTypeSelector.getValue() != null && addressField.getText() != null && date.getValue() != null);
    }

    /**
     * Formats the inputs from the window into one comma seperated string to be input into the database and
     * created into a UserData object
     * @return Formatted string to be turned into a UserData object
     */
    public String formatInputs () {
        String formattedString;
        formattedString = date.getValue().toString() + ",";
        formattedString = formattedString + addressField.getText() + ",";
        formattedString = formattedString + crimeTypeSelector.getValue() + ",";
        if (latitude.getText().equals("")) {
            formattedString = formattedString + ",";
        } else {
            formattedString = formattedString + latitude.getText() + "N,";
        }
        if (!longitude.getText().equals("")) {
            formattedString = formattedString + longitude.getText();
        } else {
            formattedString = formattedString + "N";
        }
        return formattedString;
    }

    /**
     * Gets the latitude and longitude from an input address.
     * @throws IOException if the input data is invalid.
     * @throws InterruptedException if the thread is interrupted.
     */

    public void getLatLong() throws IOException, InterruptedException {
        Double lat = null;
        Double lon = null;
        GeocoderApi geocoderApi = new GeocoderApi();
        String res = geocoderApi.doRequest(addressField.getText());
        JSONObject obj = new JSONObject(res);
        JSONArray data = obj.getJSONArray("results");
        for (int i = 0; i < data.length(); i++) {
            JSONObject result = data.getJSONObject(i);
            lat = result.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            lon = result.getJSONObject("geometry").getJSONObject("location").getDouble("lng");

        }

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
}
