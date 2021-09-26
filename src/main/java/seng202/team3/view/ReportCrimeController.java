package seng202.team3.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import seng202.team3.controller.UIDataInterface;

import javafx.event.ActionEvent;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ReportCrimeController implements Initializable {

    @FXML private ChoiceBox crimeTypeSelector;
    @FXML private TextField addressField;
    @FXML private TextField latitude;
    @FXML private TextField longitude;
    @FXML private DatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        constructCrimeChoiceBox(crimeTypeSelector);
    }

    @FXML
    public void reportCrime (ActionEvent e) throws ParseException {
        if (validateInputs ()) {
            UIDataInterface.addUserData(formatInputs());
        }
    }

    public Boolean validateInputs () {
        return (crimeTypeSelector.getValue() != null && addressField.getText() != null && date.getValue() != null);
    }

    public String formatInputs () {
        String formattedString;
        formattedString = date.getValue().toString() + ",";
        formattedString = formattedString + addressField.getText() + ",";
        formattedString = formattedString + crimeTypeSelector.getValue() + ",";
        formattedString = formattedString + latitude.getText() + ",";
        formattedString = formattedString + longitude.getText();
        return formattedString;
    }

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
