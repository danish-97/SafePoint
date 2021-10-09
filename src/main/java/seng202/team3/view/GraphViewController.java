package seng202.team3.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import seng202.team3.controller.GraphCreator;
import seng202.team3.model.CrimeData;
import seng202.team3.model.DataManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class which used the data provided by the GraphCreator class and creates and populates graphs using the
 * GraphViewWindow.
 * @author Danish Jahangir
 */
public class GraphViewController implements Initializable {

    @FXML private ChoiceBox selectCrime;
    @FXML private ChoiceBox yearSelector;

    GraphCreator graphCreator = new GraphCreator();

    @FXML LineChart lineChart;

    @Override
    /**
     * Initializes the comboBoxes
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initSelectCrime();
    }

    @FXML
    /**
     * Uses the methods to construct the Graphs.
     */
    public void viewGraph() {
        lineChart.getData().removeAll(lineChart.getData());
        if (selectCrime.getValue().equals("ALL")) {
            graphOverTime(DataManager.getData());
        }
        else {
            graphOverTimePerType(DataManager.getData(), selectCrime.getValue().toString());
        }
    }

    /**
     * Constructs the selectCrime comboBox and sets its default value to "ALL".
     */
    public void initSelectCrime() {
        ReportCrimeController.constructCrimeChoiceBox(selectCrime);
        selectCrime.getSelectionModel().selectFirst();
    }

    /**
     * This method uses the data provided by the createGraphOverTime method from the GraphCreator class and creates and
     * populates the lineChart.
     * @param crimeData is the ArrayList of crimes used to populate the graph.
     */
    public void graphOverTime(ArrayList<CrimeData> crimeData) {
        XYChart.Series series = graphCreator.createGraphOverTime(crimeData);
        lineChart.setTitle("Crimes Over Time");
        lineChart.getXAxis().setLabel("Time (Per Week)");
        lineChart.getYAxis().setLabel("Number of times");
        series.setName("ALL CRIME DATA");
        lineChart.getData().add(series);
    }

    /**
     * This method uses the data provided by the createGraphOverTimePerType method from the GraphCreator class and
     * creates and populates the lineChart.
     * @param crimeData is the ArrayList of crimes used to populate the graph.
     */
    public void graphOverTimePerType(ArrayList<CrimeData> crimeData, String crimeType) {
        XYChart.Series series = graphCreator.createGraphOverTimePerType(crimeData, crimeType);
        lineChart.setTitle("Crimes Over Time Per Type");
        lineChart.getXAxis().setLabel("Time (Per Week)");
        lineChart.getYAxis().setLabel("Number of times");
        series.setName(crimeType);
        lineChart.getData().add(series);
    }


}