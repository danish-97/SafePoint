package seng202.team3.controller;

import seng202.team3.model.CrimeData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphCreator {

    public Map<String, Integer> formattedDatesIntoGroups(ArrayList<CrimeData> crimeData) {

        List<LocalDate> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
        for (int i = 0; i < crimeData.size(); i++) {
            dates.add((LocalDate) formatter.parse(crimeData.get(i).getDate()));
        }
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);
        Map<String, Integer> datesGroupedToWeeks = dates.stream()
                .collect(Collectors.groupingBy(dates -> dates.get(weekFields.weekOfWeekBasedYear())));

        return datesGroupedToWeeks;
    }

    public static void main(String[] args) {
        GraphCreator creator = new GraphCreator();
        ReadCSV readCSV = new ReadCSV();
        ArrayList<CrimeData> crimeData = readCSV.readDataLineByLine("data.csv");
        creator.formattedDatesIntoGroups(crimeData);
    }
}
