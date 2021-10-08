package seng202.team3.controller;

import javafx.scene.chart.XYChart;
import seng202.team3.model.CrimeData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * Class which groups the dates into weeks and creates the data which populates the Graphs.
 * @author Danish Jahangir
 */
public class GraphCreator {

    /**
     * This method takes the dates field from the CrimeData and parses it into simpleDateFormat using hash maps.
     * It then takes the parsed data and uses the weekFields to group it by weeks of the year.
     * @param crimeData is the data which the method takes the dates field from.
     * @return a Map<Integer, List<CrimeData>> of the dates grouped by weeks.
     * @throws ParseException if the formatting is invalid.
     */
    public Map<Integer, List<CrimeData>> formattedDatesIntoGroups(ArrayList<CrimeData> crimeData) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);

        Map<Integer, List<CrimeData>> weeklyCrimes = new HashMap<>();
        for (CrimeData crime: crimeData) {
            ArrayList<CrimeData> crimesTemp;
            Integer weekOfYear = simpleDateFormat.parse(crime.getDate())
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate().get(weekFields.weekOfWeekBasedYear());
            if (weeklyCrimes.get(weekOfYear) == null) {
                crimesTemp = new ArrayList<>();
            } else {
                crimesTemp = (ArrayList<CrimeData>) weeklyCrimes.get(weekOfYear);
            }
            crimesTemp.add(crime);
            weeklyCrimes.put(weekOfYear, crimesTemp);
        }

        return weeklyCrimes;
    }

    /**
     * This method creates the data for the graph over time. It takes the grouped dates and then adds it to the series
     * using entrySet() to get the key and value pairs.
     * @param crimeData is the data which is used to get the dates.
     * @return the data which is used to populate the graph.
     * @throws ParseException if the formatting is invalid.
     */
    public XYChart.Series createGraphOverTime(ArrayList<CrimeData> crimeData) throws ParseException {
        XYChart.Series series = new XYChart.Series();
        Map<Integer, List<CrimeData>> formattedDates = formattedDatesIntoGroups(crimeData);

        for (Map.Entry<Integer, List<CrimeData>> data: formattedDates.entrySet()) {
            series.getData().add(new XYChart.Data(data.getKey(), data.getValue().size()));
        }

        return series;
    }

    public static void main(String[] args) throws ParseException {
        GraphCreator creator = new GraphCreator();
        ReadCSV readCSV = new ReadCSV();
        ArrayList<CrimeData> crimeData = readCSV.readDataLineByLine("data.csv");
        creator.formattedDatesIntoGroups(crimeData);
    }
}
