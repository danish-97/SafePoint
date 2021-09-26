package seng202.team3.view;


import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;
import seng202.team3.controller.FilterController;
import seng202.team3.controller.UIDataInterface;

public class MainViewController implements Initializable {

    //FXML components for filtering
    @FXML private TextField regionFilter;
    @FXML private ChoiceBox crimeSelector;
    @FXML private CheckBox policeDataToggle;
    @FXML private CheckBox userDataToggle;
    @FXML private CheckBox arrestMadeToggle;
    @FXML private CheckBox graphToggle;
    @FXML private CheckBox regionFilteringToggle;
    @FXML private ChoiceBox regionFilteringKey;
    @FXML private CheckBox dateSortToggle;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;

    @FXML private ScrollPane crimeDataPanel;

    @FXML
    private WebView mapView;

    private WebEngine webEngine;

    @FXML
    public void  updateCrimeData(ActionEvent e) throws ParseException, ClassNotFoundException {
        updateRegionCrimeData();
        updateMapSettingsData();
        updateRegionDateData();
        crimeDataPanel.setContent(DataPaneConstructor.loadActiveCrimes());
        webEngine.executeScript("removeMarkers()");
        loadData1();

    }

    @FXML
    public void reportCrime (ActionEvent e) {
        UserInputHandler uih = new UserInputHandler();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UIDataInterface.initCrimeData();
        initMap();
        initCrimeSelector();
        initRegionFilterSelector();

    }

    public void loadData() {

        ArrayList<CrimeData> tempActiveCrimeData = new ArrayList<CrimeData>();
        tempActiveCrimeData = DataManager.getData();
        String json = new Gson().toJson(tempActiveCrimeData);
        webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                webEngine.executeScript("getAllActiveCrimeData(" + json + ")");

            }
        });

    }

    public void loadData1() {

        ArrayList<CrimeData> tempActiveCrimeData = new ArrayList<CrimeData>();
        tempActiveCrimeData = DataManager.getData();
        String json = new Gson().toJson(tempActiveCrimeData);
        webEngine.executeScript("getAllActiveCrimeData(" + json + ")");


    }

    private void initMap() {
        webEngine = mapView.getEngine();
        webEngine.load(Objects.requireNonNull(getClass().getClassLoader().getResource("seng202.team3.view/googleMap.html")).toExternalForm());
        loadData();
    }

    public void initCrimeSelector () {
        ReportCrimeController.constructCrimeChoiceBox(crimeSelector);
    }

    public void initRegionFilterSelector () {
        String[] filterTypes = {"HIGH FREQUENCY", "LOW FREQUENCY", "HIGH RISK AREAS", "LOW RISK AREAS"};
        for (String filterType : filterTypes) {
            regionFilteringKey.getItems().add(filterType);
        }
    }

    public void updateRegionCrimeData () {
        if (!Objects.equals(regionFilter.getText(), "")) {
            FilterController.setActiveLocation(regionFilter.getText());
        }
        FilterController.setActiveCrimeType((String) crimeSelector.getValue());
    }

    public void updateMapSettingsData () {
        FilterController.setPoliceDataActive(policeDataToggle.isSelected());
        FilterController.setUserDataActive(userDataToggle.isSelected());
        FilterController.setArrestMade(arrestMadeToggle.isSelected());
    }

    public void updateRegionDateData () throws ParseException {
        FilterController.setRegionDataActive(regionFilteringToggle.isSelected());
        FilterController.setRegionFilteringKey((String) regionFilteringKey.getValue());
        FilterController.setDateFiltering(dateSortToggle.isSelected());
        if (startDate.getValue() != null) {
            String startDateStr = startDate.getValue().toString();
            FilterController.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr));
        }
        if (endDate.getValue() != null) {
            String endDateStr = endDate.getValue().toString();
            FilterController.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr));
        }
    }

    public WebEngine getWebEngine() {return webEngine;}

}

