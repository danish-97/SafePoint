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
    }

    @FXML
    public void reportCrime (ActionEvent e) {
        UserInputHandler uih = new UserInputHandler();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        try {
            PoliceData pData = new PoliceData("1", data);
            tempActiveCrimeData.add(pData);

            ArrayList<String> uDataList = new ArrayList<>();
            uDataList.add("THEFT");
            uDataList.add("49 MAYS ROAD");
            uDataList.add("41.812610526");
            uDataList.add("-87.723765071");
            uDataList.add("11/26/2020 07:50:00 AM");
            UserData uData = new UserData("2", uDataList);

            tempActiveCrimeData.add(uData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        initMap();
        initCrimeSelector();
        initRegionFilterSelector();
        UIDataInterface.initCrimeData();
    }

    public void loadData() {

        //convert crime data into json
        String json = new Gson().toJson(tempActiveCrimeData);
        webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                webEngine.executeScript("getAllActiveCrimeData(" + json + ")");
                webEngine.executeScript("loadMarkers()");

            }
        });

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

