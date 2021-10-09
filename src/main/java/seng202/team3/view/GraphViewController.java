package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import seng202.team3.controller.GraphCreator;
import seng202.team3.controller.ReadCSV;
import seng202.team3.model.CrimeData;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GraphViewController implements Initializable {

    @FXML private ChoiceBox crimeTypeSelector;
    @FXML private ChoiceBox yearSelector;

    GraphCreator graphCreator = new GraphCreator();
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    LineChart<Number, Number> lineChart
            = new LineChart<Number, Number>(xAxis, yAxis);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportCrimeController.constructCrimeChoiceBox(crimeTypeSelector);
    }

    public void graphOverTime(ArrayList<CrimeData> crimeData) {
        XYChart.Series series = graphCreator.createGraphOverTime(crimeData);
        lineChart.setTitle("Crimes Over Time");
        xAxis.setLabel("Time (Per Week)");
        yAxis.setLabel("Number of times");
        lineChart.getData().add(series);
    }

    public void graphOverTimePerType(ArrayList<CrimeData> crimeData) {
        XYChart.Series series = graphCreator.createGraphOverTimePerType(crimeData, "ROBBERY");
        lineChart.setTitle("Crimes Over Time Per Type");
        xAxis.setLabel("Time (Per Week)");
        yAxis.setLabel("Number of times");
        lineChart.getData().add(series);
    }

}