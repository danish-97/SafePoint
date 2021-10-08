package seng202.team3.view;

import com.opencsv.exceptions.CsvValidationException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    public void reportCrime (ActionEvent e) throws ParseException, CsvValidationException, IOException {
        if (validateInputs ()) {
            if (!isEditing) {
                UIDataInterface.addUserData(formatInputs());
                openConfirmationWindow(Integer.parseInt(CrimeData.getLatestID()));
            } else {
                ReadCSV.replaceData ("data.csv", currentID, formatInputs());
                openConfirmationWindow();
            }
            ReportCrimeWindow.closeStage();
        } else if (isEditing) {
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
        //TODO if this is false and isEditing then we want to delete the crime
        if (crimeTypeSelector.getValue() == null) return false;
        if (addressField.getText() == null) return false;
        if (Objects.equals(addressField.getText(), "")) return false;
        return date.getValue() != null;
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
            formattedString = formattedString + latitude.getText() + ",";
        }
        if (!longitude.getText().equals("")) {
            formattedString = formattedString + longitude.getText();
        } else {
            formattedString = formattedString + "N";
        }
        return formattedString;
    }

    /**
     * Adds all crime types to a ChoiceBox for selectionc
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
