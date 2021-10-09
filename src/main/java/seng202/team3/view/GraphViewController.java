package seng202.team3.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import seng202.team3.controller.GraphCreator;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GraphViewController implements Initializable {

    @FXML private ChoiceBox selectCrime;
    @FXML private ChoiceBox yearSelector;

    GraphCreator graphCreator = new GraphCreator();
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();

    @FXML LineChart lineChart = new LineChart(xAxis, yAxis);




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initSelectCrime();
    }

    @FXML
    public void viewGraph() {
        if (selectCrime.getValue().equals("All")) {
            graphOverTime(DataManager.getData());
        }
        else {
            graphOverTimePerType(DataManager.getData(), selectCrime.getValue().toString());
        }
    }

    public void initSelectCrime() {
        ReportCrimeController.constructCrimeChoiceBox(selectCrime);
    }

    public void graphOverTime(ArrayList<CrimeData> crimeData) {
        XYChart.Series series = graphCreator.createGraphOverTime(crimeData);
        lineChart.setTitle("Crimes Over Time");
        xAxis.setLabel("Time (Per Week)");
        yAxis.setLabel("Number of times");
        lineChart.getData().add(series);
    }

    public void graphOverTimePerType(ArrayList<CrimeData> crimeData, String crimeType) {
        XYChart.Series series = graphCreator.createGraphOverTimePerType(crimeData, crimeType);
        lineChart.setTitle("Crimes Over Time Per Type");
        xAxis.setLabel("Time (Per Week)");
        yAxis.setLabel("Number of times");
        lineChart.getData().add(series);
    }


}