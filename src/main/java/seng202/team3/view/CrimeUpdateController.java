package seng202.team3.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CrimeUpdateController implements Initializable {

    @FXML Label crimeIDLabel;

    public void setCrimeIDLabelLabel (String newID) {
        crimeIDLabel.setText(newID);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
