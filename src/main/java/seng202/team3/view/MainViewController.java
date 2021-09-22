package seng202.team3.view;


import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;
import seng202.team3.model.PoliceData;

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



    @FXML
    private WebView mapView;

    private WebEngine webEngine;

    private ArrayList<CrimeData> tempActiveCrimeData = new ArrayList<CrimeData>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String hi = "hello";

        String[] strSplit = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06,1183633,1851786,41.748486365,-87.602675062,(41.748486365, -87.602675062)".split(",");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(strSplit));
        try {
            PoliceData pData = new PoliceData("1", data);
            tempActiveCrimeData.add(pData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        initMap();

        initCrimeSelector();
        initRegionFilterSelector();

    }

    public void loadData() {

        //convert crime data into json
        String json = new Gson().toJson(tempActiveCrimeData);
        System.out.println(json);
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
        String[] crimeTypes = {"ARSON", "ASSAULT", "BATTERY", "BURGLARY", "CONCEALED CARRY LICENCE", "CRIMINAL DAMAGE", "CRIMINAL SEXUAL ASSAULT", "CRIMINAL TRESPASS", "DECEPTIVE PRACTICE", "HOMICIDE", "INTERFERENCE WITH PUBLIC OFFICER", "INTIMIDATION", "KIDNAPPING", "LIQUOR LAW VIOLATION", "MOTOR VEHICLE THEFT", "NARCOTICS", "OFFENSE INVOLVING CHILDREN", "OTHER NARCOTIC VIOLATION", "OTHER OFFENSE", "PROSTITUTION", "PUBLIC PEACE VIOLATION", "ROBBERY", "SEX OFFENSE", "STALKING", "THEFT", "WEAPONS VIOLATION"};
        for (String crimeType : crimeTypes) {
            crimeSelector.getItems().add(crimeType);
        }
    }

    public void initRegionFilterSelector () {
        String[] filterTypes = {"HIGH FREQUENCY", "LOW FREQUENCY", "HIGH RISK AREAS", "LOW RISK AREAS"};
        for (String filterType : filterTypes) {
            regionFilteringKey.getItems().add(filterType);
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

    public Boolean getRegionFilteringToggle () {
        return regionFilteringToggle.isSelected();
    }

    public String getRegionFilter () {
        return (String) regionFilteringKey.getValue();
    }

    public Boolean getDateSortToggle () {
        return dateSortToggle.isSelected();
    }

    public Date getStartDate () throws ParseException {
        String startDateStr = startDate.getValue().toString();
        return new SimpleDateFormat("yyyy/MM/dd").parse(startDateStr);
    }

    public Date getEndDate () throws ParseException {
        String endDateStr = endDate.getValue().toString();
        return new SimpleDateFormat("yyyy/MM/dd").parse(endDateStr);
    }
}

