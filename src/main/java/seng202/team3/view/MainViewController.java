package seng202.team3.view;


import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainViewController implements Initializable {

    @FXML
    private TextField regionFilter;

    @FXML
    private ChoiceBox crimeSelector;

    @FXML
    private CheckBox policeDataToggle;

    @FXML
    private CheckBox userDataToggle;

    @FXML
    private CheckBox arrestMadeToggle;

    @FXML
    private CheckBox graphToggle;

    @FXML
    private WebView mapView;

    private WebEngine webEngine;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMap();
        initCrimeSelector();
    }

    private void initMap() {
        webEngine = mapView.getEngine();
        webEngine.load(Objects.requireNonNull(getClass().getClassLoader().getResource("seng202.team3.view/googleMap.html")).toExternalForm());
    }

    public void initCrimeSelector () {
        String[] crimeTypes = {"ARSON", "ASSAULT", "BATTERY", "BURGLARY", "CONCEALED CARRY LICENCE", "CRIMINAL DAMAGE", "CRIMINAL SEXUAL ASSAULT", "CRIMINAL TRESPASS", "DECEPTIVE PRACTICE", "HOMICIDE", "INTERFERENCE WITH PUBLIC OFFICER", "INTIMIDATION", "KIDNAPPING", "LIQUOR LAW VIOLATION", "MOTOR VEHICLE THEFT", "NARCOTICS", "OFFENSE INVOLVING CHILDREN", "OTHER NARCOTIC VIOLATION", "OTHER OFFENSE", "PROSTITUTION", "PUBLIC PEACE VIOLATION", "ROBBERY", "SEX OFFENSE", "STALKING", "THEFT", "WEAPONS VIOLATION"};
        for (String crimeType : crimeTypes) {
            crimeSelector.getItems().add(crimeType);
        }
    }

    public String getCurrentRegion () {
        return regionFilter.getText();
    }

    public String getCurrentCrimeType () {
        return (String) crimeSelector.getValue();
    }

    public Boolean getPoliceDataToggle() {
        return policeDataToggle.isSelected();
    }

    public Boolean getUserDataToggle() {
        return userDataToggle.isSelected();
    }

    public Boolean getArrestMadeToggle() {
        return arrestMadeToggle.isSelected();
    }

    public Boolean getGraphToggle() {
        return graphToggle.isSelected();
    }
}

