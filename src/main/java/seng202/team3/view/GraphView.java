package seng202.team3.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import seng202.team3.controller.GraphCreator;
import seng202.team3.controller.ReadCSV;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;

import java.text.ParseException;
import java.util.ArrayList;


public class GraphView extends Application {

    GraphCreator graphCreator = new GraphCreator();
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    LineChart<Number, Number> lineChart
            = new LineChart<Number, Number>(xAxis, yAxis);


    @Override
    public void start(Stage stage) throws ParseException {
        stage.setTitle("Crime Data Graph");
        Scene scene  = new Scene(lineChart,800,600);
        ReadCSV readCSV = new ReadCSV();
        ArrayList<CrimeData> crimeData = readCSV.readDataLineByLine("data.csv");
        graphOverTime(crimeData);
        stage.setScene(scene);
        stage.show();
    }

    public void graphOverTime(ArrayList<CrimeData> crimeData) throws ParseException {
        XYChart.Series series = graphCreator.createGraphOverTime(crimeData);
        lineChart.setTitle("Crimes Over Time");
        xAxis.setLabel("Time (Per Week)");
        yAxis.setLabel("Number of times");
        lineChart.getData().add(series);


    }

    public static void main(String[] args) {
        launch(args);
    }
}