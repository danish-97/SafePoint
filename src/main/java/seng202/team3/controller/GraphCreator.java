package seng202.team3.controller;

import javafx.scene.chart.XYChart;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;

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
     * P.S. Morgan helped a lot in this method.
     * @param crimeData is the data which the method takes the dates field from.
     * @return a Map of the dates grouped by weeks with years as the key.
     */
    public Map<Integer, Map<Integer,List<CrimeData>>> formattedDatesIntoGroups(ArrayList<CrimeData> crimeData) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);
        Map<Integer, List<CrimeData>> weeklyCrimes;
        Map<Integer, Map<Integer, List<CrimeData>>> groupedByYears = new HashMap<>();
        for (CrimeData crime : crimeData) {
            if (crime instanceof PoliceData) {
                ArrayList<CrimeData> crimesTemp;
                Integer weekOfYear = null;
                Integer year = null;
                try {
                    year = simpleDateFormat.parse(crime.getDate()).getYear() + 1900;
                    weekOfYear = simpleDateFormat.parse(crime.getDate())
                            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate().get(weekFields.weekOfWeekBasedYear());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (groupedByYears.get(year) == null) {
                    groupedByYears.put(year, new HashMap<>());
                }
                weeklyCrimes = groupedByYears.get(year);
                if (weeklyCrimes.get(weekOfYear) == null) {
                    crimesTemp = new ArrayList<>();
                } else {
                    crimesTemp = (ArrayList<CrimeData>) weeklyCrimes.get(weekOfYear);
                }
                crimesTemp.add(crime);
                weeklyCrimes.put(weekOfYear, crimesTemp);
            }

        }
        return groupedByYears;
    }

    /**
     * This method takes the years from the key of the Map returned from the formattedDatesIntoGroups method and adds
     * it to an ArrayList.
     * @param crimeData is the data which is used to get the dates.
     * @return an ArrayList of years.
     */
    public List<Integer> getYear(ArrayList<CrimeData> crimeData) {
        ArrayList<Integer> years = new ArrayList<>();
        Map<Integer, Map<Integer,List<CrimeData>>> formattedDates = formattedDatesIntoGroups(crimeData);
        for (Map.Entry<Integer, Map<Integer,List<CrimeData>>> pair: formattedDates.entrySet()) {
            years.add(pair.getKey());
        }
        return years;

    }

        /**
         * This method creates the data for the graphOverTime. It takes the grouped dates and then adds it to the series
         * using entrySet() to get the key and value pairs.
         * @param crimeData is the data which is used to get the dates.
         * @param year is the year of the weeks in which the data is grouped.
         * @return the data which is used to populate the graph.
         */
    public XYChart.Series createGraphOverTime(ArrayList<CrimeData> crimeData, Integer year) {
        XYChart.Series series = new XYChart.Series();
            Map<Integer, Map<Integer,List<CrimeData>>> formattedDates = formattedDatesIntoGroups(crimeData);

        for (Map.Entry<Integer, Map<Integer,List<CrimeData>>> pair: formattedDates.entrySet()) {
            if (pair.getKey().equals(year)) {
                for (Map.Entry<Integer, List<CrimeData>> data : formattedDates.get(pair.getKey()).entrySet()) {
                    series.getData().add(new XYChart.Data(data.getKey(), data.getValue().size()));
                }
            }
        }

        return series;
    }

    /**
     * This method takes creates the data for the graphOverTimePerType. It first takes the crime types matching the crimeType
     * String and adds it to an ArrayList and then takes the grouped dates and then adds it to the series using entrySet()
     * to get the key and value pairs.
     * @param crimeData is the data which is used to get the dates.
     * @param crimeType is the String by which the data is filtered.
     * @param year is the year of the weeks in which the data is grouped.
     * @return the data used to populate the graph.
     */
    public XYChart.Series createGraphOverTimePerType(ArrayList<CrimeData> crimeData, String crimeType, Integer year) {
        XYChart.Series series = new XYChart.Series();
        ArrayList<CrimeData> crimesPerType = new ArrayList<>();

        for (CrimeData crime: crimeData) {
            if (crime.getCrimeType().equals(crimeType)) {
                crimesPerType.add(crime);
            }
        }

        Map<Integer, Map<Integer,List<CrimeData>>> formattedDates = formattedDatesIntoGroups(crimesPerType);

        for (Map.Entry<Integer, Map<Integer,List<CrimeData>>> pair: formattedDates.entrySet()) {
            if (pair.getKey().equals(year)) {
                for (Map.Entry<Integer, List<CrimeData>> data : formattedDates.get(pair.getKey()).entrySet()) {
                    series.getData().add(new XYChart.Data(data.getKey(), data.getValue().size()));
                }
            }
        }

        return series;
    }
}
